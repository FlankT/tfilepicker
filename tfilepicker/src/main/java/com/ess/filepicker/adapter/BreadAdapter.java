package com.ess.filepicker.adapter;




import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ess.filepicker.model.BreadModel;
import com.ess.filepicker.R;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * BreadAdapter
 * Created by TU on 2019/7/2.
 */

public class BreadAdapter extends BaseQuickAdapter<BreadModel, BaseViewHolder> {

    public BreadAdapter(@Nullable List<BreadModel> data) {
        super(R.layout.bread_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BreadModel item) {
        helper.setText(R.id.btn_bread,item.getCurName());
        helper.addOnClickListener(R.id.btn_bread);
    }
}
