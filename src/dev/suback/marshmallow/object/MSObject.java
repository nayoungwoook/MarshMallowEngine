package dev.suback.marshmallow.object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import dev.suback.marshmallow.MSDisplay;
import dev.suback.marshmallow.MSMain;
import dev.suback.marshmallow.camera.MSCamera;
import dev.suback.marshmallow.math.MSMath;
import dev.suback.marshmallow.object.shape.MSRender;
import dev.suback.marshmallow.physics.MSCollider;
import dev.suback.marshmallow.resource.MSFont;
import dev.suback.marshmallow.transform.MSVector;

public class MSObject implements Comparable<MSObject> {

	public MSVector position;
	public MSVector renderPosition;
	public MSVector anchor;

	public ArrayList<MSCollider> colliders = new ArrayList<>();

	private int width, height;
	public double renderWidth, renderHeight;
	public boolean flipX, flipY;
	protected boolean isRender;

	public MSSprite sprite;

	public float rotation = 0f;
	public boolean visible = true;

	protected Color pColor;
	protected MSFont pFont;

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

		calculateShouldRender();
	}

	protected void calculateShouldRender() {
		this.isRender = true;
		if (MSMath.getDistance(this.renderPosition, new MSVector(MSDisplay.width / 2,
				MSDisplay.height / 2)) >= (MSDisplay.width / (MSCamera.position.getZ() / 4)) * 2) {
			this.isRender = false;
		}
	}

	public void engineRender(Graphics2D g2d) {
		calculateRender();

		if (!visible || !isRender)
			return;
		
		AffineTransform backup = g2d.getTransform();
		g2d.translate(this.renderPosition.getX(), this.renderPosition.getY());
		g2d.rotate(this.rotation + MSCamera.rotation, this.renderWidth * anchor.getX(),
				this.renderHeight * anchor.getY());

		if (sprite != null)
			g2d.drawImage(sprite.getImage(), (int) this.renderPosition.getX(), (int) this.renderPosition.getY(),
					(int) Math.ceil(renderWidth), (int) Math.ceil(renderHeight), null);
		
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

	public void renderCollider(boolean fill) {
		for (int i = 0; i < colliders.size(); i++)
			colliders.get(i).renderPivots(fill);
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
