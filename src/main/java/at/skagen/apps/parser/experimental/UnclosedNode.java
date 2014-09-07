package at.skagen.apps.parser.experimental;

import java.util.List;
import java.util.Set;

public class UnclosedNode implements TableauNode {

	public Set<TableauFormula> getFormulas() {
		return null;
	}

	public void addChild(TableauNode node) {
	}

	public void addChildren(List<TableauNode> evaluateTableau) {
		// TODO Auto-generated method stub
		
	}

	public List<TableauNode> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	public char[][] renderTableau() {
		
		String label = "unclosed";
		
		char[][] surface = newSurface(label.length() + 2, 3);
		
		for (int i = 0; i < label.length(); i++) {
			surface[1][i + 1] = label.charAt(i);
		}
		
		return surface;
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
