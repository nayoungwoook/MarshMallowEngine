package dev.suback.marshmallow;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Collections;

import dev.suback.marshmallow.input.MSInput;
import dev.suback.marshmallow.object.MSObject;
import dev.suback.marshmallow.state.MSState;

public class MSMain extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static MSState state;

	public MSMain() {
		addKeyListener(new MSInput());
		addMouseListener(new MSInput());
		addMouseMotionListener(new MSInput());
		addMouseWheelListener(new MSInput());
		start();
	}

	public synchronized void start() {
		new Thread(this, "MainThread").start();
	}

	public void init() {
	}

	public void update() {
		if (state != null)
			state.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			try {
				createBufferStrategy(2);
			} catch (Exception e) {
			}
			return;
		}

		Graphics g;

		try {
			g = bs.getDrawGraphics();
		} catch (Exception e) {
			return;
		}
		
		renderObjects.clear();
	
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0, MSDisplay.width, MSDisplay.height);
		
		if (state != null)
			state.render();

		Collections.sort(renderObjects);
		
		renderImage(g2d);

		try {
			bs.show();
			g2d.dispose();
		} catch (Exception e) {
		}
	}

	public static ArrayList<MSObject> renderObjects = new ArrayList<>();
	public static int objectCount = 0;

	private void renderImage(Graphics2D g) {
		for (int i = 0; i < renderObjects.size(); i++) {
			renderObjects.get(i).engineRender(g);
		}
		objectCount = renderObjects.size();
	}

	public static int targetFps = 60, targetUps = 60;
	public static int fps = 60, ups = 60;

	@Override
	public void run() {
		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / targetUps;
		final double timeF = 1000000000 / targetFps;
		double deltaU = 0, deltaF = 0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();

		init();

		while (true) {

			long currentTime = System.nanoTime();
			deltaU += (currentTime - initialTime) / timeU;
			deltaF += (currentTime - initialTime) / timeF;
			initialTime = currentTime;

			if (deltaU >= 1) {
				MSInput.update();
				update();
				ticks++;
				deltaU--;
			}

			if (deltaF >= 1) {
				render();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				System.out.println(String.format("UPS : %s, FPS : %s", ticks, frames));
				ups = ticks;
				fps = frames;
				frames = 0;
				ticks = 0;
				timer += 1000;
			}
		}
	}

}
