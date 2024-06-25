package rs.ac.bg.etf.pp1;

import java.io.BufferedReader; 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
/*import rs.etf.pp1.symboltable.Tab;*/

public class MJParserTest {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception {
		
		Logger log = Logger.getLogger(MJParserTest.class);
		
		Reader br = null;
		try {
			File sourceCode = new File("test/mojTest.mj");  //testovi/test1sveprovere.mj
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja
			//Symbol s= p.debug_parse();
	        
	        Program prog = (Program)(s.value);
	        Tab.init();
			// ispis sintaksnog stabla
			log.info(prog.toString(""));
			log.info("===================================");

			// ispis prepoznatih programskih konstrukcija
			SemanticAnalyzer sv = new SemanticAnalyzer();
//			RuleVisitor v = new RuleVisitor();
			prog.traverseBottomUp(sv); 
	      
//			log.info(" Print count calls = " + sv.printCallCount);
//			
//			log.info(" Print variable decls = " + sv.varDeclCount);

			//log.info(" Global declarations count = " + v.varDeclCount);
			
			//	log.info(" Formal parameters count = " + v.formalParametersCount);
			
			Tab.dump();
			if(!p.errorDetected && sv.passed()){
				File objFile = new File("test/program.obj");
				if(objFile.exists()) 
					objFile.delete();
				CodeGenerator codeGenerator = new CodeGenerator();
				prog.traverseBottomUp(codeGenerator);
				Code.dataSize = sv.nVars;
				Code.mainPc = codeGenerator.getMainPC();
				Code.write(new FileOutputStream(objFile));
				log.info("Parsiranje uspesno zavrseno!");
			}else{
				log.error("Parsiranje NIJE uspesno zavrseno!");
			}
		}
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}

	}
}
