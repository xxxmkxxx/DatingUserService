package com.dating.user.controller;

import com.dating.user.data.CompressedInfoFilterData;
import com.dating.user.data.CompressedUserInfoData;
import com.dating.user.data.ResponseData;
import com.dating.user.data.UserInfoData;
import com.dating.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0.0.1/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/info/compressed")
    public ResponseEntity<ResponseData<List<CompressedUserInfoData>>> getCompressedInfos(@RequestBody CompressedInfoFilterData data) {
        return ResponseEntity.ok(userService.getCompressedUserInfos(data));
    }

    @GetMapping("/info/{login}")
    public ResponseEntity<ResponseData<UserInfoData>> getUserInfo(@PathVariable String login) {
        return ResponseEntity.ok(userService.getUserInfoByLogin(login));
    }

    @PostMapping("/{login}")
    public ResponseEntity<ResponseData<Void>> createUser(@PathVariable String login, @RequestBody CompressedUserInfoData data) {
        return new ResponseEntity<>(userService.createUser(login, data), HttpStatus.CREATED);
    }

    @PutMapping("/info/compressed/{login}")
    public ResponseEntity<ResponseData<Void>> updateUser(@PathVariable String login, @RequestBody CompressedUserInfoData data) {
        return ResponseEntity.ok(userService.changeCompressedUserInfo(login, data));
    }
}
