package dev.suback.marshmallow.object.shape;

import java.awt.image.BufferedImage;

import dev.suback.marshmallow.object.MSObject;
import dev.suback.marshmallow.object.MSSprite;

public class MSImage extends MSObject {

	public MSImage(BufferedImage sprite, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.sprite.setImage(sprite);
	}

	public MSImage(MSSprite sprite, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.sprite = sprite;
	}

}
