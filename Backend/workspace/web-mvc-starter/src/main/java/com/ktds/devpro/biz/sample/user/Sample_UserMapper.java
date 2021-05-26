package com.ktds.devpro.biz.sample.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
*
* REST CRUD 샘플 Mapper 
* <p>
*
* <pre>
* 개정이력(Modification Information)·
* 수정일   수정자    수정내용
* ------------------------------------
* 2017. 9. 28.   이종혁     최초작성
* </pre>
*
* @author 이종혁 (shineljh@kt.com)
* @since 2017. 9. 28.
* @version 1.0.0
*
*/

@Mapper
public interface Sample_UserMapper {
	public List<SampleUserVo> selectSampleUserList(SampleUserVo paramVo);
	public int countSampleUserList(SampleUserVo paramVo);
	public SampleUserVo selectSampleUser(SampleUserVo paramVo);
	public int insertSampleUser(SampleUserVo paramVo);
	public int updateSampleUser(SampleUserVo paramVo);
	public int deleteSampleUser(List<String> idList);
}