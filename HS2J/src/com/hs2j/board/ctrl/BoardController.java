package com.hs2j.board.ctrl;

import com.hs2j.board.dao.BoardDAO;
import com.hs2j.board.vo.BCSelectListVO;
import com.hs2j.board.vo.BCSelectOneVO;
import com.hs2j.board.vo.BoardVO;
import com.hs2j.comm.CommController;
import com.hs2j.comm.DTO;
import com.hs2j.comm.StringUtil;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.runtime.JspRuntimeLibrary;
import org.apache.log4j.Logger;
/**
 * Servlet implementation class BoardController
 */
@WebServlet(description = "게시판관리", urlPatterns = { "/board.do" })
public class BoardController extends HttpServlet implements CommController {
	private static final long serialVersionUID = 1L;
       
	//Logger
	private final Logger LOG = Logger.getLogger(this.getClass());
	
	//Dao
	private BoardDAO boardDAO;
	
	//Return URL
	private String retURL = "";
    
	//Return message
	private String message = "";
	
	String num = "";
	/**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        
        LOG.debug("1===========================");
        LOG.debug("BoardController");
        
        boardDAO = new BoardDAO();
        retURL = "";
        message = "";
        
        LOG.debug("boardDAO: "+boardDAO.toString());
        LOG.debug("//1=========================");
    }

	/**
     * @see CommController#ctr_add(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_add(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	LOG.debug("3.2=ctr_add=================");
    	BoardVO inVO = (BoardVO)dto;
    	
    	int flag = boardDAO.do_add(inVO);	//return 1(성공) 또는 -1(실패)
    	if(flag > 0){		
    		this.message = "등록되었습니다!";
    	}else{
    		this.message = "등록되지 않았습니다!";
    	}

    	//req.setAttribute("message", message);
    }
    
	/**
     * @see CommController#ctr_del(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_del(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	LOG.debug("3.2=ctr_del=================");
    	BoardVO inVO = (BoardVO)dto;
    	
    	int flag = boardDAO.do_del(inVO);	//return 1(성공) 또는 -1(실패)
    	if(flag > 0){
    		this.message = "삭제되었습니다.";
    	}else{
    		this.message = "삭제되지 않았습니다.";
    	}

    	//req.setAttribute("message", message);
    }

	/**
     * @see CommController#ctr_update(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_update(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	LOG.debug("3.2=ctr_update===============");
    	BoardVO inVO = (BoardVO)dto;

    	int flag = boardDAO.do_udt(inVO);	//return 1(성공) 또는 -1(실패)
    	if(flag > 0){
    		this.message = "수정되었습니다.";
    	}else{
    		this.message = "수정되지 않았습니다.";
    	}
    	
    	req.setAttribute("vo", inVO);
    	//req.setAttribute("message", message);
    }
    
	/**
     * @see CommController#ctr_selectOne(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_selectOne(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	LOG.debug("3.2=ctr_selectOne============");
    	BoardVO inVO = (BoardVO)dto;
    	LOG.debug("3.2=inVO="+inVO.toString());
    	BCSelectOneVO outVO = boardDAO.do_selectOne(inVO);    	
    	LOG.debug("3.2=outVO="+outVO.toString());
    	req.setAttribute("vo", outVO);
    }
    
    /**
     * @see CommController#ctr_selectList(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_selectList(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	LOG.debug("3.2=ctr_selectList==========");
    	BoardVO inVO = (BoardVO)dto;
    	inVO.setBdCategory(num);
    	int totalCnt = 0;
    	
    	List<BCSelectListVO> list = boardDAO.do_selectList(inVO);

    	LOG.debug("//3.2=list="+inVO.getSearchWord());
    	LOG.debug("//3.2=list="+list);
    	req.setAttribute("list", list);
    	
    	//총글수
    	if(null != list && list.size() > 0){
    		totalCnt = list.get(0).getBdTotalCnt();
    	}
    		    	
    	req.setAttribute("totalCnt", totalCnt);
    	req.setAttribute("paramVO", inVO);
    	
    	LOG.debug("//3.3=ctr_selectList========");
    }

	/**
     * @see CommController#processRequest(HttpServletRequest, HttpServletResponse)
     */
    public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	LOG.debug("3===========================");
    	LOG.debug("processRequest");
    	req.setCharacterEncoding("utf-8");
    	
    	//req -> bean mapping
    	BoardVO inVO = new BoardVO();
    	JspRuntimeLibrary.introspect(inVO, req);
    	LOG.debug("3.1.BoardVO: "+inVO.toString());
    	
    	String workDiv = StringUtil.nvl(inVO.getWorkDiv(), "");
    	LOG.debug("3.2.workDiv: "+workDiv);

    	/*
    	 * ctr_selectList : 1000
    	 * ctr_selectOne  : 1100
    	 * ctr_add		  : 2000
    	 * ctr_update	  : 3000
    	 * ctr_del 	 	  : 4000
    	 */	

    	switch(workDiv){		
    		case "1000" :	//공지사항 리스트
    			retURL = "/board/bd_list.jsp?type=2100";
    			num = "10";
    			ctr_selectList(inVO, req, res);
    		break;
    		
    		case "1010" :	//창업뉴스 리스트
    			retURL = "/board/bd_list.jsp?type=2200";
    			num = "20";
    			ctr_selectList(inVO, req, res);
    		break;
    		
    		case "1020" :	//창업후기 리스트
    			retURL = "/board/bd_list.jsp?type=2300";
    			num = "30";
    			ctr_selectList(inVO, req, res);
    		break;
    		
    		case "2000" :	//공지사항 글쓰고 난 후 리스트
    			retURL = "/board.do?workDiv=1000";
    			ctr_add(inVO, req, res);
    		break;
    		
    		case "2010" :	//창업뉴스 글쓰고 난 후 리스트
    			retURL = "/board.do?workDiv=1010";
    			ctr_add(inVO, req, res);
    		break;
    		
    		case "2020" :	//창업후기 글쓰고 난 후 리스트
    			retURL = "/board.do?workDiv=1020";
    			ctr_add(inVO, req, res);
    		break;
    		
    		case "1100" :	//더블클릭 후 내가 원하는 내용 보기	
    			retURL = "/board/bd_content.jsp";
    			ctr_selectOne(inVO, req, res);
    		break;

    		case "4000" :	//삭제 후 공지사항 리스트
    			retURL = "/board.do?workDiv=1000";
    			ctr_del(inVO, req, res);
    		break;
    		
    		case "4100" :	//삭제 후 창업뉴스 리스트
    			retURL = "/board.do?workDiv=1010";
    			ctr_del(inVO, req, res);
    		break;
    		
    		case "4200" :	//삭제 후 창업후기 리스트
    			retURL = "/board.do?workDiv=1020";
    			ctr_del(inVO, req, res);
    		break;
    		
    		
    		case "2100" :	//공지사항 글쓰기
    			retURL = "/board/bd_write.jsp?type=2100";
    		break;
    		
    		case "2200" :	//창업뉴스 글쓰기
    			retURL = "/board/bd_write.jsp?type=2200";
    		break;
    		
    		case "2300" :	//창업후기 글쓰기
    			retURL = "/board/bd_write.jsp?type=2300";
    		break;
    		
    		case "3000" :	//공지사항 수정 => bd_write.jsp
    			retURL = "/board.do?workDiv=2100";
    			ctr_update(inVO, req, res);
    		break;
    		
    		case "3100" :	//창업뉴스 수정 => bd_write.jsp
    			retURL = "/board.do?workDiv=2200";
    			ctr_update(inVO, req, res);
    		break;
    		
    		case "3200" :	//창업후기 수정 => bd_write.jsp
    			retURL = "/board.do?workDiv=2300";
    			ctr_update(inVO, req, res);
    		break;

    	}
    	
    	RequestDispatcher dispatcher = req.getRequestDispatcher(retURL);
    	dispatcher.forward(req, res);
    	
    	LOG.debug("//3=========================");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("2===========================");
        LOG.debug("doGet");
        LOG.debug("//2=========================");
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("2===========================");
		LOG.debug("doPost");
		LOG.debug("//2=========================");
		processRequest(request, response);
	}
}