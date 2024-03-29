// generated with ast extension for cup
// version 0.8
// 4/1/2024 18:13:15


package rs.ac.bg.etf.pp1.ast;

public class MultiplesIdentExpresses extends IdentExpressList {

    private IdentExpressList IdentExpressList;
    private IdentExpressListElem IdentExpressListElem;

    public MultiplesIdentExpresses (IdentExpressList IdentExpressList, IdentExpressListElem IdentExpressListElem) {
        this.IdentExpressList=IdentExpressList;
        if(IdentExpressList!=null) IdentExpressList.setParent(this);
        this.IdentExpressListElem=IdentExpressListElem;
        if(IdentExpressListElem!=null) IdentExpressListElem.setParent(this);
    }

    public IdentExpressList getIdentExpressList() {
        return IdentExpressList;
    }

    public void setIdentExpressList(IdentExpressList IdentExpressList) {
        this.IdentExpressList=IdentExpressList;
    }

    public IdentExpressListElem getIdentExpressListElem() {
        return IdentExpressListElem;
    }

    public void setIdentExpressListElem(IdentExpressListElem IdentExpressListElem) {
        this.IdentExpressListElem=IdentExpressListElem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentExpressList!=null) IdentExpressList.accept(visitor);
        if(IdentExpressListElem!=null) IdentExpressListElem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentExpressList!=null) IdentExpressList.traverseTopDown(visitor);
        if(IdentExpressListElem!=null) IdentExpressListElem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentExpressList!=null) IdentExpressList.traverseBottomUp(visitor);
        if(IdentExpressListElem!=null) IdentExpressListElem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultiplesIdentExpresses(\n");

        if(IdentExpressList!=null)
            buffer.append(IdentExpressList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IdentExpressListElem!=null)
            buffer.append(IdentExpressListElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultiplesIdentExpresses]");
        return buffer.toString();
    }
}
