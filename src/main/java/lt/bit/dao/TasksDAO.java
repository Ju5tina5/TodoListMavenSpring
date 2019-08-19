/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.dao;

import lt.bit.classes.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Justinas Pekarskis
 */
public interface TasksDAO extends JpaRepository<Tasks, Integer>{
    
}
