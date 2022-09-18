package dev.suback.marshmallow.object.shape;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import dev.suback.marshmallow.camera.MSCamera;

public class MSOval extends MSRender {

	public MSOval(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void engineRender(Graphics2D g2d) {

		calculateRender();
		
		if (!visible || !isRender)
			return;

		AffineTransform backup = g2d.getTransform();

		g2d.translate(this.renderPosition.getX(), this.renderPosition.getY());

		g2d.rotate(this.rotation + MSCamera.rotation, this.renderWidth * anchor.getX(),
				this.renderHeight * anchor.getY());

		g2d.setColor(pColor);
		g2d.fillOval(0, 0, (int) renderWidth, (int) renderHeight);

		g2d.setTransform(backup);
	}

}
