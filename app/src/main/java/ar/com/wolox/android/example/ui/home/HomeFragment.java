package ar.com.wolox.android.example.ui.home;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

/**
 * Home Fragment
 */

public class HomeFragment extends WolmoFragment<HomePresenter> implements IHomeView {

    @Inject
    HomePresenter homePresenter;

    @Override
    public int layout() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        homePresenter.attachView(this);
    }
}
