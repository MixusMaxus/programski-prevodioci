// generated with ast extension for cup
// version 0.8
// 4/1/2024 18:13:15


package rs.ac.bg.etf.pp1.ast;

public class Designator implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private Scope Scope;
    private IdentExpressList IdentExpressList;

    public Designator (Scope Scope, IdentExpressList IdentExpressList) {
        this.Scope=Scope;
        if(Scope!=null) Scope.setParent(this);
        this.IdentExpressList=IdentExpressList;
        if(IdentExpressList!=null) IdentExpressList.setParent(this);
    }

    public Scope getScope() {
        return Scope;
    }

    public void setScope(Scope Scope) {
        this.Scope=Scope;
    }

    public IdentExpressList getIdentExpressList() {
        return IdentExpressList;
    }

    public void setIdentExpressList(IdentExpressList IdentExpressList) {
        this.IdentExpressList=IdentExpressList;
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
        if(Scope!=null) Scope.accept(visitor);
        if(IdentExpressList!=null) IdentExpressList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Scope!=null) Scope.traverseTopDown(visitor);
        if(IdentExpressList!=null) IdentExpressList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Scope!=null) Scope.traverseBottomUp(visitor);
        if(IdentExpressList!=null) IdentExpressList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator(\n");

        if(Scope!=null)
            buffer.append(Scope.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IdentExpressList!=null)
            buffer.append(IdentExpressList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator]");
        return buffer.toString();
    }
}
