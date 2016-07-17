package twitterID;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DB_Hyoji {

	public static void main(String[] args) {
		Frame();
	}

	//フレームの作成
	public static void Frame(){
		JFrame frame = new JFrame("Twitter_DB");//フレームクラスの作成
		frame.setBounds(100, 100, 350, 700);//サイズ設定
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);//サイズ変更不可

		JPanel panel = new JPanel();//パネルの作成

		JTextArea TArea = new JTextArea();//テキストエリアの作成
		TArea.setEditable(false);//編集不可
		JScrollPane JP = new JScrollPane(TArea);//スクロールペインの作成
		JP.setPreferredSize(new Dimension(300,600));//スクロールペインのサイズ設定

		frame.add(panel);//フレームにパネルを追加
		panel.add(JP);//パネルにスクロールペインを追加

		frame.setVisible(true);//フレームを可視化

		TArea.setText(File());//テキストエリアの中身を書き込む

	}

	//DBのスクリーンネームと名前の一覧のStringクラスを返す
	public static String File(){
		DB_File.scNameUpDate(true);//データを更新する
		String str = "";//戻り値のStringクラス
		String[] DBscName = (String[])DB_File.SReadID().clone();//スクリーンネームのString配列
		String[] DBName = (String[])DB_File.SReadName().clone();//名前のString配列
		for(int i=0; i < DBscName.length && i < DBName.length;i++){//データの数だけloopする
			str += DBscName[i]+"\t"+DBName[i]+"\n";//Stringクラスに書き込んでいく
		}
		return str;//戻り値(Stringクラス)
	}

}
