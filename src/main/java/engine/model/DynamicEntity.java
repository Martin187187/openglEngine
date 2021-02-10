package engine.model;

import tools.vector.Vector3f;

public class DynamicEntity extends EmptyEntity {

	private RawModel model;
	public DynamicEntity(RawModel model, Vector3f postition, Vector3f rotation, Vector3f scale) {
		super(postition, rotation, scale);
		this.model = model;
		// TODO Auto-generated constructor stub
	}
	public RawModel getModel() {
		return model;
	}
	

}
