package engine.gui.animation;

import tools.vector.Vector2f;

public class FadeInAnimation extends Animation {

	public static final int FROM_LEFT = 0;
	public static final int FROM_RIGHT = 1;
	public static final int FROM_UP = 2;
	public static final int FROM_DOWN = 3;
	
	private int direction;
	private float toMove;
	private float speed;
	public FadeInAnimation(int direction, float travel, float speedPerFrame) {
		super();
		this.direction = direction;
		toMove = travel;
		speed = speedPerFrame;
	}

	@Override
	protected void startAnimation() {
		Vector2f position = animator.getComponent().getPosition();
		switch(direction) {
	case FROM_LEFT:
		position.x-=toMove;
		break;
	case FROM_RIGHT:
		position.x+=toMove;
		break;
	case FROM_UP:
		position.y+=toMove;
		break;
	case FROM_DOWN:
		position.y-=toMove;
		break;
	}
	}
	
	
	@Override
	public void updateAnimation() {
		
		Vector2f position = animator.getComponent().getPosition();
		switch(direction) {
		case FROM_LEFT:
			position.x+=speed;
			break;
		case FROM_RIGHT:
			position.x-=speed;
			break;
		case FROM_UP:
			position.y-=speed;
			break;
		case FROM_DOWN:
			position.y+=speed;
			break;
		}
		toMove-=speed;
		if(toMove<=0) 
			endAnimation();
	}

}
