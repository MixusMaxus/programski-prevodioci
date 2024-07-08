// generated with ast extension for cup
// version 0.8
// 27/5/2024 22:55:38


package rs.ac.bg.etf.pp1.ast;

public class MultipleFormParamethers extends FormParsList {

    private FormParsList FormParsList;
    private OneParamMethod OneParamMethod;

    public MultipleFormParamethers (FormParsList FormParsList, OneParamMethod OneParamMethod) {
        this.FormParsList=FormParsList;
        if(FormParsList!=null) FormParsList.setParent(this);
        this.OneParamMethod=OneParamMethod;
        if(OneParamMethod!=null) OneParamMethod.setParent(this);
    }

    public FormParsList getFormParsList() {
        return FormParsList;
    }

    public void setFormParsList(FormParsList FormParsList) {
        this.FormParsList=FormParsList;
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
        if(FormParsList!=null) FormParsList.accept(visitor);
        if(OneParamMethod!=null) OneParamMethod.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParsList!=null) FormParsList.traverseTopDown(visitor);
        if(OneParamMethod!=null) OneParamMethod.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParsList!=null) FormParsList.traverseBottomUp(visitor);
        if(OneParamMethod!=null) OneParamMethod.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleFormParamethers(\n");

        if(FormParsList!=null)
            buffer.append(FormParsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OneParamMethod!=null)
            buffer.append(OneParamMethod.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleFormParamethers]");
        return buffer.toString();
    }
}
