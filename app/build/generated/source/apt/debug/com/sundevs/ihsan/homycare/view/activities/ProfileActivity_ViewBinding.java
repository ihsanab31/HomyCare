// Generated code from Butter Knife. Do not modify!
package com.sundevs.ihsan.homycare.view.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.sundevs.ihsan.homycare.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProfileActivity_ViewBinding implements Unbinder {
  private ProfileActivity target;

  private View view2131230762;

  @UiThread
  public ProfileActivity_ViewBinding(ProfileActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProfileActivity_ViewBinding(final ProfileActivity target, View source) {
    this.target = target;

    View view;
    target.foto = Utils.findRequiredViewAsType(source, R.id.ic_profile, "field 'foto'", CircleImageView.class);
    target.nama = Utils.findRequiredViewAsType(source, R.id.txt_profile_nama, "field 'nama'", TextView.class);
    target.nohp = Utils.findRequiredViewAsType(source, R.id.txt_nohp_profile, "field 'nohp'", TextView.class);
    target.alamat = Utils.findRequiredViewAsType(source, R.id.txt_alamat_profile, "field 'alamat'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_edit, "method 'edit'");
    view2131230762 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.edit();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.foto = null;
    target.nama = null;
    target.nohp = null;
    target.alamat = null;

    view2131230762.setOnClickListener(null);
    view2131230762 = null;
  }
}
