package engineTest;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

import engine.display.DisplayManager;
import engine.display.Input;
import engine.gui.animation.FadeInAnimation;
import engine.gui.component.Component;
import engine.gui.component.StandartComponent;
import engine.gui.constaint.WindowSideConstaint;
import engine.gui.main.GuiMaster;
import engine.model.Camera;
import engine.model.ContentLoader;
import engine.model.DynamicEntity;
import engine.model.Entity;
import engine.model.RawModel;
import engine.model.RawModelCreator;
import engine.renderer.GuiRenderer;
import engine.renderer.MasterRenderer;
import tools.vector.Vector3f;

public class Test {
	
	public static boolean running = true;
	private final static float[] positions = {
			-0.5f, -0.5f, 0,
			-0.5f, 0.5f, 0,
			0.5f, -0.5f, 0,
			0.5f, 0.5f, 0};
	private final static int[] indecies = {
			0, 1, 2,
			2, 3, 1
	};
	public static void main(String[] args) {
		GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
		DisplayManager display = new DisplayManager("test", 720, 480);
		display.setBackgroundColor(0.8f, 0.8f, 0.8f);
		display.createDisplay();

		ContentLoader loader = new ContentLoader();
		List<Entity> entities = new LinkedList<Entity>();
		List<DynamicEntity> terrain = new LinkedList<DynamicEntity>();
		RawModel model = loader.loadToVAO(3, positions, indecies);
		DynamicEntity block = new DynamicEntity(model, new Vector3f(0,0,-2), new Vector3f(0,0,0), new Vector3f(1,1,1));
		terrain.add(block);
		//gui init
		GuiMaster.init(loader);
		Component comp = new StandartComponent(new WindowSideConstaint());
		GuiMaster.changeScene(comp);
		comp.setVisable(false);
		Component inner = new ExampleRectGui("b", 70, 50);
		Component gameMenu = new GameMenu();

		comp.add(inner, WindowSideConstaint.BOT_RIGHT);
		comp.add(gameMenu, WindowSideConstaint.CENTER);

		GuiMaster.rebuildGui();
		GuiRenderer guiRender = new GuiRenderer(loader);
		MasterRenderer renderer = new MasterRenderer(loader);
		
		
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
		loader.cleanUp();
		GLFW.glfwSetErrorCallback(null).free();
	}

}
