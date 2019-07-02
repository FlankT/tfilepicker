package com.ess.filepicker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;


import com.ess.filepicker.activity.SelectFileByBrowserActivity;
import com.ess.filepicker.activity.SelectFileByScanActivity;
import com.ess.filepicker.activity.SelectPictureActivity;
import com.ess.filepicker.util.DialogUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;

import androidx.annotation.StyleRes;
import androidx.fragment.app.Fragment;

/**
 * SelectCreator
 * Created by TU on 2019/7/2.
 */

public final class SelectCreator {

    private final FilePicker filePicker;
    private final SelectOptions selectOptions;
    private String chooseType;

    SelectCreator(FilePicker filePicker, String chooseType) {
        selectOptions = SelectOptions.getCleanInstance();
        this.chooseType = chooseType;
        this.filePicker = filePicker;
    }

    public SelectCreator setMaxCount(int maxCount) {
        selectOptions.maxCount = maxCount;
        //是否单选存在 bug (maxCount = 1 只选择一条 结果直接return)
//        if(maxCount <= 1){
//            selectOptions.maxCount = 1;
//            selectOptions.isSingle = true;
//        }else{
//            selectOptions.isSingle = false;
//        }
        return this;
    }

    public SelectCreator setTheme(@StyleRes int theme) {
        selectOptions.themeId = theme;
        return this;
    }

    public SelectCreator setFileTypes(String... fileTypes) {
        selectOptions.mFileTypes = fileTypes;
        return this;
    }

    public SelectCreator setSortType(String sortType) {
        selectOptions.mSortType = sortType;
        return this;
    }

    public SelectCreator isSingle() {
        selectOptions.isSingle = true;
        selectOptions.maxCount = 1;
        return this;
    }

    public SelectCreator onlyShowImages() {
        selectOptions.onlyShowImages = true;
        return this;
    }

    public SelectCreator onlyShowVideos() {
        selectOptions.onlyShowVideos = true;
        return this;
    }

    public SelectCreator placeHolder(Drawable placeHolder) {
        selectOptions.placeHolder = placeHolder;
        return this;
    }

    public SelectCreator enabledCapture(boolean enabledCapture) {
        selectOptions.enabledCapture = enabledCapture;
        return this;
    }

    public SelectCreator requestCode(int requestCode) {
        selectOptions.request_code = requestCode;
        return this;
    }

    public void start() {
        final Activity activity = filePicker.getActivity();
        if (activity == null) {
            return;
        }
        AndPermission
                .with(activity)
                .runtime()
                .permission(Permission.READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(data -> {
                    //接受权限
                    Intent intent = new Intent();
                    if (SelectCreator.this.chooseType.equals(SelectOptions.CHOOSE_TYPE_BROWSER)) {
                        intent.setClass(activity, SelectFileByBrowserActivity.class);
                    } else if (SelectCreator.this.chooseType.equals(SelectOptions.CHOOSE_TYPE_SCAN)) {
                        intent.setClass(activity, SelectFileByScanActivity.class);
                    } else if (SelectCreator.this.chooseType.equals(SelectOptions.CHOOSE_TYPE_MEDIA)) {
                        intent.setClass(activity, SelectPictureActivity.class);
                    }
                    Fragment fragment = filePicker.getFragment();
                    if (fragment != null) {
                        fragment.startActivityForResult(intent, selectOptions.request_code);
                    } else {
                        activity.startActivityForResult(intent, selectOptions.request_code);
                    }
                })
                .onDenied(data -> {
                    //拒绝权限
                    DialogUtil.showPermissionDialog(activity, Permission.transformText(activity, String.valueOf(data)).get(0));
                })
                .start();
    }
}
