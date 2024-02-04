package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticPass extends VisitorAdaptor {
	
	Struct boolType;
	boolean errorDetected = false;
	Obj currentMethod = Tab.noObj;
	Obj currenTypeObj = Tab.noObj;
	Struct currentTypeStruct = Tab.noType;
	Struct currentMethodType = null;
	int printCallCount = 0;
	int varDeclCount = 0;
	int nVars;
	int methodArgs = 0;
	Type currentType;
	String currentNamespace = "";
	
	ArrayList<Obj> potrebniParametri;
	ArrayList<Obj> prosledjeniParametri;
	
	int petlja = 0;
	
	public SemanticPass() {
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
	
	Logger log = Logger.getLogger(getClass());

    /*public void visit(Variables Variables) { 
    	varDeclCount++;
    }*/
	
    /*public void visit(StmtPrintBasic print) {
		printCallCount++;
		log.info("Nadjen print");
	}*/

    int formalParametersCount = 0;
    
    private String createName(String name, String namespace) {
    	if (namespace == "")
    		return name;
    	return namespace + "%" + name;
    }
    
    private boolean proveriSveParametre() {
    	if (prosledjeniParametri != null && potrebniParametri != null) {
    		if (prosledjeniParametri.size() != potrebniParametri.size())
	    		return false;
	    	Iterator<Obj> prosl = prosledjeniParametri.iterator();
	    	Iterator<Obj> potr = potrebniParametri.iterator();
	    	
	    	while (prosl.hasNext() && potr.hasNext()) {
	    		if (!prosl.next().getType().assignableTo(potr.next().getType()))
	    			return false;
	    	}
    	}
    	return true;
    }
    
    
    /*Program*/
    
    public void visit(ProgramName programName) {
    	programName.obj = Tab.insert(Obj.Prog, programName.getProgramName(), Tab.noType);
    	Tab.openScope();
    }
    
   public void visit(Program program) {
	   
	   nVars = Tab.currentScope.getnVars();
	   Obj mainMethod = Tab.find("main");
	   
	    if((mainMethod == Tab.noObj) || mainMethod.getKind() != Obj.Meth) {
			  report_error("Greska: Metoda main nije deklarisana u programu", null);
	   	}
	 	// Has arguments
	 	else if (mainMethod.getLevel() != 0) {
				  report_error("Greska: Metoda main ne sme imati formalne argumente "+ mainMethod.getLevel(), null);
	 	}
	 	// Is void
	 	else if((mainMethod.getType() != Tab.noType)) {
				  report_error("Greska: Metoda main mora biti VOID", null);
	
	 	}
	 	else 
	 		report_info("Sa metodom main je sve u redu", program);
	   
	    Tab.chainLocalSymbols(program.getProgramName().obj);
	    Tab.closeScope();
    }
   
   public void visit(Namespace namespace) {
	   currentNamespace = "";
   }
   
   public void visit(NamespaceName namespaceName) {
	   
	   String ime = namespaceName.getNamespaceName();
	   Obj namespaceObj = Tab.currentScope().findSymbol(ime);
	   
	   if (namespaceObj != null) {
		   report_error("Namepsace sa imenom: " + ime + " vec postoji", null);
	   }
	   else {
		   namespaceName.obj = Tab.insert(Obj.Type, ime, Tab.noType);
	   }
	   currentNamespace = ime;
   }
   
   //Type
   public void visit(Type type) {
	   
	   currentType = type;
	   currenTypeObj = Tab.noObj;
	   Obj typeObj = Tab.find(type.getTypeName());
	   
	   if (typeObj == Tab.noObj) {
		   //Tip ne postoji
		   report_error("Greska na liniji "+ type.getLine() +" : tip sa nazivom " + type.getTypeName() + " ne postoji", null);
		   type.struct = Tab.noType;
		   return;
	   }
	   if (typeObj.getKind() != Obj.Type) {
		   report_error("Greska na liniji "+ type.getLine() + " : promenljiva sa nazivom " + type.getTypeName() + " ne predstavlja tip", null);
		   type.struct = Tab.noType;
		   return;
	   }
	   currentTypeStruct = typeObj.getType();
	   type.struct = typeObj.getType();
   }
   
   /* Deklaracija konstanti*/
   
   public void visit(NumberConstant numberConstant) {
	   numberConstant.struct = Tab.intType;
   }
   
   public void visit(CharacterConstant characterConstant) {
	   characterConstant.struct = Tab.charType;
   }
   
   public void visit(BooleanConstant booleanConstant) {
	   booleanConstant.struct = boolType;
   }
   
   // IDENTIFICATOR:constName EQUAL Constant
   public void visit (ConstantDeclaration constantDeclaration) {
	   //Da li je vec zauzeto ime
	   String ime = createName(constantDeclaration.getConstName(), currentNamespace);
	   if (Tab.currentScope().findSymbol(ime) != null) {
		   report_error("Greska na liniji "+ constantDeclaration.getLine()+" : promenljiva: "+ constantDeclaration.getConstName()+"je vec deklarisana", null);
		   constantDeclaration.obj = Tab.noObj;
		   return;
	   }
	   //vidi da li su dobri po tipu const int a = Constant (true) ne moze, proveris trenutni tip i tip Constante
	   if (constantDeclaration.getConstant().struct != currentTypeStruct) {
		   report_error("Greska na liniji "+ constantDeclaration.getLine()+" : promenljiva: "+ constantDeclaration.getConstName()+"ne zadovoljava tip trenutne konstante", null);
		   constantDeclaration.obj = Tab.noObj;
		   return;
	   }
	   
	   //sve je dobro dodaj u tabelu simbola
	   constantDeclaration.obj = Tab.insert(Obj.Con, ime, constantDeclaration.getConstant().struct);
	   
	   //moras da postavis u polje adr vrednost konstante
	   
	   int kind = constantDeclaration.obj.getType().getKind();
	   
	   if (kind == Struct.Int) {
		   NumberConstant numConstant = (NumberConstant)constantDeclaration.getConstant();
		   constantDeclaration.obj.setAdr(numConstant.getNumValue());
		   report_info("Konstanta "+ constantDeclaration.getConstName() + " ima vrednost "+ numConstant.getNumValue(), constantDeclaration);
	   }
	   else if (kind == Struct.Char) {
		   CharacterConstant characterConstant = (CharacterConstant)constantDeclaration.getConstant();
		   constantDeclaration.obj.setAdr(characterConstant.getCharValue());
		   report_info("Konstanta "+ constantDeclaration.getConstName() + " ima vrednost "+ characterConstant.getCharValue(), constantDeclaration);
	   }
	   else if(kind == Struct.Bool) {
		   BooleanConstant booleanConstant = (BooleanConstant)constantDeclaration.getConstant();
		   boolean value = booleanConstant.getBoolValue();
		   if (value) {
			   constantDeclaration.obj.setAdr(1);
		   }
		   else {
			   constantDeclaration.obj.setAdr(0);
		   }
		   report_info("Konstanta "+ constantDeclaration.getConstName() + " ima vrednost "+ constantDeclaration.obj.getAdr(), constantDeclaration);
	   }	
   }
   /*deklaracija varijabli*/
   //IDENTIFICATOR:varName ArrayBrackets
   public void visit(VariableDecl variableDecl) {
	   /*varDeclCount++;*/
	   
	   //proveravamo da li je uzeto vec ime, ali pazi moras da gledas u trenutnom opsegu
	   String ime = createName(variableDecl.getVarName(), currentNamespace);
	   
	   Obj varObj = Tab.currentScope().findSymbol(ime);
	   
	   if (varObj != null) {
		   report_error("Greska na liniji " + variableDecl.getLine()+" : promenljiva "+ ime + " je vec deklarisana", null);
		   variableDecl.obj = Tab.noObj;
		   return;
	   }
	   
	   if((currentType == null) || (currentTypeStruct) == Tab.noType) {
		   variableDecl.obj = Tab.noObj;
		   return;
		 }
	   
	   //sada u zavisnosti od toga da li imamo zagrade pravimo ili niz ili promenljivu
	   if (variableDecl.getArrayBrackets() instanceof HasArrayBrackets) {
		   variableDecl.obj = Tab.insert(Obj.Var, ime, new Struct(Struct.Array, currentTypeStruct));
		   report_info("Deklarisan niz "+ ime, variableDecl);
	   }
	   else {
		   variableDecl.obj = Tab.insert(Obj.Var, ime, currentTypeStruct);
		   report_info("Deklarisana promenljiva "+ ime, variableDecl);
	   }
   }
   
   //MethodReturnType MethodName LEFTPAREN MethodParamList RIGHTPAREN VarDeclList LEFTBRACE StatementList RIGHTBRACE
   public void visit(MethodDecl methodDecl) {
	   methodDecl.obj = methodDecl.getMethodName().obj;
	   methodDecl.obj.setLevel(methodArgs);
	   
	   Tab.chainLocalSymbols(methodDecl.obj);
	   currentMethod.setLevel(methodArgs);
	   
       methodArgs = 0;
       
       currentMethod = Tab.noObj;
       currentTypeStruct = null;
       currentMethodType = null;
       Tab.closeScope();
   }
   
 //Type IDENTIFICATOR:argName ArrayBrackets
   public void visit(OneParametherMethod oneParametherMethod) {
	   if (currentMethod == null)
		   return;
	   methodArgs++;
	   String naziv = oneParametherMethod.getArgName();
	   if (oneParametherMethod.getArrayBrackets() instanceof HasArrayBrackets) {
		   oneParametherMethod.obj = Tab.insert(Obj.Var, naziv, new Struct(Struct.Array, oneParametherMethod.getType().struct));
		   report_info("Dodali smo jos jedan argument metode koji je niz: " + naziv, null);
	   }
	   else{
		   oneParametherMethod.obj = Tab.insert(Obj.Var, naziv, oneParametherMethod.getType().struct);
		   report_info("Dodali smo jos jedan argument metode koji NIJE niz: " + naziv, null);
	   }
   }
   
   public void visit(MethodReturnTypeType methodReturnTypeType) {
	   methodReturnTypeType.struct = methodReturnTypeType.getType().struct;
	   currentTypeStruct = methodReturnTypeType.getType().struct;
	   currentMethodType = methodReturnTypeType.getType().struct;
   }
   
   public void visit(MethodReturnTypeVoid methodReturnTypeVoid) {
	   methodReturnTypeVoid.struct = Tab.noType;
	   currentTypeStruct = Tab.noType;
	   currentMethodType = Tab.noType;
   }
   
   public void visit(MethodName methodName) {
	   String ime = createName(methodName.getMethodName(), currentNamespace);
	   Obj nameObj = Tab.currentScope().findSymbol(ime);
	   if (nameObj != null) {
		   report_error("Greska na liniji "+ methodName.getLine()+" : Ime "+ methodName.getMethodName()+" vec postoji unutar opsega", null);
		   methodName.obj = Tab.noObj;
	   }
	   else {
		   methodName.obj = Tab.insert(Obj.Meth, ime, currentTypeStruct);
		   /*currentMethod = Tab.insert(Obj.Meth, methodName.getMethodName(), currentTypeStruct);*/
	   }
	   Tab.openScope();
   }

   /*Faktori*/
   public void visit(FactorDesignator factorDesignator) {
	   
	  factorDesignator.struct = factorDesignator.getDesignator().obj.getType();
	   
	  FactorDesignatorActParsList nesto =  factorDesignator.getFactorDesignatorActParsList();
	  
	  if (factorDesignator.getDesignator().obj.getKind() != Obj.Meth && nesto instanceof  ParenPars){
		  report_error("Greska na liniji "+ factorDesignator.getLine()+" : Ime nije fja", null);
          return;
      }
	  
	  if (factorDesignator.getDesignator().obj.getKind() != Obj.Meth)
		  return;
	  
	// Metoda a parametri nisu postavljeni
      if (nesto instanceof NoParenPars){
          report_error("Greska na liniji "+ factorDesignator.getLine()+" : Parametri nisu postavljeni kod fje", null);
          return;
      }
      potrebniParametri = new ArrayList(factorDesignator.getDesignator().obj.getLocalSymbols());
      if (!proveriSveParametre()) {
    	  report_error("Greska na liniji "+ factorDesignator.getLine()+" : Parametri nisu dobrog tipa", null);
      }
      
      factorDesignator.struct = factorDesignator.getDesignator().obj.getType();

      potrebniParametri = null;
      prosledjeniParametri = null;
   }
   
   /*int*/
   public void visit(FactorNumCnst factorNumCnst) {
	   factorNumCnst.struct = Tab.intType;
   }
   
   public void visit(FactorCharCnst factorCharCnst) {
	   factorCharCnst.struct = Tab.charType;
   }
   
   public void visit(FactorBoolCnst factorBoolCnst) {
	   factorBoolCnst.struct = boolType;
   }
   
   //LEFTPAREN Expr RIGHTPAREN
   public void visit(FactorExpr factorExpr) {
	   factorExpr.struct = factorExpr.getExpr().struct;
   }
   
   //NEW Type LEFTBRACKET Expr RIGHTBRACKET
   public void visit(FactorNewArray factorNewArray) {
	   
	   factorNewArray.struct = new Struct(Struct.Array, factorNewArray.getType().struct);
	   if (factorNewArray.getExpr().struct != Tab.intType) {
		   report_error("Semanticka greska na liniji " + factorNewArray.getLine() + ": Mora biti za niz tip int", factorNewArray);
	   }
   }
   
   
   
   // Statementovi
   // Ovih par se samo zezas sa petljama i ugnjezdavanjima
   public void visit(StatementBreak statementBreak) {
	   if (petlja == 0)
		   report_error("Semanticka greska na liniji " + statementBreak.getLine() + ": break moze biti samo u for petlji ", null);
   }
   
   public void visit(StatementContinue statementContinue) {
	   if (petlja == 0)
		   report_error("Semanticka greska na liniji " + statementContinue.getLine() + ": CONTINUE moze biti samo u for petlji ", null);
   }
   
 //RETURN StatementReturn SEMICOLON, pazis na metodu iz koje iskaces, ako je void metoda ne smes nista da vratis ako nije moras tip da poklopis
   public void visit(ReturnStatement returnStatement) {
	   StatementReturn stmtRet = returnStatement.getStatementReturn();
	   if (stmtRet instanceof StmtReturnVoid && currentMethodType != Tab.noType) {
			   report_error("Semanticka greska na liniji " + returnStatement.getLine() + ": Ne smemo vracati nista iz VOID fje", null);
			   return;
	   }
	   if (stmtRet instanceof StmtReturnExpr && ((StmtReturnExpr) stmtRet).getExpr().struct != currentMethodType) {
		   report_error("Semanticka greska na liniji " + returnStatement.getLine() + ": Tip koji vracamo nije isti kao i tip povratnog tipa fje", null);
		   return;
	   }   
   }
   
   //READ LEFTPAREN Designator RIGHTPAREN SEMICOLON 
   //Designator mora biti ili promenljiva ili element niza, po tipu moze biti int, char ili bool
   public void visit(StatementRead statementRead) {
	   Designator desg = statementRead.getDesignator();
	   int kind = desg.obj.getKind();
	   if (!(kind == Obj.Var || kind == Obj.Elem)) {
		   report_error("Semanticka greska na liniji " + statementRead.getLine() + ": Za read mozemo koristiti samo promenljive ili elemente", null);
		   return;
	   }
	   Struct str = desg.obj.getType();
	   if (!(str == Tab.intType || str == Tab.charType || str == boolType)) {
		   report_error("Semanticka greska na liniji " + statementRead.getLine() + ": Za read mozemo koristiti samo tipove int char ili bool", null);
		   return;
	   }
   }
   
   //moze samo int, char i boolove da printujes, ostalo ne moze
   public void visit(PrintStatement printStatement) {
	  Struct printStruct =  printStatement.getExpr().struct;
	  int kind = printStruct.getKind();
	  if (!(kind == Struct.Bool || kind == Struct.Char || kind == Struct.Int)){
		  report_error("Semanticka greska na liniji " + printStatement.getLine() + ": Expr unutar PRINT Statment mora biti int, char ili bool ", null);
	  }
	  report_info("Printujemo na liniji:" + printStatement.getLine(), printStatement);
   }
   
   public void visit(StatementFor statementFor) {
	   petlja--;
   }
   
   //Dodao si FOR kao neterminal For kako bi mogao da brojis petlje i te gluposti
   public void visit(For forVisit) {
	   petlja++;
   }
   
   
   /*Conditions za uslove moramo samo tipove da isproveravamo*/
   
   //Condition OR CondTerm
   public void visit(ConditionOr conditionOr) {
	   Struct condStruct = conditionOr.getCondition().struct;
	   if (condStruct == null || condStruct != boolType || conditionOr.getCondTerm().struct != boolType) {
		    report_error("Semanticka greska na liniji " + conditionOr.getLine() + ": U OR izrazu oba operanda moraju biti bool", null);
	   		return;
	   }
	   conditionOr.struct = boolType;    
   }
   
   //CondTerm
   public void visit(ConditionCondTerm conditionCondTerm) {
	   conditionCondTerm.struct = conditionCondTerm.getCondTerm().struct;
   }
   
   public void visit(ConditionError conditionError) {
	   conditionError.struct = Tab.noType;
   }
   
   //CondTerm AND CondFact
   public void visit(CondTermAnd condTermAnd) {
	   Struct condStruct = condTermAnd.getCondTerm().struct;
	   if (condStruct == null || condStruct != boolType || condTermAnd.getCondFact().struct != boolType) {
		    report_error("Semanticka greska na liniji " + condTermAnd.getLine() + ": U  izrazu oba operanda moraju biti bool", null);
	   		return;
	   }
	   condTermAnd.struct = boolType;
   }
   
   //CondFact
   public void visit(CondTermFact condTermFact) {
	   condTermFact.struct = condTermFact.getCondFact().struct;
   }
   
   //Expr CondFactExprRelopExpr
   public void visit(CondFact condFact) {
	   
	   // tip levog 
       Struct leftOperandType = condFact.getExpr().struct;

       // tip desnog 
       Struct rightOperandType = condFact.getCondFactExprRelopExpr().struct;

       condFact.struct = boolType;

       // ne postoji desni operand
       if (rightOperandType == null)
           return;


       // tipovi nisu kompatabilni
       if (!leftOperandType.compatibleWith(rightOperandType)){
    	   report_error("Semanticka greska na liniji " + condFact.getLine() + ": Tipovi expressiona moraju biti kompatibilni", null);
    	   condFact.struct = Tab.noType;
           return;
       }


       Relop relop = ((CondFactComplex)condFact.getCondFactExprRelopExpr()).getRelop();

       boolean areRefType = leftOperandType.compatibleWith(Tab.nullType) && rightOperandType.compatibleWith(Tab.nullType);

       boolean relopIsEqualsOrNotEquals = (relop instanceof RelopEqual) || (relop instanceof  RelopNotEqual);

       // ako je referencijalni tip i ako nije operator == ili != onda je to greska
       if (areRefType && !relopIsEqualsOrNotEquals){
    	   report_error("Semanticka greska na liniji " + condFact.getLine() + ": Uz tipove mogu se koristiti samo operatori != , ==", null);
    	   return;
       }
       
       condFact.struct = boolType;
   }
   
   public void visit(CondFactComplex condFactComplex) {
	   condFactComplex.struct = condFactComplex.getExpr().struct;
   }
   
   
   /*Designator Statementi*/
   
   //Designator Assignop Expr, proveri da li su isti tipovi dodeljujes vrednost Designator = Expr;
   public void visit(DesignatorAssignop designatorAssignop) {
	   Struct leviTip = designatorAssignop.getDesignator().obj.getType();
	   Struct desniTip = designatorAssignop.getExpr().struct;
	   
	   if (leviTip.assignableTo(desniTip))
		   return;
	   report_error("Semanticka greska na liniji " + designatorAssignop.getLine() + ": Tipovi nisu kompatibilni", null);
	   
   }
   
   //pomocna metoda koja se bavi incom i decom
   private void helpIncDec(Designator desg, SyntaxNode pisanjeGreske) {
	   Obj designatorObj = desg.obj;
	   if (!(designatorObj.getKind() == Obj.Var || designatorObj.getKind() == Obj.Elem)) {
		   report_error("Semanticka greska na liniji " + pisanjeGreske.getLine() + ": Uz INC/DEC opciju mogu se koristiti samo variable ili elemnti niza", null);
		   return;
	   }
	   Struct designatorStruct = designatorObj.getType();
	   if (designatorStruct.getKind() != Struct.Int) 
		   report_error("Semanticka greska na liniji " + pisanjeGreske.getLine() + ": Uz INC/DEC opciju mogu se koristiti samo int tipovi", null);
   }
   
   //Designator INCREMENT ++
   public void visit(DesignatorIncrement designatorIncrement) {
	   helpIncDec(designatorIncrement.getDesignator(), designatorIncrement);
   }
   
   //Designator DECREMENT --
   public void visit(DesignatorDecrement designatorDecrement) {
	   helpIncDec(designatorDecrement.getDesignator(), designatorDecrement);
   }
   
   
   //Designator ::= Scope IdentExpressList;
   public void visit(Designator designator) {
	   
	   Scope scope = designator.getScope();
	   Obj desgObj = null;
	   if (scope instanceof ScopeNamespace)
		   desgObj = ((ScopeNamespace)designator.getScope()).obj;
	   else if (scope instanceof ScopeLocal)
		   desgObj = ((ScopeLocal)designator.getScope()).obj;
	   
	   if (desgObj == null) {
		   report_error("Semanticka greska na liniji " + designator.getLine() + ": Identifikator nije deklarisan", null);
		   return;
	   }
	   designator.obj = desgObj;
	   
	   int kind = designator.obj.getType().getKind();
	   if (kind == Struct.Array && designator.getIdentExpressList() instanceof MultiplesIdentExpresses)
		   designator.obj = ((MultiplesIdentExpresses) designator.getIdentExpressList()).getIdentExpressListElem().obj;
   }
   
   //Designator LEFTPAREN ActParsList RIGHTPAREN
   public void visit(DesignatorFunctionCall designatorFunctionCall) {
	   Designator desg = designatorFunctionCall.getDesignator();
	   if (desg.obj.getKind() != Obj.Meth) {
		   report_error("Semanticka greska na liniji " + designatorFunctionCall.getLine() + ": Ne postoji metoda", null);
		   potrebniParametri = null;
		   prosledjeniParametri = null;
		   return;
	   }
	   if (!proveriSveParametre()) {
		   report_error("Greska na liniji "+ designatorFunctionCall.getLine()+" : Sa parametrima nesto nije u redu", null);
		   return;
	   }
	   report_info("Pozvana je metoda na liniji: " + designatorFunctionCall.getLine(), null);
	   potrebniParametri = null;
	   prosledjeniParametri = null;
   }
   
   //ActPars COMMA Expr
   public void visit(MultipleActPars multipleActPars) {
	   if (prosledjeniParametri == null)
		   prosledjeniParametri = new ArrayList<Obj>();
	  
	   Obj add = new Obj(0, "nebitno", multipleActPars.getExpr().struct);
	   prosledjeniParametri.add(add);
   }
   
   //Expr
   public void visit(ActParsExpr actParsExpr) {
	   if (prosledjeniParametri == null)
		   prosledjeniParametri = new ArrayList<Obj>();
	  
	   Obj add = new Obj(0, "nebitno", actParsExpr.getExpr().struct);
	   prosledjeniParametri.add(add);
   }
   
   //AddopTermList
   public void visit(ExprTerm exprTerm) {
	   exprTerm.struct = exprTerm.getAddopTermList().struct;
   }
   
   //MINUS AddopTermList
   public void visit(ExprMinus exprMinus) {
	// Proveri da li je tip int
	  if (exprMinus.getAddopTermList().struct != Tab.intType) {
			report_error("Semanticka greska na liniji " + exprMinus.getLine() + ": Operacija - podrzava samo tip int ", null);
			exprMinus.struct= Tab.noType;
		return;
	  }
	  exprMinus.struct= exprMinus.getAddopTermList().struct;
   }
   
   // Term
   public void visit(OneTermAddopTerm oneTermAddopTerm) {
	   oneTermAddopTerm.struct = oneTermAddopTerm.getTerm().struct;
   }
   
   //AddopTermList Addop Term
   public void visit(MultipleAddopTerms multipleAddopTerms) {
	   if ((!multipleAddopTerms.getTerm().struct.equals(Tab.intType)) || (!multipleAddopTerms.getAddopTermList().struct.equals(Tab.intType))) {
			  report_error("Semanticka greska na liniji " + multipleAddopTerms.getLine() + ": Oba argumenta moraju biti tipa int za operacije + i -", null);
			  multipleAddopTerms.struct = Tab.noType;
			  return;
		  }
	   multipleAddopTerms.struct = multipleAddopTerms.getAddopTermList().struct;
   }
   
   //FactorMulopList ;
   public void visit(Term term) {
	   term.struct = term.getFactorMulopList().struct;
   }
   
   //Factor
   public void visit(OneFactorMulopList oneFactorMulopList) {
	   oneFactorMulopList.struct = oneFactorMulopList.getFactor().struct;
   }
   
   //FactorMulopList Mulop Factor
   public void visit(MultipleFactorMulopLists multipleFactorMulopLists) {
	   if (!(multipleFactorMulopLists.getFactor().struct.equals(Tab.intType)) || !(multipleFactorMulopLists.getFactorMulopList().struct.equals(Tab.intType))) {
			  report_error("Semanticka greska na liniji " + multipleFactorMulopLists.getLine() + ": Za operacije *, /, % oba argumenta moraju biti tipa int", null);
			  multipleFactorMulopLists.struct = Tab.noType;
			  return;
		  }
		  multipleFactorMulopLists.struct = multipleFactorMulopLists.getFactorMulopList().struct;
   }
   
   //IDENTIFICATOR:namespaceName DOUBLECOLON IDENTIFICATOR:name
   public void visit(ScopeNamespace scopeNamespace) {
	   
	   Obj scope = Tab.find(scopeNamespace.getNamespaceName());
	   
	   if (scope == Tab.noObj) {
		   report_error("Semanticka greska na liniji " + scopeNamespace.getLine() + ": Namespace ne postoji", null);
		   return;
	   }
	   String ime = createName(scopeNamespace.getName(), scopeNamespace.getNamespaceName());
	   Obj obj = Tab.find(ime);
	   
	   if (obj == Tab.noObj) {
		   report_error("Semanticka greska na liniji " + scopeNamespace.getLine() + ": Simbol sa imenom u ovom namespace-u ne postoji", null);
		   return;
	   }
	   scopeNamespace.obj = obj;
   }
   
   //IDENTIFICATOR:name
   public void visit(ScopeLocal scopeLocal) {
	   String ime = scopeLocal.getName();

       if (currentNamespace != null && Tab.currentScope().findSymbol(ime) == null)
           ime = createName(ime, currentNamespace);

       Obj obj = Tab.find(ime);
       if (obj == Tab.noObj)
    	   report_error("Semanticka greska na liniji " + scopeLocal.getLine() + ": Simbol ne postoji", null);
       else
    	   scopeLocal.obj = obj;
   }
   
   private Obj getFirstLeft(MultiplesIdentExpresses visitor) {
       if (visitor.getIdentExpressList() instanceof NoIdentExpresses) {
           SyntaxNode parent = visitor.getParent();
           while (parent instanceof MultiplesIdentExpresses)
               parent = parent.getParent();
           return getDesignatorName((Designator) parent);
       } else
           return visitor.getIdentExpressList().obj;
   }
   
   private Obj getDesignatorName(Designator designator) {

       if (designator.getScope() instanceof ScopeLocal)
           return ((ScopeLocal) designator.getScope()).obj;
       else
           return ((ScopeNamespace) designator.getScope()).obj;
   }
   
   //LEFTBRACKET Expr RIGHTBRACKET
   public void visit(IdentExpressListElem identExpressListElem) {
	   Obj firstLeft = getFirstLeft((MultiplesIdentExpresses) identExpressListElem.getParent());
       if (firstLeft == Tab.noObj)
    	   identExpressListElem.obj = Tab.noObj;
       else {
           if (identExpressListElem.getExpr().struct != Tab.intType)
               report_error("Error: inside [] muse be an Int", identExpressListElem);
           identExpressListElem.obj = new Obj(Obj.Elem, "elem", firstLeft.getType().getElemType());
       }
   }
}
