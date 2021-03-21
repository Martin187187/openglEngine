package engine.renderer;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import engine.entity.Camera;
import engine.entity.Entity;
import engine.model.RawModel;
import engine.model.TexturedModel;
import engine.shader.EntityShader;
import tools.Maths;
import tools.vector.Matrix4f;

public class EntityRenderer {

	private EntityShader shader;
	
	public EntityRenderer(EntityShader shader, Matrix4f projectionMatrix) {
		this.shader = shader;
		
		reloadProjectionMatrix(projectionMatrix);
	}
	
	public void reloadProjectionMatrix(Matrix4f projectionMatrix) {
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	public void render(List<Entity> entityList) {
		
		for(Entity entity: entityList) {
			
			TexturedModel model = entity.getModel();
			RawModel raw = model.getRawModel();
			
			GL30.glBindVertexArray(raw.getVaoID());
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(1);
			
			Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotation(),
					entity.getScale());
			shader.loadTransformationMatrix(transformationMatrix);
//			GL13.glActiveTexture(GL13.GL_TEXTURE0);
//			GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture());
			GL11.glDrawElements(GL11.GL_TRIANGLES, raw.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

		}

		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	
	public void cleanUp() {
		shader.cleanUp();
	}
}
