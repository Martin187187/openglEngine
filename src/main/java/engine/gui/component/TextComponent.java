package engine.gui.component;

import java.io.File;

import engine.fontMeshCreator.FontType;
import engine.fontMeshCreator.GuiText;
import engine.model.ContentLoader;

public interface TextComponent {


	public static final FontType FONT = new FontType(ContentLoader.loadTexture("arial"), new File("res/fonts/arial.fnt"));
	public GuiText getText();
	/**
	 *TODO remove old GuiText from vao
	 * @param text
	 */
	public void changeText(String text);
	
	
}
