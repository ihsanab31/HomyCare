// Generated code from Butter Knife. Do not modify!
package com.sundevs.ihsan.homycare.view.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.sundevs.ihsan.homycare.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  private View view2131230908;

  private View view2131230761;

  private View view2131230824;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(final RegisterActivity target, View source) {
    this.target = target;

    View view;
    target.nama = Utils.findRequiredViewAsType(source, R.id.et_nama_user, "field 'nama'", EditText.class);
    target.alamat = Utils.findRequiredViewAsType(source, R.id.et_alamat_user, "field 'alamat'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.et_password_user, "field 'password'", EditText.class);
    target.nohp = Utils.findRequiredViewAsType(source, R.id.et_nohp_user, "field 'nohp'", EditText.class);
    view = Utils.findRequiredView(source, R.id.profile_image, "field 'foto' and method 'ambilfoto'");
    target.foto = Utils.castView(view, R.id.profile_image, "field 'foto'", CircleImageView.class);
    view2131230908 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ambilfoto();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_daftar, "method 'daftar'");
    view2131230761 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.daftar();
      }
    });
    view = Utils.findRequiredView(source, R.id.flotbtn_image, "method 'pickgambar'");
    view2131230824 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.pickgambar();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.nama = null;
    target.alamat = null;
    target.password = null;
    target.nohp = null;
    target.foto = null;

    view2131230908.setOnClickListener(null);
    view2131230908 = null;
    view2131230761.setOnClickListener(null);
    view2131230761 = null;
    view2131230824.setOnClickListener(null);
    view2131230824 = null;
  }
}
