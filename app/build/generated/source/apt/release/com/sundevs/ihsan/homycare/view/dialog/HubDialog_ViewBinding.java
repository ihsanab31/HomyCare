// Generated code from Butter Knife. Do not modify!
package com.sundevs.ihsan.homycare.view.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.sundevs.ihsan.homycare.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HubDialog_ViewBinding implements Unbinder {
  private HubDialog target;

  private View view2131230766;

  private View view2131230767;

  private View view2131230768;

  @UiThread
  public HubDialog_ViewBinding(HubDialog target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HubDialog_ViewBinding(final HubDialog target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_sms, "method 'hun'");
    view2131230766 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.hun(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_telp, "method 'hun'");
    view2131230767 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.hun(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_wa, "method 'hun'");
    view2131230768 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.hun(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230766.setOnClickListener(null);
    view2131230766 = null;
    view2131230767.setOnClickListener(null);
    view2131230767 = null;
    view2131230768.setOnClickListener(null);
    view2131230768 = null;
  }
}
