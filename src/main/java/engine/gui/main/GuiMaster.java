package engine.gui.main;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

import engine.EngineSingleton;
import engine.display.Input;
import engine.fontMeshCreator.FontType;
import engine.fontMeshCreator.GuiText;
import engine.fontMeshCreator.TextMeshData;
import engine.gui.animation.Animation;
import engine.gui.component.Component;
import engine.gui.component.MouseListener;
import engine.model.ContentLoader;

public class GuiMaster {

	private static Component masterComponent = null;
	
	public static void rebuildGui() {
		masterComponent.getConstaint().updateLocationInnerComponents();
	}

	public static void updateGui(boolean isResized) {
		List<Component> comps = new LinkedList<Component>(masterComponent.getComponentList());
//		comps.remove(0);
		boolean buttonClicked = Input.isButtonClicked(GLFW.GLFW_MOUSE_BUTTON_LEFT);
		boolean buttonDown = Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT);
		boolean hasToRebuild =updateGui(comps, buttonClicked, buttonDown);
		
		if(isResized||hasToRebuild)
			rebuildGui();
	}

	private static boolean updateGui(List<Component> comps, boolean clicked, boolean down) {
		boolean hasToRebuild = false;
		for (Component comp : comps) {

			List<Animation> animations = comp.getAnimator().getAnimationList();
			int length = animations.size();
			for (int i = 0; i < length; i++) {
				animations.get(i).updateAnimation();
			}
			
			if(length>0)
				hasToRebuild = true;

			boolean inComponent = comp instanceof MouseListener && comp.isInComponent(Input.getMousePosition());
			comp.setHovered(inComponent);
			if (inComponent) {
//					System.out.println("f");
				if (clicked)
					((MouseListener) comp).mouseClicked();
				if (down)
					((MouseListener) comp).mousePressed();

//				comp.reloadLocations();
			}

			boolean recRe = updateGui(comp.getComponentList(), clicked, down);
			if(recRe)
				hasToRebuild = true;
		}
		
		return hasToRebuild;
	}
	
	public static void loadGuiText(GuiText text) {
		FontType font = text.getFont();
		TextMeshData data = font.loadText(text);
		int vao = EngineSingleton.getLoader().loadToVAO(data.getVertexPositions(), data.getTextureCoords());
		text.setMeshInfo(vao, data.getVertexCount());
	}

	public static void changeScene(Component component) {
		masterComponent = component;
	}

	public static List<Component> getVisableComponents() {
		return getVisableComponents(masterComponent);
	}

	private static List<Component> getVisableComponents(Component component) {
		List<Component> comps = new LinkedList<Component>();

		if (component.isVisable())
			comps.add(component);
		component.getComponentList().forEach(x -> getVisableComponents(x).forEach(y -> comps.add(y)));

		return comps;
	}

}
