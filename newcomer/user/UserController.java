package com.ktds.devpro.newcomer.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@GetMapping // cmd로 클라이언트로부터 받아와서 처리.
	public List<UserVO> getUserList() {
		logger.debug(">> User 전체목록 조회");
		return userService.getUserList();
	}

//	@GetMapping("/{id}")
//	public TodoVo getTodo(@PathVariable Long id) {
//		return todoService.getTodo(id);
//	}

	@GetMapping("/{uid}")
	public ResponseEntity<?> getUserById(@PathVariable String uid) {
		UserVO user = userService.getUserById(uid);
		if (user == null) {
			return new ResponseEntity<>("User Not Found ", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

//	@GetMapping("/{password}")
//	public ResponseEntity<?> getUserByPassword(@PathVariable String password) {
//		UserVO user = userService.getUserByPassword(password);
//		if (user == null) {
//			return new ResponseEntity<>("User Not Found ", HttpStatus.NOT_FOUND);
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(user);
//	}

	@PostMapping
	public ResponseEntity<?> registerUser(@RequestBody UserVO paramVo) {
		// @RequestBody : 클라이언트로부터 얻는 json Body(VO)
		int cnt = userService.registerUser(paramVo);
		if (cnt == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getUserList());
		} else {
			return new ResponseEntity<>("User insert failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{uid}")
	public ResponseEntity<?> updateUser(@PathVariable String uid, @RequestBody UserVO paramVo) {
		UserVO user = userService.getUserById(uid);
		if (user == null) {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}
		paramVo.setUid(user.getUid());
		logger.debug(">>> Update User 입력 " + paramVo);
		int cnt = userService.updateUser(paramVo);
		if (cnt == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getUserList());
		} else {
			return new ResponseEntity<>("User update failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{password}")
	public ResponseEntity<?> deleteUser(@PathVariable String password) {
		int cnt = userService.deleteUser(password);
		if (cnt == 1)
			return ResponseEntity.status(HttpStatus.OK).body(userService.getUserList());
		else
			return new ResponseEntity<>("User Delete Failed ", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
