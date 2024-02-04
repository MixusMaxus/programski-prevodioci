// generated with ast extension for cup
// version 0.8
// 4/1/2024 18:13:15


package rs.ac.bg.etf.pp1.ast;

public class MultipleInnerForDesignatorStatementLists extends InnerForDesignatorStatementList {

    private InnerForDesignatorStatementList InnerForDesignatorStatementList;
    private DesignatorStatement DesignatorStatement;

    public MultipleInnerForDesignatorStatementLists (InnerForDesignatorStatementList InnerForDesignatorStatementList, DesignatorStatement DesignatorStatement) {
        this.InnerForDesignatorStatementList=InnerForDesignatorStatementList;
        if(InnerForDesignatorStatementList!=null) InnerForDesignatorStatementList.setParent(this);
        this.DesignatorStatement=DesignatorStatement;
        if(DesignatorStatement!=null) DesignatorStatement.setParent(this);
    }

    public InnerForDesignatorStatementList getInnerForDesignatorStatementList() {
        return InnerForDesignatorStatementList;
    }

    public void setInnerForDesignatorStatementList(InnerForDesignatorStatementList InnerForDesignatorStatementList) {
        this.InnerForDesignatorStatementList=InnerForDesignatorStatementList;
    }

    public DesignatorStatement getDesignatorStatement() {
        return DesignatorStatement;
    }

    public void setDesignatorStatement(DesignatorStatement DesignatorStatement) {
        this.DesignatorStatement=DesignatorStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(InnerForDesignatorStatementList!=null) InnerForDesignatorStatementList.accept(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(InnerForDesignatorStatementList!=null) InnerForDesignatorStatementList.traverseTopDown(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(InnerForDesignatorStatementList!=null) InnerForDesignatorStatementList.traverseBottomUp(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleInnerForDesignatorStatementLists(\n");

        if(InnerForDesignatorStatementList!=null)
            buffer.append(InnerForDesignatorStatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStatement!=null)
            buffer.append(DesignatorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleInnerForDesignatorStatementLists]");
        return buffer.toString();
    }
}
