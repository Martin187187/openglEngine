package engine.gui.constaint;

import java.util.List;

import engine.gui.component.Component;
import engine.gui.component.FixedScaleComponent;
import tools.vector.Vector2f;

public class ListVerticalConstaint extends Constaint {


	@Override
	public Vector2f update() {

		List<Component> componentList = component.getComponentList();
		Vector2f position = component.getPosition();
		float currentStep = getDisY();
		float biggestSize = 0;
		for (Component com : componentList) {
			com.getConstaint().updateLocationInnerComponents();
			float x = com.getScale().getX();
			float y = com.getScale().getY();
			if (x > biggestSize)
				biggestSize = x;
			com.setPosition(new Vector2f(position.getX(), position.getY() - currentStep - y));
			currentStep += y * 2 + getDisY();
		}

		for (Component com : componentList) {
			com.setPosition(new Vector2f(com.getPosition().getX(), com.getPosition().getY() + currentStep / 2));
		}
		return new Vector2f(biggestSize + getDisX(), currentStep / 2);

	}

}
