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
import engine.entity.Light;
import engine.entity.Terrain;
import engine.gui.animation.FadeInAnimation;
import engine.gui.component.Component;
import engine.gui.component.StandartComponent;
import engine.gui.constaint.WindowSideConstaint;
import engine.gui.main.GuiMaster;
import engine.renderer.GuiRenderer;
import engine.renderer.MasterRenderer;
import engineTest.gui.ExampleRectGui;
import engineTest.gui.GameMenu;
import tools.vector.Vector3f;

public class Test {

	public static boolean running = true;

	public static void main(String[] args) {
		// Window creation
		GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
		DisplayManager display = new DisplayManager("test", 720, 480);
		display.setBackgroundColor(0.8f, 0.8f, 0.8f);
		display.createDisplay();

		// Engine init
		EngineSingleton engine = new EngineSingleton();
		// Gui creation
		Component comp = new StandartComponent(new WindowSideConstaint());
		GuiMaster.changeScene(comp);
		comp.setVisable(false);
		Component inner = new ExampleRectGui("b", 70, 50);
		Component gameMenu = new GameMenu();
		comp.add(inner, WindowSideConstaint.BOT_RIGHT);
		comp.add(gameMenu, WindowSideConstaint.CENTER);
		GuiMaster.rebuildGui();

		// Render init
		List<Entity> entities = new LinkedList<Entity>();
//		TerrainManager manager = new TerrainManager();
//		List<Terrain> terrain = manager.getTerrainList();
		List<Terrain> terrain = new LinkedList<>();
		
		
		
		GuiRenderer guiRender = new GuiRenderer();
		MasterRenderer renderer = new MasterRenderer();

		Camera cam = new Camera();
		Light light = new Light(new Vector3f(5, -10, 0), new Vector3f(1, 1, 1));
		boolean isGameMenu = true;
		display.mouseState(false);
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
				display.mouseState(isGameMenu);
				isGameMenu = !isGameMenu;
				rebuild = true;
			}
			// update
			cam.move();
			terrain.forEach(x -> x.update());
			GuiMaster.updateGui(rebuild);

			List<Component> guis = GuiMaster.getVisableComponents();

			renderer.render(cam, light, entities, terrain);
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
