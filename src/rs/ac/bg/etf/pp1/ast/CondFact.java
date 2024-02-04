// generated with ast extension for cup
// version 0.8
// 4/1/2024 18:13:15


package rs.ac.bg.etf.pp1.ast;

public class CondFact implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private Expr Expr;
    private CondFactExprRelopExpr CondFactExprRelopExpr;

    public CondFact (Expr Expr, CondFactExprRelopExpr CondFactExprRelopExpr) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.CondFactExprRelopExpr=CondFactExprRelopExpr;
        if(CondFactExprRelopExpr!=null) CondFactExprRelopExpr.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public CondFactExprRelopExpr getCondFactExprRelopExpr() {
        return CondFactExprRelopExpr;
    }

    public void setCondFactExprRelopExpr(CondFactExprRelopExpr CondFactExprRelopExpr) {
        this.CondFactExprRelopExpr=CondFactExprRelopExpr;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(CondFactExprRelopExpr!=null) CondFactExprRelopExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(CondFactExprRelopExpr!=null) CondFactExprRelopExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(CondFactExprRelopExpr!=null) CondFactExprRelopExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFact(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFactExprRelopExpr!=null)
            buffer.append(CondFactExprRelopExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFact]");
        return buffer.toString();
    }
}
