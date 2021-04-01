package engine.model;

public class Mesh {

	private float[] vertecies;
	private float[] normals;
	private int[] indecies;
	
	public Mesh(float[] vertecies, float[] normals, int[] indecies) {
		this.vertecies = vertecies;
		this.normals = normals;
		this.indecies = indecies;
	}

	public float[] getVertecies() {
		return vertecies;
	}

	public float[] getNormals() {
		return normals;
	}
	
	public int[] getIndecies() {
		return indecies;
	}	
	
}
