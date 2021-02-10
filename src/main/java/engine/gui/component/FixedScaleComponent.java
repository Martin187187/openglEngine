package engine.gui.component;

import engine.gui.constaint.Constaint;
import tools.vector.Vector2f;

public class FixedScaleComponent extends Component {


	public FixedScaleComponent() {
		super();
	}
	public FixedScaleComponent(Constaint constaint) {
		super(constaint);
	}
	
	@Override
	public void correctScale(Vector2f newScale) { }
	

}
