package pe.com.patadeperro.presentation.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.WindowManager;

import pe.com.patadeperro.domain.model.UserPreference;

public class Helper {

    public static void saveUserAppPreference(Context context, UserPreference userPreference) {

        SharedPreferences preferenciasssee = context.getSharedPreferences(Constants.PREFERENCES.PREFERENCE_CURRENT_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editoriieei = preferenciasssee.edit();
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_ID, userPreference.getId());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_IDCLOUD, userPreference.getIdCloud());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_NAME, userPreference.getName());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_PHONE, userPreference.getPhone());
        editoriieei.putBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_LOGGED, userPreference.isLogged());
        editoriieei.apply();
    }

    public static UserPreference getUserAppPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.PREFERENCES.PREFERENCE_CURRENT_USER, Context.MODE_PRIVATE);
        UserPreference userPreference =
                new UserPreference(preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_ID, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_IDCLOUD, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_NAME, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_PHONE, ""),
                        preferences.getBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_LOGGED, false));

        return userPreference;
    }

    public static void hideKeyboard(Activity activity) {//activity.class
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


}
