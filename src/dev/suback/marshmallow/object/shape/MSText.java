package dev.suback.marshmallow.object.shape;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import dev.suback.marshmallow.camera.MSCamera;
import dev.suback.marshmallow.object.MSObject;

public class MSText extends MSObject {

	private String text;
	public String textAlign = "center";

	public MSText(String text, int x, int y) {
		super(x, y, 0, 0);
		this.text = text;
	}

	@Override
	public void engineRender(Graphics2D g2d) {
		calculateRender();

		if (pFont != null)
			g2d.setFont(pFont.font);

		FontMetrics metrics = g2d.getFontMetrics();
		int x = 0;

		if (textAlign.equals("center"))
			x = (int) (position.getX() - metrics.stringWidth(text) / 2);
		else if (textAlign.equals("right"))
			x = (int) (position.getX() - metrics.stringWidth(text));
		else
			x = (int) (position.getX());

		int y = (int) position.getY() + metrics.getHeight() / 2;

		AffineTransform backup = g2d.getTransform();
		g2d.translate(this.renderPosition.getX(), this.renderPosition.getY());
		g2d.rotate(this.rotation + MSCamera.rotation, this.renderWidth * anchor.getX(),
				this.renderHeight * anchor.getY());

		g2d.setColor(pColor);
		g2d.drawString(text, 0, 0);
		
		g2d.setTransform(backup);
	}

}
