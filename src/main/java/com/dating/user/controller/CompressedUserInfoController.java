package com.dating.user.controller;

import com.dating.user.data.CompressedInfoFilterData;
import com.dating.user.data.CompressedUserInfoData;
import com.dating.user.data.ResponseData;
import com.dating.user.service.CompressedUserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0.0.1/info/compressed")
@AllArgsConstructor
public class CompressedUserInfoController {
    private CompressedUserInfoService compressedUserInfoService;

    @GetMapping("/")
    public ResponseEntity<ResponseData<List<CompressedUserInfoData>>> getCompressedUserInfos(@RequestBody CompressedInfoFilterData data) {
        return ResponseEntity.ok(compressedUserInfoService.getCompressedUserInfos(data));
    }

    @PostMapping("/{login}")
    public ResponseEntity<ResponseData<?>> updateCompressedUserInfo(@PathVariable String login, @RequestBody CompressedUserInfoData data) {
        return ResponseEntity.ok(compressedUserInfoService.updateCompressedUserInfo(login, data));
    }
}
