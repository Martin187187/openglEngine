package engine.display;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import engine.gui.main.GuiMaster;
import tools.vector.Vector3f;


public class DisplayManager  {
	private String title;
	private static int width;
	private static int height;
	
	private static long lastFrameTime;
	private static float delta;
	
	private long window;
	
	private Input input;
	private Vector3f background = new Vector3f(0, 0, 0);
	
	private GLFWWindowSizeCallback sizeCallback;
	private boolean isResized;
	private boolean isFullscreen;
	
	private int[] windowPosX = new int[1], windowPosY = new int[1];
	
	public DisplayManager(String title, int width, int height) {
		DisplayManager.width = width;
		DisplayManager.height = height;
		this.title = title;
	}
	
	public void createDisplay() {
		if (!GLFW.glfwInit()) {
			System.err.println("ERROR: GLFW wasn't initializied");
			return;
		}
		
		input = new Input();
		window = GLFW.glfwCreateWindow(width, height, title, isFullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);
		
		if (window == 0) {
			System.err.println("ERROR: Window wasn't created");
			return;
		}
		
		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		windowPosX[0] = (videoMode.width() - width) / 2;
		windowPosY[0] = (videoMode.height() - height) / 2;
		GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);
		GLFW.glfwMakeContextCurrent(window);
		GL.createCapabilities();
		GL11.glEnable(GL11.GL_DEPTH_TEST);

		createCallbacks();
		
		GLFW.glfwShowWindow(window);
		
		GLFW.glfwSwapInterval(1);
		lastFrameTime = getCurrentTime();
		
	}
	
	private void createCallbacks() {
		sizeCallback = new GLFWWindowSizeCallback() {
			@Override
			public void invoke(long window, int w, int h) {
				width = w;
				height = h;
				isResized = true;
			}
		};
		GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
		GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
		GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
		GLFW.glfwSetScrollCallback(window, input.getMouseScrollCallback());
		GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
	}
	
	public void updateDisplay() {
		
		if (isResized) {
			GL11.glViewport(0, 0, width, height);
			isResized = false;
			
			
		}
		GL11.glClearColor(background.getX(), background.getY(), background.getZ(), 1.0f);
		GLFW.glfwPollEvents();
		
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime)/1000;
		lastFrameTime = getCurrentTime();
	}
	
	public static float getFrameTimeSeconds() {
		return delta;
		
	}
	
	public void swapBuffers() {
		GLFW.glfwSwapBuffers(window);
	}
	
	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(window);
	}
	
	public void closeDisplay() {
		input.destroy();
		sizeCallback.free();
		GLFW.glfwWindowShouldClose(window);
		GLFW.glfwDestroyWindow(window);
		GLFW.glfwTerminate();
	}
	
	
	
	private long getCurrentTime() {
		return GLFW.glfwGetTimerValue()*1000/GLFW.glfwGetTimerFrequency();
	}
	
	
	public boolean isResized() {
		return isResized;
	}
	
	public void setBackgroundColor(float r, float g, float b) {
		background.set(r, g, b);
	}

	public boolean isFullscreen() {
		return isFullscreen;
	}

	public void setFullscreen(boolean isFullscreen) {
		this.isFullscreen = isFullscreen;
		isResized = true;
		if (isFullscreen) {
			GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
			GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, 1920, 1080, 0);
		} else {
			GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0], windowPosY[0], width, height, 0);
		}
	}
	
	public void mouseState(boolean lock) {
		GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, lock ? GLFW.GLFW_CURSOR_DISABLED : GLFW.GLFW_CURSOR_NORMAL);
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public String getTitle() {
		return title;
	}

	public long getWindow() {
		return window;
	}
}
