package engine.gui.component.solid;

import engine.display.DisplayManager;
import engine.gui.component.FixedScaleComponent;
import engine.gui.constaint.Constaint;
import tools.vector.Vector2f;

public class SolidComponent extends FixedScaleComponent{

	private float sizeX, sizeY;
	public SolidComponent(Constaint constaint, float sizeX, float sizeY) {
		super(constaint);
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		setVisable(true);
		setScale(new Vector2f(sizeX/DisplayManager.getWidth(), sizeY/DisplayManager.getHeight()));
	}
	
	public SolidComponent(float sizeX, float sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		setVisable(true);
		setScale(new Vector2f(sizeX/DisplayManager.getWidth(), sizeY/DisplayManager.getHeight()));
	}
	
	@Override
	public void correctScale(Vector2f newScale) {
		setScale(new Vector2f(sizeX/DisplayManager.getWidth(), sizeY/DisplayManager.getHeight()));
	}


}
