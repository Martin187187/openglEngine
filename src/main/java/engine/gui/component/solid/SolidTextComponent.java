package engine.gui.component.solid;

import engine.fontMeshCreator.GuiText;
import engine.gui.component.TextComponent;
import engine.gui.main.GuiMaster;

public class SolidTextComponent extends SolidComponent implements TextComponent{

	private GuiText guiText;
	public SolidTextComponent(String text, float sizeX, float sizeY) {
		super(sizeX, sizeY);
		guiText = new GuiText(text, FONT, scale, true);
		GuiMaster.loadGuiText(guiText);
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
