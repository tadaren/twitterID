package twitterID;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewDataWindow implements ActionListener{

	static JTextField jt1 = new JTextField(10);
	static JTextField jt2 = new JTextField(6);
	static JLabel JL = new JLabel();

	public static void main(String[] args) {
		NewDataWindow();
	}
	public static void NewDataWindow(){
		JFrame frame = new JFrame("Twitter_DB_登録");
		frame.setBounds(150, 150, 350, 150);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();

		JLabel label1 = new JLabel("ID");
		JLabel label2 = new JLabel("氏名");

		JButton btn = new JButton("登録");
		btn.addActionListener(new NewDataWindow());
		btn.setMnemonic(KeyEvent.VK_ENTER);
		frame.add(panel);
		panel.add(panel1);
		panel.add(panel2);
		panel1.add(label1);
		panel1.add(jt1);
		panel1.add(label2);
		panel1.add(jt2);
		panel1.add(btn);
		panel2.add(JL);

		frame.setVisible(true);
	}

	public static void touroku(String ID,String Name){
		DB_File.scNameUpDate(false);
		boolean blean = true;
//		StringBuilder strDB = new StringBuilder();
		String[] DBID = DB_File.SReadID();
		String[] DBName = DB_File.SReadName();

		for(int i=0;i < DBID.length && i < DBName.length;i++){
//			strDB.append(DBID[i]+" "+"＝"+" "+DBName[i]+" \t "+DBdID[i]);
			if(DBID[i].equals(ID))
				blean = false;
//			if(DBName[i].equals(Name)){
//				blean = false;
//			}
		}
		if(blean){
			File file = DB_File.lookfile();
			try {
				FileWriter filewriter = new FileWriter(file, true);
				filewriter.write(ID+" ＝ "+Name+" \t "+twitterID.IDName(ID)+"\n");
				filewriter.close();

			} catch (IOException e) {
				new errorWindow();
				e.printStackTrace();
			}
			JL.setText("登録されました");
		}else{
			JL.setText("既に登録されています");
		}

	}

//	public static void touroku1(String ID,String Name){
//		try{
//			boolean blean = true;
//			File file = DB_File.lookfile();
//			BufferedReader br = new BufferedReader(new FileReader(file));
//
//			String str = br.readLine();
//			while(str != null){
//				Pattern pID = Pattern.compile("^"+ ID + " ");
//				Matcher mID = pID.matcher(str);
//				Pattern pName = Pattern.compile(" " + Name + "$");
//				Matcher mName = pName.matcher(str);
//				if(mID.find() && mName.find()){
//					blean = false;
//				}
//
//				str = br.readLine();
//			}
//			br.close();
//
//		}catch(FileNotFoundException e){
//			JL.setText("ファイルが見つかりません");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String sID = jt1.getText();
		String sName = jt2.getText();
		if(!(sID.equals("")||sName.equals(""))){
			jt1.setText("");
			jt2.setText("");

			touroku(sID,sName);
		}else{
			JL.setText("両方入力してください");
		}

	}

}
