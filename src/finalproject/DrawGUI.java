package finalproject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DrawGUI extends JPanel{
	private JTextField responseTextBox;
	private CavernFinder cave;
	
	public DrawGUI(CavernFinder cave) {
		this.cave = cave;
		add(comboPanel(), BorderLayout.CENTER);
	}
	
	private JPanel comboPanel() {
		JPanel panel = new JPanel();
		panel.setSize(956, 200);
		
		JComboBox cavernChoice = new JComboBox();
		cavernChoice.addItem("Cavern A");
		cavernChoice.addItem("Cavern B");
		cavernChoice.addItem("Cavern C");
		cavernChoice.addItem("Cavern D");
		cavernChoice.addItem("Cavern E");
		cavernChoice.addItem("Cavern F");
		cavernChoice.addItem("Cavern G");
		cavernChoice.addItem("Cavern H");
		
		JButton submit = new JButton("Submit");
		
		panel.add(cavernChoice, BorderLayout.WEST);
		panel.add(submit, BorderLayout.EAST);
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(cavernChoice.getSelectedItem().toString()){
				case "Cavern A":
					cave.currentCave = 'A';
					break;
				case "Cavern B":
					cave.currentCave = 'B';
					break;
				case "Cavern C":
					cave.currentCave = 'C';
					break;
				case "Cavern D":
					cave.currentCave = 'D';
					break;
				case "Cavern E":
					cave.currentCave = 'E';
					break;
				case "Cavern F":
					cave.currentCave = 'F';
					break;
				case "Cavern G":
					cave.currentCave = 'G';
					break;
				case "Cavern H":
					cave.currentCave = 'H';
					break;
				}
			}
		});
		
		return new JPanel();
	}
}
