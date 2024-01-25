// generated with ast extension for cup
// version 0.8
// 25/0/2024 18:36:53


package src.rs.ac.bg.etf.pp1.ast;

public class TermDerived1 extends Term {

    private Factor Factor;
    private FactorMulopList FactorMulopList;

    public TermDerived1 (Factor Factor, FactorMulopList FactorMulopList) {
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
        this.FactorMulopList=FactorMulopList;
        if(FactorMulopList!=null) FactorMulopList.setParent(this);
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public FactorMulopList getFactorMulopList() {
        return FactorMulopList;
    }

    public void setFactorMulopList(FactorMulopList FactorMulopList) {
        this.FactorMulopList=FactorMulopList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Factor!=null) Factor.accept(visitor);
        if(FactorMulopList!=null) FactorMulopList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
        if(FactorMulopList!=null) FactorMulopList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        if(FactorMulopList!=null) FactorMulopList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TermDerived1(\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactorMulopList!=null)
            buffer.append(FactorMulopList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TermDerived1]");
        return buffer.toString();
    }
}
