package dev.suback.marshmallow.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import dev.suback.marshmallow.MSDisplay;
import dev.suback.marshmallow.camera.MSCamera;
import dev.suback.marshmallow.math.MSMath;
import dev.suback.marshmallow.transform.MSVector;

public class MSInput implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	public static boolean keys[] = new boolean[KeyEvent.KEY_LAST];
	public static int mouseScrollType;
	public static MSVector mousePointer = new MSVector(0, 0);
	public static MSVector mousePointerMaster = new MSVector(0, 0);
	public static MSVector screenMousePointer = new MSVector(0, 0);
	public static boolean mouseLeft, mouseRight, mouseCenter;

	@Override
	public void mouseDragged(MouseEvent e) {
		mousePointer.setX(e.getX());
		mousePointer.setY(e.getY());
	}

	public static void setMasterMouse() {
		int Width = MSDisplay.width, Height = MSDisplay.height;

		double _dist = MSMath.getDistance(
				new MSVector(Width / 2, Height / 2),
				new MSVector(mousePointer.getX(), mousePointer.getY()));
		double _rot = Math.atan2(Height / 2 - mousePointer.getY(),
				Width / 2 - mousePointer.getX()) - MSCamera.rotation;

		double xx = (mousePointer.getX() - (Width / 2 + MSCamera.position.getX()));
		double yy = (mousePointer.getY() - (Height / 2 + MSCamera.position.getY()));
		double _zDist = _dist / (MSCamera.position.getZ());

		double _zx = (Math.cos(_rot) * _zDist), _zy = (Math.sin(_rot) * _zDist);

		mousePointerMaster.setX((mousePointer.getX() - (xx + _zx)));
		mousePointerMaster.setY((mousePointer.getY() - (yy + _zy)));

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePointer.setX(e.getX());
		mousePointer.setY(e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			mouseLeft = true;
		}
		if (e.getButton() == MouseEvent.BUTTON2) {
			mouseCenter = true;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			mouseRight = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			mouseLeft = false;
		}
		if (e.getButton() == MouseEvent.BUTTON2) {
			mouseCenter = false;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			mouseRight = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			keys[e.getKeyCode()] = true;
		} catch (Exception f) {
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		try {
			keys[e.getKeyCode()] = false;
		} catch (Exception f) {
		}
	}

	public static void update() {
		setMasterMouse();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		mouseScrollType = (int) e.getPreciseWheelRotation();
	}
}
