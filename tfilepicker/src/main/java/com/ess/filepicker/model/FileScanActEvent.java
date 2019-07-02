package com.ess.filepicker.model;

/**
 * FileScanActEvent
 * Created by TU on 2019/7/2.
 */

public class FileScanActEvent {
    private int canSelectMaxCount;

    public FileScanActEvent(int canSelectMaxCount) {
        this.canSelectMaxCount = canSelectMaxCount;
    }

    public int getCanSelectMaxCount() {
        return canSelectMaxCount;
    }

    public void setCanSelectMaxCount(int canSelectMaxCount) {
        this.canSelectMaxCount = canSelectMaxCount;
    }
}
