package com.hs2j.item.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.hs2j.comm.DTO;
import com.hs2j.item.vo.ItemUserCodeFileReqVO;
import com.hs2j.comm.JdbcUtil;
import com.hs2j.comm.SimpleConnectionMaker;

public class ItemDAO {
	private final Logger LOG = Logger.getLogger(ItemDAO.class);
	private SimpleConnectionMaker sConnMaker = new SimpleConnectionMaker();
		
	/**
	 * 검색조건으로 간단한 Item List
	 * @param map
	 * @return List<ItemDTO>
	 */
	public List<DTO> do_selectListView(DTO dto, HashMap<String,String> map){
		List<DTO> list = new ArrayList<DTO>();
		ItemUserCodeFileReqVO vo = (ItemUserCodeFileReqVO) dto;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs= null; 
		
		try {
			connection = sConnMaker.makeConnection();
			
			StringBuilder sbParam = new StringBuilder();
			StringBuilder sbbParam = new StringBuilder();
			
			if(!map.containsValue("")) {
				if(map.containsKey("itemCompyn")){
					sbParam.append(" and tt1.item_compyn ='" + map.get("itemCompyn") + "'");

					sbbParam.append(" where b.item_compyn = '" + map.get("itemCompyn") + "'");
				}
				if(map.containsKey("itemBranch")){
					sbParam.append(" and tt1.item_branch = (select cd_dtl_nm from hs_code where cd_mst_id='ITEM_BRANCH' and cd_dtl_id='" + map.get("itemBranch") + "')");
					
					sbbParam.append(" and b.item_branch = '" + map.get("itemBranch") + "'");
//					sbbParam.append("and b.item_branch=(select cd_dtl_id from hs_code "
//					+"where cd_dtl_nm = (select cd_dtl_nm from hs_code where cd_mst_id='ITEM_BRANCH' and cd_dtl_id='"+map.get("itemBranch") + "')"+
//					" and cd_mst_id='ITEM_BRANCH')");
				}
				if(map.containsKey("itemCategory")){
					sbParam.append(" and tt1.item_category = (select cd_dtl_nm from hs_code where cd_mst_id='ITEM_CATEGORY' and cd_dtl_id='" + map.get("itemCategory") + "')");
					
					sbbParam.append(" and b.item_category = '" + map.get("itemCategory") + "'");
//					sbbParam.append("and b.item_category=(select cd_dtl_id from hs_code "
//					+"where cd_dtl_nm = (select cd_dtl_nm from hs_code where cd_mst_id='itemCategory' and cd_dtl_id='"+map.get("itemCategory") + "')"+
//					" and cd_mst_id='ITEM_CATEGORY')");	
				}
				if(map.containsKey("itemCount")){ 
					sbParam.append(" and tt1.item_count <= '" + map.get("itemCount") + "'");
					
					sbbParam.append(" and b.item_count <= '" + map.get("itemCount") + "'");
				}
				if(map.containsKey("itemStartdate")){
					sbParam.append(" and tt1.item_start_date >= '" + map.get("itemStartdate") + "'");
					
					sbbParam.append(" and b.item_start_date <= '" + map.get("itemStartdate") + "'");
				}
				if(map.containsKey("itemEndDate")){
					sbParam.append(" and tt1.item_end_date <= '" + map.get("itemEndDate") + "'");
					
					sbbParam.append(" and b.item_end_date <= '" + map.get("itemEndDate") + "'");
				}
				if(map.containsKey("itemInvestment")){
					sbParam.append(" and tt1.item_investment <= '" + map.get("itemInvestment") + "'");
					
					sbbParam.append(" and b.item_investment <= '" + map.get("itemInvestment") + "'");
				}
				if(map.containsKey("itemRegion")){
					sbParam.append(" and tt1.item_region = (select cd_dtl_nm from hs_code where cd_mst_id='ITEM_REGION' and cd_dtl_id='" + map.get("itemRegion") + "')");
					
					sbbParam.append(" and b.item_region = '" + map.get("itemRegion") + "'");
//					sbbParam.append("and b.item_region=(select cd_dtl_id from hs_code "
//					+"where cd_dtl_nm = (select cd_dtl_nm from hs_code where cd_mst_id='itemRegion' and cd_dtl_id='"+map.get("itemRegion") + "')"+
//					" and cd_mst_id='ITEM_REGION')");	
				}
				if(map.containsKey("itemTitle")){
					sbParam.append(" and tt1.item_title like '%" + map.get("itemTitle") + "%'");

					sbbParam.append(" and b.item_title like '%" + map.get("itemTitle") + "%'");
				}
				if(map.containsKey("itemRegPerson")){
					sbParam.append(" and tt1.item_reg_person like '%" + map.get("itemRegPerson") + "%'");

					sbbParam.append(" and b.item_reg_person like '%" + map.get("itemRegPerson") + "%'");
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("			SELECT ta.page_num,                                                                                                                                \n");
			sb.append("			ta.item_seq,                                                                                                                                       \n");
			sb.append("			ta.item_title,                                                                                                                                     \n");
			sb.append("			ta.nowmoney,                                                                                                                                       \n");
			sb.append("			ta.nowperson,ta.item_region,                                                                                                                       \n");
			sb.append("			ta.item_category,                                                                                                                                  \n");
			sb.append("			 TO_CHAR(ta.item_start_date,'YYYY-MM-DD') item_start_date,                                                                                         \n");
			sb.append("			  TO_CHAR(ta.item_end_date,'YYYY-MM-DD') item_end_date,                                                                                            \n");
			sb.append("			ta.item_count,                                                                                                                                     \n");
			sb.append("			ta.item_branch,                                                                                                                                    \n");
			sb.append("			ta.item_investment,                                                                                                                                \n");
			sb.append("			ta.item_reg_person,                                                                                                                                \n");
			sb.append("			TO_CHAR(ta.item_repaydate,'YYYY-MM-DD') item_repaydate,                                                                                            \n");
			sb.append("			ta.item_compyn, tb.total_Cnt                                                                                                                       \n");            
			sb.append("			 FROM (select rownum as num, tt1.* from (                                                                                                          \n");                                 
			sb.append("			       SELECT CEIL(t1.rnum/?         ) as page_num, t1.*                                                   	                                   \n");  
			sb.append("			       FROM (SELECT rownum as rnum, b.item_seq,                                                                                                    \n");
			sb.append("                          b.ITEM_TITLE, nvl(a.nowmoney,0) nowmoney,                                                                                         \n");
			sb.append("                          nvl(a.nowperson,0) nowperson,                                                                                                     \n");
			sb.append("                          (select dd.cd_dtl_nm from hs_code dd where 'ITEM_CATEGORY' = dd.cd_mst_id and b.ITEM_CATEGORY = dd.cd_dtl_id) ITEM_CATEGORY,      \n");                            
			sb.append("                          (select dd.cd_dtl_nm from hs_code dd where 'ITEM_REGION' = dd.cd_mst_id and b.ITEM_REGION = dd.cd_dtl_id) item_region,                                                                                                                       \n");
			sb.append("                          b.ITEM_START_DATE,                                                                                                                \n");
			sb.append("                          b.ITEM_END_DATE,                                                                                                                  \n");
			sb.append("                          b.ITEM_COUNT,                                                                                                                     \n");
			sb.append("                          (select dd.cd_dtl_nm from hs_code dd where 'ITEM_BRANCH' = dd.cd_mst_id and b.item_branch = dd.cd_dtl_id) item_branch ,           \n");           
			sb.append("                          b.ITEM_INVESTMENT,                                                                                                                \n");
			sb.append("                          b.ITEM_REG_PERSON,                                                                                                                \n");
			sb.append("                          b.ITEM_REPAYDATE,                                                                                                                 \n");
			sb.append("                          b.ITEM_COMPYN                                                                                                                     \n");
			sb.append("                          FROM HS_ITEM b left outer join (SELECT nvl(sum(nvl(REQ_MONEY,0)),0) nowmoney                                                      \n");
			sb.append("                                                                   ,req_item,                                                                               \n");
			sb.append("                                                                   nvl((  SELECT DISTINCT count(*) over(partition by req_item)                              \n");
			sb.append("                                                                           FROM HS_REQUESTS tt                                                              \n");              
			sb.append("                                                                           WHERE REQ_ACCEPTANCE = 10                                                        \n");              
			sb.append("                                                                          and req_category=20                                                               \n");              
			sb.append("                                                                          and tt.req_item = ta.req_item),0) nowperson                                       \n");              
			sb.append("                                                           FROM HS_REQUESTS ta                                                                              \n");                                
			sb.append("                                                           group by req_item) a                                                                             \n");                                
			sb.append("                                              on b.item_seq = a.req_item                                                                                    \n");
			sb.append("                    ) t1                                                                                                                                    \n");
			sb.append("			      ) tt1                                                                                                                                        \n");
			
			sb.append("              WHERE tt1.page_num =?                                                                                                                         \n");
			//sb.append("              and tt1.item_branch='기획'                                                                                                                      \n");
			if( sbParam.toString().length()>0){
				sb.append(sbParam.toString());
			}
			sb.append("              ) ta                                                                                                                                          \n");
			sb.append("      natural join ( select count(*) total_cnt                                                                                                              \n");
			sb.append("                    from hs_item b                                                                                                                          \n");
			if(sbbParam.toString().length()>0) {
				sb.append(sbbParam.toString());
			}
			//sb.append("                   where b.item_branch=(select cd_dtl_id from hs_code                                                                                      \n");
			//sb.append("                  where cd_dtl_nm = (select cd_dtl_nm from hs_code where cd_mst_id='ITEM_BRANCH' and cd_dtl_id='10') and cd_mst_id='ITEM_BRANCH')                                                               \n");
			
			sb.append("                  ) tb                                                                                                                                      \n");
			//sb.append("			order by item_seq  			                                                                                                                   \n");
			
			
			ps = connection.prepareStatement(sb.toString());
			ps.setInt(1, vo.getPageSize()); //page_size
		    ps.setInt(2, vo.getPageNum());	//page_num
		    
			rs = ps.executeQuery();
			while(rs.next()) {
				ItemUserCodeFileReqVO itemVO = new ItemUserCodeFileReqVO();
				itemVO.setPageNum(rs.getInt("page_num"));
				itemVO.setItemSeq(rs.getString("item_seq"));
				itemVO.setItemTitle(rs.getString("item_title"));	
				itemVO.setTotalMoney(rs.getInt("nowmoney"));
				itemVO.setTotalPerson(rs.getInt("nowperson"));	
				itemVO.setItemRegion(rs.getString("item_region"));	
				itemVO.setItemCategory(rs.getString("item_category"));
				itemVO.setItemStartDate(rs.getString("item_start_date"));
				itemVO.setItemEndDate(rs.getString("item_end_date"));
				itemVO.setItemCount(rs.getString("item_count"));
				itemVO.setItemBranch(rs.getString("item_branch"));
				itemVO.setItemInvestment(rs.getString("item_investment"));			
				itemVO.setItemRegPerson(rs.getString("item_reg_person"));
				itemVO.setItemRepaydate(rs.getString("item_repaydate"));
				itemVO.setItemCompyn(rs.getString("item_compyn"));	
				itemVO.setTotalCnt(rs.getInt("total_cnt"));	

				list.add(itemVO);
			}
		}catch (SQLException e) {
			LOG.debug("SQLException====================");
			e.printStackTrace();
			LOG.debug("====================");
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return list;		
	}
	
	
	/**
	 * ItemDTO do_selectOne
	 * @param vo
	 * @return
	 */
 	public List<DTO> do_selectOne(DTO dto) {
		List<DTO> list = new ArrayList<DTO>();
		ItemUserCodeFileReqVO vo = (ItemUserCodeFileReqVO) dto;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs= null; 
		
		try {
			connection = sConnMaker.makeConnection();
			
			StringBuilder sb = new StringBuilder();
			 sb.append("		SELECT b.item_seq,                                                                                         													  \n");
			 sb.append("			  b.item_title,                                                                                                                                               \n");
			 sb.append("			  dd.item_category,                                                                                                                                            \n");
			 sb.append("			  dc.item_region,                                                                                                                                              \n");
			 sb.append("			TO_CHAR(  b.item_start_date,'YYYY-MM-DD') item_start_date,                                                                                                    \n");
			 sb.append("			  TO_CHAR(  b.item_end_date,'YYYY-MM-DD') item_end_date,                                                                                                      \n");
			 sb.append("			  b.item_count,                                                                                                                                               \n");
			 sb.append("			  da.item_branch,                                                                                                                                              \n");
			 sb.append("			  b.item_contents,                                                                                                                                          \n"); 
			 sb.append("			  b.item_investment,                                                                                                                                                \n");
			 sb.append("			  b.item_royalty,                                                                                                                                                   \n");
			 sb.append("			 TO_CHAR(  b.item_repaydate,'YYYY-MM-DD') item_repaydate,                                                                                                           \n");
			 sb.append("			  b.item_file,                                                                                                                                                      \n");
			 sb.append("			  b.item_reg_person,                                                                                                                                                \n");
			 sb.append("			  b.item_reg_date,                                                                                                                                                  \n");
			 sb.append("			  b.item_compyn,                                                                                                                                                    \n");
			 sb.append("			  aa.investMoney,                                                                                                                                                   \n");
			 sb.append("			  aa.req_sender,                                                                                                                                                    \n");
			 sb.append("			  c.user_id,                                                                                                                                                        \n");
			 sb.append("			  c.user_email,                                                                                                                                                     \n");
			 sb.append("			  c.user_name,                                                                                                                                                      \n");
			 sb.append("			  c.user_work,                                                                                                                                                      \n");
			 sb.append("			  nvl(c.user_img_file,0) user_img_file,                                                                                                                             \n");
			 sb.append("			  c.user_p_number,                                                                                                                                                  \n");
			 sb.append("			  nvl(cc.nowmoney,0) totalMoney,                                                                                                                                    \n");
			 sb.append("			  nvl(a.nowperson,0) totalPerson                                                                                                                                    \n");
			 sb.append("			FROM hs_item b left outer JOIN (SELECT nvl(sum(ta.req_money),0) investMoney,                                                                                        \n");
			 sb.append("			                                ta.req_sender,                                                                                                                      \n");
			 sb.append("			                                tb.item_seq                                                                                                                         \n");
			 sb.append("			                        FROM hs_requests ta, HS_ITEM tb                                                                                                             \n");
			 sb.append("			                       WHERE tb.item_seq = ta.req_item                                                                                                              \n");
			 sb.append("			                       AND ta.req_acceptance='10'                                                                                                                   \n");
			 sb.append("			                      GROUP BY ta.req_sender,tb.item_seq) aa                                                                                                        \n");
			 sb.append("	                      ON b.item_seq = aa.item_seq                                                                                                                           \n");
			 sb.append("			left outer JOIN (SELECT tt.* FROM hs_user tt) c                                                                                                                     \n");
			 sb.append("			ON c.user_id = b.item_reg_person                                                                                                                                    \n");
			 sb.append("			left outer join (select count(*) nowperson, req_item from hs_requests where req_acceptance='10' and req_category='20' group by req_item) a                          \n");
			 sb.append("			  ON b.item_seq = a.req_item 	                                                                                                                                    \n");
			 sb.append("	  left outer join (select nvl(sum(nvl(req_money,0)),0) nowmoney, req_item from hs_requests where req_acceptance='10' and req_category='10' group by req_item) cc            \n");
			 sb.append("			  ON b.item_seq = cc.req_item                                                                                                                                       \n");
			  sb.append("  left outer join(select cd_dtl_nm as item_category, cd_dtl_id, cd_mst_id from hs_code where cd_mst_id = 'ITEM_CATEGORY') dd                                                    \n");
			  sb.append("  on b.item_category = dd.cd_dtl_id                                                                                                                                             \n");
			  sb.append("  left outer join(select cd_dtl_nm as item_branch, cd_dtl_id, cd_mst_id from hs_code where cd_mst_id =  'ITEM_BRANCH') da                                                       \n");
			  sb.append("  on b.item_branch = da.cd_dtl_id                                                                                                                                               \n");
			  sb.append("  left outer join(select cd_dtl_nm as item_region, cd_dtl_id, cd_mst_id from hs_code where cd_mst_id =  'ITEM_REGION') dc                                                       \n");
			  sb.append("  on b.item_region = dc.cd_dtl_id                                                                                                                                               \n");
			 sb.append("	       where b.item_seq= ?                                                                                                                                                 \n");
			
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1,vo.getItemSeq());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				ItemUserCodeFileReqVO itemVO = new ItemUserCodeFileReqVO();
				itemVO.setItemSeq(rs.getString("item_seq"));
				itemVO.setItemTitle(rs.getString("item_title"));
				itemVO.setItemCategory(rs.getString("item_category"));
				itemVO.setItemRegion(rs.getString("item_region"));
				itemVO.setItemStartDate(rs.getString("item_start_date"));
				itemVO.setItemEndDate(rs.getString("item_end_date"));
				itemVO.setItemCount(rs.getString("item_count"));
				itemVO.setItemBranch(rs.getString("item_branch"));	
				itemVO.setItemContents(rs.getString("item_contents"));
				itemVO.setItemInvestment(rs.getString("item_investment"));
				itemVO.setItemRoyalty(rs.getString("item_royalty"));
				itemVO.setItemRepaydate(rs.getString("item_repaydate"));
				itemVO.setItemFile(rs.getString("item_file"));
				itemVO.setItemRegPerson(rs.getString("item_reg_person"));
				itemVO.setItemRegDate(rs.getString("item_reg_date"));
				itemVO.setItemCompyn(rs.getString("item_compyn"));
				itemVO.setInvestMoney(rs.getInt("investMoney"));
				itemVO.setReq_sender(rs.getString("req_sender"));
				itemVO.setUserId(rs.getString("user_id"));
				itemVO.setUserEmail(rs.getString("user_email"));
				itemVO.setUserName(rs.getString("user_name"));
				itemVO.setUserWork(rs.getString("user_work"));
				itemVO.setUserImage(rs.getString("user_img_file"));
				itemVO.setUserPNumber(rs.getString("user_p_number"));
				itemVO.setTotalMoney(rs.getInt("totalMoney"));
				itemVO.setTotalPerson(rs.getInt("totalPerson"));
				
				//LOG.debug("AAAAAAAAAAAA-retVO : \n " + itemVO.toString());
				list.add(itemVO);
			}
		}catch (SQLException e) {
			LOG.debug("SQLException====================");
			e.printStackTrace();
			LOG.debug("====================");
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return list;
	}

	
	/**
	 * 아이템의 진행완료
	 * @param vo
	 * @return
	 */
	public int do_updateCompyn(DTO dto){
		int flag = -1;
		ItemUserCodeFileReqVO vo = (ItemUserCodeFileReqVO) dto;
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = sConnMaker.makeConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE hs_item          	   \n");
			sb.append("  SET item_compyn = '20'		\n");
			sb.append("  ,item_reg_date = sysdate	\n");	
			sb.append("WHERE item_seq = 37  		\n");
			sb.append("AND item_reg_person = '작성자'\n"); 	
			
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1,vo.getItemSeq());
			ps.setString(2,vo.getItemRegPerson());
			
			flag = ps.executeUpdate();
			
			//LOG.debug("dao do_updateCompyn flag: "+flag);
		}catch (SQLException e) {
			LOG.debug("SQLException====================");
			e.printStackTrace();
			LOG.debug("====================");
		}finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return flag;
	}
	

	/**
	 * Item의 정보 수정
	 * @param dto
	 * @return
	 */
	public int do_update(DTO dto) {
		int flag = -1;
		ItemUserCodeFileReqVO vo = (ItemUserCodeFileReqVO) dto;
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = sConnMaker.makeConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE hs_item 			\n");
			sb.append("SET                        \n");
			sb.append("  item_title = ?,         \n");
			sb.append("  item_category = ?,      \n");
			sb.append("  item_region=?,          \n");
			sb.append("  item_start_date=?,      \n");
			sb.append("  item_end_date=?,        \n");
			sb.append("  item_count=?,           \n");
			sb.append("  item_branch=?,          \n");
			sb.append("  item_contents=?,        \n");
			sb.append("  item_investment=?,      \n");
			sb.append("  item_royalty=?,         \n");
			sb.append("  item_repaydate=?,       \n");
			//sb.append("  item_file=?,            \n");
			sb.append("  item_reg_date = sysdate, \n");
			sb.append("  item_compyn=?           \n");
			sb.append("WHERE item_seq = ?    \n");
			sb.append("AND item_reg_person = ?\n");
			
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1,vo.getItemTitle());
			ps.setString(2,vo.getItemCategory());
			ps.setString(3,vo.getItemRegion());
			ps.setString(4,vo.getItemStartDate());
			ps.setString(5,vo.getItemEndDate());
			ps.setString(6,vo.getItemCount());
			ps.setString(7,vo.getItemBranch());
			ps.setString(8,vo.getItemContents());
			ps.setString(9,vo.getItemInvestment());
			ps.setString(10,vo.getItemRoyalty());
			ps.setString(11,vo.getItemRepaydate());
			//ps.setString(12,vo.getItemFile());
			ps.setString(12,vo.getItemCompyn());
			ps.setString(13,vo.getItemSeq());
			ps.setString(14,vo.getItemRegPerson());
			
			flag = ps.executeUpdate();
			
			//LOG.debug("flag: "+flag);
		} catch (SQLException e) {
			LOG.debug("SQLException====================");
			e.printStackTrace();
			LOG.debug("====================");
		}finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return flag;
	}
	
	
	/**
	 * 아이템 추가
	 * @param dto
	 * @return
	 */
	public int do_add(DTO dto) {
		int flag = -1;
		ItemUserCodeFileReqVO vo = (ItemUserCodeFileReqVO) dto;
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = sConnMaker.makeConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT               \n");
			sb.append("INTO hs_item         \n");
			sb.append("  (                  \n");
			sb.append("    item_seq,        \n");
			sb.append("    item_title,      \n");
			sb.append("    item_category,   \n");
			sb.append("    item_region,     \n");
			sb.append("    item_start_date, \n");
			sb.append("    item_end_date,   \n");
			sb.append("    item_count,      \n");
			sb.append("    item_branch,     \n");
			sb.append("    item_contents,   \n");
			sb.append("    item_investment, \n");
			sb.append("    item_royalty,    \n");
			sb.append("    item_repaydate,  \n");
			sb.append("    item_file,       \n");
			sb.append("    item_reg_person, \n");
			sb.append("    item_reg_date,   \n");
			sb.append("    item_compyn      \n");
			sb.append("  )         \n");
			sb.append("  VALUES    \n");
			sb.append("  (         \n");
			sb.append("    item_seq.nextval,      \n");
			sb.append("    ?,      \n");
			sb.append("    ?,      \n");
			sb.append("    ?,      \n");
			sb.append("    ?,      \n");
			sb.append("    ?,      \n");
			sb.append("    ?,      \n");
			sb.append("    ?,      \n");
			sb.append("    ?,      \n");
			sb.append("    ?,      \n");
			sb.append("    ?,      \n");
			sb.append("    ?,      \n");
			sb.append("    ?,      \n");
			sb.append("    ?,      \n");
			sb.append("    sysdate,      \n");
			sb.append("    ?       \n");
			sb.append("  )         \n");
			
			//LOG.debug("sql:\n"+sb.toString());
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1,vo.getItemTitle());
			ps.setString(2,vo.getItemCategory());
			ps.setString(3,vo.getItemRegion());
			ps.setString(4,vo.getItemStartDate());
			ps.setString(5,vo.getItemEndDate());
			ps.setString(6,vo.getItemCount());
			ps.setString(7,vo.getItemBranch());
			ps.setString(8,vo.getItemContents());
			ps.setString(9,vo.getItemInvestment());
			ps.setString(10,vo.getItemRoyalty());
			ps.setString(11,vo.getItemRepaydate());
			ps.setString(12,vo.getItemFile());
			ps.setString(13,vo.getItemRegPerson());
			ps.setString(14,vo.getItemCompyn());
			
			flag = ps.executeUpdate();
			
			//LOG.debug("dao add_flag: "+flag);
		} catch (SQLException e) {
			LOG.debug("SQLException====================");
			e.printStackTrace();
			LOG.debug("====================");
		}finally {
			JdbcUtil.close(ps);
			JdbcUtil.close(connection);
		}
		return flag;
	}
}
