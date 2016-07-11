package twitter;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DB_Hyoji {

	public static void main(String[] args) {
		Frame();
	}

	public static void Frame(){
		JFrame frame = new JFrame("Twitter_DB");
		frame.setBounds(100, 100, 350, 700);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		JPanel panel = new JPanel();

		JTextArea TArea = new JTextArea();
		TArea.setEditable(false);
		JScrollPane JP = new JScrollPane(TArea);
		JP.setPreferredSize(new Dimension(300,600));

		frame.add(panel);
		panel.add(JP);

		frame.setVisible(true);

		TArea.setText(File());

	}

	public static String File(){
		DB_File.scNameUpDate(true);
		String str = "";
		String[] DBID = (String[])DB_File.SReadID().clone();
		String[] DBName = (String[])DB_File.SReadName().clone();
		for(int i=0; i < DBID.length && i < DBName.length;i++){
			str += DBID[i]+"\t"+DBName[i]+"\n";
		}
		return str;
	}

}
