package ru.max.alfatest.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.max.alfatest.entry.ChainLink;
import ru.max.alfatest.entry.Dictionary;
import ru.max.alfatest.entry.Purpose;
import ru.max.alfatest.entry.Word;

public class ChainHandler {

	private List<ChainLink> links = new ArrayList<ChainLink>();
	private List<Word> strs = new ArrayList<Word>();
	private Purpose purpose;

	public List<String> handle(Purpose purpose, Dictionary dictionary) {
		this.strs = dictionary.getWords();
		this.purpose = purpose;
		ChainLink firstLink = new ChainLink(purpose.getFirst());
		fillChain(firstLink);
		processed(firstLink);
		return getMinChain();
	}
	
	public void printChain(List<String> strings){
		for (String string : strings) {
			System.out.println(string);
		}
	}
	
	List<String> getMinChain(){
		if(!links.isEmpty()){			
			Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
			for (ChainLink l : links) {
				List<String> path = getFullPath(l);
				map.put(path.size(), path);
			}
			Integer min = Collections.min( map.keySet() );
			List<String> list = map.get(min);
			list.add(purpose.getLast().getText());
		return list;
		} else {
			System.out.println("Цепочка не может быть построена...");
			return Collections.emptyList();
		}
	}

	ChainLink fillChain(ChainLink cl) {
		if (isFinalLink(cl))
			return cl;
		for (Word w : strs) {
			if (cl.canAdd(w)) {
				fillChain(cl.addWord(w));
			}
		}
		return cl;
	}

	void processed(ChainLink build) {
		List<ChainLink> right = build.getRight();
		if (!right.isEmpty()) {
			for (ChainLink chainLink : right) {
				processed(chainLink);
			}
		} else if (isFinalLink(build)) {
			links.add(build);
		}
	}

	boolean isFinalLink(ChainLink cl) {
		return purpose.getLast().isLeft(cl.getWord());
	}

	List<String> getFullPath(ChainLink link) {
		List<String> l = new ArrayList<String>();
		l.add(link.toString());
		ChainLink ch = link;
		while ((ch = ch.getLeft()) != null) {
			l.add(ch.getWord().toString());
		}
		Collections.reverse(l);
		return l;
	}
}
