package com.hs2j.stat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


public class StatDAOTest {

	private static final Logger LOG = Logger.getLogger(StatDAOTest.class);
	
	public static void main(String[] args) {
		
		Map<String, String> itemCountMap = new HashMap<String, String>();
		
		StatDAO sDao = new StatDAO();
		List<String> list = sDao.getCodeList();
		
		for(int i = 0 ; i < list.size() ; i++){
			String count = sDao.getItemCountsByList(list.get(i));
			LOG.debug("count="+count);
			
			itemCountMap.put(list.get(i), count);
		}
		
		LOG.debug("itemCountMap");
		LOG.debug(itemCountMap.toString());
	}

}
