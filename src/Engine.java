import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

public class Engine {
	
	public static final int FPS = 50;
	public static GameObject obj;
	public static GameObject obj2;
	public static RenderWindow renderWindow;

	public static void main(String[] args) throws MatrixDimensionException {

	
		
		
		
		JFrame mainWindow = new JFrame();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setTitle("Render Engine");
		mainWindow.setLayout(null);
		mainWindow.setSize(1000,1000);
		mainWindow.setVisible(true);
		
		Camera mainCamera = new Camera(Vector3.ZERO, Vector3.Z_AXIS, Vector3.Y_AXIS, Math.PI/2, Math.PI/2);
		obj = new GameObject(new Vector3(0,0, 2));
		obj2 = new GameObject(new Vector3(0,0, 2));
		Scene scene1 = new Scene();
		
		
		
		Plane plane1 = new Plane(new Vector3(1,0,0), new Vector3(0,1,0), new Vector3(0,0,0));
		Plane plane2 = new Plane(new Vector3(0,0,1), new Vector3(0,1,0), new Vector3(0,0,0));
		Plane plane3 = new Plane(new Vector3(0,0,1), new Vector3(1,0,0), new Vector3(0,0,0));
		Plane plane4 = new Plane(new Vector3(0,0,1), new Vector3(0,1,0), new Vector3(1,0,0));
		
		Plane plane5 = new Plane(new Vector3(1,1,1), new Vector3(1,1,0), new Vector3(1,0,1));
		Plane plane6 = new Plane(new Vector3(1,1,1), new Vector3(1,0,1), new Vector3(0,1,1));
		Plane plane7 = new Plane(new Vector3(1,1,1), new Vector3(1,1,0), new Vector3(0,1,1));
		Plane plane8 = new Plane(new Vector3(1,1,0), new Vector3(1,0,1), new Vector3(0,1,1));
		
		Mesh planeMesh = new Mesh(new Plane[] {plane1, plane2, plane3, plane4});
		Mesh planeMesh2 = new Mesh(new Plane[] { plane5, plane6, plane7, plane8});
		obj.mesh = planeMesh;
		obj2.mesh = planeMesh2;
		obj.setUniformMaterial(new Material(Color.CYAN));
		obj2.setUniformMaterial(new Material(Color.red));
		scene1.gameObjects.add(obj);
		scene1.gameObjects.add(obj2);
		
		renderWindow = new RenderWindow(800,800, scene1);
		renderWindow.setCamera(mainCamera);
		
		mainWindow.add(renderWindow);
		
		TitledBorder mainBorder = new TitledBorder("Render");
		mainBorder.setTitleJustification(TitledBorder.LEFT);
		mainBorder.setTitlePosition(TitledBorder.TOP);
	    renderWindow.setBorder(mainBorder);
		
	
		renderWindow.repaint();
		
		Timer engineTimer = new Timer();
		
		
		engineTimer.schedule(update, 0, 1000/FPS);	

	}
	
	
	
	static TimerTask update = new TimerTask()	{

		@Override
		public void run() {
			try {
				obj.rotate(new Vector3(1,1,1).normalized(), Math.PI/100);
				obj2.rotate(new Vector3(1,1,6).normalized(), -Math.PI/100);
				renderWindow.repaint();
			} catch (MatrixDimensionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
		


}
