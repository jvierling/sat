package at.skagen.apps.sat.formula.evaluation;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TableauNode {

	private Set<TableauFormula> formulas = new HashSet<TableauFormula>();
	
	private TableauNode parent = null;
	
	private List<TableauNode> children = new LinkedList<TableauNode>();
	
	public TableauNode(Set<TableauFormula> formulas) {
		this.formulas.addAll(formulas);
	}
	
	public TableauNode(TableauFormula formula) {
		this.formulas.add(formula);
	}
	
	public Set<TableauFormula> getFormulas() {
		return formulas;
	}

	public void addChild(TableauNode node) {
		children.add(node);
		node.parent = this;
	}
	
	public List<TableauNode> getChildren() {
		return children;
	}
	
	public boolean isClosed() {
		return formulasContradictKnownFormula() || allChildrenClosed();
	}
	
	private boolean isClosedBy(TableauFormula formula) {
		
		if (formula.isContradictory()) {
			return true;
		}
		for (TableauFormula otherFormula : formulas) {
			if (formula.isContradictory(otherFormula)) {
				return true;
			}
		}
		if (parent != null && parent.isClosedBy(formula)) {
			return true;
		}
		
		return false;
	}
	
	private boolean formulasContradictKnownFormula() {
		for (TableauFormula formula : formulas) {
			if (isClosedBy(formula)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean allChildrenClosed() {
		for (TableauNode child : children) {
			if (!child.isClosed()) {
				return false;
			}
		}
		
		return false;
	}
	
	
//	private final int PADDING = 1;
//	
//	private final char HORIZONTAL_SEPARATOR = '-';
//	
//	private final char VERTICAL_SEPARATOR = '\'';
//	
//	public char[][] renderTableau() {
//		
//		List<String> formulas = renderFormulas();
//		List<char[][]> children = renderChildren();
//		
//		int height = computeHeight(children);
//		int width  = computeWidth(formulas, children);
//		
//		char[][] surface = newSurface(width, height);
//		
//		drawFormulas(surface, formulas);
//		
//		if (children.size() == 0) {
//			drawLabel(surface, isClosed() ? "closed" : "unclosed");
//		} else {
//			drawChildren(surface, children);
//		}
//		
//		return surface;
//		
//	}
//	
//	private void drawLabel(char[][] surface, String label) {
//		for (int i = 0; i < label.length(); i++) {
//			surface[formulas.size() + 1][i] = label.charAt(i);
//		}
//	}
//	
//	private int computeWidth(List<String> formulas, List<char[][]> children) {
//		return Math.max(maximumFormulaWidth(formulas), sumChildWidth(children) + children.size() - 1)+ (2 * PADDING);
//	}
//	
//	private int computeHeight(List<char[][]> children) {
//		return formulas.size() + 1 + (2 * PADDING) + maximumChildHeight(children);
//	}
//	
//	private int maximumChildHeight(List<char[][]> children) {
//		
//		int maxHeight = 0;
//		
//		for (char[][] child : children) {
//			maxHeight = (maxHeight < child.length) ? child.length : maxHeight;
//		}
//		
//		return maxHeight;
//	}
//	
//	private int sumChildWidth(List<char[][]> children) {
//		
//		int sum = 0;
//		
//		for (char[][] child : children) {
//			sum += child[0].length;
//		}
//		
//		return sum;
//	}
//	
//	private int maximumFormulaWidth(List<String> formulas) {
//		
//		int maxWidth = 0;
//		
//		for (String formula : formulas) {
//			maxWidth = (maxWidth < formula.length()) ? formula.length() : maxWidth; 
//		}
//		
//		return maxWidth;
//	}
//	
//	private void drawHorizontalSeparator(char[][] surface, int y) {
//		for (int i = 0; i < surface[0].length; i++) {
//			surface[y][i] = HORIZONTAL_SEPARATOR;
//		}
//	}
//	
//	private void drawVerticalSeparator(char[][] surface, int minHeight, int x) {
//		for (int i = surface.length - 1; i >= minHeight; i--) {
//			surface[i][x] = VERTICAL_SEPARATOR;
//		}
//	}
//	
//	private void drawChild(char[][] surface, char[][] child, int x, int y) {
//		for (int i = x; i < child[0].length + x; i++) {
//			for (int j = y; j < child.length + y; j++) {
//				surface[j][i] = child[j - y][i - x];
//			}
//		}
//	}
//	
//	private void drawChildren(char[][] surface, List<char[][]> children) {
//		
//		int height = surface.length - maximumChildHeight(children);
//		
//		int offset = 0;
//		
//		for (int i = 0; i < children.size(); i++) {
//			drawChild(surface, children.get(i), offset, height);
//			offset += children.get(i)[0].length;
//			if (i < children.size() - 1) {
//				drawVerticalSeparator(surface, height, offset);
//				offset += 1;
//			}
//		}
//		drawHorizontalSeparator(surface, height - 1);
//		
//	}
//	
//	private void drawFormula(char[][] surface, String formula, int x, int y) {
//		for (int i = 0; i < formula.length(); i++) {
//			surface[y][i + x] = formula.charAt(i);
//		}
//	}
//	
//	private void drawFormulas(char[][] surface, List<String> formulas) {
//
//		int x = (surface[0].length - maximumFormulaWidth(formulas)) / 2;
//		
//		for (int j = 0; j < formulas.size(); j++) {
//			drawFormula(surface, formulas.get(j), x, PADDING + j);
//		}
//	}
//	
//	private List<String> renderFormulas() {
//		
//		List<String> renderedFormulas = new LinkedList<String>();
//		
//		for (TableauFormula formula : formulas) {
//			renderedFormulas.add((formula.getValue() ? "1" : "0") + " : " + formula.getFormula());
//		}
//		
//		return renderedFormulas;
//	}
//	
//	private List<char[][]> renderChildren() {
//		
//		List<char[][]> renderedChildren = new LinkedList<char[][]>();
//		
//		for (TableauNode child : children) {
//			renderedChildren.add(child.renderTableau());
//		}
//		
//		return renderedChildren;
//	}
}
