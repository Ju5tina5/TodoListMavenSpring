/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.controller;

import lt.bit.classes.Todo;
import lt.bit.dao.TodoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Justinas Pekarskis
 */
@RestController
public class TodoRestController {

    @Autowired
    private TodoDAO todoDAO;
    
    @GetMapping(path = "rest/todo/{id}", produces = "application/json")
    public Todo getOne(@PathVariable(name = "id", required = true) Integer id) {
        Todo td = null;
        try {
            td = todoDAO.getOne(id);
        } catch (Exception e) {
            // ignore
        }
        return td;
    }
    
    @PostMapping( path = "rest/todo", produces = "application/json")
    @Transactional
    public Todo addTodo(@RequestBody Todo td) {
        System.out.println(td);
        td.setId(null);
        todoDAO.save(td);
        return td;
    }
    
    
    
    @PostMapping(path = "rest/todo/{id}", produces = "application/json")
    @Transactional
    public Todo updateTodo(@RequestBody Todo td) {
        todoDAO.save(td);
        return td;
    }
}
