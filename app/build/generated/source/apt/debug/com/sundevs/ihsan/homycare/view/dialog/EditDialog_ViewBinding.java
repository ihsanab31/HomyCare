// Generated code from Butter Knife. Do not modify!
package com.sundevs.ihsan.homycare.view.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.sundevs.ihsan.homycare.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditDialog_ViewBinding implements Unbinder {
  private EditDialog target;

  private View view2131230763;

  @UiThread
  public EditDialog_ViewBinding(EditDialog target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditDialog_ViewBinding(final EditDialog target, View source) {
    this.target = target;

    View view;
    target.nama = Utils.findRequiredViewAsType(source, R.id.et_nama_edit, "field 'nama'", EditText.class);
    target.alamat = Utils.findRequiredViewAsType(source, R.id.et_alamat_edit, "field 'alamat'", EditText.class);
    target.nohp = Utils.findRequiredViewAsType(source, R.id.et_nohp_edit, "field 'nohp'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_edit_profile, "method 'editProfile'");
    view2131230763 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.editProfile();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    EditDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.nama = null;
    target.alamat = null;
    target.nohp = null;

    view2131230763.setOnClickListener(null);
    view2131230763 = null;
  }
}
