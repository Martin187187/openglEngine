package engineTest.marching;

import engine.marching.ExtractHandler;

public class MarchingCubesTest {

	public static void main(String[] args) {
		int[] size = {8, 8, 8};
        float[] voxSize = {1.0f, 1.0f, 1.0f};
        
        int nThreadsMin = java.lang.Thread.activeCount();
        if (nThreadsMin == 0) {
            nThreadsMin = 1;
        }
        int nThreadsMax = nThreadsMin;
        
		ExtractHandler.extractHandlerInt(null, null, size, voxSize, 0, nThreadsMax);
	}

}
