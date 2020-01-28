import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel{

	private Character char1;
	public static GamePanel panel;
	public static int level;
	
	
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private Rectangle2D.Double bottom = new Rectangle2D.Double(0, 450, 1000, 50);
	private Rectangle2D.Double top = new Rectangle2D.Double(0, 0, 1000, 13);
	
	private double time;
	private double highScore;
	
	private static Timer t;
	private static TimerTask task;
	
	public GamePanel(int n){
		level = n;
		time = 0;
		panel = this;
		
		
		
		Collision col = new Collision();
		Enemy enemy1 = new Enemy(new Rectangle2D.Double(1000, 200, 50, 50), 1, true);
		Enemy enemy2 = new Enemy(new Rectangle2D.Double(1300, 300, 50, 50), 2, true);
		Enemy enemy3 = new Enemy(new Rectangle2D.Double(1600, 100, 50, 50), 3, true);
		Enemy enemy4 = new Enemy(new Rectangle2D.Double(1450, 150, 50, 50), 4, false);
		Enemy enemy5 = new Enemy(new Rectangle2D.Double(1150, 350, 50, 50), 5, false);
		Enemy enemy6 = new Enemy(new Rectangle2D.Double(1750, 200, 50, 50), 6, false);
		char1 = new Character(new Ellipse2D.Double(400, 0, 50, 50));
	
		enemies.add(enemy1);
		enemies.add(enemy2);
		enemies.add(enemy3);
		enemies.add(enemy4);
		enemies.add(enemy5);
		enemies.add(enemy6);
		
		add(char1);
		for (Enemy e: enemies){
			add(e);
		}
		Collision.add(new Rectangle2D.Double(0, 7, 1000, 13), 7);
		Collision.add(bottom, 8);
		t = new Timer();
		task = new TimerTask(){
			public void run(){		
				try {
					char1.checkCollisions();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				time+=.01;
				if (char1.dead()){
					
					try {
						removeChars();
						task.cancel();
						t.cancel();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.scheduleAtFixedRate(task, 0, 10);
		
	}
	
	
	public void paint(Graphics g){
		
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.draw(top);
		g2.draw(bottom);
		g2.setFont(new Font("Serif", Font.BOLD, 24));
		g2.drawString(String.format("%.2f", time), 10, 35);
		giveGraphics(g2);
		
	}
	
	public void giveGraphics(Graphics2D g2){
		char1.graphics(g2);
		for (Enemy e: enemies){
			e.graphics(g2);
		}
	}
	
	public void jumps(){
		char1.jump();
	}
	
	public void add(){
		repaint();
	}
	
	public static void test(){
		panel.add();
	}
	
	public static void stop(){
		task.cancel();
		t.cancel();
		
	}
	
	public void removeChars() throws FileNotFoundException{
		FileReader reader;
		Scanner test = null;
		try {
			reader = new FileReader("scores.txt");
			test = new Scanner(reader);
			test.nextLine();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		double num = test.nextDouble();
		task.cancel();
		t.cancel();
		for (Enemy e: enemies){
			System.out.println("stop");
			e.stopAll();
		}
		char1.stopAll();
		
		if (num < time){
			String input = JOptionPane.showInputDialog("New Highscore, Enter your Name");
			PrintWriter writer = new PrintWriter("scores.txt");
			String number = String.format("%-8.3f", time);
			writer.println(input);
			writer.println(number);
			writer.close();
			time = 0;
		}
		System.out.println(time);
	}
	
	
}
