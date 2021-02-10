package engine.gui.animation;

public abstract class Animation {
	
	protected Animator animator;
	public abstract void updateAnimation();
	protected abstract void startAnimation();
	
	public void initAnimation(Animator animator) {
		this.animator = animator;
		startAnimation();
		
	}
	protected void endAnimation() {
		animator.getComponent().setLoose(false);
		animator.removeAnimation(this);
	}
	

}
