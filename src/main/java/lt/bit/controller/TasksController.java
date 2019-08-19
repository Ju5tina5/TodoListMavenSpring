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
public class TasksController {

    @Autowired
    private TodoDAO todoDAO;

    @Autowired
    private TasksDAO tasksDAO;

    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam(name = "tId", required = true) Integer taskId) {
        tasksDAO.deleteById(taskId);
        return "redirect:./";
    }

    @GetMapping("/completeTask")
    @Transactional
    public String completeTask(@RequestParam(name = "id", required = true) Integer id, @RequestParam(name = "tId", required = true) Integer tId) {
        Tasks t = null;
        Todo td = null;
        try {
            t = tasksDAO.getOne(tId);
        } catch (Exception e) {
            return "redirect:./";
        }
        try {
            td = todoDAO.getOne(id);
        } catch (Exception e) {
            return "redirect:./";
        }
        t.setComplete(new Date());
        tasksDAO.save(t);
        
        List<Tasks> l = td.getTasksList();
        
        for (Tasks tasks : l) {
            if (tasks.getComplete() != null) {
                td.setComplete(new Date());
            } else {
                td.setComplete(null);
            }
        }
        
        return "redirect:./";
    }

    @GetMapping("/cancelTaskCompetion")
    @Transactional
    public String cancelTodo(
            @RequestParam(name = "tId", required = true) Integer taskId,
            @RequestParam(name = "id", required = false) Integer id
    ) {
        Tasks t = tasksDAO.getOne(taskId);
        Todo td = todoDAO.getOne(id);
        t.setComplete(null);
        tasksDAO.save(t);
        td.setComplete(null);
        todoDAO.save(td);
        return "redirect:./";
    }

    @GetMapping("/editTask")
    public ModelAndView editTask(
            @RequestParam(name = "id", required = true) Integer id,
            @RequestParam(name = "tId", required = false) Integer taskId,
            @RequestParam(name = "complete", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date completeDate) {

        ModelAndView mav = new ModelAndView("editTask");

        Todo td = null;
        Tasks t = new Tasks();
        if (id == null) {
            return new ModelAndView("redirect:./");
        } else {
            try {
                td = todoDAO.getOne(id);
            } catch (Exception e) {
                return new ModelAndView("redirect:./");
            }
        }
        if (taskId != null) {
            try {
                t = tasksDAO.getOne(taskId);
            } catch (Exception e) {
                return new ModelAndView("redirect:./");
            }
        }
        t.setTodoId(td);
        mav.addObject("todo", td);
        mav.addObject("task", t);
        return mav;
    }

    @PostMapping("/saveTask")
    @Transactional
    public String saveTask(HttpServletRequest request,
            @RequestParam(name = "id", required = true) Integer id,
            @RequestParam(name = "tId", required = false) Integer tId,
            @RequestParam(name = "task", required = true) String taskName,
            @RequestParam(name = "complete", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date completeDate
    ) {
        Tasks t = new Tasks();
        t.setTask(taskName);
        t.setComplete(completeDate);
        t.setTodoId(todoDAO.getOne(id));
        if (tId == null) {
            t.getId();
            tasksDAO.save(t);
        } else {
            t.setId(tId);
            tasksDAO.save(t);
        }
        return "redirect:./";
    }

}
