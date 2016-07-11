package twitter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class Window1 implements ActionListener{

	static JTextField jt1 = new JTextField(10);
	static JTextField jt2 = new JTextField(6);
	static String strID;
	static String strName;
	static JLabel JL1 = new JLabel(" ");

	public void frame(){
		JFrame frame = new JFrame("Twitter_DB_参照");
		frame.setBounds(150, 150, 350, 150);
		frame.setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();

		JLabel label1 = new JLabel("ID");
		JLabel label2 = new JLabel("氏名");

		JButton btn = new JButton("実行");
		btn.addActionListener(new Window1());
		btn.setMnemonic(KeyEvent.VK_ENTER);
		frame.add(panel);
		panel.add(panel1);
		panel.add(panel2);
		panel1.add(label1);
		panel1.add(jt1);
		panel1.add(label2);
		panel1.add(jt2);
		panel1.add(btn);
		panel2.add(JL1);

		frame.setVisible(true);
	}

	public void syori(){
		try {
			boolean use = true;
			boolean ab = true;
			String Last = "";
			File file = DB_File.lookfile();
			BufferedReader br = new BufferedReader(new FileReader(file));

			String str = br.readLine();
			if(strID != null && strName != null){
				while(str != null){
					ab = false;

					Pattern pID = Pattern.compile("^"+ strID + " ");
					Matcher mID = pID.matcher(str);
					Pattern pName = Pattern.compile(" " + strName + " ");
					Matcher mName = pName.matcher(str);
					Pattern p = Pattern.compile("＝");
					Matcher m = p.matcher(str);


					if(mID.find() && !(strID.equals(""))){
						m.find();
						int end = m.end();
						if(use){
							Last = str.substring(end+1,str.length());
						}else{
							Last = Last + " " + str.substring(end+1,str.length());
						}
						JL1.setText(Last);
						use = false;

					}else if(mName.find() && !(strName.equals(""))){
						m.find();
						int start = m.start();
						if(use){
							Last = str.substring(0,start-1);
						}else{
							Last = Last + " " + str.substring(0,start-1);
						}
						JL1.setText(Last);
						use = false;
					}
					str = br.readLine();

				}
				if(ab){
					errorWindow error = new errorWindow();
					error.errorText("データがありません");
				}
				if(strID.equals("") && strName.equals("")){

				}else if(use){
					JL1.setText(strName+strID+"はtwitterをしていません");
				}
				br.close();
			}


		} catch (FileNotFoundException e) {
			errorWindow error = new errorWindow();
			error.errorText("ファイルが見つかりません");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		strID = jt1.getText();
		strName = jt2.getText();
		jt1.setText("");
		jt2.setText("");

		if(!(strID.equals(""))&&!(strName.equals(""))){
			JL1.setText("両方入力しないでください");
		}else{
			if(DB_File.scNameUpDate(true))
				syori();
		}

	}

}
