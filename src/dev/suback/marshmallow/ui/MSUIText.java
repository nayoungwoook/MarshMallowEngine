package dev.suback.marshmallow.ui;

import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class MSUIText extends MSUI {

	public String text;
	public String textAlign = "center";

	public MSUIText(String string, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.text = string;
	}

	@Override
	public void engineRender(Graphics2D g2d) {

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

		g2d.setColor(pColor);
		g2d.drawString(text, x, y);

	}
}
