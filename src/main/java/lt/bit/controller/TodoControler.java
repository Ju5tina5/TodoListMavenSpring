/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lt.bit.classes.Tasks;
import lt.bit.classes.Todo;
import lt.bit.dao.TasksDAO;
import lt.bit.dao.TodoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Justinas Pekarskis
 */
@Controller
public class TodoControler {

    @Autowired
    private TodoDAO todoDAO;
    
    @Autowired
    private TasksDAO tasksDAO;

    @GetMapping("/")
    public ModelAndView todoList() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("tasks", tasksDAO.findAll());
        mav.addObject("todos", todoDAO.findAll());
        return mav;
    }

    @GetMapping("/delete")
    @Transactional
    public String deleteTodo(@RequestParam(name = "id", required = true) Integer id) {
        todoDAO.deleteById(id);
        return "redirect:./";
    }
    
    @GetMapping("/complete")
    @Transactional
    public String completeTodo(@RequestParam(name = "id", required = true) Integer id) {
        Todo t = null;
        try {
            t = todoDAO.getOne(id);
        } catch (Exception e) {
            return "redirect:./";
        }
        t.setComplete(new Date());
        todoDAO.save(t);
        List<Tasks> l = t.getTasksList();
        for (Tasks tasks : l) {
            tasks.setComplete(new Date());
        }
        return "redirect:./";
    }
    
    @GetMapping("/cancel")
    @Transactional
    public String cancelTodo(@RequestParam(name = "id", required = true) Integer id) {
        Todo t = todoDAO.getOne(id);
        t.setComplete(null);
        todoDAO.save(t);
        return "redirect:./";
    }

    @GetMapping("/edit")
    public ModelAndView editTodo(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "complete", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date completeDate) {
        Todo t = null;
        ModelAndView mav = new ModelAndView("editor");
        if (id != null) {
            t = todoDAO.getOne(id);
        }
        mav.addObject("todo", t);
        return mav;
    }

    @PostMapping("/save")
    @Transactional
    public String saveTodo(HttpServletRequest request,
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "todo", required = true) String todoName,
            @RequestParam(name = "complete", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date completeDate
    ) {
        Todo p = new Todo();
        p.setId(id);
        p.setTodo(todoName);
        p.setComplete(completeDate);
        
        todoDAO.save(p);

        return "redirect:./";
    }

}
