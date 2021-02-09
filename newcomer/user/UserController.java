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
	private TodoService todoService;

	@GetMapping
	public List<TodoVo> getTodoList() {
		logger.debug(">> Todo 전체목록 조회");
		return todoService.getTodoList();
	}

//	@GetMapping("/{id}")
//	public TodoVo getTodo(@PathVariable Long id) {
//		return todoService.getTodo(id);
//	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getTodo(@PathVariable Long id) {
		TodoVo todo = todoService.getTodo(id);
		if (todo == null) {
			return new ResponseEntity<> ("Todo Not Found ", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(todo); 
	}

	@PostMapping
	public ResponseEntity<?> registerTodo(@RequestBody TodoVo paramVo) {
		// @RequestBody : 클라이언트로부터 얻는 json Body(VO)
		int cnt = todoService.registerTodo(paramVo);
		if (cnt == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodoList());
		} else {
			return new ResponseEntity<>("Todo insert failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> modifyTodo(@PathVariable Long id, @RequestBody TodoVo paramVo) {
		TodoVo todo = todoService.getTodo(id);
		if (todo == null) {
			return new ResponseEntity<>("Todo not found", HttpStatus.NOT_FOUND);
		}
		paramVo.setId(todo.getId());
		logger.debug(">>> Update Todo 입력 " + paramVo);
		int cnt = todoService.modifyTodo(paramVo);
		if (cnt == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodoList());
		} else {
			return new ResponseEntity<>("Todo update failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
		int cnt = todoService.deleteTodo(id);
		if (cnt == 1) 
			return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodoList());
		else
			return new ResponseEntity<> ("Todo Delete Failed ", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
