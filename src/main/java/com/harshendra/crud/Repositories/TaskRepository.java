package com.harshendra.crud.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.harshendra.crud.EntityClasses.Tasks;

public interface TaskRepository extends CrudRepository<Tasks, String>{
	public List<Tasks> findBystatus(String status); 
}
