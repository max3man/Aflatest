package ru.max.alfatest.entry;

import java.util.ArrayList;
import java.util.List;

public class ChainLink {

	private List<ChainLink> right = new ArrayList<ChainLink>();

	private Word word;
	private ChainLink left;

	public ChainLink(Word word) {
		this.word = word;
	}

	public ChainLink addWord(Word word) {
		ChainLink link = new ChainLink(word);
		link.setLeft(this);
		right.add(link);
		return link;
	}

	public boolean canAdd(Word word) {
		if (this.word.isRight(word)) {
			return !contains(word);
		}
		return false;
	}

	boolean contains(Word word) {
		return contains(word, this);
	}

	boolean contains(Word word, ChainLink link) {
		if (link != null) {
			if (link.getWord().equals(word)) {
				return true;
			} else {
				return contains(word, link.getLeft());
			}
		}
		return false;
	}

	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
		this.word = word;
	}

	public ChainLink getLeft() {
		return left;
	}

	public void setLeft(ChainLink left) {
		this.left = left;
	}

	@Override
	public String toString() {
		return word.toString();
	}	

	public List<ChainLink> getRight() {
		return right;
	}
}
