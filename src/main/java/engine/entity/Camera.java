package engine.entity;

import org.lwjgl.glfw.GLFW;

import engine.display.DisplayManager;
import engine.display.Input;
import tools.vector.Vector2f;
import tools.vector.Vector3f;

public class Camera extends EmptyEntity {
	
	Vector2f oldMousePosition = new Vector2f();
	float scale = 20f;
	public Camera() {
		super(new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1,1));
		// TODO Auto-generated constructor stub
	}
	
	public void move(){
		if(!DisplayManager.isLocked())
			return;
		
		float yaw = (float) Math.toRadians(rotation.y);
		
		if(Input.isKeyDown(GLFW.GLFW_KEY_W)){
			position.z-=Math.cos(yaw)*0.02f;
			position.x+=Math.sin(yaw)*0.02f;
		}
		if(Input.isKeyDown(GLFW.GLFW_KEY_D)){
			position.z+=Math.sin(yaw)*0.02f;
			position.x+=Math.cos(yaw)*0.02f;
		}
		if(Input.isKeyDown(GLFW.GLFW_KEY_A)){
			position.z-=Math.sin(yaw)*0.02f;
			position.x-=Math.cos(yaw)*0.02f;
		}
		if(Input.isKeyDown(GLFW.GLFW_KEY_S)){
			position.z+=Math.cos(yaw)*0.02f;
			position.x-=Math.sin(yaw)*0.02f;
		}
		if(Input.isKeyDown(GLFW.GLFW_KEY_SPACE)){
			position.y+=0.02f;
		}
		if(Input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)){
			position.y-=0.02f;
		}
		Vector2f mousePosition = Input.getMousePosition();
		Vector2f trans = new Vector2f(mousePosition.x-oldMousePosition.x, mousePosition.y-oldMousePosition.y);
		oldMousePosition = mousePosition;

		rotation.x -= scale * trans.y;
		rotation.y += scale * trans.x;
		
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
