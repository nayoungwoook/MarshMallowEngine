package dev.suback.marshmallow.ui;

import java.awt.event.MouseEvent;

import dev.suback.marshmallow.input.MSInput;

public class MSButton extends MSUI {

	private boolean onMouse = false;

	public MSButton(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void update() {
		onMouse = false;
		if ((position.getX() - getWidth() / 2) <= MSInput.mousePointer.getX()
				&& (position.getX() + getWidth() / 2) >= MSInput.mousePointer.getX()) {
			if ((position.getY() - getHeight() / 2) <= MSInput.mousePointer.getY()
					&& (position.getY() + getHeight() / 2) >= MSInput.mousePointer.getY()) {
				onMouse = true;
				if (MSInput.mouseLeft) {
					onClick(MouseEvent.BUTTON1);
					MSInput.mouseLeft = false;
				}
				if (MSInput.mouseCenter) {
					onClick(MouseEvent.BUTTON2);
					MSInput.mouseCenter = false;
				}
				if (MSInput.mouseRight) {
					onClick(MouseEvent.BUTTON3);
					MSInput.mouseRight = false;
				}
			}
		}
	}

	public void onClick(int button) {
	}

	public boolean isOnMouse() {
		return onMouse;
	}

}
