package engine.shader;

import engine.entity.Camera;
import tools.Maths;
import tools.vector.Matrix4f;
import tools.vector.Vector3f;

public class TerrainShader extends ShaderProgram{

	private static final String VERTEX_FILE = "res/shaders/entity/vertexShader.glsl";
	private static final String FRAGMENT_FILE = "res/shaders/entity/fragmentShader.glsl";


	private int projectionMatrix;
	private int transformationMatrix;
	private int viewMatrix;

	private int lightPosition;
	private int lightColour;
	
	private int reflectivity;
	private int shineDamper;
	
	public TerrainShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		projectionMatrix = super.getUniformLocation("projectionMatrix");
		transformationMatrix = super.getUniformLocation("transformationMatrix");
		viewMatrix = super.getUniformLocation("viewMatrix");
		lightPosition = super.getUniformLocation("lightPosition");
		lightColour = super.getUniformLocation("lightColour");
		reflectivity = super.getUniformLocation("reflectivity");
		shineDamper = super.getUniformLocation("shineDamper");

	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "normal");
	}
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(projectionMatrix, matrix);
	}
	
	public void loadTranslationVector(Matrix4f matrix) {
		super.loadMatrix(transformationMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera) {
		super.loadMatrix(viewMatrix, Maths.createViewMatrix(camera));
	}
	
	public void loadLightPositionVector(Vector3f position) {
		super.loadVector(lightPosition, position);
	}
	
	public void loadLightColourVector(Vector3f colour) {
		super.loadVector(lightColour, colour);
	}
	
	public void loadReflectivityFloat(float value) {
		super.loadFloat(reflectivity, value);
	}
	
	public void loadShineDamperFloat(float value) {
		super.loadFloat(shineDamper, value);
	}
}
