package com.hs2j.bic.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.hs2j.bic.dao.BicDAO;
import com.hs2j.bic.vo.BicVO;
import com.hs2j.board.dao.BoardDAO;

public class BicParsing {
	
	public BicParsing(BicDAO bicDAO) {
		super();
	}

	// tag값의 정보를 가져오는 메소드
	private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}
	
	public static void main(String[] args) {
		List<BicVO> list = new ArrayList<>();
		
		try {
			// parsing할 url 지정(API 키 포함해서)
			String url = "https://openapi.gg.go.kr/FoundationChildcareCenter?KEY=73b9f86874cb4a62a298bf3ca42bbf8f&type=xml";
			
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			
			// root tag 
			doc.getDocumentElement().normalize();
			
			// 파싱할 tag
			NodeList nList = doc.getElementsByTagName("row");
			//System.out.println("파싱할 리스트 수 : "+ nList.getLength());
				
			for(int temp = 0; temp < nList.getLength(); temp++){
				BicVO bicVo = new BicVO();
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nNode;
					bicVo.setBicName(getTagValue("CENTER_NM", eElement));
					bicVo.setBicPhone(getTagValue("CENTER_TELNO", eElement));
					bicVo.setBicAddress(getTagValue("REFINE_LOTNO_ADDR", eElement));
					bicVo.setBicRoadNm(getTagValue("REFINE_ROADNM_ADDR", eElement));
					bicVo.setBicLatitude(getTagValue("REFINE_WGS84_LAT", eElement));
					bicVo.setBicLongitude(getTagValue("REFINE_WGS84_LOGT", eElement));
				}
				list.add(bicVo);
			}
			
			BicDAO bicDao = new BicDAO();
			
			bicDao.do_add(list);
			
		} catch (Exception e){	
			e.printStackTrace();
		}	// try~catch end
	}
}