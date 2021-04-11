package engineTest.gui;

import engine.gui.component.ColorComponent;
import engine.gui.component.Component;
import engine.gui.component.StandartComponent;
import engine.gui.component.solid.SolidButton;
import engine.gui.component.solid.SolidTextComponent;
import engine.gui.constaint.BorderSideConstaint;
import engine.gui.constaint.ListVerticalConstaint;
import engineTest.Test;
import tools.vector.Vector3f;

public class GameMenu extends StandartComponent implements ColorComponent{

	private Vector3f color;
	public GameMenu() {
		super(new ListVerticalConstaint());
		
		color = new Vector3f(1,1,1);
		
		Component top = new SolidTextComponent("Das ist das tolle GameMenu", 500, 50);
		Component mid = new ExampleMovableGui(new ListVerticalConstaint());
		Component bot = new ExampleMovableGui(new BorderSideConstaint());
		add(top);
		add(mid);
		add(bot);
		
		//top
		Component a = new SolidButton("eins", 350, 60, new Vector3f(0.3f,0.3f,0.3f));
		Component b = new SolidButton("zwei", 350, 60, new Vector3f(0.3f,0.3f,0.3f));
		Component c = new SolidButton("drei", 350, 60, new Vector3f(0.3f,0.3f,0.3f));
		Component d = new SolidButton("leave Game", 350, 60, new Vector3f(0.3f,0.3f,0.3f)) {
			@Override
			public void mouseClicked() {
				Test.running = false;
			}
		};

		mid.add(a);
		mid.add(b);
		mid.add(c);
		mid.add(d);
		
		//bot

		Component confirm = new SolidButton("c", 40, 40, new Vector3f(0.3f,0.3f,0.3f));
		Component decline = new SolidButton("d", 40, 40, new Vector3f(0.3f,0.3f,0.3f));
		bot.add(confirm, BorderSideConstaint.BOT_LEFT);
		bot.add(decline, BorderSideConstaint.BOT_RIGHT);
		
		
	}

	@Override
	public Vector3f getColor() {
		// TODO Auto-generated method stub
		return color;
	}
}
