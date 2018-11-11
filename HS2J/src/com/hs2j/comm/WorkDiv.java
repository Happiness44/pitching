package com.hs2j.comm;

import java.util.List;

import com.hs2j.User.vo.UserVO;

public interface WorkDiv {
	/**
	 * 단건 조회
	 * @param vo
	 * @return DTO
	 */
	public DTO do_selectOne(DTO vo) ;
	/**
	 * 삭제
	 * @param vo
	 * @return int
	 */
	public int do_del(DTO vo);
	/**
	 * 추가
	 * @param vo
	 * @return int
	 */
	public int do_add(DTO vo);
	
	/**
	 * 조회(List)
	 * @param vo
	 * @return List<DTO>
	 */
	public List<DTO> do_selectList(DTO vo);
	/**
	 * 수정
	 * @param vo
	 * @return int
	 */
	public int do_update(DTO vo);
}
