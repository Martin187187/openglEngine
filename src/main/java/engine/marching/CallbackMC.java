package engine.marching;
import java.util.ArrayList;

/**
 * Created by Primoz on 8.7.2016.
 */
abstract class CallbackMC implements Runnable {
    private ArrayList<float []> vertices;
    private ArrayList<float []> normals;

    void setVertices(ArrayList<float []> vertices) {
        this.vertices = vertices;
    }
    void setNormals(ArrayList<float []> normals) {
        this.normals = normals;
    }
    ArrayList<float []> getVertices() {
        return this.vertices;
    }
    ArrayList<float []> getNormals() {
        return this.normals;
    }
}