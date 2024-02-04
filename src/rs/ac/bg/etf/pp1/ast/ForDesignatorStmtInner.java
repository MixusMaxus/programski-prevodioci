// generated with ast extension for cup
// version 0.8
// 4/1/2024 18:13:15


package rs.ac.bg.etf.pp1.ast;

public class ForDesignatorStmtInner extends ForDesignatorStatement {

    private InnerForDesignatorStatement InnerForDesignatorStatement;

    public ForDesignatorStmtInner (InnerForDesignatorStatement InnerForDesignatorStatement) {
        this.InnerForDesignatorStatement=InnerForDesignatorStatement;
        if(InnerForDesignatorStatement!=null) InnerForDesignatorStatement.setParent(this);
    }

    public InnerForDesignatorStatement getInnerForDesignatorStatement() {
        return InnerForDesignatorStatement;
    }

    public void setInnerForDesignatorStatement(InnerForDesignatorStatement InnerForDesignatorStatement) {
        this.InnerForDesignatorStatement=InnerForDesignatorStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(InnerForDesignatorStatement!=null) InnerForDesignatorStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(InnerForDesignatorStatement!=null) InnerForDesignatorStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(InnerForDesignatorStatement!=null) InnerForDesignatorStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForDesignatorStmtInner(\n");

        if(InnerForDesignatorStatement!=null)
            buffer.append(InnerForDesignatorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForDesignatorStmtInner]");
        return buffer.toString();
    }
}
