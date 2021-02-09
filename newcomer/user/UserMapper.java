package com.ktds.devpro.newcomer.user;

import java.util.List;

public interface UserMapper {
	public List<UserVO> selectUserList();
	public UserVO selectUser(String uid,String password);
	public int registerUser(UserVO userVO);
	public int updateUser(UserVO userVO);
	public int deleteUser(UserVO userVO);
}
