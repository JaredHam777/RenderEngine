
public class Quaternion extends Matrix {

	public Matrix R = new Matrix(3,3);	//R is the rotation matrix
	
	public Quaternion() {
		super(1,4);
	}
	public Quaternion(double a, double b, double c, double d)	{
		super(1,4);
		this.values[0] = new double[] {a,b,c,d};
		
		this.createRotationMatrix();

	}
	public Quaternion(double a, Vector3 axis)	{
		super(1,4);
		this.values[0] = new double[] {Math.cos(a/2), axis.x*Math.sin(a/2), axis.y*Math.sin(a/2), axis.z*Math.sin(a/2)};

		this.createRotationMatrix();
		
	}
	
	public void createRotationMatrix()	{
		this.normalizeH();
		
		R.setVal(0, 0, 1-2*(Math.pow(this.getj(),2)+Math.pow(this.getk(), 2)));
		R.setVal(1, 1, 1-2*(Math.pow(this.geti(),2)+Math.pow(this.getk(), 2)));
		R.setVal(2, 2, 1-2*(Math.pow(this.geti(),2)+Math.pow(this.getj(), 2)));
		
		R.setVal(1, 0, 2*(this.geti()*this.getj() + this.getk()*this.getr()));
		R.setVal(2, 0, 2*(this.geti()*this.getk() - this.getj()*this.getr()));
		
		R.setVal(0, 1, 2*(this.geti()*this.getj()-this.getk()*this.getr()));
		R.setVal(2, 1, 2*(this.getj()*this.getk()+this.geti()*this.getr()));
		
		R.setVal(0, 2, 2*(this.geti()*this.getk()+this.getj()*this.getr()));
		R.setVal(1, 2, 2*(this.getj()*this.getk()-this.geti()*this.getr()));
	}
	
	public double geti()	{
		return this.getVal(0, 1);
	}
	public double getj()	{
		return this.getVal(0, 2);
	}
	public double getk()	{
		return this.getVal(0, 3);
	}
	
	public double getr() {
		return this.getVal(0, 0);
	}
	
	public Quaternion conjugate() {
		return new Quaternion(super.getVal(0, 0), -super.getVal(0, 1), -super.getVal(0, 2), -super.getVal(0, 3));
	}
	
	
}
