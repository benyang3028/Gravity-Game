import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

public class Collision {

	public static Collision col;
	
	public static ArrayList<Rectangle2D.Double> rects = new ArrayList<Rectangle2D.Double>();
	public static Rectangle2D.Double colRect = null;
	
	public Collision(){
		col = null;

		rects = new ArrayList<Rectangle2D.Double>();
		col = this;
		colRect = null;
		
		for (int i = 0; i<9; i++){
			rects.add(null);
		}
	}
	
	public static ArrayList<Rectangle2D.Double> getArray(){
		return rects;
	}
 	
	public static void add(Rectangle2D.Double rect, int i){
		
		rects.set(i, rect);

	}
	
	public static boolean test(){
		Rectangle2D.Double player = rects.get(0);
		
		for (int i = 1; i<rects.size(); i++){
			if(player.intersects(rects.get(i))){
				colRect = rects.get(i);
				return true;
			}
		}
		
			
		return false;
		
	}
	
	
	
	
}
