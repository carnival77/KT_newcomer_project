package com.ktds.devpro.newcomer.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyUserService {
	@Autowired
	private MyUserMapper userMapper;
	
	public List<MyUserVO> getUserList(){
		return userMapper.selectUserList();
	}
	
	public MyUserVO getUser(String uid) {
		return userMapper.selectUser(uid);
	}
	
	public MyUserVO getUserWithPassword(String uid) {
		return userMapper.selectUserWithPassword(uid);
	}
	
	public int registerUser(MyUserVO paramVo) {
		return userMapper.registerUser(paramVo);
	}
	
	public int updateUser(MyUserVO paramVo) {
		return userMapper.updateUser(paramVo);
	}
	
	public int deleteUser(String password) {
		return userMapper.deleteUser(password);
	}
	
	
	
	
}
