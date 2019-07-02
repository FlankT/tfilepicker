package com.ess.filepicker.adapter;

import com.ess.filepicker.model.EssFile;

import java.util.List;

/**
 * OnFileSelectListener
 * Created by TU on 2019/7/2.
 */

public interface OnFileSelectListener {
    void onSelected(List<EssFile> essFileList);
}
