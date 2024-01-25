// generated with ast extension for cup
// version 0.8
// 25/0/2024 18:36:53


package src.rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(DeclarationList DeclarationList);
    public void visit(Mulop Mulop);
    public void visit(OneParamMethod OneParamMethod);
    public void visit(MethodDecl MethodDecl);
    public void visit(ConstantList ConstantList);
    public void visit(Constant Constant);
    public void visit(ForCondFact ForCondFact);
    public void visit(Relop Relop);
    public void visit(Assignop Assignop);
    public void visit(Namespace Namespace);
    public void visit(IdentExpressListElem IdentExpressListElem);
    public void visit(OneDesignator OneDesignator);
    public void visit(Variable Variable);
    public void visit(StatementList StatementList);
    public void visit(NamespaceList NamespaceList);
    public void visit(Addop Addop);
    public void visit(InnerForDesignatorStatementList InnerForDesignatorStatementList);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(FirstDesignator FirstDesignator);
    public void visit(VarList VarList);
    public void visit(Designator Designator);
    public void visit(Term Term);
    public void visit(FormParsList FormParsList);
    public void visit(Condition Condition);
    public void visit(ActParsList ActParsList);
    public void visit(Label Label);
    public void visit(VarDeclList VarDeclList);
    public void visit(IdentExpressList IdentExpressList);
    public void visit(Expr Expr);
    public void visit(ForDesignatorStatement ForDesignatorStatement);
    public void visit(ActPars ActPars);
    public void visit(ConstantDeclaration ConstantDeclaration);
    public void visit(DesignatorList DesignatorList);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(InsideFor InsideFor);
    public void visit(FactorMulopList FactorMulopList);
    public void visit(MethodReturnType MethodReturnType);
    public void visit(InnerForDesignatorStatement InnerForDesignatorStatement);
    public void visit(Statement Statement);
    public void visit(MethodParamList MethodParamList);
    public void visit(VarDecl VarDecl);
    public void visit(Type Type);
    public void visit(ConstDecl ConstDecl);
    public void visit(CondFact CondFact);
    public void visit(StaticInitializer StaticInitializer);
    public void visit(Declaration Declaration);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(SecondDesignator SecondDesignator);
    public void visit(Program Program);
    public void visit(AddopTermList AddopTermList);
    public void visit(MulopDerived3 MulopDerived3);
    public void visit(MulopDerived2 MulopDerived2);
    public void visit(MulopDerived1 MulopDerived1);
    public void visit(AddopDerived2 AddopDerived2);
    public void visit(AddopDerived1 AddopDerived1);
    public void visit(RelopDerived6 RelopDerived6);
    public void visit(RelopDerived5 RelopDerived5);
    public void visit(RelopDerived4 RelopDerived4);
    public void visit(RelopDerived3 RelopDerived3);
    public void visit(RelopDerived2 RelopDerived2);
    public void visit(RelopDerived1 RelopDerived1);
    public void visit(AssignopDerived1 AssignopDerived1);
    public void visit(LabelDerived1 LabelDerived1);
    public void visit(IdentExpressListElemDerived2 IdentExpressListElemDerived2);
    public void visit(IdentExpressListElemDerived1 IdentExpressListElemDerived1);
    public void visit(IdentExpressListDerived2 IdentExpressListDerived2);
    public void visit(IdentExpressListDerived1 IdentExpressListDerived1);
    public void visit(DesignatorDerived2 DesignatorDerived2);
    public void visit(DesignatorDerived1 DesignatorDerived1);
    public void visit(ActParsListDerived2 ActParsListDerived2);
    public void visit(ActParsListDerived1 ActParsListDerived1);
    public void visit(FactorDerived8 FactorDerived8);
    public void visit(FactorDerived7 FactorDerived7);
    public void visit(FactorDerived6 FactorDerived6);
    public void visit(FactorDerived5 FactorDerived5);
    public void visit(FactorDerived4 FactorDerived4);
    public void visit(FactorDerived3 FactorDerived3);
    public void visit(FactorDerived2 FactorDerived2);
    public void visit(FactorDerived1 FactorDerived1);
    public void visit(FactorMulopListDerived2 FactorMulopListDerived2);
    public void visit(FactorMulopListDerived1 FactorMulopListDerived1);
    public void visit(TermDerived1 TermDerived1);
    public void visit(AddopTermListDerived2 AddopTermListDerived2);
    public void visit(AddopTermListDerived1 AddopTermListDerived1);
    public void visit(ExprDerived2 ExprDerived2);
    public void visit(ExprDerived1 ExprDerived1);
    public void visit(CondFactDerived2 CondFactDerived2);
    public void visit(CondFactDerived1 CondFactDerived1);
    public void visit(CondTermDerived2 CondTermDerived2);
    public void visit(CondTermDerived1 CondTermDerived1);
    public void visit(ConditionDerived2 ConditionDerived2);
    public void visit(ConditionDerived1 ConditionDerived1);
    public void visit(ActParsDerived2 ActParsDerived2);
    public void visit(ActParsDerived1 ActParsDerived1);
    public void visit(OneDesignatorDerived2 OneDesignatorDerived2);
    public void visit(OneDesignatorDerived1 OneDesignatorDerived1);
    public void visit(DesignatorListDerived2 DesignatorListDerived2);
    public void visit(DesignatorListDerived1 DesignatorListDerived1);
    public void visit(SecondDesignatorDerived1 SecondDesignatorDerived1);
    public void visit(FirstDesignatorDerived4 FirstDesignatorDerived4);
    public void visit(FirstDesignatorDerived3 FirstDesignatorDerived3);
    public void visit(FirstDesignatorDerived2 FirstDesignatorDerived2);
    public void visit(FirstDesignatorDerived1 FirstDesignatorDerived1);
    public void visit(DesignatorStatementDerived2 DesignatorStatementDerived2);
    public void visit(DesignatorStatementDerived1 DesignatorStatementDerived1);
    public void visit(StatementListDerived2 StatementListDerived2);
    public void visit(StatementListDerived1 StatementListDerived1);
    public void visit(InnerForDesignatorStatementListDerived2 InnerForDesignatorStatementListDerived2);
    public void visit(InnerForDesignatorStatementListDerived1 InnerForDesignatorStatementListDerived1);
    public void visit(InnerForDesignatorStatementDerived1 InnerForDesignatorStatementDerived1);
    public void visit(ForDesignatorStatementDerived2 ForDesignatorStatementDerived2);
    public void visit(ForDesignatorStatementDerived1 ForDesignatorStatementDerived1);
    public void visit(ForCondFactDerived2 ForCondFactDerived2);
    public void visit(ForCondFactDerived1 ForCondFactDerived1);
    public void visit(InsideForDerived1 InsideForDerived1);
    public void visit(StatementDerived12 StatementDerived12);
    public void visit(StatementDerived11 StatementDerived11);
    public void visit(StatementDerived10 StatementDerived10);
    public void visit(StatementDerived9 StatementDerived9);
    public void visit(StatementDerived8 StatementDerived8);
    public void visit(StatementDerived7 StatementDerived7);
    public void visit(StatementDerived6 StatementDerived6);
    public void visit(StatementDerived5 StatementDerived5);
    public void visit(StatementDerived4 StatementDerived4);
    public void visit(StatementDerived3 StatementDerived3);
    public void visit(StatementDerived2 StatementDerived2);
    public void visit(StatementDerived1 StatementDerived1);
    public void visit(TypeDerived1 TypeDerived1);
    public void visit(VarDeclListDerived2 VarDeclListDerived2);
    public void visit(VarDeclListDerived1 VarDeclListDerived1);
    public void visit(OneParamMethodDerived1 OneParamMethodDerived1);
    public void visit(FormParsListDerived2 FormParsListDerived2);
    public void visit(FormParsListDerived1 FormParsListDerived1);
    public void visit(MethodParamListDerived2 MethodParamListDerived2);
    public void visit(MethodParamListDerived1 MethodParamListDerived1);
    public void visit(MethodReturnTypeDerived2 MethodReturnTypeDerived2);
    public void visit(MethodReturnTypeDerived1 MethodReturnTypeDerived1);
    public void visit(MethodDeclDerived1 MethodDeclDerived1);
    public void visit(MethodDeclListDerived2 MethodDeclListDerived2);
    public void visit(MethodDeclListDerived1 MethodDeclListDerived1);
    public void visit(StaticInitializerDerived1 StaticInitializerDerived1);
    public void visit(VariableDerived1 VariableDerived1);
    public void visit(VarListDerived2 VarListDerived2);
    public void visit(VarListDerived1 VarListDerived1);
    public void visit(VarDeclDerived1 VarDeclDerived1);
    public void visit(ConstantDerived3 ConstantDerived3);
    public void visit(ConstantDerived2 ConstantDerived2);
    public void visit(ConstantDerived1 ConstantDerived1);
    public void visit(ConstantDeclarationDerived1 ConstantDeclarationDerived1);
    public void visit(ConstantListDerived2 ConstantListDerived2);
    public void visit(ConstantListDerived1 ConstantListDerived1);
    public void visit(ConstDeclDerived1 ConstDeclDerived1);
    public void visit(DeclarationDerived2 DeclarationDerived2);
    public void visit(DeclarationDerived1 DeclarationDerived1);
    public void visit(DeclarationListDerived2 DeclarationListDerived2);
    public void visit(DeclarationListDerived1 DeclarationListDerived1);
    public void visit(NamespaceDerived1 NamespaceDerived1);
    public void visit(NamespaceListDerived2 NamespaceListDerived2);
    public void visit(NamespaceListDerived1 NamespaceListDerived1);
    public void visit(ProgramDerived1 ProgramDerived1);

}
