package ru.max.alfatest.entry;

import java.util.List;

public class Dictionary {

	private List<Word> words;

	public Dictionary(List<Word> words) {
		super();
		this.words = words;
	}

	public List<Word> getWords() {
		return words;
	}	
}
