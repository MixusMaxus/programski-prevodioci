// generated with ast extension for cup
// version 0.8
// 4/1/2024 18:13:15


package rs.ac.bg.etf.pp1.ast;

public class NoElseStatement extends StatementElseStatement {

    public NoElseStatement () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoElseStatement(\n");

        buffer.append(tab);
        buffer.append(") [NoElseStatement]");
        return buffer.toString();
    }
}
