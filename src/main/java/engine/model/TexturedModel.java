package engine.model;


public class TexturedModel {

	private RawModel rawModel;
	private int texture;
	
	public TexturedModel(RawModel rawModel, String path) {
		this.rawModel = rawModel;
		this.texture = ContentLoader.loadTexture(path);
	}

	public RawModel getRawModel() {
		return rawModel;
	}

	public int getTexture() {
		return texture;
	}	
	
}
