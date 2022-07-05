package dev.suback.marshmallow.object.shape;

import java.awt.FontMetrics;
import java.awt.Graphics;

import dev.suback.marshmallow.object.MSObject;

public class MSText extends MSObject {

	private String text;
	public String textAlign = "center";

	public MSText(String text, int x, int y) {
		super(x, y, 0, 0);
		this.text = text;
	}

	@Override
	public void engineRender(Graphics g2d) {
		calculateRender();

		FontMetrics metrics = g2d.getFontMetrics();
		int x = 0;

		if (textAlign.equals("center"))
			x = (int) (renderPosition.getX() - metrics.stringWidth(text) / 2);
		else if (textAlign.equals("left"))
			x = (int) (renderPosition.getX() - metrics.stringWidth(text));
		else
			x = (int) (renderPosition.getX() + metrics.stringWidth(text) / 2);

		int y = (int) (metrics.getAscent()
				+ (renderPosition.getY() - (metrics.getAscent() - metrics.getDescent())) / 2);

		g2d.setColor(pColor);
		g2d.setFont(pFont);
		g2d.drawString(text, x, y);
	}

}
