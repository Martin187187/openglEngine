package engine.gui.component.solid;

import engine.fontMeshCreator.GuiText;
import engine.gui.component.ColorComponent;
import engine.gui.component.MouseListener;
import engine.gui.component.TextComponent;
import engine.gui.main.GuiMaster;
import tools.vector.Vector3f;

public class SolidButton extends SolidComponent implements ColorComponent, TextComponent, MouseListener{

	private Vector3f color;
	private GuiText guiText;
	public SolidButton(String text, float sizeX, float sizeY, Vector3f color) {
		super(sizeX, sizeY);
		this.color = color;
		guiText = new GuiText(text, FONT, scale, true);
		GuiMaster.loadGuiText(guiText);
		
	}

	@Override
	public void mousePressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector3f getColor() {
		// TODO Auto-generated method stub
		return color;
	}
	
	@Override
	public GuiText getText() {
		// TODO Auto-generated method stub
		return guiText;
	}

	@Override
	public void changeText(String text) {
		guiText = new GuiText(text, FONT, scale, true);
		GuiMaster.loadGuiText(guiText);
	}



}
