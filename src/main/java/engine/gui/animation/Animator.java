package engine.gui.animation;

import java.util.LinkedList;
import java.util.List;

import engine.gui.component.Component;

public class Animator {

	private List<Animation> animationList = new LinkedList<Animation>();
	private Component component;
	public Animator(Component component) {
		this.component = component;
	}
	public void addAnimation(Animation animation) {
		this.component.setLoose(true);
		animationList.add(animation);
		animation.initAnimation(this);
		
	}
	public void removeAnimation(Animation animation) {
		animationList.remove(animation);
	}
	
	public Component getComponent() {
		return component;
	}
	public List<Animation> getAnimationList() {
		return animationList;
	}
	
	
}
