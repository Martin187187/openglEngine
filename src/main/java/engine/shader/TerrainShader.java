package engine.shader;

import engine.entity.Camera;
import tools.Maths;
import tools.vector.Matrix4f;
import tools.vector.Vector3f;

public class TerrainShader extends ShaderProgram{

	private static final String VERTEX_FILE = "res/shaders/entity/terrain_vertex.glsl";
	private static final String FRAGMENT_FILE = "res/shaders/entity/terrain_fragment.glsl";


	private int projectionMatrix;
	private int translationVector;
	private int viewMatrix;
	
	public TerrainShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		projectionMatrix = super.getUniformLocation("projectionMatrix");
		translationVector = super.getUniformLocation("translationVector");
		viewMatrix = super.getUniformLocation("viewMatrix");

	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(projectionMatrix, matrix);
	}
	
	public void loadTranslationVector(Vector3f translation) {
		super.loadVector(translationVector, translation);
	}
	
	public void loadViewMatrix(Camera camera) {
		super.loadMatrix(viewMatrix, Maths.createViewMatrix(camera));
	}
}
