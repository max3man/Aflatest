package ru.max.alfatest.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import ru.max.alfatest.entry.Dictionary;
import ru.max.alfatest.entry.Purpose;
import ru.max.alfatest.entry.Word;
import ru.max.alfatest.exception.WordNotFoundException;

public class WordReader {

	public static Dictionary createDictionary(Path file, Purpose purpose) throws Exception {
		int length = purpose.length();
		
		List<String> strings = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(file.toFile()));
		String line;
		while((line = br.readLine())!=null){
			String word = line.trim();
			if(length == word.length()){
				strings.add( word );					
			}
		}
		br.close();
		boolean r1 = strings.remove(purpose.getFirst().getText());
		boolean r2 = strings.remove(purpose.getLast().getText());
		
		if(!(r1 && r2)){
			throw new WordNotFoundException("В словаре не обнаружено начальное и/или конечное слово!");
		}		
		return toDictionary(strings);
	}
	
	public static Purpose createPurpose(Path file) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file.toFile()));
		String first = br.readLine();
		String last = br.readLine();
		if((first == null || last == null) || (first.trim().isEmpty() || last.trim().isEmpty())){
			throw new WordNotFoundException("Не указано начальное и/или конечное слово!");
		} else if(first.trim().length() != last.trim().length()) {
			throw new WordNotFoundException("Начальное и/или конечное слово разной длины!");
		}
		br.close();
		return new Purpose(toWord(first), toWord(last));
	}
	
	static Dictionary toDictionary(List<String> words){		
		List<Word> wds = new ArrayList<Word>();
		for (String string : words) {
			wds.add( toWord(string) );
		}		
		return new Dictionary(wds);
	}	
	
	public static Word toWord(String txt){
		return new Word(txt.trim().toUpperCase());
	}
	
	public static void chechPath(String sPath) throws IOException {
		Path path = toPath(sPath);		
		File f = path.toFile();
		if(!f.exists() || !f.isFile() || !f.canRead()){
			throw new IOException(String.format("Не удалось получить доступ к файлу '%s'", f.getAbsolutePath()));
		}		
	}
	
	public static Path toPath(String sPath){
		Path path = new File(sPath).toPath();
		path = path.normalize();
		if(!path.isAbsolute()){
			path = path.toAbsolutePath();
		}
		return path;
	}
}
