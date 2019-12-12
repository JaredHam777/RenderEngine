import java.awt.Point;

public class Vector3 {
	
	public static final Vector3 ZERO = new Vector3(0,0,0);
	
	public double x;
	public double y;
	public double z;
	
	public final static Vector3 X_AXIS = new Vector3(1,0,0);
	public final static Vector3 Y_AXIS = new Vector3(0,1,0);
	public final static Vector3 Z_AXIS = new Vector3(0,0,1);
	
	public Vector3(double x, double y, double z) {
		this.x = x;	this.y = y; this.z = z;
	}
	
	
	
	//vector functions:
	public Vector3 plus (Vector3 v) {
		return new Vector3(this.x + v.x, this.y + v.y, this.z + v.z);
	}
	public Vector3 minus (Vector3 v) {
		return new Vector3(this.x - v.x, this.y - v.y, this.z - v.z);
	}
	public boolean equals (Vector3 v) {
		if(v==null) {return false;}
		if(this.x == v.x && this.y == v.y && this.z == v.z)	{
			return true;
		}
		return false;
	}
	public double dot (Vector3 v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}
	public Vector3 cross (Vector3 v)	{
		return new Vector3(this.y*v.z-this.z*v.y, -(this.x*v.z-this.z*v.x), this.x*v.y-this.y*v.x);
	}
	public Vector3 scale(double a)	{
		return new Vector3(this.x*a, this.y*a, this.z*a);
	}
	public double squareMagnitude() {
		return Math.pow(this.x, 2)+Math.pow(this.y, 2)+Math.pow(this.z, 2);
	}
	public double magnitude() {
		return Math.sqrt(this.squareMagnitude());
	}
	
	public Vector3 project(Vector3 v) {
		Vector3 returnV;	
		returnV = v.scale((this.dot(v)/v.squareMagnitude()));
		return returnV;
	}
	
	public Vector3 normalized() {
		Vector3 returnV = new Vector3(this.x, this.y, this.z);
		returnV.normalize();
		return returnV;
	}
	
	public double angle(Vector3 v) {
		return Math.acos((this.dot(v))/(this.magnitude()*v.magnitude()));
	}
	
	public Vector3 rotate(Vector3 axis, double angle) throws MatrixDimensionException {

		Quaternion q = new Quaternion(angle, axis);
		Matrix point = new Matrix(3,1);		
		point.setVal(0, 0, this.x);
		point.setVal(1, 0, this.y);
		point.setVal(2, 0, this.z);
		
		Matrix newPoint = (q.R.multiply(point));
		
		//q.R.print();
		//newPoint.print();
		
		//newPoint.print();
		
	
		
		return new Vector3(newPoint.getVal(0, 0), newPoint.getVal(1, 0), newPoint.getVal(2, 0));
		

		
		
	}
	
	public Vector2 augmentedPosition(Camera camera)	{
		Vector3 distanceVect = this.project(camera.forward);
		Vector3 upVect = this.project(camera.up);
		Vector3 rightVect = this.project(camera.right);
		
		double planeWidth = 2*distanceVect.magnitude()*Math.sin(camera.horizontalFOV/2);
		double planeHeight = 2*distanceVect.magnitude()*Math.sin(camera.verticalFOV/2);
		
		Vector2 p = new Vector2(0,0);
		
		//System.out.println("this point's "); this.print(); System.out.print(" projection onto "); camera.right.print();
		//System.out.println("= right vect:");
		rightVect.print();
		//System.out.println("plane width: " + planeWidth);
		if(rightVect.dot(camera.right)>=0)	{
			p.x = ((2*rightVect.magnitude())/planeWidth);
		}	else	{
			p.x = -((2*rightVect.magnitude())/planeWidth);
		}
		if(upVect.dot(camera.up)>=0)	{
			p.y = -((2*upVect.magnitude())/planeHeight);
		}	else	{
			p.y = ((2*upVect.magnitude())/planeHeight);
		}
		
		
		return p;
		
	}
	
	
	//self functions:
	public void normalize()	{
		double mag = this.magnitude();
		this.x /= mag;
		this.y /= mag;
		this.z /= mag;		
	}
	

	
	//other functions:
	public void print()	{
		System.out.print("(" + this.x + "," + this.y + "," + this.z + ")\n");
	}

}
