import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RenderWindow extends JPanel{
	
	Scene currentScene;
	Camera camera;
	
	public Camera getCamera() {
		return camera;
	}
	public void setCamera(Camera camera) {
		this.camera = camera;
	}



	public RenderWindow(int width, int height, Scene scene) {
		super();
		this.setSize(width, height);
		this.currentScene=scene;
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//System.out.println("painting...");
		for(GameObject obj : this.currentScene.gameObjects) {
			//System.out.println("painting " + obj.getClass());
			for(Plane p : obj.mesh.planes) {
				//System.out.println("painting planes");
				int[] xPoints = new int[p.verts.length];
				int[] yPoints = new int[p.verts.length];
				
				for(int i=0; i<p.verts.length; i++) {
					
					Vector3 position = p.verts[i].plus(obj.origin);
					//System.out.print("augmenting point: "); position.print();
					Vector2 point =position.augmentedPosition(this.camera);
					//System.out.println("painting verts: " + point.x + "," + point.y);
					int x = (int)Math.floor((this.getWidth()/2) * point.x + this.getWidth()/2);
					int y = (int)Math.floor((this.getHeight()/2) * point.y + this.getHeight()/2);
					xPoints[i] = x;
					yPoints[i] = y;
				}
				
				g.setColor(p.material.color);
				g.fillPolygon(xPoints, yPoints, p.verts.length);
				g.setColor(Color.black);
				g.drawPolygon(xPoints, yPoints, p.verts.length);
				
				//System.out.println("filling polygon: " + xPoints[0] + "," + yPoints[0]);
				//System.out.println("filling polygon: " + xPoints[1] + "," + yPoints[1]);
				//System.out.println("filling polygon: " + xPoints[2] + "," + yPoints[2]);
				
				
			}
		}
	}
}
