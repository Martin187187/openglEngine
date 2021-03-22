package engine.model;

import java.util.LinkedList;
import java.util.List;

import tools.vector.Vector2f;

public class MarchingSquares {

	private float[] vArray;
	private int[] iArray;
	
	public MarchingSquares(int[][] grid, int iso) {
		createArray(grid, iso);
	}

	private void createArray(int[][] grid, int iso) {

		int lengthX = grid.length;
		int lengthY = grid[0].length;

		float stepX = (float)4 / lengthX;
		float stepY = (float)4 / lengthY;

		float startX = (float) (lengthX) / 2;
		float startY = (float) (lengthY) / 2;

		List<Vector2f> vertecies = new LinkedList<>();
		List<Integer> indecies = new LinkedList<>();

		for (int i = -1; i < lengthX; i++) {
			for (int j = -1; j < lengthY; j++) {
				int v00 = checkBound(i, j, lengthX, lengthY) ? 0 : grid[i][j];
				int v01 = checkBound(i, j + 1, lengthX, lengthY) ? 0 : grid[i][j + 1];
				int v10 = checkBound(i + 1, j, lengthX, lengthY) ? 0 : grid[i + 1][j];
				int v11 = checkBound(i + 1, j + 1, lengthX, lengthY) ? 0 : grid[i + 1][j + 1];

				Square sq = new Square(v00, v01, v10, v11, iso);

				List<Vector2f> localVertecies = sq.getVertecies();
				List<Integer> localIndecies = sq.getIndecies();
				
				List<Vector2f> editLocalVertecies = new LinkedList<>();
				for (Vector2f v : localVertecies) {
					Vector2f x = new Vector2f(v.x * (stepX / 2) - startX + (j + 1) * stepX,
							v.y * (stepY / 2) + startY - (i + 1) * stepY);
					System.out.println(x);
					editLocalVertecies.add(x);
					if (!vertecies.contains(x)) {
						vertecies.add(x);
					}
				}

				localIndecies.forEach(x -> indecies.add(vertecies.indexOf(editLocalVertecies.get(x))));

			}
		}

		vArray = new float[vertecies.size() * 2];
		for (int i = 0; i < vertecies.size(); i++) {
			Vector2f v = vertecies.get(i);
			vArray[i * 2] = v.x;
			vArray[i * 2 + 1] = v.y;
		}
		iArray = new int[indecies.size()];
		for (int i = 0; i < indecies.size(); i++) {
			iArray[i] = indecies.get(i);
		}
	}

	private Boolean checkBound(int x, int y, int lengthX, int lengthY) {
		return x < 0 || x >= lengthX || y < 0 || y >= lengthY;
	}

	public float[] getvArray() {
		return vArray;
	}

	public int[] getiArray() {
		return iArray;
	}

}
