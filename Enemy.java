import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class Enemy extends HorizVelocity{

	private Graphics2D g2;
	private int arrayNum;
	
	public Enemy(Rectangle2D.Double rect, int num, boolean up){
	
		super(rect, up);
		arrayNum = num;
		giveObject(this);
		startVelocity();
		g2 = null;
		Collision.add(new Rectangle2D.Double(rect.x, rect.y, rect.width, rect.height), num);
	}


	public void paint(){
		
		if (g2 != null){
			super.paint(g2);
			g2.draw(rect);
		}
	
	}
	
	public void update(){
		Collision.add(rect, arrayNum);
	}
	
	public void graphics (Graphics2D g){
		
		g2 = g;
		paint();
	}
	
	
	
} 
