// generated with ast extension for cup
// version 0.8
// 25/0/2024 18:36:53


package src.rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementDerived1 extends DesignatorStatement {

    private Designator Designator;
    private FirstDesignator FirstDesignator;

    public DesignatorStatementDerived1 (Designator Designator, FirstDesignator FirstDesignator) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.FirstDesignator=FirstDesignator;
        if(FirstDesignator!=null) FirstDesignator.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public FirstDesignator getFirstDesignator() {
        return FirstDesignator;
    }

    public void setFirstDesignator(FirstDesignator FirstDesignator) {
        this.FirstDesignator=FirstDesignator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(FirstDesignator!=null) FirstDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(FirstDesignator!=null) FirstDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(FirstDesignator!=null) FirstDesignator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementDerived1(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FirstDesignator!=null)
            buffer.append(FirstDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementDerived1]");
        return buffer.toString();
    }
}
