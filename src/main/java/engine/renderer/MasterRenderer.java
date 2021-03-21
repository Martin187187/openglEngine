package engine.renderer;

import java.util.List;

import org.lwjgl.opengl.GL11;

import engine.display.DisplayManager;
import engine.entity.Camera;
import engine.entity.DynamicEntity;
import engine.entity.Entity;
import engine.model.ContentLoader;
import engine.shader.EntityShader;
import engine.shader.TerrainShader;
import tools.vector.Matrix4f;

public class MasterRenderer {


	public static final float FOV = 70;
	public static final float NEAR_PLANE = 0.1f;
	public static final float FAR_PLANE = 2000f;
	private Matrix4f projectionMatrix;


	private EntityShader entityShader;
	private EntityRenderer entityRenderer;

	private TerrainShader terrainShader;
	private TerrainRenderer terrainRenderer;
	
	public MasterRenderer() {
		
		projectionMatrix = createProjectionMatrix();
		
		this.entityShader = new EntityShader();
		this.entityRenderer = new EntityRenderer(entityShader, projectionMatrix);
		
		terrainShader = new TerrainShader();
		terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
	}

	public void reloadProjectionMatrix() {
		projectionMatrix = createProjectionMatrix();
		entityRenderer.reloadProjectionMatrix(projectionMatrix);
		terrainRenderer.reloadProjectionMatrix(projectionMatrix);
	}
	public void render(Camera camera, List<Entity> entities, List<DynamicEntity> blocks) {
		prepare();
		
		
		entityShader.start();
		entityShader.loadViewMatrix(camera);
		entityRenderer.render(entities);
		entityShader.stop();
		
		terrainShader.start();
		terrainShader.loadViewMatrix(camera);
		terrainRenderer.render(blocks);
		terrainShader.stop();
	}

	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(1, 0, 0, 1);
	}
	
	private Matrix4f createProjectionMatrix() {
		Matrix4f projectionMatrix = new Matrix4f();
		float aspectRatio = (float) DisplayManager.getWidth() / (float) DisplayManager.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))));
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;

		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
		
		return projectionMatrix;
	}

	

	public void cleanUp() {
		entityRenderer.cleanUp();
		terrainRenderer.cleanUp();
	}
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
}
