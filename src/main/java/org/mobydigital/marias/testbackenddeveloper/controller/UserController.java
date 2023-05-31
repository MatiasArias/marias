package org.mobydigital.marias.testbackenddeveloper.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mobydigital.marias.testbackenddeveloper.model.entity.User;
import org.mobydigital.marias.testbackenddeveloper.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="Users controller")
public class UserController {
    @Autowired
    private UserDetailServiceImpl userService;

    @GetMapping("/users")
    @Operation(summary = "findAll")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/signup")
    @Operation(summary = "signup")
    public ResponseEntity<HttpStatus> signUp(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
