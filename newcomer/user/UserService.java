package com.ktds.devpro.newcomer.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public List<UserVO> getUserList(){
		return userMapper.selectUserList();
	}
	
	public UserVO getUserById(String uid) {
		return userMapper.selectUserById(uid);
	}
	
//	public UserVO getUserByPassword(String password) {
//		return userMapper.selectUserByPassword(password);
//	}
	
	public int registerUser(UserVO paramVo) {
		return userMapper.registerUser(paramVo);
	}
	
	public int updateUser(UserVO paramVo) {
		return userMapper.updateUser(paramVo);
	}
	
	public int deleteUser(String password) {
		return userMapper.deleteUser(password);
	}
	
	
	
	
}
