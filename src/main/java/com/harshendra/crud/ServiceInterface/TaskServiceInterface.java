package com.harshendra.crud.ServiceInterface;

import java.util.List;

import com.harshendra.crud.EntityClasses.Tasks;

public interface TaskServiceInterface {
	public List<Tasks> findAllTasks();
	public Tasks findTaskById(String id);
	public void addTask(Tasks Task);
	public void editTask(String id, Tasks Task);
	public void deleteTask(String id);
	public List<Tasks> findTasksByStatus(String status);
		
}
