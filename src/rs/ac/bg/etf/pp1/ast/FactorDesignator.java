// generated with ast extension for cup
// version 0.8
// 4/1/2024 18:13:15


package rs.ac.bg.etf.pp1.ast;

public class FactorDesignator extends Factor {

    private Designator Designator;
    private FactorDesignatorActParsList FactorDesignatorActParsList;

    public FactorDesignator (Designator Designator, FactorDesignatorActParsList FactorDesignatorActParsList) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.FactorDesignatorActParsList=FactorDesignatorActParsList;
        if(FactorDesignatorActParsList!=null) FactorDesignatorActParsList.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public FactorDesignatorActParsList getFactorDesignatorActParsList() {
        return FactorDesignatorActParsList;
    }

    public void setFactorDesignatorActParsList(FactorDesignatorActParsList FactorDesignatorActParsList) {
        this.FactorDesignatorActParsList=FactorDesignatorActParsList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(FactorDesignatorActParsList!=null) FactorDesignatorActParsList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(FactorDesignatorActParsList!=null) FactorDesignatorActParsList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(FactorDesignatorActParsList!=null) FactorDesignatorActParsList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorDesignator(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactorDesignatorActParsList!=null)
            buffer.append(FactorDesignatorActParsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorDesignator]");
        return buffer.toString();
    }
}
