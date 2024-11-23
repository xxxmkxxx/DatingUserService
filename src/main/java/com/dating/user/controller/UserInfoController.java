package com.dating.user.controller;

import com.dating.user.data.ResponseData;
import com.dating.user.data.UserInfoData;
import com.dating.user.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0.0.1/info")
@AllArgsConstructor
public class UserInfoController {
    private UserInfoService userInfoService;

    @GetMapping("/{login}")
    public ResponseEntity<ResponseData<UserInfoData>> getUserInfo(@PathVariable String login) {
        return ResponseEntity.ok(userInfoService.getUserInfoByLogin(login));
    }

}
