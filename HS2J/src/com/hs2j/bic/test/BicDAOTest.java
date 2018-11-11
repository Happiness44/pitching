package com.hs2j.bic.test;

import java.util.List;

import org.apache.log4j.Logger;

import com.hs2j.bic.dao.BicDAO;
import com.hs2j.bic.vo.BicVO;

public class BicDAOTest {
	
	private final Logger LOG = Logger.getLogger(BicDAOTest.class);
	private BicVO bicVO;
	
	private BicDAO bicDAO; 
	
	BicDAOTest() {
		bicVO = new BicVO();
		
		bicDAO = new BicDAO();
	}
	
	public void retrieve(){
		//--------------------------------------
		//리스트 조회
		//--------------------------------------
		/*vo03.setSearchDiv("30");							// 10 제목 / 20 작성자 / 30 분류		
		vo03.setSearchWord("IT");*/

		bicVO.setSearchDiv("수원시");
		bicVO.setSearchWord("성균관");
		List<BicVO> list = bicDAO.do_selectList(bicVO);
		
		for(int i=0; i<list.size(); i++){
			BicVO bcvo = (BicVO)list.get(i);
	        LOG.debug(bcvo.toString());
		}
	}
	
	public static void main(String[] args) {
		BicDAOTest test = new BicDAOTest();
		
		test.retrieve();
	}
}
