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

public class a22PetUpdDelActivity_ViewBinding implements Unbinder {
  private a22PetUpdDelActivity target;

  @UiThread
  public a22PetUpdDelActivity_ViewBinding(a22PetUpdDelActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public a22PetUpdDelActivity_ViewBinding(a22PetUpdDelActivity target, View source) {
    this.target = target;

    target.et_id = Utils.findRequiredViewAsType(source, R.id.et22id, "field 'et_id'", EditText.class);
    target.et_idCloud = Utils.findRequiredViewAsType(source, R.id.et22idCloud, "field 'et_idCloud'", EditText.class);
    target.et_idUser = Utils.findRequiredViewAsType(source, R.id.et22idUser, "field 'et_idUser'", EditText.class);
    target.et_name = Utils.findRequiredViewAsType(source, R.id.et22name, "field 'et_name'", EditText.class);
    target.et_race = Utils.findRequiredViewAsType(source, R.id.et22race, "field 'et_race'", EditText.class);
    target.et_gender = Utils.findRequiredViewAsType(source, R.id.et22gender, "field 'et_gender'", EditText.class);
    target.et_age = Utils.findRequiredViewAsType(source, R.id.et22age, "field 'et_age'", EditText.class);
    target.et_color = Utils.findRequiredViewAsType(source, R.id.et22color, "field 'et_color'", EditText.class);
    target.et_qrCode = Utils.findRequiredViewAsType(source, R.id.et22qrCode, "field 'et_qrCode'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    a22PetUpdDelActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.et_id = null;
    target.et_idCloud = null;
    target.et_idUser = null;
    target.et_name = null;
    target.et_race = null;
    target.et_gender = null;
    target.et_age = null;
    target.et_color = null;
    target.et_qrCode = null;
  }
}
