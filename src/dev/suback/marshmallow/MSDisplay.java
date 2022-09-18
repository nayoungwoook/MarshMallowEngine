package dev.suback.marshmallow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MSDisplay extends JFrame {

	private static final long serialVersionUID = 1L;
	public static int width, height;
	public static String title;
	public static MSMain compo;

	public MSDisplay(String title, int width, int height) {

		MSDisplay.title = title;
		MSDisplay.width = width;
		MSDisplay.height = height;

		compo = new MSMain();
		compo.setPreferredSize(new Dimension(width, height));
		compo.setMaximumSize(new Dimension(width, height));
		compo.setMinimumSize(new Dimension(width, height));
		compo.setSize(width, height);

		setLayout(new BorderLayout());
		add(compo);
		pack();

		Rectangle rc = new Rectangle();
		rc.width = width;
		rc.height = height;
		setMaximizedBounds(rc);
		setMaximizedBounds(new Rectangle(width, height));
		setTitle(title);
		setResizable(false);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusable(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setWindowedScreen(int width, int height, boolean deco) {
		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = graphics.getDefaultScreenDevice();

		Rectangle rc = new Rectangle();
		rc.width = width;
		rc.height = height;
		setMaximizedBounds(rc);

		dispose();
		device.setFullScreenWindow(null);
		device.setDisplayMode(new DisplayMode(1920, 1080, 64, 0));
		setUndecorated(!deco);
		setVisible(true);
		setSize(width, height);
		requestFocus();
	}

	public void setFullScreen() {
		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = graphics.getDefaultScreenDevice();
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

		width = (int) size.getWidth();
		height = (int) size.getHeight();

		dispose();
		setUndecorated(true);
		setResizable(false);
		device.setFullScreenWindow(this);
		requestFocus();
	}

}
