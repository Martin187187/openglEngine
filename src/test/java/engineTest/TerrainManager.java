package engineTest;

import java.util.LinkedList;
import java.util.List;

import engine.entity.Terrain;
import tools.vector.Vector3f;

public class TerrainManager {

	private final int[] SIZE = { 10, 2, 10 };
	private Terrain[][][] terrain;

	public TerrainManager() {
		terrain = new Terrain[SIZE[0]][SIZE[1]][SIZE[2]];
		for (int i = 0; i < terrain.length; i++)
			for (int j = 0; j < terrain[i].length; j++)
				for (int k = 0; k < terrain[i][j].length; k++)
					terrain[i][j][k] = new Terrain(new Vector3f(i, j, k), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
		for (int i = 0; i < terrain.length; i++)
			for (int j = 0; j < terrain[i].length; j++)
				for (int k = 0; k < terrain[i][j].length; k++) {
					if (i + j + k == 0)
						terrain[i][j][k].zeroGrid();
					terrain[i][j][k].updateMesh(terrain, SIZE);
				}
	}

	public List<Terrain> getTerrainList() {
		List<Terrain> terrainList = new LinkedList<>();
		for (int i = 0; i < terrain.length; i++)
			for (int j = 0; j < terrain[i].length; j++)
				for (int k = 0; k < terrain[i][j].length; k++)
					if (terrain[i][j][k].getModel() != null)
						terrainList.add(terrain[i][j][k]);

		return terrainList;
	}
}
