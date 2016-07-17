package twitterID;

import java.util.ArrayList;
import java.util.List;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class twitterID {
	private static final String bearer1 = "";
	private static final String consumerKey = "";
	private static final String consumerS = "";

	public static void conf(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		//cb.setOAuthConsumerKey(consumerKey);
		//cb.setOAuthConsumerSecret(consumerS);
		cb.setOAuth2TokenType("Bearer");
		cb.setOAuth2AccessToken(bearer1);
	}

	public static long IDName(String str) {
		conf();
		Twitter twitter = new TwitterFactory().getInstance();

		try {
			//System.out.println(bearer1);
			//System.out.println(twitter.getRateLimitStatus().toString());
			User user = twitter.showUser(str);
			//OAuth2Token tok = twitter.getOAuth2Token();
			//System.out.println(tok);
			//twitter.invalidateOAuth2Token();

			long l = user.getId();
//			System.out.println(l);

			return l;
		} catch (TwitterException e) {
			e.getExceptionCode();
			errorWindow error = new errorWindow();
			if(e.getExceptionCode().equals("381aea58-2f82d163 720b1cc8-c0d756c4")){
				error.errorText("通信ができません");
			}else{
				error.errorText("IDが間違っています");
			}
		}
		return 0;
	}

	public static String NameID(long l){
		conf();
		Twitter twitter = new TwitterFactory().getInstance();

		try {
			User user = twitter.showUser(l);
			String str = user.getScreenName();
//			System.out.println(str);
			return str;
		} catch (TwitterException e) {
			e.printStackTrace();
			errorWindow error = new errorWindow();
			error.errorText("通信ができません");
			System.out.println(e.getExceptionCode());
		}
		return null;

	}

	public static String[] SCName(long[] lA){
		try {
			conf();
			Twitter twitter = new TwitterFactory().getInstance();
			ResponseList<User> user = twitter.lookupUsers(lA);
			List<String> s = new ArrayList<String>();
			for(int i=0; i<user.size(); i++){
				s.add(user.get(i).getScreenName());
			}
			return (String[])s.toArray(new String[s.size()]);
		} catch (TwitterException e) {
			errorWindow error = new errorWindow();
			if(e.getExceptionCode().equals("6df1b39e-2ee1e605 f70d7c42-ad5ad662")){
				error.errorText("通信ができません");
			}else{
				error.errorText("データがありません");
			}
			e.printStackTrace();
//			System.out.println(e.getExceptionCode());
		}

		return null;

	}

	public static void main(String[] args){
		System.out.println(IDName("Tadaren_s"));
//		System.out.println(NameID(3012199428l));
	}

}


