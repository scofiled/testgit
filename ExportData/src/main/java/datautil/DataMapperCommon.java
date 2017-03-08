package datautil;

import java.util.HashMap;
import java.util.Map;

public class DataMapperCommon {
	public  static Map<String,Integer> webpagecodemap;
	public  static Map<String,Integer> sourcecrawlmap;
	
	static{
		webpagecodemap = new HashMap<String, Integer>();
		sourcecrawlmap = new HashMap<String, Integer>();
		sourcecrawlmap.put("21CN新闻网",2451);
		sourcecrawlmap.put("中国新闻网", 2445);
		sourcecrawlmap.put("中国经济网", 127);
		sourcecrawlmap.put("人民网", 23);
		sourcecrawlmap.put("新华网", 21);
		sourcecrawlmap.put("新浪", 17);
		sourcecrawlmap.put("新浪微博", 44);
		sourcecrawlmap.put("网易", 18);
		sourcecrawlmap.put("腾讯网", 16);
		sourcecrawlmap.put("齐鲁网", 2452);
		//百度名字注意
		sourcecrawlmap.put("百度", 2448);
		
		
		
	}
	
	
	
}
