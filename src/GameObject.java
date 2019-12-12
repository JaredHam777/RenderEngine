import java.awt.Point;

public class GameObject {
	public Mesh mesh;
	public Vector3 origin;
	public Quaternion globalRotation;
	
	public GameObject(Vector3 location) {
		this.origin = location;
	}
	
	public void rotate(Vector3 axis, double angle) throws MatrixDimensionException {
		for(Plane p : this.mesh.planes)	{
			p.rotate(axis, angle);
		}
	}
	
	public void printVerts()	{
		System.out.println("This game object's verts:");
		for(Plane p : mesh.planes) {
			for(Vector3 v : p.verts) {
				v.print();
			}
		}
		System.out.println("-----------------");
	}

	public void setUniformMaterial(Material m)	{
		for(Plane p : mesh.planes) {
			p.material = m;
		}
	}
	
	
}
