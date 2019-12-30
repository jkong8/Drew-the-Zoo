import java.awt.Canvas;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends Canvas{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7935321088545016599L;
	public Window(int width, int height, Game game){
		JLabel label = new JLabel();
		ImageIcon icon = new ImageIcon("C:/stage.jpg");
		label.setIcon(icon);
		label.setVisible(true);
		JFrame frame = new JFrame("Drew the Zoo!");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.add(label);
		//Adds the Game class to the Window
		frame.add(game);
		game.start();
	}
	
}