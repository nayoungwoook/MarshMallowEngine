package dev.suback.marshmallow.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class MSUIRect extends MSUI {

	public MSUIRect(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void engineRender(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(pColor);

		float renderX = (int) position.getX() - getWidth() / 2, renderY = (int) position.getY() - getHeight() / 2;

		g2d.fillRect((int) renderX, (int) renderY, getWidth(), getHeight());
	}

}
 