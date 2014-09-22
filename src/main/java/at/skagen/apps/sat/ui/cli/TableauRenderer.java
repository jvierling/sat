package at.skagen.apps.sat.ui.cli;

import java.util.LinkedList;
import java.util.List;

import at.skagen.apps.sat.formula.evaluation.tableau.TableauFormula;
import at.skagen.apps.sat.formula.evaluation.tableau.TableauNode;

public class TableauRenderer {

	private final int PADDING = 1;
	
	private final String CLOSED_LABEL = "closed";
	
	private final String UNCLOSED_LABEL = "unclosed";
	
	private final int SEPARATOR_HEIGHT = 1;
	
	private final char HORIZONTAL_SEPARATOR = '-';
	
	private final char VERTICAL_SEPARATOR = '|';
	
	private final char CORNER_SYMBOL = '+';
	
	public String renderTableau(TableauNode root) {
		
		String result = "";
		
		char[][] renderedTableau = renderBorderedTableau(root);
		
		for (int i = 0; i < renderedTableau.length; i++) {
			for (int j = 0; j < renderedTableau[i].length; j++) {
				result += renderedTableau[i][j];
			}
			result += "\n";
		}
		
		return result.substring(0, result.length());
	}
	
	private char[][] renderBorderedTableau(TableauNode root) {
		
		int width  = computeSurfaceWidth(root)  + 2;
		int height = computeSurfaceHeight(root) + 2;
		
		char[][] borderedTableau = newSurface(width + 2, height + 2);
		borderedTableau[0][0] 	  				= CORNER_SYMBOL;
		borderedTableau[height - 1][width - 1]  = CORNER_SYMBOL;
		borderedTableau[height - 1][0] 	  		= CORNER_SYMBOL;
		borderedTableau[0][width - 1] 			= CORNER_SYMBOL;
		
		drawVerticalSeparator(borderedTableau, 0, 1, height - 2);
		drawVerticalSeparator(borderedTableau, width - 1, 1, height - 2);
		
		drawHorizontalSeparator(borderedTableau, 1, 0, width - 2);
		drawHorizontalSeparator(borderedTableau, 1, height - 1, width - 2);
		
		drawNode(root, borderedTableau, 1, 1, width - 2, height - 2);
		
		return borderedTableau;
	}
	
	private void drawNode(TableauNode node, char[][] surface, int x, int y, int width, int height) {
		
		List<String> renderedFormulas = renderFormulas(node);
		
		drawFormulas(renderedFormulas, surface, x + PADDING, y + PADDING);
		drawHorizontalSeparator(surface, x, y + 2 * PADDING + renderedFormulas.size(), width);
		if (node.getChildren().size() > 0) {
			drawChildren(node, surface, x, y + 2 * PADDING + renderedFormulas.size() + 1, width,
					height - (2 * PADDING + renderedFormulas.size() + 1));
		} else {
			drawLeaf(node, surface, x,  y + 2 * PADDING + renderedFormulas.size() + 1, width,
					height - (2 * PADDING + renderedFormulas.size() + 1));
		}
	}
	
	private void drawLeaf(TableauNode node, char[][] surface, int x, int y, int width, int height) {
		
		String label = (node.isClosed()) ? CLOSED_LABEL : UNCLOSED_LABEL;
		
		for (int i = 0; i < label.length(); i++) {
			surface[y + PADDING][x + PADDING + i] = label.charAt(i);
		}
	}
	
	private void drawChildren(TableauNode node, char[][] surface, int x, int y, int width, int height) {
		
		List<TableauNode> children = node.getChildren();
		int offset = 0;
		
		for (int i = 0; i < children.size(); i++) {
			
			int childWidth = computeNodeWidth(children.get(i));
			
			if (i == children.size() - 1 && width - (offset + childWidth) > 0) {
				childWidth += width - (offset + childWidth);
			}
			
			drawNode(children.get(i), surface, x + offset, y, childWidth, height);
			
			offset += childWidth;
			
			if (i < children.size() - 1) {
				drawVerticalSeparator(surface, x + offset, y, height);
				offset += 1;
			}
		}
	}
	
	private void drawVerticalSeparator(char[][] surface, int x, int y, int height) {
		for (int i = 0; i < height; i++) {
			surface[y + i][x] = VERTICAL_SEPARATOR;
		}
	}
	
	private void drawHorizontalSeparator(char[][] surface, int x, int y, int width) {
		for (int i = 0; i < width; i++) {
			surface[y][x + i] = HORIZONTAL_SEPARATOR;
		}
	}
	
	private void drawFormulas(List<String> formulas, char[][] surface, int x, int y) {
		for (int i = 0; i < formulas.size(); i++) {
			drawFormula(formulas.get(i), surface, x, y + i);
		}
	}
	
	private void drawFormula(String formula, char[][] surface, int x, int y) {
		for (int i = 0; i < formula.length(); i++) {
			surface[y][x + i] = formula.charAt(i);
		}
	}
	
	private List<String> renderFormulas(TableauNode node) {
		
		List<String> formulas = new LinkedList<String>();
		
		for (TableauFormula formula : node.getFormulas()) {
			formulas.add(formula.toString());
		}
		
		return formulas;
	}
	
	private int computeSurfaceWidth(TableauNode root) {
		return computeNodeWidth(root);
	}
	
	private int computeSurfaceHeight(TableauNode root) {
		return computeNodeHeight(root);
	}
	
	private int maxChildrenHeight(TableauNode node) {
		
		int maxHeight = 0;
		
		for (TableauNode child : node.getChildren()) {
			maxHeight = maxHeight < computeNodeHeight(child) ? computeNodeHeight(child) : maxHeight;
		}
		
		return maxHeight;
	}
	
	private int maxFormulaWidth(TableauNode node) {
		
		int maxWidth = 0;
		
		for (TableauFormula formula : node.getFormulas()) {
			maxWidth = maxWidth < formula.toString().length() ? formula.toString().length() : maxWidth; 
		}
		
		return maxWidth;
	}
	
	private int sumChildrenWidth(TableauNode node) {
		
		int sumWidth = 0;
		
		for (TableauNode child : node.getChildren()) {
			sumWidth += computeNodeWidth(child);
		}
		
		return sumWidth + (node.getChildren().size() - 1);
	}
	
	private int computeNodeWidth(TableauNode node) {
		return (node.getChildren().size() == 0) ? computeLeafWidth(node) : computeInnerNodeWidth(node);
	}
	
	private int computeLeafWidth(TableauNode node) {
		
		String label = node.isClosed() ? CLOSED_LABEL : UNCLOSED_LABEL;
		
		return Math.max(maxFormulaWidth(node), label.length()) + 2 * PADDING;
	}
	
	private int computeInnerNodeWidth(TableauNode node) {
		return Math.max(maxFormulaWidth(node) + 2 * PADDING, sumChildrenWidth(node));
	}
	
	private int computeNodeHeight(TableauNode node) {
		return (node.getChildren().size() == 0) ? computeLeafHeight(node) : computeInnerNodeHeight(node);
	}
	
	private int computeLeafHeight(TableauNode node) {
		return 2 * PADDING + node.getFormulas().size() + SEPARATOR_HEIGHT + 2 * PADDING + 1;
	}
	
	private int computeInnerNodeHeight(TableauNode node) {
		return 2 * PADDING + node.getFormulas().size() + SEPARATOR_HEIGHT + maxChildrenHeight(node);
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
