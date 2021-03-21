package engine.entity;

import org.lwjgl.glfw.GLFW;

import engine.display.Input;
import tools.vector.Vector3f;

public class Camera extends EmptyEntity {

	public Camera() {
		super(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1));
		// TODO Auto-generated constructor stub
	}
	
	public void move(){
		if(Input.isKeyDown(GLFW.GLFW_KEY_W)){
			position.z-=0.02f;
		}
		if(Input.isKeyDown(GLFW.GLFW_KEY_D)){
			position.x+=0.02f;
		}
		if(Input.isKeyDown(GLFW.GLFW_KEY_A)){
			position.x-=0.02f;
		}
		if(Input.isKeyDown(GLFW.GLFW_KEY_S)){
			position.z+=0.02f;
		}
		
		if(Input.isKeyDown(GLFW.GLFW_KEY_LEFT_CONTROL)){
			rotation.y+=1f;
		}
		if(Input.isKeyDown(GLFW.GLFW_KEY_Q)){
			position.y+=0.02f;
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return rotation.x;
	}

	public float getYaw() {
		return rotation.y;
	}

	public float getRoll() {
		return rotation.z;
	}

}
