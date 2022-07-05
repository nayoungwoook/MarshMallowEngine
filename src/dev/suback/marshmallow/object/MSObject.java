package dev.suback.marshmallow.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import dev.suback.marshmallow.MSDisplay;
import dev.suback.marshmallow.MSMain;
import dev.suback.marshmallow.camera.MSCamera;
import dev.suback.marshmallow.math.MSMath;
import dev.suback.marshmallow.object.shape.MSRender;
import dev.suback.marshmallow.transform.MSVector;

public class MSObject implements Comparable<MSObject> {

	public MSVector position;
	public MSVector renderPosition;
	public double renderWidth, renderHeight;
	public MSVector anchor;
	public MSSprite sprite;
	private MSSprite subSprite;
	public boolean flipX, flipY;
	protected boolean isRender;
	private int width, height;
	public float rotation = 0f;
	public boolean visible = true;

	protected Color pColor;
	protected Font pFont;

	public MSObject(int x, int y, int width, int height) {
		position = new MSVector(x, y, 1f);
		renderPosition = new MSVector(x, y, 1f);
		anchor = new MSVector(0.5f, 0.5f);
		this.width = width;
		this.height = height;

		if (MSRender.getColor() != null) {
			pColor = MSRender.getColor();
		}

		if (MSRender.getFont() != null)
			pFont = MSRender.getFont();
	}

	public final MSVector getAnchor() {
		return anchor;
	}

	public final void setAnchor(MSVector anchor) {
		this.anchor = anchor;
	}

	public final void setPColor(Color pColor) {
		this.pColor = pColor;
	}

	public final MSSprite getSprite() {
		if (sprite != null)
			return sprite;
		else
			return null;
	}

	public void update() {
	}

	public void render() {
		MSMain.renderObjects.add(this);
	}

	protected boolean inScreen = false;

	public final boolean getInScreen() {
		return inScreen;
	}

	protected void calculateRender() {

		MSVector _pos = MSMath.toScreen(position);
		MSVector _size = MSMath.toScreenSize(width, height, flipX, flipY);

		this.renderWidth = _size.getX();
		this.renderHeight = _size.getY();

		double _ww = renderWidth / 2;
		double _hh = renderHeight / 2;

		this.renderPosition.setX(_pos.getX() - _ww);
		this.renderPosition.setY(_pos.getY() - _hh);
	}

	protected void calculateShouldRender() {
		this.isRender = true;
		if (MSMath.getDistance(this.renderPosition,
				new MSVector(MSDisplay.width / 2, MSDisplay.height / 2)) >= (MSDisplay.width / MSCamera.position.getZ())
						* 2) {
			this.isRender = false;
		}
	}

	public void engineRender(Graphics g) {

		calculateRender();
		calculateShouldRender();

		if (!visible || !isRender)
			return;

		Graphics2D g2d = (Graphics2D) g;

		AffineTransform backup = g2d.getTransform();

		AffineTransform a = new AffineTransform();

		a.translate(this.renderPosition.getX(), this.renderPosition.getY());

		a.rotate(this.rotation + MSCamera.rotation, this.renderWidth * anchor.getX(),
				this.renderHeight * anchor.getY());

		g2d.setTransform(a);

		if (sprite != null)
			g2d.drawImage(sprite.getImage(), 0, 0, (int) Math.ceil(renderWidth), (int) Math.ceil(renderHeight), null);

		g2d.setTransform(backup);
	}

	public final int getWidth() {
		return width;
	}

	public final int getHeight() {
		return height;
	}

	public final void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public final void setWidth(int width) {
		this.width = width;
	}

	public final void setHeight(int height) {
		this.height = height;
	}

	public final boolean isRendering() {
		return isRender;
	}

	public final boolean isVisible() {
		return visible;
	}

	public final void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setBrightness(int value) {
		if (getSprite() == null || getSprite().getImage() == null)
			return;
		BufferedImage _image = null;

		if (subSprite != null) {
			_image = subSprite.clone();

			for (int i = 0; i < _image.getWidth(); i++) {
				for (int j = 0; j < _image.getHeight(); j++) {
					Color _c = new Color(_image.getRGB(i, j), true);
					int r = _c.getRed();
					int g = _c.getGreen();
					int b = _c.getBlue();
					int a = _c.getAlpha();

					if (a > 0) {
						r += value;
						g += value;
						b += value;

						if (r < 0)
							r = 0;
						if (r > 255)
							r = 255;

						if (g < 0)
							g = 0;
						if (g > 255)
							g = 255;

						if (b < 0)
							b = 0;
						if (b > 255)
							b = 255;

						Color _rc = new Color(r, g, b, a);
						_image.setRGB(i, j, _rc.getRGB());
					}
				}
			}
		}

		sprite = new MSSprite(_image);
	}

	@Override
	public int compareTo(MSObject o) {
		if (this.position.getZ() < o.position.getZ()) {
			return -1;
		} else if (this.position.getZ() == o.position.getZ()) {
			return 0;
		} else {
			return 1;
		}
	}
}
