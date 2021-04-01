package engine.entity;

import java.io.File;

import engine.EngineSingleton;
import engine.marching.ExtractHandler;
import engine.model.Mesh;
import engine.model.RawModel;
import tools.vector.Vector3f;

public class DynamicEntity extends EmptyEntity {

	private int[][] grid;
	private RawModel model;
	public DynamicEntity(Vector3f postition, Vector3f rotation, Vector3f scale) {
		super(postition, rotation, scale);
		int[] size = {64, 64, 64};
        float[] voxSize = {1.0f, 1.0f, 1.0f};
        
        int nThreadsMin = java.lang.Thread.activeCount();
        if (nThreadsMin == 0) {
            nThreadsMin = 1;
        }
        int nThreadsMax = nThreadsMin;
        
		Mesh mesh = ExtractHandler.extractHandlerInt(null, new File("out.txt"), size, voxSize, 0, nThreadsMax);
		for(int i = 0; i < mesh.getNormals().length; i++) {
			System.out.println(mesh.getNormals()[i]);
		}
		model = EngineSingleton.getLoader().loadToVAO(3, mesh.getVertecies(), mesh.getIndecies());
	}
	

	
	public void update() {
		rotation.y+=0.08f;
	}
	public RawModel getModel() {
		return model;
	}
	

}
