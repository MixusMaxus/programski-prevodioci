// generated with ast extension for cup
// version 0.8
// 25/0/2024 18:36:53


package src.rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementDerived2 extends DesignatorStatement {

    private SecondDesignator SecondDesignator;
    private Designator Designator;

    public DesignatorStatementDerived2 (SecondDesignator SecondDesignator, Designator Designator) {
        this.SecondDesignator=SecondDesignator;
        if(SecondDesignator!=null) SecondDesignator.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
    }

    public SecondDesignator getSecondDesignator() {
        return SecondDesignator;
    }

    public void setSecondDesignator(SecondDesignator SecondDesignator) {
        this.SecondDesignator=SecondDesignator;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SecondDesignator!=null) SecondDesignator.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SecondDesignator!=null) SecondDesignator.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SecondDesignator!=null) SecondDesignator.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementDerived2(\n");

        if(SecondDesignator!=null)
            buffer.append(SecondDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementDerived2]");
        return buffer.toString();
    }
}
