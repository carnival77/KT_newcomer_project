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
	
	public UserVO getUser(String uid, String password) {
		return userMapper.selectUser(uid,password);
	}
	
	public int registerUser(UserVO userVO) {
		return userMapper.registerUser(userVO);
	}
	
	public int updateUser(UserVO userVO) {
		return userMapper.updateUser(userVO);
	}
	
	public int deleteUser(UserVO userVO) {
		return userMapper.deleteUser(userVO);
	}
	
	
	
	
}
