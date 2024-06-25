package rs.ac.bg.etf.pp1;

import java.io.*;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;

public class Compiler {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		Logger log = Logger.getLogger(Compiler.class);
		
		Reader buffReader = null;
		try {

			if(args.length < 2) {
				log.error("Potrebno je uneti naziv ulaznog file-a");
				return;
			}
			
			File sourceCode = new File(args[0]);  
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			buffReader = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(buffReader);
			
			//Sintaksna analiza
			MJParser parser = new MJParser(lexer);
	        Symbol symbol = parser.parse();
	        
	        Program prog = (Program)(symbol.value); 
	        Tab.init();
			// ispis sintaksnog stabla
			log.info(prog.toString(""));
			log.info("===================================");

			// ispis prepoznatih programskih konstrukcija
			SemanticAnalyzer sv = new SemanticAnalyzer();
			prog.traverseBottomUp(sv); 
			
			log.info("===================================");
			
			Tab.dump();
			
			if (!parser.errorDetected && sv.passed()) {
				// Generisemo kod
				File objFile = new File(args[1]);
				if(objFile.exists()) objFile.delete();
				
				CodeGenerator codeGen = new CodeGenerator();
				prog.traverseBottomUp(codeGen);
				Code.dataSize = sv.nVars;
				Code.mainPc = codeGen.getMainPC();
				Code.write(new FileOutputStream(objFile));
				log.info("Parsiranje koda je uspesno");
			}
			else {
				log.error("Parsiranje koda je neuspesno");
			}
		} 
		finally {
			if (buffReader != null) try { buffReader.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}

	}

}
