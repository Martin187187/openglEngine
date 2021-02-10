package engine.renderer;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import engine.fontMeshCreator.GuiText;
import engine.gui.component.ColorComponent;
import engine.gui.component.Component;
import engine.gui.component.TextComponent;
import engine.gui.component.TextureComponent;
import engine.model.ContentLoader;
import engine.model.RawModel;
import engine.shader.FontShader;
import engine.shader.GuiShader;
import tools.Maths;
import tools.vector.Matrix4f;
import tools.vector.Vector2f;

public class GuiRenderer {

	private final RawModel quad;

	private GuiShader shader;
	private FontShader fontShader;

	public GuiRenderer(ContentLoader loader) {
		this.shader = new GuiShader();
		fontShader = new FontShader();

		float[] positions = { -1, 1, -1, -1, 1, 1, 1, -1 };
		quad = loader.loadToVAO(positions);

	}

	public void render(List<Component> entities) {
		shader.start();
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		prepare();
		for (Component com : entities) {
			Matrix4f transformationMatrix = Maths.createTransformationMatrix(com.getPosition(), null, com.getScale());
			boolean hasTexture = com instanceof TextureComponent;
			boolean hasColor = com instanceof ColorComponent;

			if (hasTexture) {
				GL13.glActiveTexture(GL13.GL_TEXTURE0);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, ((TextureComponent) com).getTexture());

			}
			if (hasColor)
				shader.loadColor(((ColorComponent) com).getColor());
			shader.loadTransformationMatrix(transformationMatrix);
			shader.loadPictureModeBoolean(hasTexture);
			shader.loadHoveredBoolean(com.isHovered());
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());

			if (com instanceof TextComponent) {
				shader.stop();
				GL20.glDisableVertexAttribArray(0);
				GL30.glBindVertexArray(0);

				// render
				renderText(((TextComponent) com).getText(), com);

				GL30.glBindVertexArray(quad.getVaoID());
				GL20.glEnableVertexAttribArray(0);
				shader.start();
			}
		}
		endRendering();
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();

	}

	private void renderText(GuiText text, Component comp) {
		fontShader.start();
		GL30.glBindVertexArray(text.getMesh());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);

		fontShader.loadColor(text.getColour());
		Vector2f position = new Vector2f(comp.getPosition().getX() - comp.getScale().getX(),
				comp.getPosition().getY() + comp.getScale().getY());
		Vector2f scale = new Vector2f(text.getScaleUp().x * comp.getScale().x, text.getScaleUp().y * comp.getScale().y);
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(position, null, scale);
		fontShader.loadTransformationMatrix(transformationMatrix);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, text.getFont().getTextureAtlas());
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, text.getVertexCount());

		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
		fontShader.stop();
	}

	private void prepare() {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}

	private void endRendering() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
	}

	public void cleanUp() {
		shader.cleanUp();
		fontShader.cleanUp();
	}
}
