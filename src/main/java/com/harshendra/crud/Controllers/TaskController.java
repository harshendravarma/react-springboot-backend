package com.harshendra.crud.Controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


import com.harshendra.crud.EntityClasses.Tasks;
import com.harshendra.crud.Servicecs.TaskService;;
@RestController
public class TaskController {
	
	@Autowired
	private TaskService TaskService;
	
	@GetMapping("/Tasks")
    public ResponseEntity<List<Tasks>> getAllTasks() {
        List<Tasks> list = TaskService.findAllTasks();
        return new ResponseEntity<List<Tasks>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	@GetMapping("/Taskscompleted")
	public ResponseEntity<?> getCompletedTasks() {
		String status="c";
		List<Tasks> list=TaskService.findTasksByStatus(status);
		return  new ResponseEntity<>(list, HttpStatus.OK);
	}
	@GetMapping("/Tasksinprogress")
	public ResponseEntity<?> getInProgressTasks() {
		String status="p";
		List<Tasks> list= TaskService.findTasksByStatus(status);
		return  new ResponseEntity<>(list, HttpStatus.OK);
	}
	@GetMapping("/Tasks/{id}")
	public ResponseEntity<?> findTaskById(@PathVariable String id) {
		Tasks task=TaskService.findTaskById(id);
		if(task==null) {
			return new ResponseEntity<>("no task found for given id", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(task, HttpStatus.OK);
	}
	@PostMapping(value="/Tasks")
	public ResponseEntity<String> addTask(RequestEntity<Tasks> requestEntity) {
		if(requestEntity.getBody().getStatus().contentEquals("p") || requestEntity.getBody().getStatus().contentEquals("c")) {
			TaskService.addTask(requestEntity.getBody());
			return new ResponseEntity<>(
				      "Task added succesfully ", HttpStatus.OK);  
		}
		else {
			return new ResponseEntity<>(
					"invalid body format", HttpStatus.BAD_REQUEST);
		}	
		}
		
	@PutMapping(value="/Tasks/{id}")
	public ResponseEntity<String> editTaskById(RequestEntity<Tasks> requestEntity,@PathVariable String id) {
		if(findTaskById(id)==null) {
			return new ResponseEntity<>("not task found with this id", HttpStatus.NOT_FOUND);
		}
		else {
		TaskService.editTask(id,requestEntity.getBody()); 
		return new ResponseEntity<>(
			      "Task edited ", HttpStatus.OK);	
		}
	}
	@DeleteMapping(value="/Tasks/{id}")
	public ResponseEntity<String> deleteTaskById(@PathVariable String id) {
		TaskService.deleteTask(id);
		if(findTaskById(id)==null) {
			return new ResponseEntity<>("not task found with this id", HttpStatus.NOT_FOUND);
		}
		else {
			TaskService.deleteTask(id);
			return new ResponseEntity<>(
			      "You deleted "+id, HttpStatus.OK);
		}	
	}
}
