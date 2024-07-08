// generated with ast extension for cup
// version 0.8
// 27/5/2024 22:55:38


package rs.ac.bg.etf.pp1.ast;

public class OneDsg extends DesignatorList {

    private OneDesignator OneDesignator;

    public OneDsg (OneDesignator OneDesignator) {
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
        buffer.append("OneDsg(\n");

        if(OneDesignator!=null)
            buffer.append(OneDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneDsg]");
        return buffer.toString();
    }
}
