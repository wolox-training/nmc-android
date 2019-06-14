package ar.com.wolox.android.example.ui.signup;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

/**
 * Sign Up Activity
 */

public class SingUpActivity extends WolmoActivity {

    @Override
    protected int layout() {
        return R.layout.activity_sing_up;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.activitySignUpBaseContent, new SignUpFragment());
    }
}
