package com.lovcreate.greendaodemo.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;

import com.lovcreate.greendaodemo.R;
import com.lovcreate.greendaodemo.bean.School;

import org.byteam.superadapter.IMulItemViewType;
import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;

/**
 * Created by wanghaoyu on 2017/11/9.
 */

public class SchoolListAdapter extends SuperAdapter<School> {
    public SchoolListAdapter(Context context, List<School> items, @LayoutRes int layoutResId) {
        super(context, items, layoutResId);
    }

    public SchoolListAdapter(Context context, List<School> items, IMulItemViewType<School> mulItemViewType) {
        super(context, items, mulItemViewType);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, School item) {
        holder.setText(R.id.name, item.getName());
        holder.setText(R.id.address, item.getAddress());
        holder.setText(R.id.tel, item.getTel());
    }
}
