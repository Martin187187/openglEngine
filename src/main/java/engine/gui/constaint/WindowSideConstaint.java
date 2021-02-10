package engine.gui.constaint;

import java.util.List;

import engine.gui.component.Component;
import tools.vector.Vector2f;

public class WindowSideConstaint extends SideConstaint {



	@Override
	public Vector2f update() {
		List<Component> componentList = component.getComponentList();
		
		for (Component com : componentList) {
			com.getConstaint().updateLocationInnerComponents();
			Vector2f currentSizeVector = com.getScale();
			switch (com.getNumber()) {
			case TOP_LEFT:
				com.setPosition(new Vector2f(currentSizeVector.x +getDisX()- 1, 1 - currentSizeVector.y -getDisY()));
				break;
			case TOP_RIGHT:
				com.setPosition(new Vector2f(1 - currentSizeVector.x-getDisX() , 1 - currentSizeVector.y-getDisY()));
				break;
			case BOT_LEFT:
				com.setPosition(new Vector2f(currentSizeVector.x +getDisX() - 1, currentSizeVector.y +getDisY() - 1));
				break;
			case BOT_RIGHT:
				com.setPosition(new Vector2f(1 - currentSizeVector.x-getDisX(), currentSizeVector.y +getDisY() - 1));
				break;
			case CENTER:
				com.setPosition(new Vector2f(0, 0));
				break;

			}
		}
		return component.getScale();
	}


}
