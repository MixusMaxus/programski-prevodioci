// generated with ast extension for cup
// version 0.8
// 27/5/2024 22:55:38


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(DeclarationList DeclarationList);
    public void visit(ArrayBrackets ArrayBrackets);
    public void visit(OneParamMethod OneParamMethod);
    public void visit(Mulop Mulop);
    public void visit(FactorDesignatorActParsList FactorDesignatorActParsList);
    public void visit(StatementElseStatement StatementElseStatement);
    public void visit(ConstantList ConstantList);
    public void visit(Constant Constant);
    public void visit(ForCondFact ForCondFact);
    public void visit(Relop Relop);
    public void visit(Assignop Assignop);
    public void visit(OneDesignator OneDesignator);
    public void visit(DesignatorIdentificator DesignatorIdentificator);
    public void visit(Variable Variable);
    public void visit(StatementList StatementList);
    public void visit(Addop Addop);
    public void visit(InnerForDesignatorStatementList InnerForDesignatorStatementList);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(VarList VarList);
    public void visit(CondFactExprRelopExpr CondFactExprRelopExpr);
    public void visit(FormParsList FormParsList);
    public void visit(Condition Condition);
    public void visit(FactorNewType FactorNewType);
    public void visit(IfMaybe IfMaybe);
    public void visit(ActParsList ActParsList);
    public void visit(Expr Expr);
    public void visit(IdentExpressList IdentExpressList);
    public void visit(ForDesignatorStatement ForDesignatorStatement);
    public void visit(ActPars ActPars);
    public void visit(DesignatorList DesignatorList);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(FactorMulopList FactorMulopList);
    public void visit(MethodReturnType MethodReturnType);
    public void visit(MethodVariableList MethodVariableList);
    public void visit(Statement Statement);
    public void visit(MethodParamList MethodParamList);
    public void visit(StatementPrint StatementPrint);
    public void visit(Declaration Declaration);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(StatementReturn StatementReturn);
    public void visit(AddopTermList AddopTermList);
    public void visit(MulopSusedi MulopSusedi);
    public void visit(MulopOperator MulopOperator);
    public void visit(MulopMod MulopMod);
    public void visit(MulopDiv MulopDiv);
    public void visit(MulopMul MulopMul);
    public void visit(AddopMinus AddopMinus);
    public void visit(AddopPlus AddopPlus);
    public void visit(RelopLessEqual RelopLessEqual);
    public void visit(RelopLess RelopLess);
    public void visit(RelopGrtEqual RelopGrtEqual);
    public void visit(RelopGrt RelopGrt);
    public void visit(RelopNotEqual RelopNotEqual);
    public void visit(RelopEqual RelopEqual);
    public void visit(AssignopError AssignopError);
    public void visit(AssignopEqual AssignopEqual);
    public void visit(Label Label);
    public void visit(NoIdentExpresses NoIdentExpresses);
    public void visit(MultiplesIdentExpresses MultiplesIdentExpresses);
    public void visit(DesignatorName DesignatorName);
    public void visit(Designator Designator);
    public void visit(NoActPars NoActPars);
    public void visit(ActParsComplex ActParsComplex);
    public void visit(NoParenPars NoParenPars);
    public void visit(ParenPars ParenPars);
    public void visit(FactorSum FactorSum);
    public void visit(FactorRange FactorRange);
    public void visit(FactorExpr FactorExpr);
    public void visit(FactorNewArray FactorNewArray);
    public void visit(FactorBoolCnst FactorBoolCnst);
    public void visit(FactorCharCnst FactorCharCnst);
    public void visit(FactorNumCnst FactorNumCnst);
    public void visit(FactorDesignator FactorDesignator);
    public void visit(OneFactorMulopList OneFactorMulopList);
    public void visit(MultipleFactorMulopLists MultipleFactorMulopLists);
    public void visit(Term Term);
    public void visit(OneTermAddopTerm OneTermAddopTerm);
    public void visit(MultipleAddopTerms MultipleAddopTerms);
    public void visit(ExprMinus ExprMinus);
    public void visit(ExprTerm ExprTerm);
    public void visit(CondFactSimple CondFactSimple);
    public void visit(CondFactComplex CondFactComplex);
    public void visit(CondFact CondFact);
    public void visit(CondTermFact CondTermFact);
    public void visit(CondTermAnd CondTermAnd);
    public void visit(ConditionError ConditionError);
    public void visit(ConditionCondTerm ConditionCondTerm);
    public void visit(ConditionOr ConditionOr);
    public void visit(ActParsExpr ActParsExpr);
    public void visit(MultipleActPars MultipleActPars);
    public void visit(NoDesignator NoDesignator);
    public void visit(OneDesignatorDsg OneDesignatorDsg);
    public void visit(OneDsg OneDsg);
    public void visit(MultipleDesignatorLists MultipleDesignatorLists);
    public void visit(NoIF NoIF);
    public void visit(ActualIf ActualIf);
    public void visit(DSListComprehension DSListComprehension);
    public void visit(MultipleAssignements MultipleAssignements);
    public void visit(DesignatorIncDobl DesignatorIncDobl);
    public void visit(DesignatorDecrement DesignatorDecrement);
    public void visit(DesignatorIncrement DesignatorIncrement);
    public void visit(DesignatorFunctionCall DesignatorFunctionCall);
    public void visit(DesignatorAssignop DesignatorAssignop);
    public void visit(NoStatementsLists NoStatementsLists);
    public void visit(MultipleStatementsLists MultipleStatementsLists);
    public void visit(NoInnerForDesignatorStatementLists NoInnerForDesignatorStatementLists);
    public void visit(MultipleInnerForDesignatorStatementLists MultipleInnerForDesignatorStatementLists);
    public void visit(InnerForDesignatorStatement InnerForDesignatorStatement);
    public void visit(NoForDesignatorStmt NoForDesignatorStmt);
    public void visit(ForDesignatorStmtInner ForDesignatorStmtInner);
    public void visit(NoForCndFact NoForCndFact);
    public void visit(ForCndFact ForCndFact);
    public void visit(InsideFor InsideFor);
    public void visit(StmtPrintBasic StmtPrintBasic);
    public void visit(StmtPrintComplex StmtPrintComplex);
    public void visit(StmtReturnVoid StmtReturnVoid);
    public void visit(StmtReturnExpr StmtReturnExpr);
    public void visit(NoElseStatement NoElseStatement);
    public void visit(ElseStatement ElseStatement);
    public void visit(For For);
    public void visit(MultipleStatements MultipleStatements);
    public void visit(StatementFor StatementFor);
    public void visit(PrintStatement PrintStatement);
    public void visit(StatementRead StatementRead);
    public void visit(ReturnStatement ReturnStatement);
    public void visit(StatementContinue StatementContinue);
    public void visit(StatementBreak StatementBreak);
    public void visit(StatementIfElse StatementIfElse);
    public void visit(StatementDesignator StatementDesignator);
    public void visit(Type Type);
    public void visit(NoVarDeclarations NoVarDeclarations);
    public void visit(MultipleVarDeclarations MultipleVarDeclarations);
    public void visit(OneParametherMethodError OneParametherMethodError);
    public void visit(OneParametherMethod OneParametherMethod);
    public void visit(OneFormParamether OneFormParamether);
    public void visit(MultipleFormParamethers MultipleFormParamethers);
    public void visit(NoMethodParamethers NoMethodParamethers);
    public void visit(MultipleMethodParamethers MultipleMethodParamethers);
    public void visit(MethodReturnTypeVoid MethodReturnTypeVoid);
    public void visit(MethodReturnTypeType MethodReturnTypeType);
    public void visit(MethodName MethodName);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoMethodDeclarations NoMethodDeclarations);
    public void visit(MultipleMethodDeclarations MultipleMethodDeclarations);
    public void visit(StaticInitializer StaticInitializer);
    public void visit(NoArrayBrackets NoArrayBrackets);
    public void visit(HasArrayBrackets HasArrayBrackets);
    public void visit(ErrorVariable ErrorVariable);
    public void visit(VariableDecl VariableDecl);
    public void visit(OneVariable OneVariable);
    public void visit(MultipleVariables MultipleVariables);
    public void visit(VarDecl VarDecl);
    public void visit(BooleanConstant BooleanConstant);
    public void visit(CharacterConstant CharacterConstant);
    public void visit(NumberConstant NumberConstant);
    public void visit(ConstantDeclaration ConstantDeclaration);
    public void visit(OneConstant OneConstant);
    public void visit(MultipleConstants MultipleConstants);
    public void visit(ConstDecl ConstDecl);
    public void visit(DeclarationVarDecl DeclarationVarDecl);
    public void visit(DeclarationConstDecl DeclarationConstDecl);
    public void visit(NoDeclarations NoDeclarations);
    public void visit(MultipleDeclarations MultipleDeclarations);
    public void visit(ProgramName ProgramName);
    public void visit(Program Program);

}
