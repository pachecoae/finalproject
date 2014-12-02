package finalproject;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DrawCave extends JPanel {
	private CavernFinder cF;
	public JTextField responseTextBox;
	
	public DrawCave(CavernFinder cavernFinder) {
		this.cF = cavernFinder;
	}
	
	// Drawing Functions and Drawing the Frame
	public void drawFrame() {
		// Create a JFrame
		JFrame f = new JFrame();
		f.setSize(1000, 1000);
		f.setTitle("Cave Finder");
		f.setLayout(null);

		// Add JPanel to your JFrame
		JPanel panel = new JPanel();
		panel.setLocation(0, 0);
		panel.setSize(750, 750);

		panel.setVisible(true);

		f.add(panel);

		// Necessary at end
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(this);
		f.setVisible(true);
		f.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 30; j++) {
				cF.getTheCave()[i][j].draw(g, cF);
			}
		}

		// Color temp = g.getColor();
		//
		// if (game.turn % 6 == 0 && game.turn > 1) {
		// g.setColor(Color.GREEN);
		// for (BoardCell b : targetList) {
		// g.fillRect(b.getPixelRow(), b.getPixelCol(), 30, 30);
		// }
		// g.setColor(temp);
		// }
		//
		for (Robot r : cF.getRobots()) {
			r.draw(g);
		}
	}
}