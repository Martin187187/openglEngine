package engine.shader;

import tools.vector.Matrix4f;
import tools.vector.Vector3f;

public class FontShader extends ShaderProgram{

	private static final String VERTEX_FILE = "res/shaders/gui/font_vertex.glsl";
	private static final String FRAGMENT_FILE = "res/shaders/gui/font_fragment.glsl";
	
	private int color;
	private int location_transformationMatrix;
	
	public FontShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		color = super.getUniformLocation("color");
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}
	
	public void loadColor(Vector3f color) {
		super.loadVector(this.color, color);
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}
}
