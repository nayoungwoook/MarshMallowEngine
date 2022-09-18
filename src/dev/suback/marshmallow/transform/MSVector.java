package dev.suback.marshmallow.transform;

public class MSVector {

	private double x, y, z;

	public MSVector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public MSVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public MSVector cloneVector() {
		MSVector result = new MSVector(0, 0);
		result.setTransform(x, y, z);
		return result;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public void setTransform(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setTransform(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void translate(double xv, double yv) {
		this.x += xv;
		this.y += yv;
	}

	public void translate(double xv, double yv, double zv) {
		this.x += xv;
		this.y += yv;
		this.z += zv;
	}

}
