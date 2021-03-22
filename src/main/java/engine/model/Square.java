package engine.model;

import java.util.LinkedList;
import java.util.List;

import tools.vector.Vector2f;

public class Square {

	private List<Vector2f> vertecies;
	private List<Integer> indecies;
	
	public Square(int v00, int v01, int v10, int v11, int iso) {
		vertecies = new LinkedList<>();
		indecies = new LinkedList<>();
		
		byte window = 0;

		if (v00 >= iso)
			window += 1;
		if (v01 >= iso)
			window += 2;
		if (v10 >= iso)
			window += 4;
		if (v11 >= iso)
			window += 8;

		switch (window) {
		case 1:
			vertecies.add(new Vector2f(1, 0));
			vertecies.add(new Vector2f(0, -1));
			
			indecies.add(0);
			indecies.add(1);
			break;
		case 2:
			vertecies.add(new Vector2f(0, -1));
			vertecies.add(new Vector2f(-1, 0));
			
			indecies.add(0);
			indecies.add(1);
			break;
		case 3:
			vertecies.add(new Vector2f(1, 0));
			vertecies.add(new Vector2f(-1, 0));
			
			indecies.add(0);
			indecies.add(1);
			break;
		case 4:
			vertecies.add(new Vector2f(-1, 0));
			vertecies.add(new Vector2f(0, -1));
			
			indecies.add(0);
			indecies.add(1);
			break;
		case 5:
			vertecies.add(new Vector2f(0, 1));
			vertecies.add(new Vector2f(0, -1));
			
			indecies.add(0);
			indecies.add(1);
			break;
		case 6:
			break;
		case 7:
			vertecies.add(new Vector2f(1, 0));
			vertecies.add(new Vector2f(0, -1));
			
			indecies.add(0);
			indecies.add(1);
			break;
		case 8:
			vertecies.add(new Vector2f(0, -1));
			vertecies.add(new Vector2f(1, 0));
			
			indecies.add(0);
			indecies.add(1);
			break;
		case 9:
			break;
		case 10:
			vertecies.add(new Vector2f(0, -1));
			vertecies.add(new Vector2f(0, 1));
			
			indecies.add(0);
			indecies.add(1);
			break;
		case 11:
			vertecies.add(new Vector2f(0, -1));
			vertecies.add(new Vector2f(-1, 0));
			
			indecies.add(0);
			indecies.add(1);
			break;
		case 12:
			vertecies.add(new Vector2f(-1, 0));
			vertecies.add(new Vector2f(1, 0));
			
			indecies.add(0);
			indecies.add(1);
			break;
		case 13:
			vertecies.add(new Vector2f(0, 1));
			vertecies.add(new Vector2f(1, 0));
			
			indecies.add(0);
			indecies.add(1);
			break;
		case 14:
			vertecies.add(new Vector2f(-1, 0));
			vertecies.add(new Vector2f(0, 1));
			
			indecies.add(0);
			indecies.add(1);
			break;
		case 15:
			break;
		default:
			break;
		}
	}
	
	public List<Vector2f> getVertecies(){
		return vertecies;
	}
	
	public List<Integer> getIndecies(){
		return indecies;
	}
}