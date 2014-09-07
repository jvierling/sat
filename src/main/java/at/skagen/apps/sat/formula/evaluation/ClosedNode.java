package at.skagen.apps.sat.formula.evaluation;

import java.util.List;
import java.util.Set;

public class ClosedNode implements TableauNode {

	public Set<TableauFormula> getFormulas() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addChild(TableauNode node) {
		// TODO Auto-generated method stub
		
	}

	public void addChildren(List<TableauNode> evaluateTableau) {
		// TODO Auto-generated method stub
		
	}

	public List<TableauNode> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	public char[][] renderTableau() {
		
		String label = "closed";
		
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