package ru.max.alfatest.entry;

public class Purpose {

	private Word first;
	private Word last;
	
	public Purpose(Word first, Word last) {
		this.first = first;
		this.last = last;
	}
	
	public Word getFirst() {
		return first;
	}
	
	public Word getLast() {
		return last;
	}
	
	public int length(){
		return getFirst().getText().length();
	}
}
