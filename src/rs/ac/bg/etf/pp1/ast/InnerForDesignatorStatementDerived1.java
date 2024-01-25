// generated with ast extension for cup
// version 0.8
// 25/0/2024 18:36:53


package src.rs.ac.bg.etf.pp1.ast;

public class InnerForDesignatorStatementDerived1 extends InnerForDesignatorStatement {

    private DesignatorStatement DesignatorStatement;
    private InnerForDesignatorStatementList InnerForDesignatorStatementList;

    public InnerForDesignatorStatementDerived1 (DesignatorStatement DesignatorStatement, InnerForDesignatorStatementList InnerForDesignatorStatementList) {
        this.DesignatorStatement=DesignatorStatement;
        if(DesignatorStatement!=null) DesignatorStatement.setParent(this);
        this.InnerForDesignatorStatementList=InnerForDesignatorStatementList;
        if(InnerForDesignatorStatementList!=null) InnerForDesignatorStatementList.setParent(this);
    }

    public DesignatorStatement getDesignatorStatement() {
        return DesignatorStatement;
    }

    public void setDesignatorStatement(DesignatorStatement DesignatorStatement) {
        this.DesignatorStatement=DesignatorStatement;
    }

    public InnerForDesignatorStatementList getInnerForDesignatorStatementList() {
        return InnerForDesignatorStatementList;
    }

    public void setInnerForDesignatorStatementList(InnerForDesignatorStatementList InnerForDesignatorStatementList) {
        this.InnerForDesignatorStatementList=InnerForDesignatorStatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStatement!=null) DesignatorStatement.accept(visitor);
        if(InnerForDesignatorStatementList!=null) InnerForDesignatorStatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.traverseTopDown(visitor);
        if(InnerForDesignatorStatementList!=null) InnerForDesignatorStatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatement!=null) DesignatorStatement.traverseBottomUp(visitor);
        if(InnerForDesignatorStatementList!=null) InnerForDesignatorStatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InnerForDesignatorStatementDerived1(\n");

        if(DesignatorStatement!=null)
            buffer.append(DesignatorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(InnerForDesignatorStatementList!=null)
            buffer.append(InnerForDesignatorStatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [InnerForDesignatorStatementDerived1]");
        return buffer.toString();
    }
}
