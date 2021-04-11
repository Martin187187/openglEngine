package engine.entity;

import tools.vector.Vector3f;

public class Light {
	
	private Vector3f position, colour;
	
	private float scalar;
	
	public Light(Vector3f position, Vector3f colour) {
		super();
		this.position = position;
		this.colour = colour;
		
		scalar = 0;
	}
	
	public void update() {
		scalar = (scalar + 0.02f)%scalar;
		position.x = (float) Math.sin(scalar);
		position.y = (float) Math.cos(scalar);
	}
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public Vector3f getColour() {
		return colour;
	}
	public void setColour(Vector3f colour) {
		this.colour = colour;
	}

}
