import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;


public class Character extends Gravity{
	
	
	public static int vel;
	public static int ACCEL = 5;
	public static boolean gDown;
	
	private Graphics2D g2;
	
	public Character(Ellipse2D.Double circ){
		
		super(new Ellipse2D.Double(circ.x, circ.y, circ.width, circ.height));
		giveObject(this);
		startGravity();
		Collision.add(new Rectangle2D.Double(circ.x, circ.y, circ.width, circ.height), 0);
	}
	
	public void jump(){
		super.jump();
		if (touching){
			stopTimer();
			startGravity();
			touching = false;
		}
	}
	

	public void paint(){
	
		if (g2 != null){
			super.paint(g2);
			g2.draw(rect);
		}

	}
	
	
	public void update(){
		//paint();
		Collision.add(new Rectangle2D.Double(rect.x, rect.y, rect.width, rect.height), 0);
	}
	
	public void graphics (Graphics2D g){
		g2 = g;
		paint();
	} 
	
	private boolean touching = false;
	private boolean dead = false;
	
	public void checkCollisions() throws FileNotFoundException{
		Collision.getArray();
		if (Collision.test() && !touching){
		//	System.out.println("collide");
			if (Collision.colRect.y != 7){
				stopVel();
				touching = true;
			}else{
			//	System.out.println("top");
				vel = -1*vel-20;
			}
		}
		if (rect.x < 4){
			
			stopAll();
			dead = true;
			GamePanel.stop();
			GameTest.menu();
		}

		
	}
	
	public boolean dead(){
		return dead;
	}
	
}
