package dev.suback.marshmallow.object.shape;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import dev.suback.marshmallow.camera.MSCamera;
import dev.suback.marshmallow.object.MSObject;

public class MSRect extends MSObject {

	public MSRect(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void engineRender(Graphics g2d) {
		calculateRender();
		calculateShouldRender();

		renderShape((Graphics2D) g2d);
	}

	public void renderShape(Graphics2D g2d) {
		if (!visible || !isRender)
			return;

		AffineTransform backup = g2d.getTransform();

		AffineTransform a = new AffineTransform();

		a.translate(this.renderPosition.getX(), this.renderPosition.getY());

		a.rotate(this.rotation + MSCamera.rotation, this.renderWidth * anchor.getX(),
				this.renderHeight * anchor.getY());

		g2d.setTransform(a);
		g2d.setColor(pColor);

		g2d.fillRect(0, 0, (int) Math.ceil(renderWidth), (int) Math.ceil(renderHeight));

		g2d.setTransform(backup);
	}

}
