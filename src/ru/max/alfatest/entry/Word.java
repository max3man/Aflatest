package ru.max.alfatest.entry;


public class Word {

	private String txt;
	private char firstChar;
	private char lastChar;
	private char[] chars;
		
	public Word(String word) {
		this.txt = word;
		char[] charArray = word.toCharArray();
		setFirstChar(charArray[0]);
		setLastChar(charArray[charArray.length - 1]);
		setChars(charArray);
	}
	
	
	public String getText() {
		return txt;
	}
		
	public char getFirstChar() {
		return firstChar;
	}
	
	private void setFirstChar(char firstChar) {
		this.firstChar = firstChar;
	}
	
	private void setChars(char[] chars) {
		this.chars = chars;
	}
	
	
	public char getLastChar() {
		return lastChar;
	}
	
	private void setLastChar(char lastChar) {
		this.lastChar = lastChar;
	}
	
	
	public boolean isLeft(Word word){
		return firstChar == word.getLastChar() && almostEqual(word);
	}
	
	public boolean isRight(Word word){
		return lastChar == word.getFirstChar() && almostEqual(word);
	}


	public char[] getChars() {
		return chars;
	}
	
	boolean almostEqual(Word word) {
		int count = 0;
		char[] chars1 = getChars();
		char[] chars2 = word.getChars();
		for(int i = 0; i < chars1.length; i++){
			if(chars1[i] != chars2[i]){
				count++;
			}
		}
		return count < 2;
	}
	
	@Override
	public String toString() {
		return txt;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this){
			return true;
		}
		
		if(obj != null && obj.getClass().equals(getClass())){
			Word w = (Word) obj;
			if(this.getText().equals(w.getText())){
				return true;
			}			
		}
		return super.equals(obj);
	}
}
