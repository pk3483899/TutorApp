package com.tutor.controller;


import com.tutor.entity.User;
import com.tutor.service.serviceImpl.EmailVerificationServiceImpl;
import com.tutor.service.serviceImpl.EmailServiceImpl;
import com.tutor.service.serviceImpl.UserServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    private EmailServiceImpl emailService;

    private EmailVerificationServiceImpl emailVerificationService;

    @Autowired
    public UserController(UserServiceImpl userService, EmailServiceImpl emailService, EmailVerificationServiceImpl emailVerificationService) {
        this.userService = userService;
        this.emailService = emailService;
        this.emailVerificationService = emailVerificationService;
    }

//    http://localhost:8080/tutor/findAll
    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAllUser(){
        List<User> all = userService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

//    http://localhost:8080/tutor/create
//    @PostMapping("/create")
//    public Map<Object, Object> createUser(@Valid @RequestBody User user, BindingResult bindingResult){
//        if (bindingResult.hasErrors()){
//            return bindingResult.getFieldError().getDefaultMessage();
//        }
//        User create = userService.createUser(user);
//        Map<String, String> emailSent = emailService.sendOtpEmail(user.getEmail());
//        return emailSent;
//    }
@PostMapping("/create")
public Map<Object, Object> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
    Map<Object, Object> response = new HashMap<>();

    // Check for validation errors
    if (bindingResult.hasErrors()) {
        // Add validation errors to the response
        List<String> validationErrors = bindingResult.getAllErrors()
                .stream()
                .map(x->x.getDefaultMessage())
                .collect(Collectors.toList());
        response.put("validationErrors", validationErrors);

        // Return the response
        return response;
    }

    // No validation errors, proceed with creating the user
    User createdUser = userService.createUser(user);

    // Send OTP email
    Map<String, String> emailSent = emailService.sendOtpEmail(user.getEmail());

    // Add any other data you want to return to the response
    response.put("user", createdUser);
    response.put("emailSent", emailSent);

    // Return the response
    return response;
}


    //    http://localhost:8080/tutor/email/py570395@gmail.com
    @DeleteMapping("/id/{id}")
    public String deleteById(@PathVariable long id){
        userService.deleteUser(id);
        return "User deleted at id : "+id;
    }
// http://localhost:8080/tutor/name/Pradeep Kumar Yadav
    @GetMapping("/email/{email}")
    public User findByEmailId(@PathVariable String email){
        User byEmail = userService.findByEmail(email);
        return byEmail;
    }
//    http://localhost:8080/tutor/id/18
    @GetMapping("/name/{name}")
    public List<User> findByName(@PathVariable String name){
        List<User> byName = userService.findByName(name);
        return byName;
    }
//http://localhost:8080/tutor/email/py570395@gmail.com
    @DeleteMapping("/email/{email}")
    @Transactional
    public String deletByEmailId(@PathVariable String email){
        userService.deleteByEmail(email);
        return "User's data deleted at email id : "+email;
    }

    //http://localhost:8080/tutor/verify-otp?email=&otp=
    @PostMapping("/verify-otp")
    public Map<String, String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        return emailVerificationService.verifyOtp(email, otp);
    }


    //    http://localhost:8080/user/updatePassword/{email}
    @PutMapping("/updatePassword/{email}")
    public ResponseEntity<String> updatePassword(@PathVariable String email, @RequestBody User user){
        User userByEmail = userService.getUserByEmail(email);
        String email1 = userByEmail.getEmail();
        System.out.println(email1);
        if(email1!=null && email1.equals(email)) {
            userByEmail.setPassword(user.getPassword());
            return new ResponseEntity<>("Password updated for email id: "+email, HttpStatus.OK);
        }
            return new ResponseEntity<>("Email Id not found please Sign in ", HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
