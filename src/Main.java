
import java.awt.Toolkit;

import javax.swing.*;

import javax.swing.JFrame;

public class Main {
	public static void main(String [] args) {
		JPanel p = new WeatherNowGui();
		JFrame jf = new JFrame();
		
		//Setting up the JFrame
		jf.setTitle("WeatherNow");	
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/images/weatherIcon.png")));
		jf.add(p);
		jf.setSize(800, 600);
		jf.setResizable(false); 
		jf.setVisible(true);
		jf.setLocationRelativeTo(null); //Put GUI in center 
		
	}
}
