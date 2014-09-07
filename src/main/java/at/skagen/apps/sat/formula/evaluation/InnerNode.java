package at.skagen.apps.sat.formula.evaluation;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class InnerNode implements TableauNode {

	private final int PADDING = 1;
	
	private final char HORIZONTAL_SEPARATOR = '-';
	
	private final char VERTICAL_SEPARATOR = '\'';
	
	private Set<TableauFormula> formulas;
	
	private String rule;
	
	private List<TableauNode> children = new LinkedList<TableauNode>();
	
	public InnerNode(Set<TableauFormula> formulas) {
		this(formulas, null);
	}
	
	public InnerNode(TableauFormula formula) {
		formulas = new HashSet<TableauFormula>();
		formulas.add(formula);
	}
	
	public InnerNode(Set<TableauFormula> formulas, String rule) {
		this.formulas = formulas;
		this.rule = rule;
	}
	
	public String getRule() {
		return rule;
	}
	
	public void addChildren(List<TableauNode> children) {
		this.children.addAll(children);
	}
	
	public Set<TableauFormula> getFormulas() {
		return formulas;
	}
	
	public void addChild(TableauNode node) {
		children.add(node);
	}
	
	public List<TableauNode> getChildren() {
		return children;
	}
	
	public char[][] renderTableau() {
		
		List<String> formulas = renderFormulas();
		List<char[][]> children = renderChildren();
		
		int height = computeHeight(children);
		int width  = computeWidth(formulas, children);
		
		char[][] surface = newSurface(width, height);
		
		drawFormulas(surface, formulas);
		drawChildren(surface, children);
		
		return surface;
		
	}
	
	private int computeWidth(List<String> formulas, List<char[][]> children) {
		return Math.max(maximumFormulaWidth(formulas), sumChildWidth(children) + children.size() - 1)+ (2 * PADDING);
	}
	
	private int computeHeight(List<char[][]> children) {
		return formulas.size() + 1 + (2 * PADDING) + maximumChildHeight(children);
	}
	
	private int maximumChildHeight(List<char[][]> children) {
		
		int maxHeight = 0;
		
		for (char[][] child : children) {
			maxHeight = (maxHeight < child.length) ? child.length : maxHeight;
		}
		
		return maxHeight;
	}
	
	private int sumChildWidth(List<char[][]> children) {
		
		int sum = 0;
		
		for (char[][] child : children) {
			sum += child[0].length;
		}
		
		return sum;
	}
	
	private int maximumFormulaWidth(List<String> formulas) {
		
		int maxWidth = 0;
		
		for (String formula : formulas) {
			maxWidth = (maxWidth < formula.length()) ? formula.length() : maxWidth; 
		}
		
		return maxWidth;
	}
	
	private void drawHorizontalSeparator(char[][] surface, int y) {
		for (int i = 0; i < surface[0].length; i++) {
			surface[y][i] = HORIZONTAL_SEPARATOR;
		}
	}
	
	private void drawVerticalSeparator(char[][] surface, int minHeight, int x) {
		for (int i = surface.length - 1; i >= minHeight; i--) {
			surface[i][x] = VERTICAL_SEPARATOR;
		}
	}
	
	private void drawChild(char[][] surface, char[][] child, int x, int y) {
		for (int i = x; i < child[0].length + x; i++) {
			for (int j = y; j < child.length + y; j++) {
				surface[j][i] = child[j - y][i - x];
			}
		}
	}
	
	private void drawChildren(char[][] surface, List<char[][]> children) {
		
		int height = surface.length - maximumChildHeight(children);
		
		int offset = 0;
		
		for (int i = 0; i < children.size(); i++) {
			drawChild(surface, children.get(i), offset, height);
			offset += children.get(i)[0].length;
			if (i < children.size() - 1) {
				drawVerticalSeparator(surface, height, offset);
				offset += 1;
			}
		}
		drawHorizontalSeparator(surface, height - 1);
		
	}
	
	private void drawFormula(char[][] surface, String formula, int x, int y) {
		for (int i = 0; i < formula.length(); i++) {
			surface[y][i + x] = formula.charAt(i);
		}
	}
	
	private void drawFormulas(char[][] surface, List<String> formulas) {

		int x = (surface[0].length - maximumFormulaWidth(formulas)) / 2;
		
		for (int j = 0; j < formulas.size(); j++) {
			drawFormula(surface, formulas.get(j), x, PADDING + j);
		}
	}
	
	private List<String> renderFormulas() {
		
		List<String> renderedFormulas = new LinkedList<String>();
		
		for (TableauFormula formula : formulas) {
			renderedFormulas.add((formula.getValue() ? "1" : "0") + " : " + formula.getFormula());
		}
		
		return renderedFormulas;
	}
	
	private List<char[][]> renderChildren() {
		
		List<char[][]> renderedChildren = new LinkedList<char[][]>();
		
		for (TableauNode child : children) {
			renderedChildren.add(child.renderTableau());
		}
		
		return renderedChildren;
	}
	
	private char[][] newSurface(int width, int height) {
		char[][] surface = new char[height][width];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				surface[j][i] = ' ';
			}
		}
		
		return surface;
	}
}
