package mapperrule;

import org.apache.log4j.chainsaw.Main;

public class DealMain {
	
	public static void main(String args[]){
		//����webpage��webcontent
		//NewsWebpageAndContent.exportDataBasicContent(5300);
		NewsCommentMapper.exportBatch();
	}
}
