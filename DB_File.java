package twitter;

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

public class DB_File {

	public static File lookfile1(){
		File file = new File(".\\DB_twitter.txt"); //ファイル
		if(!(file.exists())){
			try {
				file.createNewFile();
				FileWriter fr = new FileWriter(file);
				fr.write("ID半スペース	＝半スペース名前半スペースタブ半スペースID\n");
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
				new errorWindow();
			}
		}
		return file;
	}

	public static File lookfile(){
		File file = new File(".\\DB\\DB_twitter.txt");
		File filed = new File(".\\DB");
		if(!(filed.exists())){
			filed.mkdir();
		}
		if(!(file.exists())){
			try {
				file.createNewFile();
				FileWriter fr = new FileWriter(file);
				fr.write("ID半スペース	＝半スペース名前半スペースタブ半スペースID");
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
				errorWindow error = new errorWindow();
			}
		}
		return file;
	}

	public static List<String> ReadID(){
		List<String> DB_ID = new ArrayList<String>();
		try {
			File file = lookfile();
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str = br.readLine();
			Pattern pID = Pattern.compile("^[0-9a-zA-Z|_]+ ");
			while(str != null){
				Matcher mID = pID.matcher(str);
				if(mID.find()){
					DB_ID.add(str.substring(0,mID.end() - 1));
				}
				str = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DB_ID;
	}

	public static List<String> ReadName(){
		List<String> DB_Name = new ArrayList<String>();
		try{
			File file = lookfile();
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str = br.readLine();
			Pattern pName = Pattern.compile("＝ .+ ");
			while(str != null){
				Matcher mName = pName.matcher(str);
				if(mName.find()){
					DB_Name.add(str.substring(mName.start() + 2,mName.end()-3));
				}
				str = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DB_Name;
	}

	public static String[] ReaddID(){
		List<String> DB_dID = new ArrayList<String>();
		try{
			File file = lookfile();
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str = br.readLine();
			Pattern pdID = Pattern.compile(" [0-9]++$");
			while(str != null){
				Matcher mdID = pdID.matcher(str);
				if(mdID.find()){
					DB_dID.add(str.substring(mdID.start() + 1,mdID.end()));
				}
				str = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (String[])DB_dID.toArray(new String[DB_dID.size()]);
	}

	public static String[] SReadName(){
		String[] Name = (String[])ReadName().toArray(new String[ReadName().size()]);

		return Name;
	}
	public static String[] SReadID(){
		String[] ID = (String[])ReadID().toArray(new String[ReadID().size()]);

		return ID;
	}
	public static long[] LReadID(){
		String[] IDs = (String[])ReaddID().clone();
		long[] ID = new long[IDs.length];
		for(int i=0; i<ID.length; i++){
			ID[i] = Long.parseLong(IDs[i]);
		}
		return ID;
	}

	public static boolean scNameUpDate(boolean bl){
		File file = lookfile();
		boolean b = true;
		try {
			StringBuilder strb = new StringBuilder();
			String[] DBName = (String[])SReadName().clone(); //データベースに書いてある名前
			long[] dID = (long[])LReadID().clone(); //データベースに書いてあるID
			strb.append("ID半スペース＝半スペース名前半スペースタブ半スペースID\n");
			FileWriter filewriter = new FileWriter(file);
			if(dID.length<100 && dID.length != 0){
				String[] ScID = (String[])twitterID.SCName(dID).clone();
				for(int i=0; i<ScID.length && i<DBName.length && i<dID.length; i++){
					strb.append(ScID[i]+" ＝ "+DBName[i]+" \t "+dID[i]+"\n");
				}
			}else if(dID.length == 0){
				if(bl){
					errorWindow error = new errorWindow();
					error.errorText("データがありません");
					b = false;
				}
			}else{
				int n = (dID.length/2)+1;
				for(int j=0; j<n; j++){
					long[] l = Arrays.copyOfRange(dID, 100*j+1, 100*(j+1));
					String[] ScID = (String[])twitterID.SCName(l).clone();
					for(int i=0; i<100; i++){
						strb.append(ScID[i]+" ＝ "+DBName[i]+" \t "+dID[i]+"\n");
					}
				}

			}
			filewriter.write(strb.toString());
			filewriter.close();

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return b;


	}

}
