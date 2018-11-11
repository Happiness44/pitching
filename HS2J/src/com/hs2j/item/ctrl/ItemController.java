package com.hs2j.item.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.runtime.JspRuntimeLibrary;
import org.apache.log4j.Logger;

import com.hs2j.code.dao.CodeDAO;
import com.hs2j.code.vo.CodeVO;
import com.hs2j.comm.CommController;
import com.hs2j.comm.DTO;
import com.hs2j.comm.StringUtil;
import com.hs2j.item.dao.ItemDAO;
import com.hs2j.item.vo.ItemUserCodeFileReqVO;

/**
 * Servlet implementation class UserController
 */
@WebServlet(description = "아이템관리", urlPatterns = { "/item.do" })
public class ItemController extends HttpServlet implements CommController {
	private static final long serialVersionUID = 1L;
       
	//Logger
	private final Logger LOG = Logger.getLogger(this.getClass());
	
	//Dao
	private ItemDAO itemDAO;

	//Return URL
	private String retURL = "";
	
	//Return Message
	private String message = "";
	
	private HashMap<String,String> map= new HashMap<String,String>();
		
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemController() {
        super();
        LOG.debug("1=======================");
        LOG.debug("ItemController");
        
        itemDAO = new ItemDAO();
		retURL="";
		message = "";
		
        LOG.debug("itemDAO: "+itemDAO.toString());
        LOG.debug("//1=======================");
    }

	/**
     * @see CommController#processRequest(DTO, HttpServletRequest, HttpServletResponse)
     */
    public void processRequest(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
         // TODO Auto-generated method stub
    }


	/**
     * @see CommController#processRequest(HttpServletRequest, HttpServletResponse)
     */
    public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
         // TODO Auto-generated method stub
		 LOG.debug("3=======================");
	     LOG.debug("processRequest");
	     req.setCharacterEncoding("utf-8");
	     res.setContentType("text/html; charset=UTF-8");
	     
	     //req->bean mapping
	     ItemUserCodeFileReqVO inVO = new ItemUserCodeFileReqVO();
	     JspRuntimeLibrary.introspect(inVO, req);
	     LOG.debug("3.1.ItemVO: "+inVO.toString());
	     
	     /**
	      * ctl_selectList	: 1000
	      * ctr_selectOne 	: 1100
	      * ctr_add 		: 2000
	      */
	     String workDiv = StringUtil.nvl(inVO.getWorkDiv(),"");
	     String itemSeq = StringUtil.nvl(inVO.getItemSeq(),"");
	     //LOG.debug("3.2.workDiv: "+workDiv);
	     //LOG.debug("3.2.itemSeq: "+itemSeq);
	     
	     switch(workDiv) {
	     case "1000":
	    	 retURL ="/item/item_list.jsp";
	    	 ctr_selectList(inVO, req, res);
	    	 break;
	     case "1200":
	    	 retURL ="/item/item_list.jsp";
	    	 break;
	     case "1100":
	    	 retURL ="/item/item_mng.jsp";
	    	 ctr_selectOne(inVO, req, res);
	    	 break;
	     case "3100":
	    	 retURL ="/item/item_register.jsp";
	    	 ctr_selectOne(inVO, req, res);
	    	 break;
	     case "3200":
	    	 retURL ="/item.do?workDiv=1100";
	    	 ctr_update(inVO, req, res);
	    	 break;
	    // 등록 화면으로 이동
	     case "2100":
	    	 retURL ="/item/item_register.jsp";
	    	 break;
	     case "2000": 
	    	 retURL ="/item/item_list.jsp";
	    	 ctr_add(inVO, req, res);
	    	 break;
	     }
	     
		//5단계 알맞은 view로 forwarding
	    RequestDispatcher dispatcher = req.getRequestDispatcher(retURL);
	    dispatcher.forward(req, res);
	    
	    LOG.debug("//3=======================");
    }

	@Override
	public void ctr_selectOne(DTO dto, HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		ItemUserCodeFileReqVO inVO = (ItemUserCodeFileReqVO) dto;

		List<DTO> d = itemDAO.do_selectOne(inVO);

    	ItemUserCodeFileReqVO outVO = (ItemUserCodeFileReqVO) d.get(0);
    	req.setAttribute("vo", outVO);
	}

	@Override
	public void ctr_selectList(DTO dto, HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		LOG.debug("3.2=ctr_selectList======================");
		ItemUserCodeFileReqVO inVO = (ItemUserCodeFileReqVO)dto;
		int totalCnt = 0;
		
		if(inVO.getSearchDiv()!=null) {
			map.put("itemCategory", inVO.getSearchDiv());
		}if(inVO.getItemRegion()!=null) {
			map.put("itemRegion", inVO.getItemRegion());
		}if(inVO.getItemBranch()!=null) {
			map.put("itemBranch", inVO.getItemBranch());
		}if(inVO.getItemRegPerson()!=null) {
			map.put("itemRegPerson", inVO.getItemRegPerson());
		}if(inVO.getItemTitle()!=null) {
			map.put("itemTitle", inVO.getItemTitle());
		}if(inVO.getItemCount()!=null) {
			map.put("itemCount", inVO.getItemCount());
		}if(inVO.getItemInvestment()!=null) {
			map.put("itemInvestment", inVO.getItemInvestment());
		}if(inVO.getItemCompyn()!=null) {
			map.put("itemCompyn", inVO.getItemCompyn());
		}if(inVO.getItemStartDate()!=null) {
			map.put("itemStartDate", inVO.getItemStartDate());
		}if(inVO.getItemEndDate()!=null) {
			map.put("itemEndDate", inVO.getItemEndDate());
		}
				
		List<DTO> list = itemDAO.do_selectListView(inVO, map);
		req.setAttribute("list", list);
		
		//총글수
		if(null != list && list.size() > 0) {
			totalCnt = list.get(0).getTotalCnt();
		}

		req.setAttribute("totalCnt",totalCnt);
		req.setAttribute("paramVO",inVO);

		LOG.debug("//3.2=ctr_selectList======================");

		map.clear();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("2=======================");
	     LOG.debug("doGet");
	     LOG.debug("//2=======================");
		processRequest(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("2=======================");
        LOG.debug("doPost");
        LOG.debug("//2=======================");
		processRequest(request, response);
	}

	@Override
	public void ctr_add(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LOG.debug("3.2=ctr_add======================");
		ItemUserCodeFileReqVO inVO = (ItemUserCodeFileReqVO)dto;
		
		int flag = itemDAO.do_add(inVO);
		
		if(flag>0) {
			this.message = "등록 되었습니다.";
		}else {
			this.message = "등록을 실패하였습니다.";		
		}
		req.setAttribute("message", message);
		LOG.debug("//3.2=ctr_add======================");
	}

	@Override
	public void ctr_update(DTO dto, HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		LOG.debug("3.2=ctr_update======================");
		ItemUserCodeFileReqVO inVO = (ItemUserCodeFileReqVO)dto;
		
		//LOG.debug("TTTTTUPDATE"+inVO.toString());
		
		int flag = itemDAO.do_update(inVO);
		
		if(flag>0) {
			this.message = "수정 되었습니다.";
		}else {
			this.message = "수정을 실패하였습니다.";		
		}
		req.setAttribute("message", message);

		LOG.debug("//3.2=ctr_update======================");
		
	}

	@Override
	public void ctr_del(DTO dto, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}
