package engine.model;

import java.util.List;

import tools.vector.Vector2f;

public class Square {

	private List<Vector2f> vertecies;
	private List<Integer> indecies;
	
	public Square(int value0, int value1, int value2, int value3, int iso) {

		byte window = 0;

		if (value0 >= iso)
			window += 1;
		if (value1 >= iso)
			window += 2;
		if (value2 >= iso)
			window += 4;
		if (value3 >= iso)
			window += 8;

		switch (window) {
		case 1:
			vertecies.add(new Vector2f(-1, 0));
			vertecies.add(new Vector2f(0, -1));
			
			indecies.add(0);
			indecies.add(1);
		default:
			break;
		}
	}

}