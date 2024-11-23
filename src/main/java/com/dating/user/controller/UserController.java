package com.dating.user.controller;

import com.dating.user.data.CompressedUserInfoData;
import com.dating.user.data.ResponseData;
import com.dating.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0.0.1/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/{login}")
    public ResponseEntity<ResponseData<?>> createUser(@PathVariable String login, @RequestBody CompressedUserInfoData data) {
        return new ResponseEntity<>(userService.createUser(login, data), HttpStatus.CREATED);
    }
}
