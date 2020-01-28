import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;


public class Gravity extends JComponent{

	public Ellipse2D.Double rect;
	public static double vel;
	public static double ACCEL = 0.25;
	public static boolean gDown;
	public static boolean tGo = true;
	Character chars;
	
	public Gravity(Ellipse2D.Double obj){
		
		rect = obj;
		gDown = true;
		vel = 0;
	}
	
	private Timer t;
	private TimerTask task;
	private TimerTask task2;
	
	public void giveObject(Character test){
		chars = test;
	}
	
	public void startGravity(){
		t = new Timer();
		task = new TimerTask(){
			public void run(){		
				
				if (gDown){
					if (rect.y + ACCEL + vel < 425){
						 rect = new Ellipse2D.Double(rect.x, rect.y+vel+ACCEL, rect.width, rect.height);
						vel += ACCEL;
						chars.update();
						GamePanel.test();
					}
				}else{
					if (rect.y + ACCEL + vel > 0){
						rect = new Ellipse2D.Double(rect.x, rect.y+vel+ACCEL, rect.width, rect.height);
						vel += ACCEL;
						chars.update();
						GamePanel.test();
					}
				}
				
			}
		};
		t.scheduleAtFixedRate(task, 0, 6);
	}
	
	
	public void jump(){
		
		if (rect.y > 20){
			if (gDown){
				vel = -7;
			}else{
				vel = 7;
			}
		}
		
	}
	
	public void stopVel(){
		vel = 0;
		stopGravity();
	}
	
	public void stopTimer(){
		task2.cancel();
		t.cancel();
	}
	
	public void stopGravity(){
		
		task.cancel();
		t.cancel();
		t = new Timer();
		task2 = new TimerTask(){
			public void run(){
					if (rect.x + 3 > 0){
						rect = new Ellipse2D.Double(rect.x-2*GamePanel.level, rect.y, rect.width, rect.height);
						chars.update();
						GamePanel.test();	
					}
				}
		};
		t.scheduleAtFixedRate(task2, 0, 17);
	}
	
	public void stopAll(){
		task2.cancel();
		task.cancel();
		t.cancel();
	}
	
	
}
