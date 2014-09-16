package at.skagen.apps.sat.formula.pattern;

import java.util.LinkedList;
import java.util.List;

public class Pattern<T> {

	private T value;
	
	private List<Pattern<T>> children = new LinkedList<Pattern<T>>();
	
	public Pattern(T value) {
		this.value = value;
	}
	
	public void addChild(Pattern<T> child) {
		children.add(child);
	}
	
	public T getValue() {
		return value;
	}
	
	public List<Pattern<T>> getChildren() {
		return children;
	}
}
