// generated with ast extension for cup
// version 0.8
// 25/0/2024 18:36:53


package src.rs.ac.bg.etf.pp1.ast;

public class InnerForDesignatorStatementListDerived2 extends InnerForDesignatorStatementList {

    public InnerForDesignatorStatementListDerived2 () {
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
        buffer.append("InnerForDesignatorStatementListDerived2(\n");

        buffer.append(tab);
        buffer.append(") [InnerForDesignatorStatementListDerived2]");
        return buffer.toString();
    }
}
