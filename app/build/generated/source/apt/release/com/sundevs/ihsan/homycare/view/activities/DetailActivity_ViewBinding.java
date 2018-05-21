// Generated code from Butter Knife. Do not modify!
package com.sundevs.ihsan.homycare.view.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.android.volley.toolbox.NetworkImageView;
import com.sundevs.ihsan.homycare.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailActivity_ViewBinding implements Unbinder {
  private DetailActivity target;

  private View view2131230760;

  @UiThread
  public DetailActivity_ViewBinding(DetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailActivity_ViewBinding(final DetailActivity target, View source) {
    this.target = target;

    View view;
    target.txt_nama = Utils.findRequiredViewAsType(source, R.id.txt_nama_detail, "field 'txt_nama'", TextView.class);
    target.txt_harga = Utils.findRequiredViewAsType(source, R.id.txt_harga, "field 'txt_harga'", TextView.class);
    target.txt_pendidikan = Utils.findRequiredViewAsType(source, R.id.txt_pendidikan, "field 'txt_pendidikan'", TextView.class);
    target.txt_alamat = Utils.findRequiredViewAsType(source, R.id.txt_alamat_detail, "field 'txt_alamat'", TextView.class);
    target.txt_umur = Utils.findRequiredViewAsType(source, R.id.txt_umur_detail, "field 'txt_umur'", TextView.class);
    target.txt_jarak = Utils.findRequiredViewAsType(source, R.id.txt_jarak_detail, "field 'txt_jarak'", TextView.class);
    target.imageView = Utils.findRequiredViewAsType(source, R.id.img_foto_detail, "field 'imageView'", NetworkImageView.class);
    view = Utils.findRequiredView(source, R.id.btn_booking, "method 'booking'");
    view2131230760 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.booking();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    DetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txt_nama = null;
    target.txt_harga = null;
    target.txt_pendidikan = null;
    target.txt_alamat = null;
    target.txt_umur = null;
    target.txt_jarak = null;
    target.imageView = null;

    view2131230760.setOnClickListener(null);
    view2131230760 = null;
  }
}
