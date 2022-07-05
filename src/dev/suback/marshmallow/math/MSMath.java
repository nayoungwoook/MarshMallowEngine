package dev.suback.marshmallow.math;

import java.util.ArrayList;

import dev.suback.marshmallow.MSDisplay;
import dev.suback.marshmallow.camera.MSCamera;
import dev.suback.marshmallow.object.MSObject;
import dev.suback.marshmallow.transform.MSVector;

public class MSMath {

	public static float getDistance(MSVector position, MSVector position2) {
		return (float) Math.abs(Math.sqrt(((position.getX() - position2.getX()) * (position.getX() - position2.getX())
				+ (position.getY() - position2.getY()) * (position.getY() - position2.getY()))));
	}

	public static float getAngle(MSVector position, MSVector position2) {
		return (float) Math.atan2(position2.getY() - position.getY(), position2.getX() - position.getX());
	}

	public static float getXv(float moveSpeed, MSVector position, MSVector position2) {
		return (float) Math.cos(getAngle(position, position2)) * moveSpeed;
	}

	public static float getYv(float moveSpeed, MSVector position, MSVector position2) {
		return (float) Math.sin(getAngle(position, position2)) * moveSpeed;
	}

	public static MSVector toScreenSize(int width, int height, boolean flipX, boolean flipY) {
		double fx = 1, fy = 1;

		double renderWidth, renderHeight;

		renderWidth = width * (MSCamera.position.getZ());
		renderHeight = height * (MSCamera.position.getZ());

		if (flipX) {
			fx = -1;
		}

		if (flipY) {
			fy = -1;
		}

		renderWidth *= (fx);
		renderHeight *= (fy);

		return new MSVector(renderWidth, renderHeight);
	}

	public static MSVector toScreen(MSVector position) {

		int Width = MSDisplay.width, Height = MSDisplay.height;

		double _dist = MSMath.getDistance(
				new MSVector(Width / 2 + MSCamera.position.getX(), Height / 2 + MSCamera.position.getY()),
				new MSVector(position.getX(), position.getY()));
		double _rot = Math.atan2(Height / 2 + MSCamera.position.getY() - position.getY(),
				Width / 2 + MSCamera.position.getX() - position.getX()) + MSCamera.rotation;
		double xx = (position.getX() - (Width / 2 + MSCamera.position.getX()));
		double yy = (position.getY() - (Height / 2 + MSCamera.position.getY()));
		double _zDist = _dist * (MSCamera.position.getZ());

		double _zx = (Math.cos(_rot) * _zDist), _zy = (Math.sin(_rot) * _zDist);

		MSVector result = new MSVector(0, 0);
		
		result.setX((position.getX() - MSCamera.position.getX() - (xx + _zx)));
		result.setY((position.getY() - MSCamera.position.getY() - (yy + _zy)));

		return result;
	}

	public static MSVector toWorld(MSVector position) {

		int Width = MSDisplay.width, Height = MSDisplay.height;

		double _dist = MSMath.getDistance(
				new MSVector(Width / 2 + MSCamera.position.getX(), Height / 2 + MSCamera.position.getY()),
				new MSVector(position.getX(), position.getY()));
		double _rot = Math.atan2(Height / 2 + MSCamera.position.getY() - position.getY(),
				Width / 2 + MSCamera.position.getX() - position.getX()) + MSCamera.rotation;
		double xx = (position.getX() + (Width / 2 + MSCamera.position.getX()));
		double yy = (position.getY() + (Height / 2 + MSCamera.position.getY()));
		double _zDist = _dist * (MSCamera.position.getZ());

		double _zx = (Math.cos(_rot) / _zDist), _zy = (Math.sin(_rot) / _zDist);

		MSVector result = new MSVector(0, 0);

		result.setX((position.getX() + MSCamera.position.getX() - (xx + _zx)));
		result.setY((position.getY() + MSCamera.position.getY() - (yy + _zy)));
		return result;
	}

	public static MSObject getShortestDistance(MSVector pos, ArrayList<MSObject> list) {
		boolean _firstTry = true;
		float _shortDist = 0, _nowDist;
		int _shortIndex = -1;

		for (int i = 0; i < list.size(); i++) {
			_nowDist = getDistance(list.get(i).position, pos);
			if (_firstTry) {
				_firstTry = false;
				_shortDist = _nowDist;
				_shortIndex = i;
			} else if (_shortDist > _nowDist) {
				_shortDist = _nowDist;
				_shortIndex = i;
			}
		}

		if (_shortIndex == -1)
			return null;
		else
			return list.get(_shortIndex);
	}

}
