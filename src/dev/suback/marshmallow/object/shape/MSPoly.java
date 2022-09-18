package dev.suback.marshmallow.object.shape;

import java.awt.Graphics2D;
import java.util.ArrayList;

import dev.suback.marshmallow.math.MSMath;
import dev.suback.marshmallow.object.MSObject;
import dev.suback.marshmallow.transform.MSVector;

public class MSPoly extends MSObject {

	public ArrayList<MSVector> pivots = new ArrayList<>();
	
	public MSPoly(MSVector ... pivots) {
		super(0, 0, 0, 0);
		
		for(MSVector v : pivots) {
			this.pivots.add(v);
		}
	}
	
	public MSPoly(ArrayList<MSVector> pivots) {
		super(0, 0, 0, 0);
		
		this.pivots.addAll(pivots);
	}

	@Override
	public void engineRender(Graphics2D g2d) {
		
		if (!visible)
			return;
		
		ArrayList<MSVector> renderPivots = new ArrayList<>();
		int xx[] = new int[pivots.size()], yy[] = new int[pivots.size()];
		
		for(int i=0; i<pivots.size(); i++) {
			renderPivots.add(MSMath.toScreen(pivots.get(i)));
			xx[i] = (int) renderPivots.get(i).getX();
			yy[i] = (int) renderPivots.get(i).getY();
		}
		
		g2d.setColor(pColor);
		g2d.fillPolygon(xx, yy, pivots.size());
	}

}
