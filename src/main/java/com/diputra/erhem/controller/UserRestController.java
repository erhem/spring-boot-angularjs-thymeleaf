package com.diputra.erhem.controller;

import com.diputra.erhem.dao.UserRepository;
import com.diputra.erhem.entity.Role;
import com.diputra.erhem.entity.User;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {
    
    @Autowired
    private UserRepository userRepository;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, 
                new CustomDateEditor(dateFormat, true));
    }
    
    @RequestMapping(value="/user", method=RequestMethod.GET)
    public Page<User> userList(Pageable page) {
        return userRepository.findAll(page);
    }
    
    @RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") Integer id) {
        userRepository.delete(id);
    }
    
    @RequestMapping(value="/user", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertNewUser(@RequestBody @Valid User user) {
        
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("ROLE_USER");
        user.setActive(true);
        user.getRoles().add(role);
        userRepository.save(user);
    }
    
    @RequestMapping(value="/user/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable("id") Integer id) {
        return userRepository.findOne(id);
    }
}
