package engine.renderer;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import engine.entity.DynamicEntity;
import engine.model.RawModel;
import engine.shader.TerrainShader;
import tools.Maths;
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
			shader.loadShineDamperFloat(10);
			shader.loadReflectivityFloat(1);
			shader.stop();
		}
	
	public void render(List<? extends DynamicEntity> blocks) {
		
		for(DynamicEntity block: blocks) {
			
			RawModel raw = block.getModel();
			GL30.glBindVertexArray(raw.getVaoID());
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(1);
			Matrix4f matrix = Maths.createTransformationMatrix(block.getPosition(), block.getRotation(), block.getScale());
			shader.loadTranslationVector(matrix);
			GL11.glDrawElements(GL11.GL_TRIANGLES, raw.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		}
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
	
	public void cleanUp() {
		shader.cleanUp();
	}
}
