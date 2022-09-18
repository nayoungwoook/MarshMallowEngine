package dev.suback.marshmallow.physics;

import java.awt.Color;
import java.util.ArrayList;

import dev.suback.marshmallow.MSMain;
import dev.suback.marshmallow.object.shape.MSPoly;
import dev.suback.marshmallow.object.shape.MSRender;
import dev.suback.marshmallow.transform.MSVector;

public class MSCollider {

	public ArrayList<MSVector> pivots = new ArrayList<>();

	public void renderPivots(boolean fill) {
		MSRender.setColor(Color.green);
		MSMain.renderObjects.add(new MSPoly(pivots));
	}

}
