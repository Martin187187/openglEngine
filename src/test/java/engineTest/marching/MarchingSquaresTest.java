package engineTest.marching;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import engine.model.MarchingSquares;

public class MarchingSquaresTest {

	@Test
	public void marchingTest() {
		
		int[][] grid = new int[4][4];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = 1;
			}
		}
		
		MarchingSquares m = new MarchingSquares(grid, 1);
		System.out.println(Arrays.toString(m.getiArray()));
	}
}
