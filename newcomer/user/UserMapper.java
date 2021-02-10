package com.ktds.devpro.newcomer.user;

import java.util.List;

public interface UserMapper {
	public List<UserVO> selectUserList();
	public UserVO selectUserById(String uid);
//	public UserVO selectUserByPassword(String password);
	public int registerUser(UserVO userVO);
	public int updateUser(UserVO userVO);
	public int deleteUser(String password);
}
