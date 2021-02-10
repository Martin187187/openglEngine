package engine.gui.constaint;

import engine.display.DisplayManager;
import engine.gui.component.Component;
import tools.vector.Vector2f;

public abstract class Constaint {

	protected Component component;
	protected float disX = 10;
	protected float disY = 10;

	protected abstract Vector2f update();
	
	public void updateLocationInnerComponents() {
		Vector2f scale = component.getConstaint().update();
		component.correctScale(scale);
	}
	public void loadConstaint(Component component) {
		this.component = component;
	}
	protected float getDisX() {
		return disX / DisplayManager.getWidth();
	}
	
	protected float getDisY() {
		return disY / DisplayManager.getHeight();
	}
}
