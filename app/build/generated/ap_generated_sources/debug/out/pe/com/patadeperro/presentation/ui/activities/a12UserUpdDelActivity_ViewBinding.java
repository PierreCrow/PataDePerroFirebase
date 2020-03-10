// Generated code from Butter Knife. Do not modify!
package pe.com.patadeperro.presentation.ui.activities;

import android.view.View;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.com.patadeperro.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class a12UserUpdDelActivity_ViewBinding implements Unbinder {
  private a12UserUpdDelActivity target;

  @UiThread
  public a12UserUpdDelActivity_ViewBinding(a12UserUpdDelActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public a12UserUpdDelActivity_ViewBinding(a12UserUpdDelActivity target, View source) {
    this.target = target;

    target.et_name = Utils.findRequiredViewAsType(source, R.id.et32name, "field 'et_name'", EditText.class);
    target.et_phoneNumber = Utils.findRequiredViewAsType(source, R.id.et32phoneNumber, "field 'et_phoneNumber'", EditText.class);
    target.et_email = Utils.findRequiredViewAsType(source, R.id.et32email, "field 'et_email'", EditText.class);
    target.et_lat = Utils.findRequiredViewAsType(source, R.id.et32lat, "field 'et_lat'", EditText.class);
    target.et_lng = Utils.findRequiredViewAsType(source, R.id.et32lng, "field 'et_lng'", EditText.class);
    target.et_logged = Utils.findRequiredViewAsType(source, R.id.et32logged, "field 'et_logged'", EditText.class);
    target.et_active = Utils.findRequiredViewAsType(source, R.id.et32active, "field 'et_active'", EditText.class);
    target.et_created_at = Utils.findRequiredViewAsType(source, R.id.et32created_at, "field 'et_created_at'", EditText.class);
    target.et_notifications = Utils.findRequiredViewAsType(source, R.id.et32notifications, "field 'et_notifications'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    a12UserUpdDelActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.et_name = null;
    target.et_phoneNumber = null;
    target.et_email = null;
    target.et_lat = null;
    target.et_lng = null;
    target.et_logged = null;
    target.et_active = null;
    target.et_created_at = null;
    target.et_notifications = null;
  }
}
