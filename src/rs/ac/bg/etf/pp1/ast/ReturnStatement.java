// generated with ast extension for cup
// version 0.8
// 4/1/2024 18:13:15


package rs.ac.bg.etf.pp1.ast;

public class ReturnStatement extends Statement {

    private StatementReturn StatementReturn;

    public ReturnStatement (StatementReturn StatementReturn) {
        this.StatementReturn=StatementReturn;
        if(StatementReturn!=null) StatementReturn.setParent(this);
    }

    public StatementReturn getStatementReturn() {
        return StatementReturn;
    }

    public void setStatementReturn(StatementReturn StatementReturn) {
        this.StatementReturn=StatementReturn;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StatementReturn!=null) StatementReturn.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StatementReturn!=null) StatementReturn.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StatementReturn!=null) StatementReturn.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReturnStatement(\n");

        if(StatementReturn!=null)
            buffer.append(StatementReturn.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReturnStatement]");
        return buffer.toString();
    }
}
