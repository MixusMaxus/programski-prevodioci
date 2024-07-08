package rs.ac.bg.etf.pp1;

import java.io.Console;
import java.util.List;
import java.util.Stack;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;
import rs.etf.pp1.mj.runtime.Run;

public class CodeGenerator extends VisitorAdaptor {

	
	public int mainPC;
	
	public int getMainPC() {
		return mainPC;
	}

	public void setMainPC(int mainPc) {
		this.mainPC = mainPC;
	}
	
    @Override
    public void visit(MethodName methodName) {
    	Code.put(Code.enter);
    	Code.put(methodName.obj.getLevel());
		Code.put(methodName.obj.getLocalSymbols().size());
    }
	
    @Override
    public void visit(MethodDecl methodDecl) {
        Code.put(Code.exit);
        Code.put(Code.return_);
    }
	
	private void storeDesignator(Obj designator) {
		 if (designator.getKind() != Obj.Elem)
	            Code.store(designator);
	        else if (designator.getType() == Tab.intType)
	            Code.put(Code.astore);
	        else
	            Code.put(Code.bastore);
	}
	
	 private void loadDesignator(Obj designatorObj){
	        if (designatorObj.getKind() != Obj.Elem)
	            Code.load(designatorObj);
	        else if (designatorObj.getType() == Tab.intType)
	            Code.put(Code.aload);
	        else
	            Code.put(Code.baload);
	    }
	 
//	 =============================================================
	 
//	 DesignatorStatement ::= (DesignatorAssignop) Designator Assignop Expr
	 public void visit(DesignatorAssignop designatorAssignop) {
		 storeDesignator(designatorAssignop.getDesignator().obj);
	 }
	 
//	 DesignatorStatement ::= (DesignatorIncrement) Designator INCREMENT
	 public void visit(DesignatorIncrement designatorIncrement) {
		 if (designatorIncrement.getDesignator().obj.getKind() == Obj.Elem) {
			 Code.put(Code.dup2);
		 }
		 
		 loadDesignator(designatorIncrement.getDesignator().obj);
		 Code.loadConst(1);
		 Code.put(Code.add);
		 storeDesignator(designatorIncrement.getDesignator().obj);
	 }
	 
//	 DesignatorStatement ::= (DesignatorIncDobl) Designator INCDOBL
	 public void visit(DesignatorIncDobl designatorIncDobl) {
		 if (designatorIncDobl.getDesignator().obj.getKind() == Obj.Elem) {
			 Code.put(Code.dup2);
		 }
		 
		 loadDesignator(designatorIncDobl.getDesignator().obj);
		 Code.loadConst(2);
		 Code.put(Code.add);
		 storeDesignator(designatorIncDobl.getDesignator().obj);
	 }
	 
//	 DesignatorStatement ::= (DesignatorDecrement) Designator DECREMENT
	 public void visit(DesignatorDecrement designatorDecrement) {
		 if (designatorDecrement.getDesignator().obj.getKind() == Obj.Elem) {
			 Code.put(Code.dup2);
		 }
		 
		 loadDesignator(designatorDecrement.getDesignator().obj);
		 Code.loadConst(1);
		 Code.put(Code.sub);
		 storeDesignator(designatorDecrement.getDesignator().obj);
	 }
	 
//	 Statement ::= (StatementRead) READ LEFTPAREN Designator RIGHTPAREN SEMICOLON
	 public void visit(StatementRead statementRead) {
		 Obj designatorObj = statementRead.getDesignator().obj;
		 if (designatorObj.getType() == Tab.intType) {
			 Code.put(Code.read);
		 }
		 else {
			 Code.put(Code.bread);
		 }
		 storeDesignator(designatorObj);
	 }
	 
//	 Statement ::= (PrintStatement) PRINT LEFTPAREN Expr StatementPrint RIGHTPAREN SEMICOLON
	 public void visit(PrintStatement printStatement) {
		 Expr expr = printStatement.getExpr();
		 if (expr.struct.getKind() == Struct.Array) {
			 Code.loadConst(0);
			 Code.put(Code.dup);
			 // skok
			 Code.put(Code.pop);
			 Code.put(Code.dup2);
			 
			 //print
			 if (expr.struct.getElemType() == Tab.charType) {
				 Code.put(Code.baload);
				 Code.loadConst(1);
				 Code.put(Code.bprint);
			 }
			 else {
				 Code.put(Code.aload);
				 Code.loadConst(5);
				 Code.put(Code.print);  // adr i
			 } 
			 
			 //inc indeksa
			 Code.loadConst(1); 
			 Code.put(Code.add); //adr i+ 
			 
			 Code.put(Code.dup2);  // adr i+ adr i+
			 Code.put(Code.pop);
			 Code.put(Code.arraylength);   // adr i+ arrLen
			 Code.put(Code.dup2);  // adr i+ arrLen i+ arrLen
			 Code.putFalseJump(Code.ge, Code.pc - 11);  // adr i+ arrLen
			 
			 Code.put(Code.pop);
			 Code.put(Code.pop);
			 Code.put(Code.pop);
			 
		 }
		 else if (expr.struct == Tab.charType) {
			 Code.loadConst(1);
			 Code.put(Code.bprint);
		 }
		 else {
			 Code.loadConst(5);
			 Code.put(Code.print);
		 }
//		 Code.put(Code.dup);
//		 Code.put(Code.arraylength);
//		 Code.loadConst(1);
//		 Code.put(Code.sub);
//		 Code.put(Code.dup2);
//		 Code.put(Code.aload);
//		 Code.loadConst(5);
//		 Code.put(Code.print);
//		 Code.put(Code.dup);
//		 Code.loadConst(0);
//		 Code.putFalseJump(Code.le, 38);
//		 Code.put(Code.pop);
//		 Code.put(Code.pop);
		 	
	 }
	 
//	 AddopTermList ::= (MultipleAddopTerms) AddopTermList Addop Term
	 public void visit(MultipleAddopTerms multipleAddopTerms) {
		 if (multipleAddopTerms.getAddop() instanceof AddopPlus) {
			 Code.put(Code.add);
		 }
		 else {
			 Code.put(Code.sub);
		 } 
	 }
	 
//	 FactorMulopList ::= (MultipleFactorMulopLists) FactorMulopList Mulop Factor
	 public void visit(MultipleFactorMulopLists multipleFactorMulopLists) {
		 Mulop mul = multipleFactorMulopLists.getMulop();
		 if (mul instanceof MulopMul) {
			 Code.put(Code.mul);
		 }
		 else if (mul instanceof MulopDiv) {
			 Code.put(Code.div);
		 }
		 else if (mul instanceof MulopMod){
			 Code.put(Code.rem);
		 }
		 else if (mul instanceof MulopOperator){
			   Code.put(Code.dup2);
			   Code.put(Code.dup2);
			   Code.put(Code.mul);
			   Code.loadConst(2);
			   Code.put(Code.mul);
			   Code.put(Code.dup2);
			   Code.put(Code.pop);
			   Code.put(Code.dup);
			   Code.put(Code.mul);
			   Code.put(Code.add);
			   Code.put(Code.add);
			   Code.put(Code.dup2);
			   Code.put(Code.pop);
			   Code.put(Code.dup);
			   Code.put(Code.mul);
			   Code.put(Code.add);
			   Code.put(Code.add);
			   Code.put(Code.sub);
			   Code.put(Code.neg);
			   Code.put(Code.sub);
			   Code.put(Code.neg);
		 }
		 else {
			 Code.put(Code.dup2);
			 Code.put(Code.dup2);
			 Code.put(Code.pop);
			 Code.put(Code.arraylength);
			 Code.put(Code.sub);
			 Code.put(Code.neg);
			 Code.put(Code.aload);
			 Code.put(Code.dup_x2);
			 Code.put(Code.pop);
			 Code.loadConst(1);
			 Code.put(Code.sub);
			 Code.put(Code.aload);
			 Code.put(Code.add);
		 }
	 }
//	 Expr ::= (ExprMinus) MINUS AddopTermList
	 public void visit(ExprMinus exprMinus) {
		 Code.put(Code.neg);
	 }
	 
//	 Factor ::= (FactorNumCnst) NUMCONSTANT:numValue
	 public void visit(FactorNumCnst factorNumCnst) {
		 Code.loadConst(factorNumCnst.getNumValue());
	 }
	 
//	 Factor ::= (FactorCharCnst)CHARCONSTANT:charValue
	 public void visit(FactorCharCnst factorCharCnst) {
		 Code.loadConst(factorCharCnst.getCharValue());
	 }
	 
//	 Factor ::= (FactorBoolCnst)BOOLCONSTANT:boolValue
	 public void visit(FactorBoolCnst factorBoolCnst) {
		 int load = factorBoolCnst.getBoolValue() ? 1 : 0;
		 Code.loadConst(load);
	 }
	 
//	 Factor ::= (FactorNewArray) NEW Type LEFTBRACKET Expr RIGHTBRACKET
	 public void visit(FactorNewArray factorNewArray) {
		 Code.put(Code.newarray);
		 int load = factorNewArray.getType().struct == Tab.charType ? 0 : 1;
		 Code.put(load);
	 }
	 
//	 Factor ::= (FactorDesignator) Designator FactorDesignatorActParsList
	 public void visit(FactorDesignator factorDesignator) {
		 Code.load(factorDesignator.getDesignator().obj);
	 }
	 
//		DesignatorName ::= (DesignatorName) IDENTIFICATOR:designatorName ;
		public void visit(DesignatorName designatorName) {
			SyntaxNode parent = designatorName.getParent();
			if (parent instanceof Designator) {
				IdentExpressList nesto = ((Designator) parent).getIdentExpressList();
				if (nesto instanceof MultiplesIdentExpresses)
					Code.load(designatorName.obj);
			}
		}
		
//		(FactorSum) SUM LEFTPAREN Expr RIGHTPAREN
		public void visit(FactorSum factorSum) {
			Obj sum = Tab.insert(Obj.Var, "sum", new Struct(Struct.Int));
			Code.loadConst(0);
			Code.store(sum);
			Code.loadConst(0);
			Code.put(Code.dup);
			 // skok
			 Code.put(Code.pop);
			 Code.put(Code.dup2);
			 
			 //print
			 Code.put(Code.aload);
			 Code.load(sum);
			 Code.put(Code.add);
			 Code.store(sum);
			 
			 //inc indeksa
			 Code.loadConst(1); 
			 Code.put(Code.add); //adr i+ 
			 
			 Code.put(Code.dup2);  // adr i+ adr i+
			 Code.put(Code.pop);
			 Code.put(Code.arraylength);   // adr i+ arrLen
			 Code.put(Code.dup2);  // adr i+ arrLen i+ arrLen
			 Code.putFalseJump(Code.ge, Code.pc - 12);  // adr i+ arrLen
			 
			 Code.put(Code.pop);
			 Code.put(Code.pop);
			 Code.put(Code.pop);
			 Code.load(sum);
		}
	 
//	 (FactorRange) RANGE LEFTPAREN Expr RIGHTPAREN
	 public void visit(FactorRange factorRange) {
		 //dodeli memoriju nizu
		 Code.put(Code.newarray);
		 Code.put(1);
		 
		 Code.loadConst(0);
		 Code.put(Code.dup);
		 // skok
		 Code.put(Code.pop);
		 Code.put(Code.dup2);
		 Code.put(Code.dup);
		 
		 //smesti niz
		 Code.put(Code.astore);
		 
		 //inc indeksa
		 Code.loadConst(1); 
		 Code.put(Code.add); //adr i+ 
		 
		 Code.put(Code.dup2);  // adr i+ adr i+
		 Code.put(Code.pop);
		 Code.put(Code.arraylength);   // adr i+ arrLen
		 Code.put(Code.dup2);  // adr i+ arrLen i+ arrLen
		 Code.putFalseJump(Code.ge, Code.pc - 10);  // adr i+ arrLen
		 
		 Code.put(Code.pop);
		 Code.put(Code.pop);
	 }
}
