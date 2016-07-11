package twitter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class MainWindow implements ActionListener{

	public static void main(String[] args) {
		JFrame frame = new JFrame("Twitter_DB");
		frame.setBounds(100, 100, 350, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		JLabel label1 = new JLabel("　　IDと名字のDB参照");
		JLabel label2 = new JLabel("　　DBへの新規登録");
		JLabel label3 = new JLabel("　　DBの出力");

		JButton btn1 = new JButton("実行");
		btn1.addActionListener(new MainWindow());
		JButton btn2 = new JButton("実行");
		btn2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				NewDataWindow.NewDataWindow();
			}
		});
		JButton btn3 = new JButton("表示");
		btn3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				DB_Hyoji.Frame();
			}
		});

		frame.add(panel);
		panel.add(label1);
		panel.add(panel1);
		panel.add(label2);
		panel.add(panel2);
		panel.add(label3);
		panel.add(panel3);

		panel1.add(btn1);
		panel2.add(btn2);
		panel3.add(btn3);

		frame.setVisible(true);
		DB_File.lookfile();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Window1 window = new Window1();
		boolean bl = false;
		window.frame();

	}

}
