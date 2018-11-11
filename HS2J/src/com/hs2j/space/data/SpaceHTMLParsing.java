package com.hs2j.space.data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document; //웹페이지 전체
import org.jsoup.nodes.Element; 
import org.jsoup.select.Elements;
import com.hs2j.space.vo.SpaceVO;

public class SpaceHTMLParsing {
	
	private final Logger LOG = Logger.getLogger(SpaceHTMLParsing.class);
	
	public Connection makeConnection(){
		Connection connection = null;
		
		try{
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB 연결
			connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe",
													 "hs2j","hs2j");
			LOG.debug("Connection:" + connection);
		} catch(ClassNotFoundException cnfe){
			LOG.debug("---SpaceHTMLParsing.makeConnection.ClassNotFoundException---");
			LOG.debug(cnfe.getMessage());
		} catch(SQLException se){	
			LOG.debug("---SpaceHTMLParsing.makeConnection.SQLException---");
			LOG.debug(se.getMessage());
		}//--try-catch
		
		return connection;
		
	}// --makeConnection
	
	public List htmlParse(String pageNo){
		
		List<SpaceVO> list = new ArrayList<SpaceVO>();
		
		try {
			String preUrl = "http://land.naver.com/article/divisionInfo.nhn?rletTypeCd=D01&tradeTypeCd=&hscpTypeCd=&cortarNo=1168000000&page=";
			//String pageNo =  pageNo;
			
			String url = preUrl + pageNo;
			
			Document doc = Jsoup.connect(url).get();
			
			Elements title = doc.select("td.desc div.inner span.txt");
			Elements price = doc.select("td.num div.inner strong");
			Elements naverUrlElement = doc.select("div.inner span.btn_naverlink a");
			Elements photoUrlElement = doc.select("td.bottomline div.inner div.thmb div.thmb_area");
			
			for(int i = 0 ; i < 20 ; i++)
			{
					Element naverUrlLink = naverUrlElement.get(i);
					String naverUrl = naverUrlLink.attr("href");
					
					Element photoUrlLink = photoUrlElement.get(i);
					String photoUrl = photoUrlLink.attr("img_id");
					
					LOG.debug((i+1) + "||" + title.get(i).text() 
							                 + "||" + price.get(i).text()
							                 + "||" + photoUrl);

					SpaceVO retVO = new SpaceVO(title.get(i).text(), price.get(i).text(), "10","2",naverUrl,photoUrl);
					list.add(retVO);
					
			}
			
		} catch (Exception e){
			LOG.debug("---SpaceHTMLParsing.Exception---");
			e.printStackTrace();
		}//--try-catch
		
		return list;
		
	}//--htmlParse
	
	
	
	
	
	
	public static void main(String[] args) {
	}
}
