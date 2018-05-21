package com.sundevs.ihsan.homycare.adapter.bases;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 0878253827096
 * on Sunday, 19-11-2017
 * ------------------------------
 * This class for base view holder
 */

public abstract class BaseItemAdapter<M> extends BaseAdapter {

    private List<M> data;

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public M getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        return bindView(position, view, parent);
    }

    protected abstract View bindView(int position, View view, ViewGroup parent);

    public List<M> getData() {
        return data;
    }

    public void setData(List<M> data) {
        this.data = data;
    }
}
