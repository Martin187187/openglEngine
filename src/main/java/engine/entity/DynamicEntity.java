package engine.entity;

import engine.model.MarchingSquares;
import engine.model.RawModel;
import tools.vector.Vector3f;

public class DynamicEntity extends EmptyEntity {

	private int[][] grid;
	private RawModel model;
	public DynamicEntity(Vector3f postition, Vector3f rotation, Vector3f scale) {
		super(postition, rotation, scale);
		
		initFullGrid();
		this.model = MarchingSquares.createRawModel(grid, 1);
	}
	
	private void initFullGrid() {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				grid[i][j] = 1;
			}
		}
	}
	
	public void update() {
		//change grid
		this.model = MarchingSquares.createRawModel(grid, 1);
	}
	public RawModel getModel() {
		return model;
	}
	

}
