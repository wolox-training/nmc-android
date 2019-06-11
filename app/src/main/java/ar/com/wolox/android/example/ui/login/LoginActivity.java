package ar.com.wolox.android.example.ui.login;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

/**
 * LoginActivity
 */

public class LoginActivity extends WolmoActivity {

    @Override
    protected int layout() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.activityLoginBaseContent, new LoginFragment());
    }
}
