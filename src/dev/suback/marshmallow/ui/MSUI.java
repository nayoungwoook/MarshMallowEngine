package dev.suback.marshmallow.ui;

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import dev.suback.marshmallow.object.MSObject;

public class MSUI extends MSObject {

	public MSUI(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void engineRender(Graphics g) {

		int xflip = 1, yflip = 1;

		if (flipX)
			xflip = -1;
		if (flipY)
			yflip = -1;

		Graphics2D g2d = (Graphics2D) g;

		g.setColor(pColor);
		AffineTransform backup = g2d.getTransform();

		float renderX = (int) position.getX() - getWidth() / 2, renderY = (int) position.getY() - getHeight() / 2;

		AffineTransform a = AffineTransform.getRotateInstance(rotation, renderX + getAnchor().getX() * xflip,
				renderY + getAnchor().getY() * yflip);

		g2d.setTransform(a);
		g2d.drawImage(getSprite().getImage(), (int) renderX, (int) renderY, getWidth(), getHeight(), null);
		g2d.setTransform(backup);

	}
}
