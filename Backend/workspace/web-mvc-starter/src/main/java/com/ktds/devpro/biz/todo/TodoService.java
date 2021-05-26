package com.ktds.devpro.biz.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
	@Autowired
	private TodoMapper todoMapper;
	
	public List<TodoVo> getTodoList(){
		return todoMapper.selectTodoList();
	}
	
	public TodoVo getTodo(long id) {
		return todoMapper.selectTodo(id);
	}
	
	public int registerTodo(TodoVo paramVo) {
		return todoMapper.insertTodo(paramVo);
	}
	
	public int modifyTodo(TodoVo paramVo) {
		return todoMapper.updateTodo(paramVo);
	}
	
	public int deleteTodo(long id) {
		return todoMapper.deleteTodo(id);
	}
	
	public int deleteAllTodo() {
		return todoMapper.deleteAllTodo();
	}
	
	
	
	
}
