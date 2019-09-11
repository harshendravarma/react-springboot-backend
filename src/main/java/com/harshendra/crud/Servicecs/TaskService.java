package com.harshendra.crud.Servicecs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.harshendra.crud.EntityClasses.Tasks;
import com.harshendra.crud.ServiceInterface.TaskServiceInterface;
import com.harshendra.crud.Repositories.TaskRepository;
import com.harshendra.crud.TaskNotFoundException;;


@Service
public class TaskService  implements TaskServiceInterface {
	@Autowired
	private TaskRepository TaskRepository;
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	public List<Tasks> findAllTasks(){
		List<Tasks> allTasks=new ArrayList<Tasks>();
		TaskRepository.findAll().forEach(allTasks::add);;
		if(allTasks.size() > 0) {
            return allTasks;
        } else {
            return new ArrayList<Tasks>();
        }
	}
	public Tasks findTaskById(String id) {

		Optional<Tasks> task= TaskRepository.findById(id);
		if(task.isPresent()) {
            return task.get();
        } else {
        	logger.error("No task record exist for given id");
            throw new TaskNotFoundException("No task record exist for given id");
        }
		
	}
	public void addTask(Tasks Task) {
		TaskRepository.save(Task);
	}
	public void editTask(String id, Tasks Task) {
		Optional<Tasks> task= TaskRepository.findById(id);
		if(task.isPresent()) {
		TaskRepository.save(Task);
		}
		else {
			logger.error("No task record exist for given id");
            throw new TaskNotFoundException("No task record exist for given id");
		}
	}
	public void deleteTask(String id) {
		Optional<Tasks> task= TaskRepository.findById(id);
		if(task.isPresent()) {
		TaskRepository.deleteById(id);
		}
		else {
			logger.error("No task record exist for given id");
            throw new TaskNotFoundException("No task record exist for given id");
		}
	}
	public List<Tasks> findTasksByStatus(String status){
		List<Tasks> Tasks=new ArrayList<Tasks>();
		TaskRepository.findBystatus(status).forEach(Tasks::add);;
		return Tasks;
	}
	

}
