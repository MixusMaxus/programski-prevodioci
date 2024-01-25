// generated with ast extension for cup
// version 0.8
// 25/0/2024 18:36:53


package src.rs.ac.bg.etf.pp1.ast;

public class DesignatorListDerived1 extends DesignatorList {

    private DesignatorList DesignatorList;
    private OneDesignator OneDesignator;

    public DesignatorListDerived1 (DesignatorList DesignatorList, OneDesignator OneDesignator) {
        this.DesignatorList=DesignatorList;
        if(DesignatorList!=null) DesignatorList.setParent(this);
        this.OneDesignator=OneDesignator;
        if(OneDesignator!=null) OneDesignator.setParent(this);
    }

    public DesignatorList getDesignatorList() {
        return DesignatorList;
    }

    public void setDesignatorList(DesignatorList DesignatorList) {
        this.DesignatorList=DesignatorList;
    }

    public OneDesignator getOneDesignator() {
        return OneDesignator;
    }

    public void setOneDesignator(OneDesignator OneDesignator) {
        this.OneDesignator=OneDesignator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorList!=null) DesignatorList.accept(visitor);
        if(OneDesignator!=null) OneDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorList!=null) DesignatorList.traverseTopDown(visitor);
        if(OneDesignator!=null) OneDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorList!=null) DesignatorList.traverseBottomUp(visitor);
        if(OneDesignator!=null) OneDesignator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorListDerived1(\n");

        if(DesignatorList!=null)
            buffer.append(DesignatorList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OneDesignator!=null)
            buffer.append(OneDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorListDerived1]");
        return buffer.toString();
    }
}
