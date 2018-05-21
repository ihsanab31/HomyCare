package com.sundevs.ihsan.homycare.adapter.model;


import com.sundevs.ihsan.homycare.R;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 087825382796
 * on Sunday, 19-11-2017
 * ------------------------------
 * This class for
 */

@Data
@NoArgsConstructor
public class GridMenu {

    private long id;
    private int title;
    private int icon;
    private int notificationSize;
    private boolean enable = true;

    public GridMenu(int title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public GridMenu(int title, int icon, boolean enable) {
        this.title = title;
        this.icon = icon;
        this.enable = enable;
    }

    public GridMenu(int title, int icon, int notificationSize) {
        this.title = title;
        this.icon = icon;
        this.notificationSize = notificationSize;
    }

    public static List<GridMenu> getDummyGridMenuData() {
        List<GridMenu> gridMenuList = new ArrayList<>();

        gridMenuList.add(new GridMenu(R.string.label_list_bs, R.drawable.ic_doctor));
        gridMenuList.add(new GridMenu(R.string.label_menu_member, R.drawable.ic_user));
        gridMenuList.add(new GridMenu(R.string.label_explorer, R.drawable.ic_explorer));
        return gridMenuList;
    }
}
