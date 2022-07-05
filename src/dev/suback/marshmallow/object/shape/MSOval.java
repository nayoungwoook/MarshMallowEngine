package dev.suback.marshmallow.object.shape;

import java.awt.Graphics;

public class MSOval extends MSRender {

	public MSOval(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void engineRender(Graphics g2d) {
		calculateRender();
		g2d.setColor(pColor);
		g2d.fillOval((int) renderPosition.getX(), (int) renderPosition.getY(), (int) renderWidth, (int) renderHeight);
	}

}
