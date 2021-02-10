package engine.gui.component;

import engine.gui.constaint.Constaint;
import engine.gui.constaint.WindowSideConstaint;
import tools.io.Tools;

public class StandartComponent extends Component {

	public StandartComponent() {
		super(new WindowSideConstaint());
	}
	
	public StandartComponent(Constaint constaint) {
		super(constaint);
	}

	
}
