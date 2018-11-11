package com.hs2j.space.ctr;

import com.hs2j.comm.CommController;
import com.hs2j.comm.DTO;
import com.hs2j.comm.StringUtil;
import com.hs2j.space.dao.SpaceDAO;
import com.hs2j.space.vo.SpaceVO;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class SpaceController
 */
@WebServlet(description = "창업공간 관리", urlPatterns = { "/space.do","/space.json"})
public class SpaceController extends HttpServlet implements CommController {
	private static final long serialVersionUID = 1L;
	
    private final Logger LOG = Logger.getLogger(this.getClass());
    private SpaceDAO spaceDAO;
    private String retURL = "";
    private String message = "";
    
	/**
	 * ctr_selectList : 1000
	 * //공간목록
	 * 
	 * ctr_add		 : 2000
	 * //새 요청 (아이템 페이지에서)
	 * 
	 *
	 
	 
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpaceController() {
        super();
        LOG.debug("1-----------------");
        LOG.debug("SpaceController:생성자");
        spaceDAO = new SpaceDAO();
        LOG.debug("spaceDAO:" + spaceDAO.toString());
        LOG.debug("//1-----------------");
    }//---SpaceController()

	/**
     * @see CommController#ctr_add(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_add(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	 LOG.debug("3.2.ctr_add--------------");
         SpaceVO inVO = (SpaceVO) dto;
         SpaceVO outVO = new SpaceVO();
         
         LOG.debug("inVO:" + inVO.toString());
         
         int flag = spaceDAO.do_add(inVO);
         
         if(flag > 0){
        	 outVO.setMessage("등록되었습니다.");
        	 outVO.setMessageDiv("1");
         } else {
        	 outVO.setMessage("등록에 실패하였습니다.");
        	 outVO.setMessageDiv("0");
         }
         
         PrintWriter out = res.getWriter();
         out.write(outVO.getMessageDiv()+"|"+outVO.getMessage());

         LOG.debug("//3.2.ctr_add--------------");
    }

	/**
     * @see CommController#ctr_update(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_update(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        
    	
    }

	/**
     * @see CommController#processRequest(HttpServletRequest, HttpServletResponse)
     */
    public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        LOG.debug("3----------------------");
        LOG.debug("processRequest---------");
        
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");

        // req -> bean mapping
        SpaceVO inVO = new SpaceVO();
        JspRuntimeLibrary.introspect(inVO, req);
        
        LOG.debug("3.1. inVO : " + inVO.toString());
        
        String workDiv = StringUtil.nvl(inVO.getWorkDiv(), "");
        LOG.debug("3.2. workDiv : " + workDiv);
        
        switch(workDiv){
        case "1000": //ctr_selectList
        	retURL = "/space/space_list.jsp";
        	ctr_selectList(inVO, req, res);
        	break;
        case "2000": //ctr_add
        	retURL = "/space/space_list.jsp";
        	ctr_add(inVO, req, res);
        	break;
        }//--switch(workDiv)

        
        RequestDispatcher dispatcher = req.getRequestDispatcher(retURL);
        dispatcher.forward(req, res);
        
        LOG.debug("//3--------------");
    }
    
    
    

	/**
     * @see CommController#ctr_selectOne(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_selectOne(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	
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
        LOG.debug("3.2.ctr_selectList--------------");

        SpaceVO inVO = (SpaceVO) dto;
        inVO.setPageSize(20);
        int totalCnt = 0;
        PrintWriter out = res.getWriter();
        
        LOG.debug("inVO:" + inVO.toString());
        

        List<DTO> list = spaceDAO.do_selectList(inVO);
        
        req.setAttribute("list", list);
        LOG.debug("list:" + list);
        
        //총글수
    	if(null != list && list.size() > 0 ){
    		totalCnt = list.get(0).getTotalCnt();
    	}
    	
    	req.setAttribute("totalCnt", totalCnt);
    	req.setAttribute("paramVO", inVO);
        LOG.debug("//3.2.ctr_selectList--------------");

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("2-----------------");
        LOG.debug("doGet-------------");
        processRequest(request, response);
        LOG.debug("//2-----------------");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("2-----------------");
        LOG.debug("doPost-------------");
        processRequest(request, response);
        LOG.debug("//2-----------------");
	}

}
