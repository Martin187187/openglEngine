package engine.entity;

import engine.EngineSingleton;
import engine.marching.ExtractHandler;
import engine.model.Mesh;
import engine.model.RawModel;
import tools.vector.Vector3f;

public class DynamicEntity extends EmptyEntity {

	protected int[][][] grid;
	protected final int[] SIZE = { 8, 8, 8 };
	protected final float[] VOX_SIZE = { 1.0f, 1.0f, 1.0f };

	protected RawModel model;
	
	
	public DynamicEntity(Vector3f postition, Vector3f rotation, Vector3f scale) {
		super(postition, rotation, scale);

		grid = new int[SIZE[0]][SIZE[1]][SIZE[2]];
		createGrid();
	}
	
	public int[][][] getGrid() {
		return grid;
	}

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
//		Mesh mesh = ExtractHandler.extractHandlerInt(getScalarGrid(), SIZE, VOX_SIZE, 0, 1);
		Mesh mesh = ExtractHandler.extractHandlerInt(null, null, SIZE, VOX_SIZE, 0, 1);

		model = EngineSingleton.getLoader().loadToVAO(3, mesh.getVertecies(), mesh.getIndecies());
	
	}

	protected int[] getScalarGrid() {

		int[] scalarGrid = new int[SIZE[0] * SIZE[1] * SIZE[2]];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				for (int k = 0; k < grid[i][j].length; k++) {
					scalarGrid[i * grid.length * grid[i].length + j * grid[i].length + k] = grid[i][j][k];
				}
			}
		}
		return scalarGrid;
	}

	public void update() {
//		rotation.y += 0.08f;
	}

	public RawModel getModel() {
		return model;
	}

}
