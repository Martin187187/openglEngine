package engine;

import engine.model.ContentLoader;

public class EngineSingleton {

	private final static ContentLoader loader = new ContentLoader();

	public static ContentLoader getLoader() {
		return loader;
	}
	
	public void cleanUp() {
		loader.cleanUp();
	}
}
