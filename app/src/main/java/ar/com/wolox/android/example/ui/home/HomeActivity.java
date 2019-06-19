package ar.com.wolox.android.example.ui.home;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

/**
 * Home Activity
 */

public class HomeActivity extends WolmoActivity {

    @Override
    protected int layout() {
        return R.layout.activity_home;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.activityHomeBaseContent, new HomeFragment());
    }
}