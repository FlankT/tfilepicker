package com.ess.filepicker.model;


/**
 * FileScanFragEvent
 * Created by TU on 2019/7/2.
 */

public class FileScanFragEvent {

    private EssFile selectedFile;
    private boolean isAdd;

    public FileScanFragEvent(EssFile selectedFile, boolean isAdd) {
        this.selectedFile = selectedFile;
        this.isAdd = isAdd;
    }

    public EssFile getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(EssFile selectedFile) {
        this.selectedFile = selectedFile;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public EssFile getSelectedFileList() {
        return selectedFile;
    }

    public void setSelectedFileList(EssFile selectedFileList) {
        this.selectedFile = selectedFileList;
    }
}
