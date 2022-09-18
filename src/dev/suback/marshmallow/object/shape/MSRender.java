package dev.suback.marshmallow.object.shape;

import java.awt.Color;
import java.util.ArrayList;

import dev.suback.marshmallow.MSMain;
import dev.suback.marshmallow.object.MSObject;
import dev.suback.marshmallow.object.MSSprite;
import dev.suback.marshmallow.resource.MSFont;
import dev.suback.marshmallow.transform.MSVector;
import dev.suback.marshmallow.ui.MSUI;
import dev.suback.marshmallow.ui.MSUIRect;
import dev.suback.marshmallow.ui.MSUIText;

public class MSRender extends MSObject {

	public MSRender(int x, int y, int width, int height) {
		super(x, y, width, height);
		pColor = MSRender.color;
		pFont = MSRender.font;
	}

	private static Color color = Color.black;
	private static MSFont font = null;

	public static final void setFont(MSFont f) {
		font = f;
	}

	public static final MSFont getFont() {
		return font;
	}

	public static final Color getColor() {
		return color;
	}

	public static final void setColor(Color c) {
		color = c;
	}

	public static final void renderRect(int x, int y, int width, int height) {
		MSRect rect = new MSRect(x, y, width, height);
		rect.position.setZ(2f);
		MSMain.renderObjects.add(rect);
	}

	public static final void renderRect(int x, int y, double z, int width, int height) {
		MSRect rect = new MSRect(x, y, width, height);
		rect.position.setZ(z);
		MSMain.renderObjects.add(rect);
	}

	public static final void renderOval(int x, int y, int width, int height) {
		MSOval oval = new MSOval(x, y, width, height);
		oval.position.setZ(2f);
		MSMain.renderObjects.add(oval);
	}

	public static final void renderOval(int x, int y, double z, int width, int height) {
		MSOval oval = new MSOval(x, y, width, height);
		oval.position.setZ(z);
		MSMain.renderObjects.add(oval);
	}

	public static final void renderImage(MSSprite sprite, int x, int y, int width, int height) {
		MSImage image = new MSImage(sprite, x, y, width, height);
		image.position.setZ(2f);
		MSMain.renderObjects.add(image);
	}

	public static final void renderImage(MSSprite sprite, int x, int y, double z, int width, int height) {
		MSImage image = new MSImage(sprite, x, y, width, height);
		image.position.setZ(z);
		MSMain.renderObjects.add(image);
	}

	public static final void renderText(String text, int x, int y) {
		MSText tex = new MSText(text, x, y);
		tex.position.setZ(2f);
		MSMain.renderObjects.add(tex);
	}

	public static final void renderText(String text, int x, int y, double z) {
		MSText tex = new MSText(text, x, y);
		tex.position.setZ(z);
		MSMain.renderObjects.add(tex);
	}

	public static final void renderText(String text, int x, int y, String align) {
		MSText tex = new MSText(text, x, y);
		tex.textAlign = align;
		MSMain.renderObjects.add(tex);
	}
	
	public static final void renderText(String text, int x, int y, double z, String align) {
		MSText tex = new MSText(text, x, y);
		tex.position.setZ(z);
		tex.textAlign = align;
		MSMain.renderObjects.add(tex);
	}

	public static final void renderPolygon(ArrayList<MSVector> pivots) {
		MSPoly poly = new MSPoly(pivots);
		MSMain.renderObjects.add(poly);
	}

	public static final void renderPolygon(MSPoly poly) {
		MSMain.renderObjects.add(poly);
	}

	///////////////////////////////////////////////////////////////

	public static final void renderText(MSText tex) {
		MSMain.renderObjects.add(tex);
	}

	public static final void renderImage(MSImage image) {
		MSMain.renderObjects.add(image);
	}

	public static final void renderRect(MSRect rect) {
		MSMain.renderObjects.add(rect);
	}

	public static final void renderRect(MSOval oval) {
		MSMain.renderObjects.add(oval);
	}

	// UI

	public static final void renderUI(MSUI ui) {
		MSMain.renderObjects.add(ui);
	}

	public static final void renderUIImage(MSSprite sprite, int x, int y, int width, int height) {
		MSUI uiImage = new MSUI(x, y, width, height);
		uiImage.sprite = sprite;
		MSMain.renderObjects.add(uiImage);
	}

	public static final void renderUIImage(MSSprite sprite, int x, int y, double z, int width, int height) {
		MSUI uiImage = new MSUI(x, y, width, height);
		uiImage.position.setZ(z);
		uiImage.sprite = sprite;
		MSMain.renderObjects.add(uiImage);
	}

	public static final void renderUIImage(MSSprite sprite, int x, int y, double z, float rotation, int width,
			int height) {
		MSUI uiImage = new MSUI(x, y, width, height);
		uiImage.rotation = rotation;
		uiImage.position.setZ(z);
		uiImage.sprite = sprite;
		MSMain.renderObjects.add(uiImage);
	}

	public static final void renderUIRect(int x, int y, int width, int height) {
		MSUIRect rect = new MSUIRect(x, y, width, height);
		MSMain.renderObjects.add(rect);
	}

	public static final void renderUIRect(int x, int y, double z, int width, int height) {
		MSUIRect rect = new MSUIRect(x, y, width, height);
		rect.position.setZ(z);
		MSMain.renderObjects.add(rect);
	}

	public static final void renderUIText(String text, int x, int y) {
		MSUIText uiText = new MSUIText(text, x, y, 100, 100);
		MSMain.renderObjects.add(uiText);
	}

	public static final void renderUIText(String text, int x, int y, double z) {
		MSUIText uiText = new MSUIText(text, x, y, 100, 100);
		uiText.position.setZ(z);
		MSMain.renderObjects.add(uiText);
	}

	public static final void renderUIText(String text, int x, int y, String align) {
		MSUIText uiText = new MSUIText(text, x, y, 100, 100);
		uiText.textAlign = align;
		MSMain.renderObjects.add(uiText);
	}

	public static final void renderUIText(String text, int x, int y, double z, String align) {
		MSUIText uiText = new MSUIText(text, x, y, 100, 100);
		uiText.textAlign = align;
		uiText.position.setZ(z);
		MSMain.renderObjects.add(uiText);
	}

	public static final void renderUIText(String text, int x, int y, double z, float rotation) {
		MSUIText uiText = new MSUIText(text, x, y, 100, 100);
		uiText.rotation = rotation;
		uiText.position.setZ(z);
		MSMain.renderObjects.add(uiText);
	}

}
