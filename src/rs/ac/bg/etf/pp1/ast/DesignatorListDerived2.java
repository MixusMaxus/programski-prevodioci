// generated with ast extension for cup
// version 0.8
// 25/0/2024 18:36:53


package src.rs.ac.bg.etf.pp1.ast;

public class DesignatorListDerived2 extends DesignatorList {

    private OneDesignator OneDesignator;

    public DesignatorListDerived2 (OneDesignator OneDesignator) {
        this.OneDesignator=OneDesignator;
        if(OneDesignator!=null) OneDesignator.setParent(this);
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
        if(OneDesignator!=null) OneDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OneDesignator!=null) OneDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OneDesignator!=null) OneDesignator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorListDerived2(\n");

        if(OneDesignator!=null)
            buffer.append(OneDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorListDerived2]");
        return buffer.toString();
    }
}
