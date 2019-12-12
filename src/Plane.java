
public class Plane {
	public Vector3[] verts;	//every plane has 3 verts
	public Material material;
	
	public Plane(Vector3 p1, Vector3 p2, Vector3 p3)	{
		this.verts = new Vector3[]{p1, p2, p3};
	}
	
	public void rotate(Vector3 axis, double angle) throws MatrixDimensionException	{
		for(Vector3 p : this.verts) {
			Vector3 pNew = p.rotate(axis, angle);
			p.x = pNew.x;
			p.y = pNew.y;
			p.z = pNew.z;
		}
	}
	
	
}
