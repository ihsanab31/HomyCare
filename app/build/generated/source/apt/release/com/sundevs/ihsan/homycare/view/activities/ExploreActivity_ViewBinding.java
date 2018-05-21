// Generated code from Butter Knife. Do not modify!
package com.sundevs.ihsan.homycare.view.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.sundevs.ihsan.homycare.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExploreActivity_ViewBinding implements Unbinder {
  private ExploreActivity target;

  @UiThread
  public ExploreActivity_ViewBinding(ExploreActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ExploreActivity_ViewBinding(ExploreActivity target, View source) {
    this.target = target;

    target.coordinatorLayout = Utils.findRequiredViewAsType(source, R.id.coordinator_ex, "field 'coordinatorLayout'", CoordinatorLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ExploreActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.coordinatorLayout = null;
  }
}
