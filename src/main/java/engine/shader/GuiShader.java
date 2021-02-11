package engine.shader;

import tools.vector.Matrix4f;
import tools.vector.Vector3f;

public class GuiShader extends ShaderProgram {

	private static final String VERTEX_FILE = "res/shaders/gui/gui_vertex.glsl";
	private static final String FRAGMENT_FILE = "res/shaders/gui/gui_fragment.glsl";

	private int transformationMatrix;
	private int color;
	private int pictureModeBoolean;
	private int hoverdBoolean;
	public GuiShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		transformationMatrix = super.getUniformLocation("transformationMatrix");
		color = super.getUniformLocation("color");
		pictureModeBoolean = super.getUniformLocation("isPictureMode");
		hoverdBoolean = super.getUniformLocation("isHovered");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(transformationMatrix, matrix);
	}

	public void loadColor(Vector3f color) {
		super.loadVector(this.color, color);
	}
	
	public void loadPictureModeBoolean(boolean isPictureMode) {
		super.loadBoolean(pictureModeBoolean, isPictureMode);
	}
	
	public void loadHoveredBoolean(boolean isHovered) {
		super.loadBoolean(hoverdBoolean, isHovered);
	}
	
}
