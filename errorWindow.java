package twitter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class errorWindow {
	JLabel label = new JLabel();
	public errorWindow(){
		JFrame frame = new JFrame("ERROR");
		frame.setBounds(100, 100, 350, 100);
		frame.setResizable(false);
		JPanel panel = new JPanel();
		label.setText("エラーが発生しました");

		frame.add(panel);
		panel.add(label);

		frame.setVisible(true);
	}
	public void errorText(String str){
		label.setText(str);
	}
}
