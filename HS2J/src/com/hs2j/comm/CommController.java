package com.hs2j.comm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * processRequest : 거래 분기
 * ctr_selectList : 1000
 * ctr_selectOne : 1100
 * ctr_add		 : 2000
 * ctr_update	 : 3000
 * ctr_del 	 	 : 4000

 * 
 * @author SIST01
 *
 */

public interface CommController {

	public void processRequest(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;
	
	/**
	 * 1000 조회
	 * @param dto
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	public void ctr_selectList(DTO dto, HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;
	
	/**
	 * 1100 단건조회
	 * @param dto
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	public void ctr_selectOne(DTO dto, HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;	

	/**
	 * 2000 등록
	 * @param dto
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	public void ctr_add(DTO dto, HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;	
	/**
	 * 3000 수정
	 * @param dto
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	public void ctr_update(DTO dto, HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;	
	
	/**
	 * 4000 삭제
	 * @param dto
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	public void ctr_del(DTO dto, HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException;	
}
