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

public class LoginDialog_ViewBinding implements Unbinder {
  private LoginDialog target;

  private View view2131230759;

  @UiThread
  public LoginDialog_ViewBinding(final LoginDialog target, View source) {
    this.target = target;

    View view;
    target.etEmail = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'etEmail'", EditText.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'etPassword'", EditText.class);
    view = Utils.findRequiredView(source, R.id.bt_sign_in, "method 'onOkClicked'");
    view2131230759 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onOkClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etEmail = null;
    target.etPassword = null;

    view2131230759.setOnClickListener(null);
    view2131230759 = null;
  }
}
