package com.hs2j.comm;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hs2j.file.vo.FileVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final Logger LOG=Logger.getLogger(UploadServlet.class);   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		
		//Upload path
		//String savePath ="C:\\upfile";
		String savePath ="/attachedFiles";
		
		int uploadFileSizeLimit = 10*1024*1024;//10M
		String encType = "UTF-8";
		
		ServletContext context = this.getServletContext();
		
		//String uploadFilePath = savePath;
		String uploadFilePath  = context.getRealPath(savePath);
		
		LOG.debug("1---uploadFilePath="+uploadFilePath);
		
		//C:\\upfile\YYYY\MM 
		Date now = new Date();
		SimpleDateFormat sdfY=new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfM=new SimpleDateFormat("MM");
		
		//년도
		String yyyy = sdfY.format(now);
		LOG.debug("2---yyyy="+yyyy);
		
		File dirYear = new File(uploadFilePath+File.separator+yyyy);
		if(dirYear.exists() == false)dirYear.mkdirs();
		
		//년도
		String mm = sdfM.format(now);
		LOG.debug("2---mm="+mm);	
		
		uploadFilePath = uploadFilePath+File.separator+yyyy+File.separator+mm;
		File dirYYYYMM = new File(uploadFilePath);
		if(dirYYYYMM.exists() == false)dirYYYYMM.mkdirs();
		
		LOG.debug("3---uploadFilePath="+uploadFilePath);
		
		try{
			LOG.debug("1. cos---");
			MultipartRequest multi=new MultipartRequest(request,
					uploadFilePath,
					uploadFileSizeLimit,
					encType,
					new DefaultFileRenamePolicy()
					);
			
			List<FileVO> list=new ArrayList<FileVO>();
			
			Enumeration paramFileNames = multi.getFileNames();
			while(paramFileNames.hasMoreElements()){
				String fileInput   = (String) paramFileNames.nextElement();
				LOG.debug("8. cos--fileInput-"+fileInput);
				String fileName = multi.getFilesystemName(fileInput);
				LOG.debug("8.1 cos--fileName-"+fileName);
				
				if(null !=fileName && fileName.length()>0){
					FileVO vo=new FileVO();
					File fileObj = multi.getFile(fileInput);//Upload File
					
					vo.setF_ORG_NM(multi.getOriginalFileName(fileInput));//원본
					vo.setF_RE_NM(uploadFilePath+File.separator+fileName);//저장
					//vo.setFileSize(String.valueOf(fileObj.length()));
					LOG.debug("9. vo-"+vo);
					list.add(vo);
				}
			}
			request.setAttribute("list", list);
			RequestDispatcher dispatcher= request.getRequestDispatcher("/board/bd_list.jsp");
			dispatcher.forward(request, response);
			
		}catch(Exception e){
			LOG.debug("==============");
			LOG.debug(e.getMessage());
			LOG.debug("==============");
		}
	}
}