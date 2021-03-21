package engine.renderer;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import engine.entity.DynamicEntity;
import engine.model.RawModel;
import engine.shader.TerrainShader;
import tools.vector.Matrix4f;

public class TerrainRenderer {

	private TerrainShader shader;
	public TerrainRenderer(TerrainShader shader, Matrix4f projectionMatrix) {
		this.shader = shader;
		reloadProjectionMatrix(projectionMatrix);
		}
		
		public void reloadProjectionMatrix(Matrix4f projectionMatrix) {
			shader.start();
			shader.loadProjectionMatrix(projectionMatrix);
			shader.stop();
		}
	
	public void render(List<DynamicEntity> blocks) {
		
		for(DynamicEntity block: blocks) {
			
			RawModel raw = block.getModel();
			GL30.glBindVertexArray(raw.getVaoID());
			GL20.glEnableVertexAttribArray(0);
			shader.loadTranslationVector(block.getPosition());
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, raw.getVertexCount());
		}
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	
	public void cleanUp() {
		shader.cleanUp();
	}
}
