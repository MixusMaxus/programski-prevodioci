// generated with ast extension for cup
// version 0.8
// 4/1/2024 18:13:15


package rs.ac.bg.etf.pp1.ast;

public class OneFormParamether extends FormParsList {

    private OneParamMethod OneParamMethod;

    public OneFormParamether (OneParamMethod OneParamMethod) {
        this.OneParamMethod=OneParamMethod;
        if(OneParamMethod!=null) OneParamMethod.setParent(this);
    }

    public OneParamMethod getOneParamMethod() {
        return OneParamMethod;
    }

    public void setOneParamMethod(OneParamMethod OneParamMethod) {
        this.OneParamMethod=OneParamMethod;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OneParamMethod!=null) OneParamMethod.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OneParamMethod!=null) OneParamMethod.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OneParamMethod!=null) OneParamMethod.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OneFormParamether(\n");

        if(OneParamMethod!=null)
            buffer.append(OneParamMethod.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneFormParamether]");
        return buffer.toString();
    }
}
