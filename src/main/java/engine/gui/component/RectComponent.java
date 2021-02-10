package engine.gui.component;

import engine.gui.constaint.Constaint;
import tools.vector.Vector2f;

public class RectComponent extends FixedScaleComponent{

	public RectComponent(Vector2f scale) {
		this.scale = scale;
		setVisable(true);
	}
	
	public RectComponent(Constaint constatint, Vector2f scale) {
		super(constatint);
		this.scale = scale;
		setVisable(true);
	}

	
}
