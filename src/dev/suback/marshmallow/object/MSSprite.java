package dev.suback.marshmallow.object;

import java.awt.Color;
import java.awt.image.BufferedImage;

import dev.suback.marshmallow.resource.MSResource;

public class MSSprite {

	private BufferedImage image;
	public BufferedImage originalImage;

	public MSSprite(String path) {
		image = new MSResource().getImage(path);
		originalImage = clone();
	}

	public MSSprite(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public MSSprite cutImage(int x, int y, int w, int h) {
		BufferedImage cutImage = clone();
		return new MSSprite(cutImage.getSubimage(x, y, w, h));
	}

	public void setBrightness(int value) {
		BufferedImage _image = new MSSprite(originalImage).clone();

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
		this.image = _image;
	}
	
	public void setColorTint(Color c) {
		BufferedImage _image = new MSSprite(originalImage).clone();
		
		for (int i = 0; i < _image.getWidth(); i++) {
			for (int j = 0; j < _image.getHeight(); j++) {
				Color _c = new Color(_image.getRGB(i, j), true);
				int r = _c.getRed();
				int g = _c.getGreen();
				int b = _c.getBlue();
				int a = _c.getAlpha();
				
				if (a > 0) {
					r += c.getRed();
					g += c.getGreen();
					b += c.getBlue();
					
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
		this.image = _image;
	}
	
	public void setAlpha(int alpha) {
		BufferedImage _image = new MSSprite(originalImage).clone();
		
		for (int i = 0; i < _image.getWidth(); i++) {
			for (int j = 0; j < _image.getHeight(); j++) {
				Color _c = new Color(_image.getRGB(i, j), true);
				int r = _c.getRed();
				int g = _c.getGreen();
				int b = _c.getBlue();
				int a = _c.getAlpha();
				
				if (a > 0) {
					
					a += alpha;
					if(a > 255)
						a = 255;
					
					Color _rc = new Color(r, g, b, a);
					_image.setRGB(i, j, _rc.getRGB());
				}
			}
		}
		this.image = _image;
	}

	@Override
	public final BufferedImage clone() {
		BufferedImage _result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				Color c = new Color(image.getRGB(i, j), true);

				if (c.getAlpha() != 0)
					_result.setRGB(i, j, image.getRGB(i, j));
				else
					_result.setRGB(i, j, new Color(0, 0, 0, 0).getRGB());
			}
		}

		return _result;
	}

}
