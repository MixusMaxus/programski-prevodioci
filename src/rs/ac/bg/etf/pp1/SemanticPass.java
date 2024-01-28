package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;

public class SemanticPass extends VisitorAdaptor {
	
	int printCallCount = 0;
	int varDeclCount = 0;
	
	Logger log = Logger.getLogger(getClass());

	public void visit(VariableName Variable) { 
		varDeclCount++;
	}
    /*public void visit(Variables Variables) { 
    	varDeclCount++;
    }*/
	
    public void visit(StmtPrintBasic print) {
		printCallCount++;
		log.info("Nadjen print");
	}

    int formalParametersCount=0;
}
