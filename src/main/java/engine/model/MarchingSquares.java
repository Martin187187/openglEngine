package engine.model;

import tools.vector.Vector2f;

public class MarchingSquares {
	
	public static RawModel createRawModel(int[][] grid, int iso) {
		
		int lengthX = grid.length;
		int lengthY = grid[0].length;

		
		
		for(int i = 0; i< lengthX+1; i++) {
			for(int j = 0; j < lengthY+1; j++) {
				
				Square square = new Square(iso);
				
			}
		}
		return null;
	}
	

	
}
