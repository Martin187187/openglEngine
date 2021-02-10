package engineTest;

import engine.gui.component.ColorComponent;
import engine.gui.component.StandartComponent;
import engine.gui.constaint.Constaint;
import tools.vector.Vector3f;

public class ExampleMovableGui extends StandartComponent implements ColorComponent{
	

	private Vector3f color;
	
	public ExampleMovableGui(Constaint constaint) {
		super(constaint);
		this.color = new Vector3f(1,0,0);
	}

	@Override
	public Vector3f getColor() {
		// TODO Auto-generated method stub
		return color;
	}
	
	
/*
	@Override
	public void mousePressed() {
		Vector2f mousePosition = Input.getMousePosition();
		Vector2f newPosition = Vector2f.sub(mousePosition, position, null);

		getMovedPosition().x = newPosition.x;
		getMovedPosition().y = newPosition.y;
	}

	@Override
	public void mouseClicked() {
//		this.addAnimation(new FadeInAnimation(FadeInAnimation.FROM_LEFT, 0.5f, 0.005f));
	}
*/
}
