package datautil;

import java.util.HashMap;
import java.util.Map;

public class DataMapperCommon {
	public  static Map<String,Integer> webpagecodemap;
	public  static Map<String,Integer> sourcecrawlmap;
	
	static{
		webpagecodemap = new HashMap<String, Integer>();
		sourcecrawlmap = new HashMap<String, Integer>();
		sourcecrawlmap.put("21CN������",2451);
		sourcecrawlmap.put("�й�������", 2445);
		sourcecrawlmap.put("�й�������", 127);
		sourcecrawlmap.put("������", 23);
		sourcecrawlmap.put("�»���", 21);
		sourcecrawlmap.put("����", 17);
		sourcecrawlmap.put("����΢��", 44);
		sourcecrawlmap.put("����", 18);
		sourcecrawlmap.put("��Ѷ��", 16);
		sourcecrawlmap.put("��³��", 2452);
		//�ٶ�����ע��
		sourcecrawlmap.put("�ٶ�", 2448);
		
		
		
	}
	
	
	
}
