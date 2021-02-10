package engine.gui.component;

import java.util.LinkedList;
import java.util.List;

import engine.gui.animation.Animator;
import engine.gui.constaint.Constaint;
import engine.gui.constaint.WindowSideConstaint;
import tools.vector.Vector2f;

public abstract class Component {

	protected Vector2f position = new Vector2f(0, 0);
	protected Vector2f scale = new Vector2f(1, 1);
	protected float rotation;

	protected Constaint constaint;
	protected Animator animator = new Animator(this);
	private List<Component> componentList = new LinkedList<Component>();
	private int number = 0;
	protected boolean visable = true;
	protected boolean isLoose = false;
	private boolean hovered = false;

	public Component() {
		constaint = new WindowSideConstaint();
		constaint.loadConstaint(this);
	}

	public Component(Constaint constaint) {
		this.constaint = constaint;
		constaint.loadConstaint(this);
	}

	public boolean isInComponent(Vector2f location) {
		Vector2f p = Vector2f.sub(location, position, null);
		p.x = Math.abs(p.x);
		p.y = Math.abs(p.y);

		if (p.x < scale.x && p.y < scale.y)
			return true;
		return false;
	}

	public void add(Component component) {
		componentList.add(component);
	}

	public void add(Component component, int number) {
		component.setNumber(number);
		componentList.add(component);
	}

	public void remove(Component component) {
		componentList.remove(component);
	}
	public void setPosition(Vector2f relativePosition) {
		if (!isLoose)
			scale(Vector2f.sub(this.position, relativePosition, null));
	}

	private void scale(Vector2f translate) {
		position.sub(translate);
		for (Component comp : componentList) {
			comp.scale(translate);
		}
	}

	public Vector2f getPosition() {
		return position;
	}



	public void setConstaint(Constaint constaint) {
		this.constaint = constaint;
	}

	public Animator getAnimator() {
		return animator;
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}

	public void correctScale(Vector2f newScale) {
		this.scale = newScale;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public List<Component> getComponentList() {
		return componentList;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Constaint getConstaint() {
		return constaint;
	}

	public boolean isVisable() {
		return visable;
	}

	public void setVisable(boolean visable) {
		this.visable = visable;
	}

	public boolean isLoose() {
		return isLoose;
	}

	public void setLoose(boolean isLoose) {
		this.isLoose = isLoose;
	}

	public boolean isHovered() {
		return hovered;
	}

	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\n[" + getClass().getName() + ": " + position.toString() + ", " + scale.toString() + ", Visable = "
				+ visable + "]";
	}

}
