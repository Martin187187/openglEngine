package engineTest;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

import engine.EngineSingleton;
import engine.display.DisplayManager;
import engine.display.Input;
import engine.entity.Camera;
import engine.entity.DynamicEntity;
import engine.entity.Entity;
import engine.gui.animation.FadeInAnimation;
import engine.gui.component.Component;
import engine.gui.component.StandartComponent;
import engine.gui.constaint.WindowSideConstaint;
import engine.gui.main.GuiMaster;
import engine.renderer.GuiRenderer;
import engine.renderer.MasterRenderer;

public class Test {
	
	public static boolean running = true;
	public static void main(String[] args) {
		//Window creation
		GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
		DisplayManager display = new DisplayManager("test", 720, 480);
		display.setBackgroundColor(0.8f, 0.8f, 0.8f);
		display.createDisplay();
		
		//Engine init
		EngineSingleton engine = new EngineSingleton();
		//Gui creation
		Component comp = new StandartComponent(new WindowSideConstaint());
		GuiMaster.changeScene(comp);
		comp.setVisable(false);
		Component inner = new ExampleRectGui("b", 70, 50);
		Component gameMenu = new GameMenu();
		comp.add(inner, WindowSideConstaint.BOT_RIGHT);
		comp.add(gameMenu, WindowSideConstaint.CENTER);
		GuiMaster.rebuildGui();
		
		//Render init
		List<Entity> entities = new LinkedList<Entity>();
		List<DynamicEntity> terrain = new LinkedList<DynamicEntity>();
		
		GuiRenderer guiRender = new GuiRenderer();
		MasterRenderer renderer = new MasterRenderer();
		
		
		Camera cam = new Camera();
		boolean isGameMenu = true;
		while (!display.shouldClose() && running) {

			boolean rebuild = display.isResized();
			if (rebuild)
				renderer.reloadProjectionMatrix();
			if (Input.isKeyClicked(GLFW.GLFW_KEY_ESCAPE)) {
				if (isGameMenu)
					comp.remove(gameMenu);
				else {
					gameMenu.getAnimator().addAnimation(new FadeInAnimation(FadeInAnimation.FROM_RIGHT, 2, 0.2f));
					comp.add(gameMenu);
				}
				isGameMenu = !isGameMenu;
				rebuild = true;
			}
			// update
			cam.move();
			GuiMaster.updateGui(rebuild);

			List<Component> guis = GuiMaster.getVisableComponents();

			renderer.render(cam, entities, terrain);
			guiRender.render(guis);

			display.updateDisplay();
			display.swapBuffers();
		}
		guiRender.cleanUp();
		renderer.cleanUp();
		display.closeDisplay();
		engine.cleanUp();
		GLFW.glfwSetErrorCallback(null).free();
	}

}
