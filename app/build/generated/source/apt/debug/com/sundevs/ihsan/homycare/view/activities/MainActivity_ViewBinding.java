// Generated code from Butter Knife. Do not modify!
package com.sundevs.ihsan.homycare.view.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.android.volley.toolbox.NetworkImageView;
import com.sundevs.ihsan.homycare.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131230840;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.gridViewMenu = Utils.findRequiredViewAsType(source, R.id.gv_menu, "field 'gridViewMenu'", GridView.class);
    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
    target.tv_no_hp = Utils.findRequiredViewAsType(source, R.id.tv_no_hp, "field 'tv_no_hp'", TextView.class);
    target.image_user = Utils.findRequiredViewAsType(source, R.id.image_users, "field 'image_user'", NetworkImageView.class);
    view = Utils.findRequiredView(source, R.id.image_circle_user, "field 'circleImageView' and method 'profile'");
    target.circleImageView = Utils.castView(view, R.id.image_circle_user, "field 'circleImageView'", CircleImageView.class);
    view2131230840 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.profile();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.gridViewMenu = null;
    target.tv_name = null;
    target.tv_no_hp = null;
    target.image_user = null;
    target.circleImageView = null;

    view2131230840.setOnClickListener(null);
    view2131230840 = null;
  }
}
