package com.ktds.devpro.newcomer.user;

import java.util.List;

public interface MyUserMapper {
	public List<MyUserVO> selectUserList();
	public MyUserVO selectUser(String uid);
	public MyUserVO selectUserWithPassword(String uid);
	public int registerUser(MyUserVO userVO);
	public int updateUser(MyUserVO userVO);
	public int deleteUser(String password);
}
