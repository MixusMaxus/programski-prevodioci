// generated with ast extension for cup
// version 0.8
// 25/0/2024 18:36:53


package src.rs.ac.bg.etf.pp1.ast;

public class InsideForDerived1 extends InsideFor {

    private ForDesignatorStatement ForDesignatorStatement;
    private ForCondFact ForCondFact;
    private ForDesignatorStatement ForDesignatorStatement1;

    public InsideForDerived1 (ForDesignatorStatement ForDesignatorStatement, ForCondFact ForCondFact, ForDesignatorStatement ForDesignatorStatement1) {
        this.ForDesignatorStatement=ForDesignatorStatement;
        if(ForDesignatorStatement!=null) ForDesignatorStatement.setParent(this);
        this.ForCondFact=ForCondFact;
        if(ForCondFact!=null) ForCondFact.setParent(this);
        this.ForDesignatorStatement1=ForDesignatorStatement1;
        if(ForDesignatorStatement1!=null) ForDesignatorStatement1.setParent(this);
    }

    public ForDesignatorStatement getForDesignatorStatement() {
        return ForDesignatorStatement;
    }

    public void setForDesignatorStatement(ForDesignatorStatement ForDesignatorStatement) {
        this.ForDesignatorStatement=ForDesignatorStatement;
    }

    public ForCondFact getForCondFact() {
        return ForCondFact;
    }

    public void setForCondFact(ForCondFact ForCondFact) {
        this.ForCondFact=ForCondFact;
    }

    public ForDesignatorStatement getForDesignatorStatement1() {
        return ForDesignatorStatement1;
    }

    public void setForDesignatorStatement1(ForDesignatorStatement ForDesignatorStatement1) {
        this.ForDesignatorStatement1=ForDesignatorStatement1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForDesignatorStatement!=null) ForDesignatorStatement.accept(visitor);
        if(ForCondFact!=null) ForCondFact.accept(visitor);
        if(ForDesignatorStatement1!=null) ForDesignatorStatement1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForDesignatorStatement!=null) ForDesignatorStatement.traverseTopDown(visitor);
        if(ForCondFact!=null) ForCondFact.traverseTopDown(visitor);
        if(ForDesignatorStatement1!=null) ForDesignatorStatement1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForDesignatorStatement!=null) ForDesignatorStatement.traverseBottomUp(visitor);
        if(ForCondFact!=null) ForCondFact.traverseBottomUp(visitor);
        if(ForDesignatorStatement1!=null) ForDesignatorStatement1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InsideForDerived1(\n");

        if(ForDesignatorStatement!=null)
            buffer.append(ForDesignatorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCondFact!=null)
            buffer.append(ForCondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForDesignatorStatement1!=null)
            buffer.append(ForDesignatorStatement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [InsideForDerived1]");
        return buffer.toString();
    }
}
