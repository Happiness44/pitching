package com.hs2j.bic.ctrl;

import com.hs2j.bic.dao.BicDAO;
import com.hs2j.bic.vo.BicVO;
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
 * Servlet implementation class BicController
 */
@WebServlet(description = "창업보육센터관리", urlPatterns = {"/bic.do"})
public class BicController extends HttpServlet implements CommController {
	private static final long serialVersionUID = 1L;
    
	//Logger
	private final Logger LOG = Logger.getLogger(this.getClass());
	
	//DAO
	private BicDAO bicDAO;
	
	//Return URL
	private String retURL = "";
    
	//Return message
	private String message = "";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BicController() {
        super();
        
        LOG.debug("1===========================");
        LOG.debug("BicController");
        
        bicDAO = new BicDAO();
        retURL = "";
        message = "";
        
        LOG.debug("boardDAO: "+bicDAO.toString());
        LOG.debug("//1=========================");
    }

	/**
     * @see CommController#ctr_add(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_add(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
         // TODO Auto-generated method stub
    }

	/**
     * @see CommController#ctr_update(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_update(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
         // TODO Auto-generated method stub
    }

	/**
     * @see CommController#processRequest(HttpServletRequest, HttpServletResponse)
     */
    public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	LOG.debug("3===========================");
    	LOG.debug("processRequest");
    	req.setCharacterEncoding("utf-8");
    	
    	BicVO inVO = new BicVO();
    	JspRuntimeLibrary.introspect(inVO, req);
    	LOG.debug("3.1.BicVO: "+inVO.toString());
    	
    	String workDiv = StringUtil.nvl(inVO.getWorkDiv(), "");
    	LOG.debug("3.2.workDiv: "+workDiv);
    	
    	//switch(workDiv) {
        //	case "1000":
        	retURL = "/bic/bic_list.jsp";
        	ctr_selectList(inVO, req, res);
        //	break;
    	//}
    	
    	RequestDispatcher dispatcher = req.getRequestDispatcher(retURL);
    	dispatcher.forward(req, res);
    	
    	LOG.debug("//3=========================");
    }

	/**
     * @see CommController#ctr_selectOne(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_selectOne(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
         // TODO Auto-generated method stub
    }

	/**
     * @see CommController#ctr_del(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_del(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
         // TODO Auto-generated method stub
    }

	/**
     * @see CommController#ctr_selectList(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_selectList(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	LOG.debug("3.2=ctr_selectList==========");
    	BicVO inVO = (BicVO)dto;
    	
    	List<BicVO> list = bicDAO.do_selectList(inVO);
    	
    	LOG.debug("//3.2=list="+inVO.getSearchDiv());
    	LOG.debug("//3.2=list="+inVO.getSearchWord());
    	LOG.debug("//3.2=list="+list);
    	
    	req.setAttribute("searchDiv", inVO.getSearchDiv());
    	req.setAttribute("searchWord", inVO.getSearchWord());
    	
    	if((list.size() > 0)) {
        	req.setAttribute("fLat", list.get(0).getBicLatitude());
        	req.setAttribute("fLon", list.get(0).getBicLongitude());
    	}else{
    		req.setAttribute("fLat", "37.5553406");
    		req.setAttribute("fLon", "126.9694522");
    	}
    	
    	req.setAttribute("list", list);
    }

	/**
	 * @see HttpServlet#doGet(HtㅋtpServletRequest request, HttpServletResponse response)
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
