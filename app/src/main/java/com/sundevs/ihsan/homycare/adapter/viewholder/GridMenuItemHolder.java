package com.sundevs.ihsan.homycare.adapter.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.sundevs.ihsan.homycare.R;
import com.sundevs.ihsan.homycare.adapter.bases.BaseItemHolder;
import com.sundevs.ihsan.homycare.adapter.model.GridMenu;

import butterknife.BindView;


/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 087825382796
 * on Sunday, 19-11-2017
 * ------------------------------
 * This class for
 */

public class GridMenuItemHolder extends BaseItemHolder<GridMenu> {

    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_notif_size)
    TextView tvNotifSize;

    public GridMenuItemHolder(ViewGroup parent, GridMenu data) {
        super(parent, data);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.grid_item_menu;
    }

    @Override
    protected void bindView() {
        ivIcon.setImageResource(getData().getIcon());
        tvTitle.setText(getData().getTitle());
        ivIcon.setEnabled(getData().isEnable());
        tvTitle.setEnabled(getData().isEnable());

        if (getData().getNotificationSize() > 0)
            tvNotifSize.setText(String.valueOf(getData().getNotificationSize()));
        else
            tvNotifSize.setVisibility(View.GONE);
    }
}
