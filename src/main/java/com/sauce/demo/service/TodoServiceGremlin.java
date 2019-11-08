package com.sauce.demo.service;

import com.sauce.demo.models.Todo;
import com.sauce.demo.respositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value = "todoService")
public class TodoServiceGremlin  implements  TodoService{
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserService userService;

    @Override
    public Todo findTodoById(long id) {
        return todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Todo with ID " + id + " not found"));
    }

    @Override
    public Todo save(Todo todo, long userid) {
        return null;
    }

    @Override
    public Todo update(Todo todo, long todoid) {
        return null;
    }
}
