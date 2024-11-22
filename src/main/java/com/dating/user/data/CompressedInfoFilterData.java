package com.dating.user.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompressedInfoFilterData {
    private boolean gender;
    private boolean showHidden;
    private boolean allInfos;
}
