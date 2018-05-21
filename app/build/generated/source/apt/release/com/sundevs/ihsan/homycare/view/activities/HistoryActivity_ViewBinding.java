// Generated code from Butter Knife. Do not modify!
package com.sundevs.ihsan.homycare.view.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.sundevs.ihsan.homycare.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HistoryActivity_ViewBinding implements Unbinder {
  private HistoryActivity target;

  private View view2131230911;

  private View view2131230939;

  private View view2131230833;

  @UiThread
  public HistoryActivity_ViewBinding(HistoryActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HistoryActivity_ViewBinding(final HistoryActivity target, View source) {
    this.target = target;

    View view;
    target.llDashboardHeader = Utils.findRequiredViewAsType(source, R.id.ll_transaksi, "field 'llDashboardHeader'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.proses, "field 'btn_proses' and method 'showDeals'");
    target.btn_proses = Utils.castView(view, R.id.proses, "field 'btn_proses'", Button.class);
    view2131230911 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showDeals(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.selesai, "field 'btn_selesai' and method 'showRecommendation'");
    target.btn_selesai = Utils.castView(view, R.id.selesai, "field 'btn_selesai'", Button.class);
    view2131230939 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showRecommendation(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ic_menu, "method 'kembali'");
    view2131230833 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.kembali();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    HistoryActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llDashboardHeader = null;
    target.btn_proses = null;
    target.btn_selesai = null;

    view2131230911.setOnClickListener(null);
    view2131230911 = null;
    view2131230939.setOnClickListener(null);
    view2131230939 = null;
    view2131230833.setOnClickListener(null);
    view2131230833 = null;
  }
}
