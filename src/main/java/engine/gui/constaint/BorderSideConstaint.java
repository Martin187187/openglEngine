package engine.gui.constaint;

import java.util.List;

import engine.gui.component.Component;
import tools.vector.Vector2f;

public class BorderSideConstaint extends SideConstaint {

	@Override
	public Vector2f update() {
		List<Component> componentList = component.getComponentList();

		float x1 = 0;
		float x2 = 0;
		float x3 = 0;

		float y1 = 0;
		float y2 = 0;
		float y3 = 0;
		for (Component com : componentList) {
			com.getConstaint().updateLocationInnerComponents();
			float x = com.getScale().x;
			float y = com.getScale().y;
			switch (com.getNumber()) {
			case TOP_LEFT:
				x1 = Math.max(x1, x);
				y1 = Math.max(y1, y);
				break;
			case TOP_RIGHT:
				x3 = Math.max(x3, x);
				y1 = Math.max(y1, y);
				break;
			case BOT_LEFT:
				x1 = Math.max(x1, x);
				y3 = Math.max(y3, y);
				break;
			case BOT_RIGHT:
				x3 = Math.max(x3, x);
				y3 = Math.max(y3, y);
				break;
			case CENTER:
				x2 = Math.max(x2, x);
				y2 = Math.max(y2, y);
				break;
			}
		}

		float lx = x2 == 0 ? x3==0? 0 : -x3 - getDisX()/2: -x1 - x2 - getDisX();
		float rx = x2 == 0 ? x1==0? 0 : x1 + getDisX()/2: x2 + x3 + getDisX();
		float ty = y2 == 0 ? y3==0? 0 : y3 + getDisY()/2: y1 + y2 + getDisY();
		float by = y2 == 0 ? y1==0? 0 : -y1 - getDisY()/2: -y2 - y3 - getDisY();
		for (Component com : componentList) {
			float x = component.getPosition().x;
			float y = component.getPosition().y;
			switch (com.getNumber()) {
			case TOP_LEFT:
				com.setPosition(new Vector2f(lx+x, ty+y));
				break;
			case TOP_RIGHT:
				com.setPosition(new Vector2f(rx+x, ty+y));
				break;
			case BOT_LEFT:
				com.setPosition(new Vector2f(lx+x, by+y));
				break;
			case BOT_RIGHT:
				com.setPosition(new Vector2f(rx+x, by+y));
				break;
			case CENTER:
				com.setPosition(new Vector2f(0, 0));
				break;
			}
		}
		
		return new Vector2f(x1 + x2 + x3+ 2*getDisX(), y1 + y2 + y3+ 2*getDisY());
	}
}
