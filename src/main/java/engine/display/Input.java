package engine.display;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

import tools.vector.Vector2f;

public class Input {
	private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
	private static boolean[] keysClicked = new boolean[GLFW.GLFW_KEY_LAST];
	private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
	private static boolean[] buttonsClicked = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
	private static float mouseX, mouseY;
	private static float scrollX, scrollY;

	private GLFWKeyCallback keyboard;
	private GLFWCursorPosCallback mouseMove;
	private GLFWMouseButtonCallback mouseButtons;
	private GLFWScrollCallback mouseScroll;

	public Input() {
		keyboard = new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {

				if (!keysClicked[key])
					keysClicked[key] = (action != GLFW.GLFW_RELEASE);
				keys[key] = (action != GLFW.GLFW_RELEASE);
			}
		};

		mouseMove = new GLFWCursorPosCallback() {
			@Override
			public void invoke(long window, double xpos, double ypos) {
				mouseX = (float) xpos;
				mouseY = (float) ypos;
			}
		};

		mouseButtons = new GLFWMouseButtonCallback() {
			@Override
			public void invoke(long window, int button, int action, int mods) {

				if (!buttonsClicked[button])
					buttonsClicked[button] = (action != GLFW.GLFW_RELEASE);
				buttons[button] = (action != GLFW.GLFW_RELEASE);

			}
		};

		mouseScroll = new GLFWScrollCallback() {
			@Override
			public void invoke(long window, double offsetx, double offsety) {
				scrollX += offsetx;
				scrollY += offsety;
			}
		};
	}

	public static boolean isKeyDown(int key) {
		return keys[key];
	}

	public static boolean isKeyClicked(int key) {

		boolean result = keysClicked[key];
		keysClicked[key] = false;
		return result;
	}

	public static boolean isButtonDown(int button) {
		return buttons[button];
	}

	public static boolean isButtonClicked(int button) {

		boolean result = buttonsClicked[button];
		buttonsClicked[button] = false;
		return result;
	}
	

	public void destroy() {
		keyboard.free();
		mouseMove.free();
		mouseButtons.free();
		mouseScroll.free();
	}

	public static float getMouseX() {
		return mouseX;
	}

	public static float getMouseY() {
		return mouseY;
	}
	
	public static Vector2f getMousePosition() {
		float halfX = DisplayManager.getWidth()/2;
		float halfY = DisplayManager.getHeight()/2;
		return new Vector2f((mouseX-halfX)/halfX, (halfY-mouseY)/halfY);
	}

	public static float getScrollX() {
		return scrollX;
	}

	public static float getScrollY() {
		return scrollY;
	}

	public GLFWKeyCallback getKeyboardCallback() {
		return keyboard;
	}

	public GLFWCursorPosCallback getMouseMoveCallback() {
		return mouseMove;
	}

	public GLFWMouseButtonCallback getMouseButtonsCallback() {
		return mouseButtons;
	}

	public GLFWScrollCallback getMouseScrollCallback() {
		return mouseScroll;
	}
}