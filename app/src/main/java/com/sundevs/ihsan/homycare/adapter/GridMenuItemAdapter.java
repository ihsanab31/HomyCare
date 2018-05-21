package com.sundevs.ihsan.homycare.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.sundevs.ihsan.homycare.adapter.bases.BaseItemAdapter;
import com.sundevs.ihsan.homycare.adapter.model.GridMenu;
import com.sundevs.ihsan.homycare.adapter.viewholder.GridMenuItemHolder;


/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 087825382796
 * on Sunday, 19-11-2017
 * ------------------------------
 * This class for GridMenu adapter
 */

public class GridMenuItemAdapter extends BaseItemAdapter<GridMenu> {

    @Override
    protected View bindView(int position, View view, ViewGroup parent) {
        GridMenuItemHolder holder = new GridMenuItemHolder(
                parent,
                getData().get(position)
        );
        return holder.getRootView();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return getData().get(position).isEnable();
    }
}