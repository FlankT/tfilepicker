package com.ess.filepicker.task;

import android.os.AsyncTask;
import android.util.Log;

import com.ess.filepicker.SelectOptions;
import com.ess.filepicker.model.EssFile;
import com.ess.filepicker.model.EssFileFilter;
import com.ess.filepicker.model.EssFileListCallBack;
import com.ess.filepicker.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * EssFileListTask
 * Created by TU on 2019/7/2.
 */

public class EssFileListTask extends AsyncTask<Void, Void, List<EssFile>> {

    private int type;
    private List<EssFile> mSelectedFileList;
    private String queryPath;
    private String[] types;
    private int mSortType;
    private EssFileListCallBack callBack;

    public EssFileListTask(List<EssFile> mSelectedFileList, String queryPath, String[] types, int mSortType, EssFileListCallBack fileCallBack) {
        this.mSelectedFileList = mSelectedFileList;
        this.queryPath = queryPath;
        this.types = types;
        this.mSortType = mSortType;
        this.callBack = fileCallBack;
        //  this.type = type;
    }

    @Override
    protected List<EssFile> doInBackground(Void... voids) {
        File file = new File(queryPath);
        File[] files = file.listFiles(new EssFileFilter(types));
        if (files == null) {
            return new ArrayList<>();
        }
        List<File> fileList = Arrays.asList(files);
        if (mSortType == FileUtils.BY_NAME_ASC) {
            Collections.sort(fileList, new FileUtils.SortByName());
        } else if (mSortType == FileUtils.BY_NAME_DESC) {
            Collections.sort(fileList, new FileUtils.SortByName());
            Collections.reverse(fileList);
        } else if (mSortType == FileUtils.BY_TIME_ASC) {
            Collections.sort(fileList, new FileUtils.SortByTime());
        } else if (mSortType == FileUtils.BY_TIME_DESC) {
            Collections.sort(fileList, new FileUtils.SortByTime());
            Collections.reverse(fileList);
        } else if (mSortType == FileUtils.BY_SIZE_ASC) {
            Collections.sort(fileList, new FileUtils.SortBySize());
        } else if (mSortType == FileUtils.BY_SIZE_DESC) {
            Collections.sort(fileList, new FileUtils.SortBySize());
            Collections.reverse(fileList);
        } else if (mSortType == FileUtils.BY_EXTENSION_ASC) {
            Collections.sort(fileList, new FileUtils.SortByExtension());
        } else if (mSortType == FileUtils.BY_EXTENSION_DESC) {
            Collections.sort(fileList, new FileUtils.SortByExtension());
            Collections.reverse(fileList);
        }
        List<EssFile> tempFileList = EssFile.getEssFileList(fileList);
        for (EssFile selectedFile :
                mSelectedFileList) {
            for (int i = 0; i < tempFileList.size(); i++) {
                if (selectedFile.getAbsolutePath().equals(tempFileList.get(i).getAbsolutePath())) {
                    tempFileList.get(i).setChecked(true);
                    break;
                }
            }
        }
        return tempFileList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<EssFile> essFileList) {


        if (callBack != null) {
            int childFolderCount = 0;
            int childFileCount = 0;
            //set文件数量
            for (int i = 0; i < essFileList.size(); i++) {
                File file = new File(essFileList.get(i).getAbsolutePath());
                File[] files = file.listFiles(new EssFileFilter(SelectOptions.getInstance().getFileTypes()));
                if (files == null) {
                    break;
                }
                List<EssFile> fileList = EssFile.getEssFileList(Arrays.asList(files));
                for (EssFile essFile :
                        fileList) {
                    if (essFile.isDirectory()) {
                        childFolderCount++;
                    } else {
                        childFileCount++;
                    }
                }
                essFileList.get(i).setChildCounts(String.valueOf(childFileCount), String.valueOf(childFolderCount));
                childFileCount = 0;
                childFolderCount = 0;
            }
            // 移除文件、文件夹 数量为0的数据，重新添加
            List<EssFile> newEssFileList = new ArrayList<>();
            for (int i = 0; i < essFileList.size(); i++) {
                if (!essFileList.get(i).getChildFileCount().equals("加载中")) {
                    if (Integer.parseInt(essFileList.get(i).getChildFileCount()) == 0 && Integer.parseInt(essFileList.get(i).getChildFolderCount()) == 0) {
                        continue;
                    }
                }
                newEssFileList.add(essFileList.get(i));
            }
            callBack.onFindFileList(queryPath, newEssFileList);
        }
    }
}
