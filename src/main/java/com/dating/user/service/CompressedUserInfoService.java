package com.dating.user.service;

import com.dating.user.data.CompressedInfoFilterData;
import com.dating.user.data.CompressedUserInfoData;
import com.dating.user.data.ResponseData;
import com.dating.user.mapper.CompressedUserInfoDataMapper;
import com.dating.user.model.CompressedUserInfoModel;
import com.dating.user.repository.CompressedUserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompressedUserInfoService {
    private CompressedUserInfoRepository repository;
    public ResponseData<List<CompressedUserInfoData>> getCompressedUserInfos(CompressedInfoFilterData filter) {
        List<CompressedUserInfoModel> result;

        if (filter.isAllInfos() && filter.isShowHidden()) {
            result = repository.findAll();
        } else {
            result = repository.findByHideAndGender(false, filter.isGender());
        }

        return new ResponseData<>(
                true, null,
                result.stream()
                        .map(CompressedUserInfoDataMapper::map)
                        .toList()
        );
    }

    public ResponseData<CompressedUserInfoModel> createCompressedUserInfo(CompressedUserInfoData data) {
        CompressedUserInfoModel compressedUserInfo = new CompressedUserInfoModel();
        compressedUserInfo.setName(data.getName());
        compressedUserInfo.setSurname(data.getSurname());
        compressedUserInfo.setAge(data.getAge());
        compressedUserInfo.setBriefDescription(data.getBriefDescription());
        compressedUserInfo.setIconPath(data.getIconPath());
        compressedUserInfo.setGender(data.isGender());

        return new ResponseData<>(true, null, repository.save(compressedUserInfo));
    }

    public ResponseData<?> updateCompressedUserInfo(String login, CompressedUserInfoData data) {
        return repository.findByUserInfoUserLogin(login)
                .map(model -> {
                    model.setName(data.getName());
                    model.setSurname(data.getSurname());
                    model.setAge(data.getAge());
                    model.setIconPath(data.getIconPath());
                    model.setBriefDescription(data.getBriefDescription());
                    model.setGender(data.isGender());

                    repository.save(model);

                    return new ResponseData<>(true, "User information updated successfully!", null);
                })
                .orElse(new ResponseData<>(false, "User not found!", null));
    }

}
