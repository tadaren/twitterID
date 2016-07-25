package twitterID;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter.errorWindow;

public class DB_File {

	//残骸
	public static File lookfile1(){
		File file = new File(".\\DB_twitter.txt"); //ファイルクラスの作成
		if(!(file.exists())){ //ディレクトリが存在するかを確認する
			try {
				file.createNewFile(); //存在しなければディレクトリを作成
				FileWriter fr = new FileWriter(file); //書き込み用クラスの作成
				fr.write("ID半スペース＝半スペース名前半スペースタブ半スペースID\n"); //1行目の文を書き込む
				fr.close(); //クラスを閉じる
			} catch (IOException e) { //エラー処理
				e.printStackTrace();
				new errorWindow(); //エラーウィンドウを作成する
			}
		}
		return file; //返り値にファイルを返す
	}

	//ファイルの存在確認部分
	public static File lookfile(){
		File file = new File(".\\DB\\DB_twitter.txt"); //テキストファイルのファイルクラスの作成
		File filed = new File(".\\DB"); //ディレクトリのファイルクラス
		if(!(filed.exists())){ //ディレクトリが存在するかを判定
			filed.mkdir(); //存在しないなら作成
		}
		if(!(file.exists())){ //ファイルが存在するかを判定
			try {
				file.createNewFile();//存在しないなら作成
				FileWriter fr = new FileWriter(file);//書き込みのクラスを作成
				fr.write("ID半スペース＝半スペース名前半スペースタブ半スペースID");//初期文を書き込む
				fr.close();//クラスを閉じる
			} catch (IOException e) {//エラー処理
				e.printStackTrace();
				errorWindow error = new errorWindow();
			}
		}
		return file;//戻り値（ファイルクラス）
	}

	//DBからスクリーンネームを読み取りリストを返すメソッド
	public static List<String> ReadID(){
		List<String> DB_ID = new ArrayList<String>(); //リストの作成
		try {
			File file = lookfile(); //ファイルの確認
			BufferedReader br = new BufferedReader(new FileReader(file));//ファイル読み込みのクラスの作成
			String str = br.readLine();//1行目の読み込み
			Pattern pID = Pattern.compile("^[0-9a-zA-Z|_]+ ");//スクリーンネーム読み込みのためのパターン作成
			while(str != null){//読み込みのループ
				Matcher mID = pID.matcher(str);//読み込んだ文とでマッチャを作成
				if(mID.find()){//文と一致していたら
					DB_ID.add(str.substring(0,mID.end() - 1));//リストにスクリーンネームを追加
				}
				str = br.readLine();//次の一文を読み込む
			}
			br.close();//読み込みクラスを閉じる
		} catch (FileNotFoundException e) {//エラー処理
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DB_ID;//戻り値(スクリーンネームのリスト)
	}

	//DBから名前を読み取りリストを返す
	public static List<String> ReadName(){
		List<String> DB_Name = new ArrayList<String>();//リストの作成
		try{
			File file = lookfile();//ファイルの確認
			BufferedReader br = new BufferedReader(new FileReader(file));//読み取りのクラス
			String str = br.readLine();//1行目の読み込み
			Pattern pName = Pattern.compile("＝ .+ ");//パターンの作成
			while(str != null){//読み取りのループ
				Matcher mName = pName.matcher(str);//読み込んだ文とパターンでマッチャを作る
				if(mName.find()){//一致している部分があったら
					DB_Name.add(str.substring(mName.start() + 2,mName.end()-3));//リストに名前を追加する
				}
				str = br.readLine();//次の行を読み込む
			}
			br.close();//クラスを閉じる
		} catch (FileNotFoundException e) {//エラー処理
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DB_Name;//戻り値（名前のリスト）
	}

	//DBからIDを読み取ってString配列で返す
	public static String[] ReaddID(){
		List<String> DB_dID = new ArrayList<String>();//リストの作成
		try{
			File file = lookfile();//ファイルの確認
			BufferedReader br = new BufferedReader(new FileReader(file));//ファイル読み取りのクラス
			String str = br.readLine();//1行を読み込む
			Pattern pdID = Pattern.compile(" [0-9]++$");//パターンの作成
			while(str != null){//読み込みのループ
				Matcher mdID = pdID.matcher(str);//パターンと読み込んだ文でマッチャを作成
				if(mdID.find()){//一致している部分があったら
					DB_dID.add(str.substring(mdID.start() + 1,mdID.end()));//リストにIDを追加
				}
				str = br.readLine();//次の行を読み込む
			}
			br.close();//クラスを閉じる
		} catch (FileNotFoundException e) {//エラー処理
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (String[])DB_dID.toArray(new String[DB_dID.size()]);//戻り値(リストを配列にして返す)
	}

	//DB内の名前をString配列で返す
	public static String[] SReadName(){
		String[] Name = (String[])ReadName().toArray(new String[ReadName().size()]);
		return Name;
	}
	//DB内のスクリーンネームをString配列で返す
	public static String[] SReadID(){
		String[] ID = (String[])ReadID().toArray(new String[ReadID().size()]);
		return ID;
	}
	//DB内のIDをlong配列で返す
	public static long[] LReadID(){
		String[] IDs = (String[])ReaddID().clone();
		long[] ID = new long[IDs.length];
		for(int i=0; i<ID.length; i++){
			ID[i] = Long.parseLong(IDs[i]);
		}
		return ID;
	}

	//DBのIDを最新のものに更新する(更新できたらTrueを返す)(引数はエラーを出したくないときFalseを渡す)
	public static boolean scNameUpDate(boolean bl){
		File file = lookfile();//ファイルを確認
		boolean b = true;//戻り値用boolean変数
		try {
			StringBuilder strb = new StringBuilder();//ファイルに書き込む元のStringBuilderクラス
			String[] DBName = (String[])SReadName().clone(); //データベースに書いてある名前
			long[] dID = (long[])LReadID().clone(); //データベースに書いてあるID
			strb.append("ID半スペース＝半スペース名前半スペースタブ半スペースID\n");//DBに書き込む一文目
			FileWriter filewriter = new FileWriter(file);//書き込み用のクラス
			if(dID.length<100 && dID.length != 0){//データの数が0でなく100以下の時
				String[] ScID = (String[])twitterID.SCName(dID).clone();//TwitterAPIから今のIDを受け取りString配列を作る
				for(int i=0; i<ScID.length && i<DBName.length && i<dID.length; i++){//書き込み用のStringBuilderに追加していく
					strb.append(ScID[i]+" ＝ "+DBName[i]+" \t "+dID[i]+"\n");
				}
			}else if(dID.length == 0){//データ数が0の時
				if(bl){//引数がTrueの時エラーウィンドウを出す
					errorWindow error = new errorWindow();
					error.errorText("データがありません");
					b = false;
				}
			}else{//データ数が100以上の時
				int n = (dID.length/2)+1; //配列の長さを100で割って＋1した値の変数
				for(int j=0; j<n; j++){ //上の変数の数だけループさせる
					long[] l = Arrays.copyOfRange(dID, 100*j+1, 100*(j+1));百ごとに区切ったlong配列を作る
					String[] ScID = (String[])twitterID.SCName(l).clone();
					for(int i=0; i<100; i++){
						strb.append(ScID[i]+" ＝ "+DBName[i]+" \t "+dID[i]+"\n");
					}
				}

			}
			filewriter.write(strb.toString());//ファイルに書き込む
			filewriter.close();

		} catch (IOException e) {//エラー処理
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return b;//戻り値


	}

}
