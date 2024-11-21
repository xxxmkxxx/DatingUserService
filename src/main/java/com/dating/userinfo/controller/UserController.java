package com.dating.userinfo.controller;

import com.dating.userinfo.data.CompressedInfoFilterData;
import com.dating.userinfo.data.CompressedUserInfoData;
import com.dating.userinfo.data.ResponseData;
import com.dating.userinfo.data.UserInfoData;
import com.dating.userinfo.service.UserService;
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
    public ResponseEntity<ResponseData<List<CompressedUserInfoData>>> getCompressedInfos(@RequestParam CompressedInfoFilterData data) {
        return ResponseEntity.ok(userService.getCompressedUserInfos(data));
    }

    @GetMapping("/info/{login}")
    public ResponseEntity<ResponseData<UserInfoData>> getUserInfo(@PathVariable String login) {
        return ResponseEntity.ok(userService.getUserInfoByLogin(login));
    }

    @PostMapping("/{login}")
    public ResponseEntity<ResponseData<Void>> createUser(@PathVariable String login, @RequestParam CompressedUserInfoData data) {
        return new ResponseEntity<>(userService.createUser(login, data), HttpStatus.CREATED);
    }

    @PutMapping("/info/compressed/{login}")
    public ResponseEntity<ResponseData<Void>> updateUser(@PathVariable String login, @RequestParam CompressedUserInfoData data) {
        return ResponseEntity.ok(userService.changeCompressedUserInfo(login, data));
    }
}
