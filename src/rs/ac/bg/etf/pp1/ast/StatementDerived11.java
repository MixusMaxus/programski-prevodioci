// generated with ast extension for cup
// version 0.8
// 25/0/2024 18:36:53


package src.rs.ac.bg.etf.pp1.ast;

public class StatementDerived11 extends Statement {

    private InsideFor InsideFor;
    private Statement Statement;

    public StatementDerived11 (InsideFor InsideFor, Statement Statement) {
        this.InsideFor=InsideFor;
        if(InsideFor!=null) InsideFor.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public InsideFor getInsideFor() {
        return InsideFor;
    }

    public void setInsideFor(InsideFor InsideFor) {
        this.InsideFor=InsideFor;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(InsideFor!=null) InsideFor.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(InsideFor!=null) InsideFor.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(InsideFor!=null) InsideFor.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementDerived11(\n");

        if(InsideFor!=null)
            buffer.append(InsideFor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementDerived11]");
        return buffer.toString();
    }
}
