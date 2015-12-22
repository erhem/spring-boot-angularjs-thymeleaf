package com.diputra.erhem.controller;

import com.diputra.erhem.dao.UserRepository;
import com.diputra.erhem.entity.Role;
import com.diputra.erhem.entity.User;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, 
                new CustomDateEditor(dateFormat, true));
    }
    
    @RequestMapping(value = "/thymeleaf", method = RequestMethod.GET)
    public String Thymeleaf(Model model) { 
        model.addAttribute("userList", userRepository.findAll());
        return "/user/thymeleaf/thymeleaf";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String getInsertPage(Model model, 
            @RequestParam(required = false, name = "id") Integer id) {
        
        if(id == null) {
            model.addAttribute("user", new User());
        } else {
            User user = userRepository.findOne(id);
            model.addAttribute("user", userRepository
                    .findOne(id));
        }
        return "/user/thymeleaf/form";
    }
    
    @RequestMapping(value = "/form", method =  RequestMethod.POST)
    public String postInsertPage(Model model, 
            @Valid User user, BindingResult bindingresult) {
        System.out.println("Error = " + bindingresult.hasErrors());
        System.out.println(bindingresult.getFieldError());
        if(bindingresult.hasErrors()) {
            return "/user/thymeleaf/form";
        }
        System.out.println("username = " + user.getUserName());
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("ROLE_USER");
        user.setActive(true);
        user.getRoles().add(role);
        userRepository.save(user);
        return "redirect:thymeleaf";
    }
    
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(Model model, 
            @RequestParam("id") Integer id) {
        
        userRepository.delete(id);
        return "redirect:thymeleaf";
    }
    
    @RequestMapping(value = "/angularjs", method = RequestMethod.GET)
    public String getAngularPage() {
        return "/user/angularjs/angularjs";
    }
    
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomePage() {
        return "home";
    }

}
