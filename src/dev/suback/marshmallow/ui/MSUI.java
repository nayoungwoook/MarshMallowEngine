package dev.suback.marshmallow.ui;

import java.awt.Graphics2D;

import dev.suback.marshmallow.object.MSObject;

public class MSUI extends MSObject {

	public MSUI(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void engineRender(Graphics2D g2d) {

		int xflip = 1, yflip = 1;

		if (flipX)
			xflip = -1;
		if (flipY)
			yflip = -1;

		g2d.setColor(pColor);
		float renderX = (int) position.getX() - getWidth() / 2, renderY = (int) position.getY() - getHeight() / 2;
		g2d.drawImage(getSprite().getImage(), (int) renderX, (int) renderY, getWidth() * xflip, getHeight() * yflip, null);
	}
}
