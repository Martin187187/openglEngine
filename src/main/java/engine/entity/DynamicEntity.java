package engine.entity;

import engine.EngineSingleton;
import engine.model.MarchingSquares;
import engine.model.RawModel;
import tools.vector.Vector3f;

public class DynamicEntity extends EmptyEntity {

	private int[][] grid;
	private RawModel model;
	public DynamicEntity(Vector3f postition, Vector3f rotation, Vector3f scale) {
		super(postition, rotation, scale);
		
		initFullGrid();
		MarchingSquares m = new MarchingSquares(grid, 1);
		this.model = EngineSingleton.getLoader().loadToVAO(2, m.getvArray(), m.getiArray());
	}
	
	private void initFullGrid() {
		grid = new int[4][4];
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				grid[i][j] = 1;
			}
		}
	}
	
	public void update() {
		//change grid
	}
	public RawModel getModel() {
		return model;
	}
	

}
