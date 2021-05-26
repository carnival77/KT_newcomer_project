package com.ktds.devpro.biz.todo;

import java.util.List;

public interface TodoMapper {
	public List<TodoVo> selectTodoList();
	public TodoVo selectTodo(Long id);
	public int insertTodo(TodoVo todoVo);
	public int updateTodo(TodoVo todoVo);
	public int deleteTodo(long id );
	public int deleteAllTodo();
}
