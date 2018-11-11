package com.hs2j.message.ctr;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hs2j.comm.CommController;
import com.hs2j.comm.DTO;
import com.hs2j.comm.StringUtil;
import com.hs2j.message.dao.MessageDAO;
import com.hs2j.message.vo.MessageVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.Request;
import org.apache.jasper.runtime.JspRuntimeLibrary;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class ReqController
 */
@WebServlet(description = "쪽지 관리", urlPatterns = { "/msg.do","/msg.json" })
public class MsgController extends HttpServlet implements CommController {
	private static final long serialVersionUID = 1L;
	
    private final Logger LOG = Logger.getLogger(this.getClass());
    private MessageDAO messageDAO;
    private String retURL = "";
    private String message = "";
    
	/**
	 * ctr_selectList : 1000
	 * //받은(목록), 보낸(목록) 
	 * 
	 * ctr_selectOne : 1100
	 * //개별 항목
	 * 
	 * ctr_add		 : 2000
	 * //새 쪽지
	 * 
	 * ctr_update	 : 3000
	 * 읽음/안읽음
	 */
	 
	 
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MsgController() {
        super();
        LOG.debug("1-----------------");
        LOG.debug("MsgController:생성자");
        messageDAO = new MessageDAO();
        LOG.debug("messageDAO:" + messageDAO.toString());
        LOG.debug("//1-----------------");
    }//---MsgController()

	/**
     * @see CommController#ctr_add(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_add(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	 LOG.debug("3.2.ctr_add--------------");
         MessageVO inVO = (MessageVO) dto;
         MessageVO outVO = new MessageVO();
         
         LOG.debug("inVO:" + inVO.toString());
         
         int flag = messageDAO.do_add(inVO);
         
         if(flag > 0){
        	 outVO.setMessage("등록되었습니다.");
        	 outVO.setMessageDiv("1");
         } else {
        	 outVO.setMessage("등록에 실패하였습니다.");
        	 outVO.setMessageDiv("0");
         }
         
//         PrintWriter out = res.getWriter();
//         out.write(outVO.getMessageDiv()+"|"+outVO.getMessage());
         req.setAttribute("outVOmessage", outVO.getMessageDiv()+"|"+outVO.getMessage());
         LOG.debug("//3.2.ctr_add--------------");
    }

    public void ctr_select_hierlist(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	 LOG.debug("3.2.ctr_select_hierlist----------------------");
    	 MessageVO inVO = (MessageVO) dto;
    	 
    	 List<DTO> list = messageDAO.do_select_hierList(inVO);
    	
    	 req.setAttribute("list", list);
    	
    	 LOG.debug("//3.2.ctr_select_hierlist----------------------");
    	
    }
	/**
     * @see CommController#ctr_update(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_update(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
         // update acceptance
    	 LOG.debug("3.2.ctr_update--------------");
         MessageVO inVO = (MessageVO) dto;
         MessageVO outVO = new MessageVO();
         
         LOG.debug("inVO:" + inVO.toString());
         
         int flag = messageDAO.do_update_read(inVO);
         
         if(flag > 0){
        	 outVO.setMessage("읽음으로 업데이트되었습니다.");
        	 outVO.setMessageDiv("1");
         } else {
        	 outVO.setMessage("읽음으로 업데이트에 실패하였습니다.");
        	 outVO.setMessageDiv("0");
         }
         
         PrintWriter out = res.getWriter();
         out.write(outVO.getMessageDiv()+"|"+outVO.getMessage());

         LOG.debug("//3.2.ctr_update--------------");
    	
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
        MessageVO inVO = new MessageVO();
        JspRuntimeLibrary.introspect(inVO, req);
        
        LOG.debug("3.1. inVO : " + inVO.toString());
        
        String workDiv = StringUtil.nvl(inVO.getWorkDiv(), "");
        LOG.debug("3.2. workDiv : " + workDiv);
        
        switch(workDiv){
        case "1000": //ctr_selectList
        	retURL = "/my_page/msg/msg_list.jsp";
        	ctr_selectList(inVO, req, res);
        	break;
        case "1100": //ctr_selectOne
        	retURL = "/my_page/msg/msg_oneView.jsp"; //새창으로
        	ctr_selectOne(inVO, req, res);
        	break;
        case "1500":
        	retURL = "/my_page/msg/msg_multiView_connected.jsp";
        	ctr_select_hierlist(inVO, req, res);
        	break;
        case "2000": //ctr_add
        	retURL = "/my_page/msg/msg_complete.jsp";
        	ctr_add(inVO, req, res);
        	break;
        case "2100":
        	retURL = "/my_page/msg/msg_new.jsp";
        	ctr_selectOne(inVO,req,res);
        	break;
        case "3000": //ctr_update(readYn)
        	retURL = "/my_page/req/req_list.jsp";
        	ctr_update(inVO, req, res);
        	break;
        }//--switch(workDiv)

        //retURL 막기
        if("1000".equals(workDiv) == true || "3000".equals(workDiv) == true ){
                return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(retURL);
        dispatcher.forward(req, res);
        
        LOG.debug("//3--------------");
    }
    
    
    

	/**
     * @see CommController#ctr_selectOne(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void ctr_selectOne(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    	 LOG.debug("3.2.ctr_selectOne--------------");
    	 
    	  MessageVO inVO = (MessageVO) dto;
          
          LOG.debug("inVO:" + inVO.toString());
          
          MessageVO outVO = (MessageVO) messageDAO.do_selectOne(inVO);
          
          LOG.debug("outVO:" + outVO.toString());
          req.setAttribute("outVO", outVO);    	 

          LOG.debug("//3.2.ctr_selectOne--------------");
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
        dto.setPageSize(5); //Message는 pageSize 5
        MessageVO inVO = (MessageVO) dto;
        int totalCnt = 0;
        PrintWriter out = res.getWriter();
        
        LOG.debug("inVO:" + inVO.toString());
        
        if(inVO.getSearchDiv().equals("99")){
        	LOG.debug("999999");
        	out.write("데이터가 없습니다.");
        	return;
        }
        
        //JSON으로 보내기
        List<DTO> list = messageDAO.do_select_list(inVO);

        //총글수
    	if(null != list && list.size() > 0 ){
    		totalCnt = list.get(0).getTotalCnt();
    	}
    	
    	//JSON으로 보내기
        Gson gson = new Gson();
        String json = gson.toJson(list);
        LOG.debug(json.toString());
        
        out.write(json);
        
    	//req.setAttribute("list", list);
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