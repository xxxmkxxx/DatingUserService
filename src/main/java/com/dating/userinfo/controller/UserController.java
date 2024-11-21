package com.dating.userinfo.controller;

import com.dating.userinfo.data.CompressedUserInfoData;
import com.dating.userinfo.data.ResponseData;
import com.dating.userinfo.data.UserInfoData;
import com.dating.userinfo.model.UserInfoModel;
import com.dating.userinfo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v0.0.1/user")
@AllArgsConstructor
public class UserController {
    private UserService userInfoService;

    @GetMapping("/info/compressed")
    public ResponseEntity<ResponseData<List<CompressedUserInfoData>>> getCompressedInfos(@RequestParam CompressedUserInfoData data) {
        return ResponseEntity.ok(
                new ResponseData<>(
                        true, null,
                        userInfoService.getAllCompressedUserInfos().stream()
                                .map(CompressedUserInfoData::new)
                                .toList()
                )
        );
    }

    @GetMapping("/info/{login}")
    public ResponseEntity<ResponseData<UserInfoData>> getUserInfo(@PathVariable String login) {
        Optional<UserInfoModel> userInfoModelOptional = userInfoService.getUserInfoByLogin(login);

        if (userInfoModelOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            new ResponseData<>(false, "No user information found for this login!", null)
                    );
        }

        UserInfoModel model = userInfoModelOptional.get();

        return ResponseEntity.ok(new ResponseData<>(true, null, new UserInfoData(model)));
    }

    @PostMapping("/{login}")
    public ResponseEntity<ResponseData<Void>> createUser(@PathVariable String login, @RequestParam CompressedUserInfoData data) {
        return ResponseEntity.ok(userInfoService.createUser(login, data));
    }

}
