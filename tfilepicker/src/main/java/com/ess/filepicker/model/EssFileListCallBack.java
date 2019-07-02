package com.ess.filepicker.model;

import java.util.List;

/**
 * EssFileListCallBack
 * Created by TU on 2019/7/2.
 */

public interface EssFileListCallBack {
    void onFindFileList(String queryPath, List<EssFile> essFileList);
}
