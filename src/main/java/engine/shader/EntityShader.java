package engine.shader;

import engine.entity.Camera;
import tools.Maths;
import tools.vector.Matrix4f;

public class EntityShader extends ShaderProgram {

	private static final String VERTEX_FILE = "res/shaders/entity/entity_vertex.glsl";
	private static final String FRAGMENT_FILE = "res/shaders/entity/entity_fragment.glsl";

	private int projectionMatrix;
	private int transformationMatrix;
	private int viewMatrix;
	public EntityShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getAllUniformLocations() {
		projectionMatrix = super.getUniformLocation("projectionMatrix");
		transformationMatrix = super.getUniformLocation("transformationMatrix");
		viewMatrix = super.getUniformLocation("viewMatrix");

	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");

	}

	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(projectionMatrix, matrix);
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(transformationMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera) {
		super.loadMatrix(viewMatrix, Maths.createViewMatrix(camera));
	}
}
