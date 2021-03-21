package engine.model;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import tools.io.Tools;
import tools.vector.Vector3f;


public class ContentLoader {

	private Map<Integer, List<Integer>> vaos = new HashMap<Integer, List<Integer>>();
	private List<Integer> textures = new LinkedList<Integer>();
	
	public RawModel loadToVAO(int dimension, float[] positions, int[] indices) {
		int vaoID = createVAO();
		bindIndicesBuffer(indices, vaoID);
		storeDataInAttributeList(0, dimension, positions, vaoID);
		unbindVAO();
		return new RawModel(vaoID, indices.length);
	}
	
	public RawModel loadToVAO(int dimension, float[] positions) {
		int vaoID = createVAO();
		storeDataInAttributeList(0, dimension, positions, vaoID);
		unbindVAO();
		return new RawModel(vaoID, positions.length/dimension);
	}
	
	public RawModel loadToVAO(float[] positions, float[] textureCoords, int[] indices) {
		int vaoID = createVAO();
		bindIndicesBuffer(indices, vaoID);
		storeDataInAttributeList(0, 3, positions, vaoID);
		storeDataInAttributeList(1, 2, textureCoords, vaoID);
		unbindVAO();
		return new RawModel(vaoID, indices.length);
	}
	
	public int loadToVAO(float[] positions, float[] textureCoords) {
		int vaoID = createVAO();
		storeDataInAttributeList(0, 2, positions, vaoID);
		storeDataInAttributeList(1, 2, textureCoords, vaoID);
		unbindVAO();
		return vaoID;
	}
	public RawModel loadToVAO(float[] positions) {
		int vaoID = createVAO();
		storeDataInAttributeList(0, 2, positions, vaoID);
		unbindVAO();
		return new RawModel(vaoID, positions.length/2);
	}
	

	public void editVBOS(int vaoID, Stack<Integer> index, Stack<Float> height, Stack<Vector3f> normalVector) {


		List<Integer> vbos = vaos.get(vaoID);
		
		float[] input = new float[1];
		float[] normal = new float[3];

		FloatBuffer buffer = null;
		FloatBuffer buffer2 = null;
		while (!height.isEmpty()) {
			int indexNumber = index.pop();
			
			

			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbos.get(1));
			input[0] = height.pop();
			
			buffer = storeDataInFloatBuffer(input, buffer);
			GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, indexNumber * 12 + 3 + 1, buffer);
			
			

			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbos.get(3));
			Vector3f norm = normalVector.pop();
			normal[0] = norm.x;
			normal[1] = norm.y;
			normal[2] = norm.z;

			buffer2 = storeDataInFloatBuffer(normal, buffer2);
			GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, indexNumber * 12 + 8, buffer2);
		}
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	public int loadTexture(String fileName) {
		int tex = Tools.loadTexture(fileName);
		textures.add(tex);
		return tex;
	}
	
	private int createVAO() {
		int vaoID = GL30.glGenVertexArrays();
		
		List<Integer> vbos = new ArrayList<Integer>();
		vaos.put(vaoID, vbos);
		
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private void unbindVAO() {
		GL30.glBindVertexArray(0);
	}
	
	private void storeDataInAttributeList(int attributeNumber, int coordinateSize, float[] data, int vaoID) {
		int vboID = GL15.glGenBuffers();
		
		List<Integer> vbos = vaos.get(vaoID);
		vbos.add(vboID);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data, null);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data, FloatBuffer buffer) {
		if(buffer==null)
			buffer = BufferUtils.createFloatBuffer(data.length);
		else
			buffer.clear();
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private void bindIndicesBuffer(int[] indices, int vaoID) {
		int vboID = GL15.glGenBuffers();
		

		List<Integer> vbos = vaos.get(vaoID);
		vbos.add(vboID);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	private IntBuffer storeDataInIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	
public void cleanUp() {
		
		
		for(int vao : vaos.keySet()) {
			
			List<Integer> vbos = vaos.get(vao);
			for(int vbo : vbos) {
				GL15.glDeleteBuffers(vbo);
			}

			GL30.glDeleteVertexArrays(vao);
		}
		
		textures.forEach(x -> GL11.glDeleteTextures(x));
	}
}
