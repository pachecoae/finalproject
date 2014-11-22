package finalproject;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DrawGUI extends JPanel{
	private JTextField responseTextBox;
	
	public DrawGUI() {
		add(comboPanel(), BorderLayout.SOUTH);
	}
	
	private JPanel comboPanel() {
		return new JPanel();
	}
	
	private JPanel button() {
		return new JPanel();
	}
	
	private JPanel comboBox() {
		JPanel centerPanel = new JPanel();
		JTextField textBox = new JTextField(20);
		textBox.setEditable(false);
		JLabel name = new JLabel("Response");
		centerPanel.setLayout(new GridLayout(2, 1));
		centerPanel.add(name);
		centerPanel.add(textBox);
		responseTextBox = textBox;
		centerPanel.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		return centerPanel;
	}
}
