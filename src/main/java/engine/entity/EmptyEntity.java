package engine.entity;

import tools.vector.Vector3f;

public class EmptyEntity {

	protected Vector3f position, rotation, scale;

	public EmptyEntity(Vector3f postition, Vector3f rotation, Vector3f scale) {
		this.position = postition;
		this.rotation = rotation;
		this.scale = scale;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public Vector3f getScale() {
		return scale;
	}

}
