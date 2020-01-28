import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{

	public MenuPanel(){
		
		setLayout(new GridLayout(5, 1));
		
		JButton button1 = new JButton("Level 1");
		JButton button2 = new JButton("Level 2");
		JButton button3 = new JButton("Level 3");
		JButton button4 = new JButton("Level 4");
		JButton button5 = new JButton("Level 5");
		
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		
		class Action implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int num = 0;
				
				if (e.getSource() == button1){
					
					num = 1;
				}
				if (e.getSource() == button2){
				
					num = 2;
				}
				if (e.getSource() == button3){
					
					num = 3;
				}
				if (e.getSource() == button4){
					
					num = 4;
				}
				if (e.getSource() == button5){
					
					num = 5;
				}

				
				GameTest.start(num);
			}
			
		}
		
		ActionListener listener = new Action();
		button1.addActionListener(listener);
		button2.addActionListener(listener);
		button3.addActionListener(listener);
		button4.addActionListener(listener);
		button5.addActionListener(listener);
	}
	
	
	
	
	
	
}
