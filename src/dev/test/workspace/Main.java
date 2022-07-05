package dev.test.workspace;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import dev.suback.marshmallow.MSDisplay;
import dev.suback.marshmallow.camera.MSCamera;
import dev.suback.marshmallow.input.MSInput;
import dev.suback.marshmallow.object.MSSprite;
import dev.suback.marshmallow.object.shape.MSRender;
import dev.suback.marshmallow.resource.MSSound;
import dev.suback.marshmallow.state.MSState;

public class Main {

	public static MSDisplay display;
	public static WorkSpace workspace;

	public static final MSSound sound = new MSSound("Overworld.wav");

	public static void main(String[] args) {
		display = new MSDisplay("Workspace", 1280, 720);
		workspace = new WorkSpace();

		MSState.setState(workspace);
	}

}

class WorkSpace implements MSState {

	public static MSSprite sprite = new MSSprite("bigPinkCookie.png");

	@Override
	public void init() {
		// Main.sound.play();
		// Main.sound.startPosition = 12;
	}

	@Override
	public void update() {
		if (MSInput.keys[KeyEvent.VK_UP]) {
			MSCamera.position.translate(0, 0, 0.01);
		}
		if (MSInput.keys[KeyEvent.VK_DOWN]) {
			MSCamera.position.translate(0, 0, -0.01);
		}

		int _xv = 0, _yv = 0;
		if (MSInput.keys[KeyEvent.VK_W])
			_yv -= 10;
		if (MSInput.keys[KeyEvent.VK_S])
			_yv += 10;
		if (MSInput.keys[KeyEvent.VK_A])
			_xv -= 10;
		if (MSInput.keys[KeyEvent.VK_D])
			_xv += 10;

		if (MSInput.keys[KeyEvent.VK_E])
			MSCamera.rotation += 0.02;
		if (MSInput.keys[KeyEvent.VK_Q])
			MSCamera.rotation -= 0.02;

		MSCamera.position.translate(_xv, _yv);
	}

	@Override
	public void render() {

		MSRender.setColor(Color.red);
		MSRender.setFont(new Font("±Ã¼­", Font.BOLD, 50));
		MSRender.renderText("asdfa", 0, 0);

		MSRender.setColor(Color.black);
		MSRender.setFont(new Font("±Ã¼­", Font.BOLD, 50));
		MSRender.renderText("oh yes yes yes", 300, 260, 3);

		MSRender.setColor(Color.blue);
		MSRender.setFont(new Font("±Ã¼­", Font.BOLD, 30));
		MSRender.renderUIText("asdfa", 100, 50);

		MSRender.setColor(Color.blue);
		MSRender.renderUIRect(100, 50, 100, 100);
		MSRender.renderRect(100, 50, 100, 100);

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				MSRender.renderImage(sprite, 100 + i * 100, 100 + j * 100, 100, 100);
			}
		}

		// MSRender.renderImage(sprite, 0, 0, 300, 300);
	}

}
