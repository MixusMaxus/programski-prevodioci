package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	private Logger log = Logger.getLogger(getClass());
	private Struct boolType;
	private boolean errorDetected = false;
	private boolean passed = true;
	private String namespaceName = "";
	int nVars;
	private Type currentType = null;
	private Struct currentMethod = null;
	private int formParams = 0;
	private int kontrolneFor = 0;
	private Collection<Obj> requiredParams;
    private ArrayList<Struct> passedParams;
	
	public SemanticAnalyzer() {
		// Inserting boolean type into table of symbols
		boolType = new Struct(Struct.Bool);
		Tab.currentScope().addToLocals(new Obj(Obj.Type, "bool", boolType));
	}
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public boolean passed() {
		return this.passed;
	}
	
	private String imeSimbola(String namespace, String symbol) {
		StringBuilder sb = new StringBuilder();
		if (namespace != "") {
			sb.append(namespace);
			sb.append("::");
		}
		sb.append(symbol);
		return sb.toString();
	}
	
	
	private boolean dobriParametri() {
		if (passedParams != null && !requiredParams.isEmpty()) {
			if (Math.abs(passedParams.size() -  requiredParams.size()) > 1)
				return false;
			int i = 0;
			Iterator<Obj> iterator = requiredParams.iterator();
			while(iterator.hasNext() && i < passedParams.size()) {
				if (!iterator.next().getType().assignableTo(passedParams.get(i)))
					return false;
				i++;
			}
			return true;
		}
		else {
			if (passedParams == null && requiredParams.isEmpty())
				return true;
			else
				return false;
		}
	}
	
	/*==================================================================================================================*/
	
	//	Type ::= (Type) IDENTIFICATOR:typeName
	public void visit(Type type) {
		
		currentType = type;
		String ime = type.getTypeName();
		Obj objType = Tab.find(ime);
		
	  	if (objType == Tab.noObj) {
	  		// Tip ne postoji, baci gresku
			report_error("Greska na liniji "+ type.getLine()+" : tip "+ ime +" ne postoji", type);
			type.struct = Tab.noType;
			currentType.struct = Tab.noType;
			return;
	  	}
	  	
	  	if(objType.getKind() != Obj.Type) {
			  // Given type name does not present any type 
			  report_error("Greska na liniji "+ type.getLine()+" : promenljiva sa nazivom "+ ime +" ne predstavlja tip", type);
			  type.struct = Tab.noType;
			  currentType.struct = Tab.noType;
			  return;
	  	}
		type.struct = objType.getType();
	}
	
	
	public void visit(ProgramName programName) {
		programName.obj = Tab.insert(Obj.Prog, programName.getProgramName(), Tab.noType);
	    Tab.openScope();
	}
	
	public void visit(Program program) {
		Obj mainMethod = Tab.find("main");
		nVars = Tab.currentScope.getnVars();
		if((mainMethod == Tab.noObj) || mainMethod.getKind() != 3) {
			  report_error("Greska: Metoda main nije deklarisana", null);
		}
	  	// Argumenti
	  	else if (mainMethod.getLevel()!=0) {
				  report_error("Greska: Metoda main ne sme imati formalne argumente "+ mainMethod.getLevel(), null);
	  	}

		//Void
	  	else if(mainMethod.getType() != Tab.noType) {
				  report_error("Greska: Metoda main mora biti VOID", null);
	 
	  	}
	  	Tab.chainLocalSymbols(program.getProgramName().obj);
	  	Tab.closeScope();
	}
	
	//ConstantDeclaration ::= (ConstantDeclaration) IDENTIFICATOR:constName EQUAL Constant ;
	public void visit(ConstantDeclaration constantDeclaration) {
		String ime = imeSimbola(this.namespaceName, constantDeclaration.getConstName());
		Obj objekat = Tab.find(ime);
		
		if (objekat != Tab.noObj) {
			report_error("Greska na liniji "+ constantDeclaration.getLine()+" : promenljiva sa nazivom "+ constantDeclaration.getConstName()+"je vec deklarisana", null);
			constantDeclaration.obj = Tab.noObj;
			return;
		}
		
		if (currentType.struct != constantDeclaration.getConstant().struct) {
			// Constant value has wrong type
			report_error("Greska na liniji "+ constantDeclaration.getLine()+" : promenljiva sa nazivom "+ constantDeclaration.getConstName()+" nije odgovarajuceg tipa sa dodeljenom vrednoscu", null);
			constantDeclaration.obj=Tab.noObj;
			return;
		}
		
		constantDeclaration.obj = Tab.insert(Obj.Con, ime, constantDeclaration.getConstant().struct);
		Constant konstanta = constantDeclaration.getConstant();
		
		if (konstanta instanceof NumberConstant) {
			NumberConstant numerickaKonstanta = (NumberConstant)konstanta;
			constantDeclaration.obj.setAdr(numerickaKonstanta.getNumValue());
			report_info("Deklarisana konstanta "+ constantDeclaration.getConstName() + " jednaka "+ numerickaKonstanta.getNumValue(), constantDeclaration);
			return;
		}
		
		if (konstanta instanceof CharacterConstant) {
			CharacterConstant charKonstanta = (CharacterConstant)konstanta;
			constantDeclaration.obj.setAdr(charKonstanta.getCharValue());
			report_info("Deklarisana konstanta "+ constantDeclaration.getConstName() + " jednaka "+ charKonstanta.getCharValue(), constantDeclaration);
			return;
		}
		
		if (konstanta instanceof BooleanConstant) {
			BooleanConstant boolKonstanta = (BooleanConstant)konstanta;
			constantDeclaration.obj.setAdr(boolKonstanta.getBoolValue() ? 1 : 0);
			report_info("Deklarisana konstanta "+ constantDeclaration.getConstName() + " jednaka "+ boolKonstanta.getBoolValue(), constantDeclaration);
			return;
		}
	}
	
	//(NumberConstant) NUMCONSTANT:numValue 
	public void visit(NumberConstant numberConstant) {
		numberConstant.struct = Tab.intType;
	}
	
	//(CharacterConstant) CHARCONSTANT:charValue 
	public void visit(CharacterConstant characterConstant) {
		characterConstant.struct = Tab.charType;
	}
	
	//(BooleanConstant) BOOLCONSTANT:boolValue
	public void visit(BooleanConstant booleanConstant) {
		booleanConstant.struct = boolType;
	}
	
	// Variable ::= (VariableDecl) IDENTIFICATOR:varName ArrayBrackets
	public void visit(VariableDecl variableDecl) {
		String ime = variableDecl.getVarName();
		if (currentMethod == null)
			ime = imeSimbola(namespaceName, ime);
		
		if (Tab.currentScope.findSymbol(ime) != null) {
			// Variable is already declared error
			report_error("Greska na liniji " + variableDecl.getLine() + " : promenljiva sa nazivom " + variableDecl.getVarName() + "je vec deklarisana", null);
			variableDecl.obj = Tab.noObj;
			return;
		}
		ArrayBrackets ab = variableDecl.getArrayBrackets();
		
		if (ab instanceof HasArrayBrackets) {
			// Niz
			variableDecl.obj = Tab.insert(Obj.Var, ime, new Struct(Struct.Array, currentType.struct));
			report_info("Deklarisan niz "+ ime, variableDecl);
		}
		else {
			// Ne niz
			variableDecl.obj = Tab.insert(Obj.Var, ime, currentType.struct);
			report_info("Deklarisana promenljiva "+ ime, variableDecl);
		}
	}
	
//	===============================================================================================================
//	METODI
	
    // MethodDecl ::= (MethodDecl) MethodReturnType MethodName LEFTPAREN MethodParamList RIGHTPAREN MethodVariableList LEFTBRACE StatementList RIGHTBRACE ;
	public void visit(MethodDecl methodDecl) {
		
		methodDecl.obj = methodDecl.getMethodName().obj;
		methodDecl.obj.setLevel(formParams);
		Tab.chainLocalSymbols(methodDecl.obj);
		Tab.closeScope();
		formParams = 0;
		this.currentMethod = null;
	}
	
	//	MethodName ::= (MethodName) IDENTIFICATOR:methodName ;
	public void visit(MethodName methodName) {
		
		String ime = methodName.getMethodName();
		ime = imeSimbola(namespaceName, ime);
		
		Obj objekatMetode = Tab.currentScope().findSymbol(ime);
		
		if (objekatMetode != null) {
			report_error("Greska na liniji "+ methodName.getLine()+" : Ime "+ methodName.getMethodName()+" vec postoji unutar istog opsega", null);
			methodName.obj = Tab.noObj;
			return;
		}
		
		methodName.obj = Tab.insert(Obj.Meth, ime, currentMethod);
		Tab.openScope();
		report_info("Deklarisana funkcija "+ ime, methodName);
	}
	
	public void visit(MethodReturnTypeType methodReturnTypeType) {
		this.currentMethod = methodReturnTypeType.getType().struct;
		methodReturnTypeType.struct = methodReturnTypeType.getType().struct;
	}
	
	public void visit(MethodReturnTypeVoid methodReturnTypeVoid) {
		this.currentMethod = Tab.noType;
		methodReturnTypeVoid.struct = Tab.noType;
	}
	
	//	OneParamMethod ::= (OneParametherMethod) Type IDENTIFICATOR:argName ArrayBrackets
	public void visit(OneParametherMethod oneParametherMethod) {
		this.formParams++;
		Struct tip = oneParametherMethod.getType().struct;
		
		if (oneParametherMethod.getArrayBrackets() instanceof HasArrayBrackets) {
			tip = new Struct(Struct.Array, tip);
			report_info("Niz", oneParametherMethod);
		}
			
		oneParametherMethod.obj = Tab.insert(Obj.Var, oneParametherMethod.getArgName(), tip);
		report_info("Definisali smo argument metode koji se zove: " + oneParametherMethod.getArgName(), oneParametherMethod);
	}
	
//	===========================================================================================
//	Statements
	
	//	(StatementBreak) BREAK SEMICOLON
	public void visit(StatementBreak statementBreak) {
		if (kontrolneFor == 0) {
			  report_error("Semanticka greska na liniji " + statementBreak.getLine() + ": Break moze biti samo u for petlji ", statementBreak);
		  }
	}
	
	//	(StatementContinue) CONTINUE SEMICOLON
	public void visit(StatementContinue statementContinue) {
		if (kontrolneFor == 0) {
			  report_error("Semanticka greska na liniji " + statementContinue.getLine() + ": Continue moze biti samo u for petlji ", statementContinue);
		  }
	}
	
	//	(ReturnStatement) RETURN StatementReturn SEMICOLON
	public void visit(ReturnStatement returnStatement) {
		StatementReturn ret = returnStatement.getStatementReturn();
		
		if (ret instanceof StmtReturnVoid && (currentMethod != Tab.noType)) {
			report_error("Semanticka greska na liniji " + returnStatement.getLine() + " Metoda nije void, mora postojati povratna vrednost", returnStatement);
			return;
		}
		if (ret instanceof StmtReturnExpr) {
			Struct returnValueType = ((StmtReturnExpr) ret).getExpr().struct;
			if (!returnValueType.compatibleWith(currentMethod)){
				report_error("Semanticka greska na liniji " + returnStatement.getLine() + " Metoda ne vraca odgovarajuci tip podatka", returnStatement);
			}
		}
	}
	
	//	(StatementRead) READ LEFTPAREN Designator RIGHTPAREN SEMICOLON
	public void visit(StatementRead statementRead) {
		
		Obj designatorObj = statementRead.getDesignator().obj;
		
		int kind = designatorObj.getKind();
		Struct struct = designatorObj.getType();
		
		if (!(kind == Obj.Var || kind == Obj.Elem)){
            report_error("Read statement se ne koristi za promenljivu ili element niza", statementRead);
            return;
        }

        if (!(struct == boolType || struct == Tab.intType || struct == Tab.charType))
        	report_error("Read statement se ne koristi za dobar tip", statementRead);
	}
	
	//	(PrintStatement) PRINT LEFTPAREN Expr StatementPrint RIGHTPAREN SEMICOLON
	public void visit(PrintStatement printStatement) {
		Struct struct = printStatement.getExpr().struct;
		if (!(struct == boolType || struct == Tab.intType || struct == Tab.charType || struct.getKind() == Struct.Array))
        	report_error("Print statement se ne koristi za dobar tip", printStatement);
	}
	
	//	(StatementFor) For LEFTPAREN InsideFor RIGHTPAREN Statement
	public void visit(StatementFor statementFor) {
		kontrolneFor--;
	}
	
	//	For ::= (For) FOR ;
	public void visit(For For) {
		kontrolneFor++;
	}
	
//	============================================================================
//	Faktori
	
	//	(FactorNumCnst) NUMCONSTANT:numValue
	public void visit(FactorNumCnst factorNumCnst) {
		factorNumCnst.struct = Tab.intType;
	}

	//	(FactorCharCnst)CHARCONSTANT:charValue
	public void visit(FactorCharCnst factorCharCnst) {
		factorCharCnst.struct = Tab.charType;
	}
	
	//	(FactorBoolCnst)BOOLCONSTANT:boolValue
	public void visit(FactorBoolCnst factorBoolCnst) {
		factorBoolCnst.struct = boolType;
	}
	
	//	Factor ::= (FactorDesignator) Designator FactorDesignatorActParsList
	public void visit(FactorDesignator factorDesignator) {
		
		Obj designatorObj = factorDesignator.getDesignator().obj;
		factorDesignator.struct = designatorObj.getType();
		FactorDesignatorActParsList nesto = factorDesignator.getFactorDesignatorActParsList();
		
		if (designatorObj.getKind() != Obj.Meth && nesto instanceof  ParenPars){
			report_error("Semanticka greska na liniji " + factorDesignator.getLine() + ": Ovo nije metoda, a prosledili smo argumente", factorDesignator);
            return;
        }
		
		if (designatorObj.getKind() != Obj.Meth)
			return;
		
		if (nesto instanceof NoParenPars) {
			report_error("Semanticka greska na liniji " + factorDesignator.getLine() + ": Ovo je metoda, a nismo postavili argumente za nju", factorDesignator);
			return;
		}
		
		requiredParams = factorDesignator.getDesignator().obj.getLocalSymbols();
		
		if (!dobriParametri()) {
			report_error("Semanticka greska na liniji " + factorDesignator.getLine() + ": Prosledili smo lose parametre", factorDesignator);
		}	

        requiredParams = null;
        passedParams = null;
	}

	//	(FactorNewArray) NEW Type LEFTBRACKET Expr RIGHTBRACKET
	public void visit(FactorNewArray factorNewArray) {
		
		factorNewArray.struct = new Struct(Struct.Array, factorNewArray.getType().struct);
		if (factorNewArray.getExpr().struct != Tab.intType) {
			report_error("Semanticka greska na liniji " + factorNewArray.getLine() + ": Expr za kreiranje niza mora biti int", factorNewArray);
		}
	}
	
	//	(FactorExpr) LEFTPAREN Expr RIGHTPAREN
	public void visit(FactorExpr factorExpr) {
		factorExpr.struct = factorExpr.getExpr().struct;
	}
	
	// (FactorRange) RANGE LEFTPAREN Expr RIGHTPAREN
	public void visit(FactorRange factorRange) {
		if (factorRange.getExpr().struct != Tab.intType) {
			report_error("Semanticka greska na liniji " + factorRange.getLine() + ": Expr za Range funkciju mora biti tipa int", null);
		}
	}
	
//	====================================================================
//	Designator Statement
	
	
	//	DesignatorStatement ::= (DesignatorAssignop) Designator Assignop Expr
	public void visit(DesignatorAssignop designatorAssignop) {
		Obj designatorObj = designatorAssignop.getDesignator().obj;
		
		int kind = designatorObj.getKind();
		
		if (!(kind == Obj.Var || kind == Obj.Fld || kind == Obj.Elem)) {
			report_error("Semanticka greska na liniji " + designatorAssignop.getLine() + ": Designator mora biti polje ili element niza ili promenljiva", designatorAssignop);
			return;
		}
		
		Struct strc = designatorAssignop.getExpr().struct;
		if (strc != null && !strc.assignableTo(designatorObj.getType())) {
			report_error("Semanticka greska na liniji " + designatorAssignop.getLine() + ": Designator mora biti kompatibilan sa Expressionom", designatorAssignop);
			return;
		}
	}
	
	//	(DesignatorFunctionCall) Designator LEFTPAREN ActParsList RIGHTPAREN
	public void visit(DesignatorFunctionCall designatorFunctionCall) {
		Obj designatorObj = designatorFunctionCall.getDesignator().obj;
		if (designatorObj.getKind() != Obj.Meth) {
			report_error("Semanticka greska na liniji " + designatorFunctionCall.getLine() + ": Designator nije metodaBBBBB", designatorFunctionCall);
			return;
		}
		requiredParams = designatorObj.getLocalSymbols();
		if (!dobriParametri()) 
			report_error("Semanticka greska na liniji " + designatorFunctionCall.getLine() + ": Metoda nije pozvana sa dobrim argumentima", designatorFunctionCall);
		else
			report_info("Na liniji " + designatorFunctionCall.getLine() + ": je pozvana Metoda sa dobrim argumentima", designatorFunctionCall);
		requiredParams = null;
		passedParams = null;
	}
	
	//	(DesignatorIncrement) Designator INCREMENT
	public void visit(DesignatorIncrement designatorIncrement) {
		Obj designatorObj = designatorIncrement.getDesignator().obj;
		int kind = designatorObj.getKind();
		
		if (!(kind == Obj.Var || kind == Obj.Fld || kind == Obj.Elem)) {
			report_error("Semanticka greska na liniji " + designatorIncrement.getLine() + ": Designator mora biti polje ili element niza ili promenljiva", designatorIncrement);
			return;
		}
		
		if (designatorObj.getType() != Tab.intType) {
			report_error("Semanticka greska na liniji " + designatorIncrement.getLine() + ": Designator mora biti tip int", designatorIncrement);
			return;
		}
	}
	
	//	(DesignatorDecrement) Designator DECREMENT
	public void visit(DesignatorDecrement designatorDecrement) {
		Obj designatorObj = designatorDecrement.getDesignator().obj;
		int kind = designatorObj.getKind();
		
		if (!(kind == Obj.Var || kind == Obj.Fld || kind == Obj.Elem)) {
			report_error("Semanticka greska na liniji " + designatorDecrement.getLine() + ": Designator mora biti polje ili element niza ili promenljiva", designatorDecrement);
			return;
		}
		
		if (designatorObj.getType() != Tab.intType) {
			report_error("Semanticka greska na liniji " + designatorDecrement.getLine() + ": Designator mora biti tip int", designatorDecrement);
			return;
		}
	}
	
	//	(MultipleAssignements) LEFTBRACKET DesignatorList MULTIPLY Designator RIGHTBRACKET EQUAL Designator
	public void visit(MultipleAssignements multipleAssignements) {
		if (multipleAssignements.getDesignator().obj.getType().getKind() != Struct.Array)
			report_error("Semanticka greska na liniji " + multipleAssignements.getLine() + ": Designator mora biti niz", multipleAssignements);
        if (multipleAssignements.getDesignator1().obj.getType().getKind() != Struct.Array)
        	report_error("Semanticka greska na liniji " + multipleAssignements.getLine() + ": Designator mora biti niz", multipleAssignements);
	}
	
	//	(DSListComprehension) Designator EQUAL LEFTBRACKET FOR Expr IN Designator IfMaybe RIGHTBRACKET
	public void visit(DSListComprehension dSListComprehension) {
		Struct des1 = dSListComprehension.getDesignator().obj.getType();
		Struct des2 = dSListComprehension.getDesignator1().obj.getType();
		Expr expr = dSListComprehension.getExpr();

		if (!(des1.getKind() == Struct.Array && des2.getKind() == Struct.Array)){
			report_error("Semanticka greska na liniji: " + dSListComprehension.getLine() + " promenljive moraju biti nizovi", null);
			return;
		}
		
		if (des1.getElemType() != des2.getElemType()) {
			report_error("Semanticka greska na liniji: " + dSListComprehension.getLine() + " promenljive moraju biti istog tipa", null);
			return;
		}
		
		if (expr.struct.getKind() != des2.getElemType().getKind()) {
			report_error("Semanticka greska na liniji: " + dSListComprehension.getLine() + " Iterator mora biti istog tipa kao i niz kroz koji iterira", null);
		}
	}
	
//	==================================================================
//	USLOVI
	

	//	CondFact ::= (CondFact) Expr CondFactExprRelopExpr ;
	public void visit(CondFact condFact) {
		Expr exp = condFact.getExpr();
		CondFactExprRelopExpr ostalo = condFact.getCondFactExprRelopExpr();
		
		condFact.struct = boolType;
		
		if (ostalo instanceof CondFactSimple)
			return;
		
		if (!exp.struct.compatibleWith(ostalo.struct)) {
			report_error("Semanticka greska na liniji " + condFact.getLine() + ": Tipovi moraju biti kompatibilni. Tip1 je: " + exp.struct.toString() + " a tip 2 je " + ostalo.struct.toString() ,  condFact);
			return;
		}
		
		Relop relop = ((CondFactComplex)ostalo).getRelop();
		boolean isGoodRelop = (relop instanceof RelopEqual || relop instanceof RelopNotEqual);
		boolean isArray = exp.struct.getKind() == Struct.Array;
		
		if (!isGoodRelop && isArray) {
			report_error("Semanticka greska na liniji " + condFact.getLine() + ": Uz nizove mogu ici samo operatori EQUAL (==) I NOTEQUAL (!=)", condFact);
		}
	}

	//	CondFactExprRelopExpr ::= (CondFactComplex) Relop Expr
	public void visit(CondFactComplex condFactComplex) {
		condFactComplex.struct = condFactComplex.getExpr().struct;
	}
	
	//	(CondTermFact) CondFact
	public void visit(CondTermFact condTermFact) {
		condTermFact.struct = condTermFact.getCondFact().struct;
	}

	//	CondTerm ::= (CondTermAnd) CondTerm AND CondFact
	public void visit(CondTermAnd condTermAnd) {
		CondTerm cndTrm = condTermAnd.getCondTerm();
		CondFact cndFact = condTermAnd.getCondFact();
		if (!(cndTrm.struct == boolType && cndFact.struct == boolType)) {
			report_error("Semanticka greska na liniji " + condTermAnd.getLine() + ": Oba uslova moraju biti tipa bool u AND operatoru", condTermAnd);
			return;
		}
		condTermAnd.struct = boolType;
	}

	//	(ConditionCondTerm) CondTerm
	public void visit(ConditionCondTerm conditionCondTerm) {
		conditionCondTerm.struct = conditionCondTerm.getCondTerm().struct;
	}
	
	//	Condition ::=   (ConditionOr) Condition OR CondTerm
	public void visit(ConditionOr conditionOr) {
		Condition cond = conditionOr.getCondition();
		CondTerm condTrm = conditionOr.getCondTerm();
		if (!(cond.struct == boolType && condTrm.struct == boolType)) {
			report_error("Semanticka greska na liniji " + conditionOr.getLine() + ": Oba uslova moraju biti tipa bool u OR operatoru", conditionOr);
			return;
		}
		
		conditionOr.struct = boolType;		
	}
	
	//	==================================================================================
	//EXPR
	

	//	Expr ::= (ExprTerm) AddopTermList
	public void visit(ExprTerm exprTerm) {
		exprTerm.struct = exprTerm.getAddopTermList().struct;
	}
	
	//	Expr ::= (ExprMinus) MINUS AddopTermList
	public void visit(ExprMinus exprMinus) {
		AddopTermList list = exprMinus.getAddopTermList();
		if (list.struct != Tab.intType) {
			report_error("Semanticka greska na liniji " + exprMinus.getLine() + ": Minus operator moze stajati samo uz tip INT", exprMinus);
			return;
		}
		exprMinus.struct = list.struct;
	}

	//	AddopTermList ::= (MultipleAddopTerms) AddopTermList Addop Term
	public void visit(MultipleAddopTerms multipleAddopTerms) {
		AddopTermList list = multipleAddopTerms.getAddopTermList();
		Term term = multipleAddopTerms.getTerm();
		
		if (!(term.struct == Tab.intType && list.struct == Tab.intType)) {
			report_error("Semanticka greska na liniji " + multipleAddopTerms.getLine() + ": Sabiranje i oduzimanje operator moze stajati samo uz tip INT", multipleAddopTerms);
		}
		multipleAddopTerms.struct = Tab.intType;
	}

	//	AddopTermList ::= (OneTermAddopTerm) Term
	public void visit(OneTermAddopTerm oneTermAddopTerm) {
		oneTermAddopTerm.struct = oneTermAddopTerm.getTerm().struct;
	}
	

	//	Term ::= (Term) FactorMulopList ;
	public void visit(Term term) {
		term.struct = term.getFactorMulopList().struct;
	}
	
	//	FactorMulopList ::= (MultipleFactorMulopLists) FactorMulopList Mulop Factor
	public void visit(MultipleFactorMulopLists multipleFactorMulopLists) {
		FactorMulopList list = multipleFactorMulopLists.getFactorMulopList();
		Factor fct = multipleFactorMulopLists.getFactor();
		
		if (!(fct.struct == Tab.intType && list.struct == Tab.intType)) {
			report_error("Semanticka greska na liniji " + multipleFactorMulopLists.getLine() + ": Mnozenje i deljenje operator moze stajati samo uz tip INT", multipleFactorMulopLists);
		}
		multipleFactorMulopLists.struct = Tab.intType;
	}
	
	//	FactorMulopList ::=(OneFactorMulopList) Factor
	public void visit(OneFactorMulopList oneFactorMulopList) {
		oneFactorMulopList.struct = oneFactorMulopList.getFactor().struct;
	}

//	Designator ::= (Designator) DesignatorName IdentExpressList ;
	public void visit(Designator designator) {
		String ime = designator.getDesignatorName().getDesignatorName();
		Obj dsgObj = Tab.find(ime);
		if (dsgObj == Tab.noObj) {
			designator.obj = Tab.noObj;
			report_error("Semanticka greska na liniji " + designator.getLine() + ": Promenljiva "+ ime +" nije deklarisana!", null);
			return;
		}
		if (designator.getIdentExpressList() instanceof NoIdentExpresses) {
			designator.obj = dsgObj;
		}
		else {
			if (dsgObj.getType().getKind() != Struct.Array) {
				designator.obj = Tab.noObj;
				report_error("Semanticka greska na liniji " + designator.getLine() + ": Promenljiva "+ ime +" nije nizovnog tipa", null);
				return;
			}
			MultiplesIdentExpresses nesto = (MultiplesIdentExpresses)designator.getIdentExpressList();
			if (nesto.getExpr().struct != Tab.intType) {
				designator.obj = Tab.noObj;
				report_error("Semanticka greska na liniji " + designator.getLine() + ": Expr nije tipa int", null);
				return;
			}
			designator.obj = new Obj(Obj.Elem, dsgObj.getName(), dsgObj.getType().getElemType());
		}
	}
	
//	DesignatorName ::= (DesignatorName) IDENTIFICATOR:designatorName ;
	public void visit(DesignatorName designatorName) {
		designatorName.obj = Tab.find(designatorName.getDesignatorName());
	}
	
//	IdentExpressList ::= (MultiplesIdentExpresses) LEFTBRACKET Expr RIGHTBRACKET
	public void visit(MultiplesIdentExpresses multiplesIdentExpresses) {
		
	}
	
	//	ActPars ::= (MultipleActPars) ActPars COMMA Expr
	public void visit(MultipleActPars multipleActPars) {
		if (passedParams == null)
            passedParams = new ArrayList<>();
        passedParams.add(multipleActPars.getExpr().struct);
	}
	
	//	ActPars ::= (ActParsExpr) Expr
	public void visit(ActParsExpr actParsExpr) {
		if (passedParams == null)
            passedParams = new ArrayList<>();
        passedParams.add(actParsExpr.getExpr().struct);
	}
}
