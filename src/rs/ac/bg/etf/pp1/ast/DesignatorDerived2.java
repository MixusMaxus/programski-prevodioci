// generated with ast extension for cup
// version 0.8
// 25/0/2024 18:36:53


package src.rs.ac.bg.etf.pp1.ast;

public class DesignatorDerived2 extends Designator {

    private IdentExpressList IdentExpressList;

    public DesignatorDerived2 (IdentExpressList IdentExpressList) {
        this.IdentExpressList=IdentExpressList;
        if(IdentExpressList!=null) IdentExpressList.setParent(this);
    }

    public IdentExpressList getIdentExpressList() {
        return IdentExpressList;
    }

    public void setIdentExpressList(IdentExpressList IdentExpressList) {
        this.IdentExpressList=IdentExpressList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentExpressList!=null) IdentExpressList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentExpressList!=null) IdentExpressList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentExpressList!=null) IdentExpressList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorDerived2(\n");

        if(IdentExpressList!=null)
            buffer.append(IdentExpressList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorDerived2]");
        return buffer.toString();
    }
}