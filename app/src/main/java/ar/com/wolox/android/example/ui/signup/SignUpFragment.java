package ar.com.wolox.android.example.ui.signup;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

/**
 * SignUp Fragment
 */

public class SignUpFragment extends WolmoFragment<SignUpPresenter> implements ISignUpView {

    @Override
    public int layout() {
        return R.layout.fragment_sign_up;
    }

    @Override
    public void init() {

    }
}
