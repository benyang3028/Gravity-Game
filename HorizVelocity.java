import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;

public class HorizVelocity extends JComponent{

	public Rectangle2D.Double rect;
	private Rectangle2D.Double ogrect;
	Graphics2D g2;
	private int enemyVel;
	Enemy enemys;
	private boolean vert;
	private boolean up = true;
	
	public HorizVelocity(Rectangle2D.Double rects, boolean test){
		rect = rects;
		ogrect = rects;
		vert = test;
	}
	
	public void giveObject(Enemy test){
		enemys = test;
	}
	
	private Timer t2;
	private TimerTask task2;
	
	public void startVelocity(){
		
		enemyVel = 6*GamePanel.level;
		 t2 = new Timer();
		Random generator = new Random();
		int test = generator.nextInt(4) + 1;
		 task2 = new TimerTask(){
			public void run(){
				
					int rngVel = test;
					if (rect.x + enemyVel/3 > 0){
						rect = new Rectangle2D.Double(rect.x-enemyVel/3, rect.y, rect.width, rect.height);
							
					}else{
						int rng = generator.nextInt(325) + 25;
						rect = new Rectangle2D.Double(1000, rng, ogrect.width, ogrect.height);
				
					}
					
					if (vert){
						
						if (rect.y - rngVel > 15 && up){
							rect = new Rectangle2D.Double(rect.x, rect.y-rngVel, rect.width, rect.height);
							
							
						}else{
							up = false;
							if (rect.y + rngVel < 450){
								rect = new Rectangle2D.Double(rect.x, rect.y+rngVel, rect.width, rect.height);	
							}else{
								up = true;
							}
							
						}
					}
					
					enemys.update();
					GamePanel.test();
				}
		};
		t2.scheduleAtFixedRate(task2, 0, 17);
	}
	
	public void stopAll(){
	
		task2.cancel();
	
		t2.cancel();
	}
	
	
}
