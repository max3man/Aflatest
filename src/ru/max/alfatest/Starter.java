package ru.max.alfatest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.List;

import ru.max.alfatest.entry.Dictionary;
import ru.max.alfatest.entry.Purpose;
import ru.max.alfatest.handler.ChainHandler;
import ru.max.alfatest.handler.WordReader;

public class Starter {

	public static void main(String[] args) {
		
		fixEncoding();
		
		try {
			
			String sChainPath = "../";
			String sDictPath = "../";
			
			if(!commandIsValid(args)){
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));				
				System.out.print("Укажите путь к конечному и начальному слову:\n");
				sChainPath = br.readLine();
				System.out.print("Укажите путь к словарю:\n");
				sDictPath = br.readLine();				
			} else {
				sChainPath = args[0];
				sDictPath = args[1];
			}			
			
			Path chainPath = chechPath(sChainPath);
			Path dictPath = chechPath(sDictPath);
			
			Purpose purpose = WordReader.createPurpose(chainPath);
			Dictionary dictionary = WordReader.createDictionary(dictPath, purpose);
			ChainHandler chainHandler = new ChainHandler();
			List<String> handle = chainHandler.handle(purpose, dictionary);
			chainHandler.printChain(handle);
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static Path chechPath(String sPath) throws IOException {
		if(sPath == null || sPath.trim().isEmpty()){
			throw new IOException("Укажите путь к файлу!");
		}
		WordReader.chechPath(sPath);		
		return WordReader.toPath(sPath);
	}
	
	static boolean commandIsValid(String[] args) throws IOException{
		if(args == null || args.length < 2){
			return false;
		}
		for (String string : args) {
			if(string == null || string.trim().isEmpty()){
				return false;
			}
		}
		return true;
	}
	
	static void fixEncoding(){
		String osName = System.getProperty("os.name");
		String cp = null;
		if(osName.startsWith("win") || osName.startsWith("Win")){
			cp = "cp866";
		} else {
			cp = "UTF8";
		}		
		try {
			System.setOut( new PrintStream(System.out, true, cp) );
			System.setErr( new PrintStream(System.out, true, cp) );			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}	

}
