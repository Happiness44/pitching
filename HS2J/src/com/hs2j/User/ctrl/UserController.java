package com.hs2j.User.ctrl;

import com.hs2j.comm.CommController;
import com.hs2j.comm.DTO;
import com.hs2j.comm.StringUtil;
import com.hs2j.User.dao.UserDAO;
import com.hs2j.User.vo.UserVO;

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
 * Servlet implementation class UserController
 */
@WebServlet(description = "사용자관리", urlPatterns = { "/user.do" })
public class UserController extends HttpServlet implements CommController {
	private static final long serialVersionUID = 1L;
    
	//Logger
	private final Logger LOG = Logger.getLogger(this.getClass());
	
	//Dao
	private UserDAO userDAO;
	
	//Return URL
	private String retURL = "";
	
	
	//Return message
	private String message = "";
		
		
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        
        LOG.debug("1=============");
        LOG.debug("UserController");
        userDAO =new UserDAO();
        retURL = "";
        message= ""; 
        
        LOG.debug("userDAO:"+userDAO.toString());
        LOG.debug("//1=============");
    }

	/**
     * @see CommController#ctr_selectOne(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_selectOne(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	LOG.debug("3.2=ctr_selectOne=");
    	LOG.debug("TTTTTT");
    	UserVO inVO = (UserVO) dto;
    	LOG.debug("3.2=inVO="+inVO.toString());
    	LOG.debug("TTTTTT"+inVO.getUserId());
    	UserVO outVO= userDAO.do_selectOne(inVO);
    	
    	//작업구분 화면을 전달
    	outVO.setWorkDiv(inVO.getWorkDiv());
    	req.setAttribute("vo", outVO);
    	
    }

	/**
     * @see CommController#ctr_del(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_del(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
  
    }

	/**
     * @see CommController#ctr_add(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_add(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	LOG.debug("3.2=ctr_add=");
    	UserVO inVO = (UserVO) dto;

    
    	int flag = userDAO.do_add(inVO); 
		
    	if(flag>0){
    		this.message = "회원가입 되었습니다.";
    	}else{
    		this.message = "회원가입 실패.";
    	}
    	
    	req.setAttribute("message", message);
    }

	/**
     * @see CommController#processRequest(HttpServletRequest, HttpServletResponse)
     */
    public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	LOG.debug("3========================");
		LOG.debug("processRequest");
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=UTF-8");
		//req->bean mapping
		UserVO inVO=new UserVO();
		JspRuntimeLibrary.introspect(inVO, req);
		LOG.debug("3.1.UserVO:"+inVO.toString());

    	LOG.debug("TTTTTT");
		String workDiv = StringUtil.nvl(inVO.getWorkDiv(),"");
		LOG.debug("3.2.workDiv:"+workDiv);
		/*
		 * ctr_selectList: 1000  
		   ctr_selectOne : 1100 
		   ctr_add       : 2000
		   ctr_update    : 3000
		   ctr_del       : 4000
		 */
		switch(workDiv){
			case "1000"://selectList
				retURL ="/main/user_list.jsp";
				ctr_selectList(inVO,req,res);
			break;
			case "1100"://selectOne
				retURL ="/main/user_register.jsp";
				ctr_selectOne(inVO,req,res);
			break;
			
			case "2000"://ctr_add
				retURL ="/user.do?workDiv=2200";
				ctr_add(inVO,req,res);
			break;	
			
			case "2100"://등록화면으로 이동
				retURL ="/main/user_register.jsp";
				req.setAttribute("vo", inVO);
			break;
					
			case "2200"://main으로 이동
				retURL = "/main/main_bootstrap.jsp";
			break;
			
			case "3000"://ctr_update
				retURL ="/user.do?workDiv=2200";
				ctr_update(inVO,req,res);
			break;		
		}
		
		//5단계  알맞은 view로 포워딩
		RequestDispatcher dispatcher= req.getRequestDispatcher(retURL);
		dispatcher.forward(req, res);
		
		LOG.debug("//3========================");
    }

	/**
     * @see CommController#ctr_selectList(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_selectList(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	LOG.debug("3.2=ctr_selectList=");
    	UserVO inVO = (UserVO) dto;
    	int totalCnt = 0;
    	
    	List<DTO> list = userDAO.do_selectList(inVO);
    	LOG.debug("//3.2=list="+list);
    	req.setAttribute("list", list);
    	
    	//총글수
    	if(null != list && list.size() > 0) {
    		totalCnt = list.get(0).getTotalCnt();
    	}
    	
    	req.setAttribute("totalCnt", totalCnt);
    	req.setAttribute("paramVO", inVO);
    	
    	LOG.debug("//3.3=ctr_selectList=");
    }

	/**
     * @see CommController#ctr_update(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_update(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	LOG.debug("3.2=ctr_update=");
    	UserVO inVO = (UserVO) dto;
    	UserVO outVO= new UserVO();
    	
    	int flag = userDAO.do_update(inVO);
    	if(flag>0){
    		outVO.setMessage("수정 되었습니다.");
    		outVO.setMessageDiv("1");    		
    	}else{
    		outVO.setMessage("수정 실패.");
    		outVO.setMessageDiv("0");  
    		
    	}
    	
    	PrintWriter out=res.getWriter();
    	out.write(outVO.getMessageDiv()+"|"+outVO.getMessage());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("2========================");
		LOG.debug("doGet");
		LOG.debug("2========================");
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LOG.debug("2========================");
		LOG.debug("doPost");
		LOG.debug("2========================");
		processRequest(request, response);
	}


}
