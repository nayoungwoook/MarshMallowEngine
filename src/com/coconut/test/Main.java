package com.coconut.test;

import java.awt.Color;
import java.awt.event.KeyEvent;

import dev.suback.marshmallow.MSDisplay;
import dev.suback.marshmallow.camera.MSCamera;
import dev.suback.marshmallow.input.MSInput;
import dev.suback.marshmallow.object.MSSprite;
import dev.suback.marshmallow.object.shape.MSRender;
import dev.suback.marshmallow.state.MSState;

public class Main {

	public static Workspace workspace;
	public static MSDisplay display;

	public static void main(String[] args) {
		workspace = new Workspace();
		display = new MSDisplay("Workspace", 1280, 720);

		MSState.setState(workspace);
	}

}

class Workspace implements MSState {

	private MSSprite sprite = new MSSprite("img/bigPinkCookie.png");

	@Override
	public void init() {

	}

	private boolean fullScreen = false;
	private double xv = 0, yv = 0;

	@Override
	public void update() {
		if (MSInput.keys[KeyEvent.VK_F11]) {

			fullScreen = !fullScreen;

			if (fullScreen)
				Main.display.setFullScreen();
			else
				Main.display.setWindowedScreen(1280, 720, true);

			MSInput.keys[KeyEvent.VK_F11] = false;
		}

		if (MSInput.keys[KeyEvent.VK_W]) {
			yv -= 4;
		}
		if (MSInput.keys[KeyEvent.VK_S]) {
			yv += 4;
		}
		if (MSInput.keys[KeyEvent.VK_A]) {
			xv -= 4;
		}
		if (MSInput.keys[KeyEvent.VK_D]) {
			xv += 4;
		}

		xv += (0 - xv) / 5;
		yv += (0 - yv) / 5;

		MSCamera.position.translate(xv, yv);

		double scr = MSInput.mouseScrollType;
		MSCamera.position.translate(0, 0, -(scr / 10));
		MSInput.mouseScrollType = 0;
	}

	@Override
	public void render() {
		MSRender.setColor(new Color(20, 20, 20));
		MSRender.renderRect(MSDisplay.width / 2, MSDisplay.height / 2, MSDisplay.width, MSDisplay.height);

		for (int i = 0; i < 100; i++)
			for (int j = 0; j < 100; j++)
				MSRender.renderImage(sprite, MSDisplay.width / 2 + (-50 + i) * 20,
						MSDisplay.height / 2 + (-50 + j) * 20, 20, 20);
	}
}
