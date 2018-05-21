// Generated code from Butter Knife. Do not modify!
package com.sundevs.ihsan.homycare.view.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.sundevs.ihsan.homycare.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ListBabyActivity_ViewBinding implements Unbinder {
  private ListBabyActivity target;

  @UiThread
  public ListBabyActivity_ViewBinding(ListBabyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ListBabyActivity_ViewBinding(ListBabyActivity target, View source) {
    this.target = target;

    target.swipe = Utils.findRequiredViewAsType(source, R.id.sr_baby_siter, "field 'swipe'", SwipeRefreshLayout.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.rv_baby_siter, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ListBabyActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.swipe = null;
    target.mRecyclerView = null;
  }
}
