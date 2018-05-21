// Generated code from Butter Knife. Do not modify!
package com.sundevs.ihsan.homycare.adapter.viewholder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.sundevs.ihsan.homycare.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GridMenuItemHolder_ViewBinding implements Unbinder {
  private GridMenuItemHolder target;

  @UiThread
  public GridMenuItemHolder_ViewBinding(GridMenuItemHolder target, View source) {
    this.target = target;

    target.ivIcon = Utils.findRequiredViewAsType(source, R.id.iv_icon, "field 'ivIcon'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvNotifSize = Utils.findRequiredViewAsType(source, R.id.tv_notif_size, "field 'tvNotifSize'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GridMenuItemHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivIcon = null;
    target.tvTitle = null;
    target.tvNotifSize = null;
  }
}
