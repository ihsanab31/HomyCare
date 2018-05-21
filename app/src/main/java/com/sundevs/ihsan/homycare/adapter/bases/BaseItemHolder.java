package com.sundevs.ihsan.homycare.adapter.bases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 0878253827096
 * on Sunday, 19-11-2017
 * ------------------------------
 * This class for base view holder
 */

public abstract class BaseItemHolder<M> {

    Context context;
    View rootView;
    M data;

    public BaseItemHolder(ViewGroup parent, M data) {
        context = parent.getContext();
        rootView = LayoutInflater.from(context)
                .inflate(getItemLayout(), parent, false);
        this.data = data;
        ButterKnife.bind(this, rootView);
        bindView();
    }

    protected abstract int getItemLayout();

    protected abstract void bindView();

    public Context getContext() {
        return context;
    }

    public View getRootView() {
        return rootView;
    }

    public M getData() {
        return data;
    }
}
