import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameTest {

	public static JFrame frame;
	public static GamePanel game;
	public static MenuPanel menu;
	
	public static void main(String[] args) {
		
		frame = new JFrame();
		frame.setTitle("Prototype");
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menu = new MenuPanel();
	
		frame.add(menu);
		frame.setVisible(true);
		
	}
	
	public static void start(int n){
		Collision.col = null;
		Collision.rects = null;
	
		frame.setVisible(false);
		frame.remove(menu);
		frame.dispose();
		frame=null;
		frame = new JFrame();
		frame.setTitle("Prototype");
		frame.setSize(1000, 500); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game = new GamePanel(n);
		frame.add(game);
		class Keys implements KeyListener{

			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_UP){
				
					game.jumps();	
					
				} 
				
				if (e.getKeyCode() == KeyEvent.VK_SPACE){
					
					Gravity.vel = 0;
					if (Gravity.gDown){
						Gravity.ACCEL = -0.25;
						Gravity.gDown = false;
					}else{
						Gravity.ACCEL = 0.25;
						Gravity.gDown = true;
					}
					
				}
				game.add();
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
		Keys listener = new Keys();
		frame.setFocusable(true);
		frame.addKeyListener(listener);

		frame.setVisible(true);
	}
	
	public static void menu() throws FileNotFoundException{
		Collision.col = null;
		Collision.rects = null;
		game.removeChars();
		frame.remove(game);
		frame.dispose();
		frame=null;
		frame = new JFrame();
		frame.setTitle("Prototype");
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		menu = new MenuPanel();
		
		frame.add(menu);
	
		frame.setVisible(true);
	}

}
