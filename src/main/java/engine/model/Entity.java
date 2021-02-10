package engine.model;

import tools.vector.Vector3f;

public class Entity extends EmptyEntity {

	protected TexturedModel model;
	public Entity(TexturedModel model, Vector3f postition, Vector3f rotation, Vector3f scale) {
		super(postition, rotation, scale);
		this.model = model;
	}
	public TexturedModel getModel() {
		return model;
	}

	
}
