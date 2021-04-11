package engine.entity;

import engine.EngineSingleton;
import engine.marching.ExtractHandler;
import engine.model.Mesh;
import tools.vector.Vector3f;

public class Terrain extends DynamicEntity {

	public Terrain(Vector3f postition, Vector3f rotation, Vector3f scale) {
		super(postition, rotation, scale);

	}

	@Override
	public void createGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				for (int k = 0; k < grid[i][j].length; k++) {

					if (i == 0 || j == 0 || k == 0 || i == grid.length - 1 || j == grid[i].length - 1
							|| k == grid[i][j].length - 1)
						grid[i][j][k] = 0;
					else
						grid[i][j][k] = 1;

				}
			}
		}

	}

	public void zeroGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				for (int k = 0; k < grid[i][j].length; k++) {
					grid[i][j][k] = 0;
				}
			}
		}

	}

	public void updateMesh(Terrain[][][] terrain, int[] terrainSize) {
		int maxX = SIZE[0] - 1;
		int maxY = SIZE[1] - 1;
		int maxZ = SIZE[2] - 1;
		// TOP
		if (position.y < terrainSize[1] - 1) {
			Terrain terrainTOP = terrain[(int) position.x][(int) position.y + 1][(int) position.z];

			for (int i = 1; i < grid.length - 1; i++) {
				for (int j = 1; j < grid[i][maxY].length - 1; j++) {
					boolean b = grid[i][maxY-1][j]==0;
					grid[i][maxY][j] = b ? 0 : terrainTOP.grid[i][maxY - 1][j];
				}
			}

		}

		// BOT
		if (position.y > 0) {
			Terrain terrainBOT = terrain[(int) position.x][(int) position.y - 1][(int) position.z];

			for (int i = 1; i < grid.length - 1; i++) {
				for (int j = 1; j < grid[i][maxY].length - 1; j++) {
					boolean b = grid[i][1][j]==0;
					grid[i][0][j] = b ? 0 : terrainBOT.grid[i][1][j];
				}
			}

		} 

		// WEST
		if (position.x < terrainSize[0] - 1) {
			Terrain terrainWEST = terrain[(int) position.x + 1][(int) position.y][(int) position.z];

			for (int i = 1; i < grid.length - 1; i++) {
				for (int j = 1; j < grid[i][maxZ].length - 1; j++) {
					boolean b = grid[i][j][maxZ-1] == 0;
					grid[i][j][maxZ] = b ? 0 : terrainWEST.grid[i][j][maxZ - 1];
				}
			}

		} 
		// OST
		if (position.x > 0) {
			Terrain terrainOST = terrain[(int) position.x - 1][(int) position.y][(int) position.z];

			for (int i = 1; i < grid.length - 1; i++) {
				for (int j = 1; j < grid[i][0].length - 1; j++) {
					boolean b = grid[i][j][1] == 0;
					grid[i][j][0] = b ? 0 : terrainOST.grid[i][j][1];
				}
			}

		} 

		// NORTH
		if (position.z < terrainSize[2] - 1) {
			Terrain terrainWEST = terrain[(int) position.x][(int) position.y][(int) position.z + 1];

			for (int i = 1; i < grid.length - 1; i++) {
				for (int j = 1; j < grid[maxX][i].length - 1; j++) {
					boolean b = grid[maxX-1][i][j]== 0;
					grid[maxX][i][j] = b ? 0 : terrainWEST.grid[maxX - 1][i][j];
				}
			}

		} 
		// SOUTH
		if (position.z > 0) {
			Terrain terrainWEST = terrain[(int) position.x][(int) position.y][(int) position.z - 1];

			for (int i = 1; i < grid.length - 1; i++) {
				for (int j = 1; j < grid[0][i].length - 1; j++) {
					boolean b = grid[1][i][j]==0;
					grid[0][i][j] = b ? 0 : terrainWEST.grid[1][i][j];
				}
			}

		} 
		Mesh mesh = ExtractHandler.extractHandlerInt(getScalarGrid(), SIZE, VOX_SIZE, 0, 1);
		if(mesh.getNormals().length>0)
			model = EngineSingleton.getLoader().loadToVAO(3, mesh.getVertecies(), mesh.getIndecies());
	}
}
