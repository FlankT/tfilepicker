package com.ess.filepicker.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ess.filepicker.R;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * SelectSdcardAdapter
 * Created by TU on 2019/7/2.
 */

public class SelectSdcardAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    public SelectSdcardAdapter(@Nullable List<String> data) {
        super(R.layout.item_select_sdcard,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item_select_sdcard,item);
    }
}
