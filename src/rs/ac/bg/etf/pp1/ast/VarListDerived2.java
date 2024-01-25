// generated with ast extension for cup
// version 0.8
// 25/0/2024 18:36:53


package src.rs.ac.bg.etf.pp1.ast;

public class VarListDerived2 extends VarList {

    private Variable Variable;

    public VarListDerived2 (Variable Variable) {
        this.Variable=Variable;
        if(Variable!=null) Variable.setParent(this);
    }

    public Variable getVariable() {
        return Variable;
    }

    public void setVariable(Variable Variable) {
        this.Variable=Variable;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Variable!=null) Variable.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Variable!=null) Variable.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Variable!=null) Variable.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarListDerived2(\n");

        if(Variable!=null)
            buffer.append(Variable.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarListDerived2]");
        return buffer.toString();
    }
}
