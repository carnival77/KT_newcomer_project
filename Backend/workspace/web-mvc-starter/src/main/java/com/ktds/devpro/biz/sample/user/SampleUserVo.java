package com.ktds.devpro.biz.sample.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
*
* 샘플 사용자 VO 클래스 
*  Table: SAMPLE_USER
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
@JsonInclude(Include.NON_NULL)
public class SampleUserVo {

    private String userId;		// 사용자 ID
    private String password;	 	// 비밀번호
    private String userName;		// 사용자 명
    private String createDate;	// 생성시간  (FIXME: 리팩토링.. BaseVo로 이동 검토- 이종혁)

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SampleUserVo [userId=").append(userId).append(", password=").append(password)
				.append(", userName=").append(userName).append(", createDate=").append(createDate).append("]");
		return builder.toString();
	}
    
    
}
