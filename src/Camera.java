
public class Camera extends GameObject{
	public double horizontalFOV;
	public double verticalFOV;
	public Vector3 forward;
	public Vector3 up;
	public Vector3 right;
	
	public Camera(Vector3 location, Vector3 forward, Vector3 up, double hFOV, double vFOV) {
		super(location);
		this.horizontalFOV=hFOV;
		this.verticalFOV=vFOV;
		this.forward=forward;
		this.up = up;
		this.right = this.forward.cross(this.up);
	}
}
