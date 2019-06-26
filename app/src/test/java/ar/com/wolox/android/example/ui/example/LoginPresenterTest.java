package ar.com.wolox.android.example.ui.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ar.com.wolox.android.example.ui.login.ILoginView;
import ar.com.wolox.android.example.ui.login.LoginAdapterAPI;
import ar.com.wolox.android.example.ui.login.LoginPresenter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoginPresenterTest {

    private ILoginView mILoginView;
    private LoginPresenter mLoginPresenter;
    private LoginAdapterAPI mLoginAdapterApi;

    @Before
    public void createInstances() {
        mLoginAdapterApi = mock(LoginAdapterAPI.class);
        mLoginPresenter = new LoginPresenter(mLoginAdapterApi);
        mILoginView = mock(ILoginView.class);
        mLoginPresenter.attachView(mILoginView);
    }

    @Test
    public void should_ShowHomeView_When_CredentialsAreValid() {
        doAnswer((invocation) -> {
            ((Runnable)invocation.getArgument(2)).run();
            return null;
        }).when(mLoginAdapterApi).getUsers(anyString(), anyString(), any(), any(), any());

        mLoginPresenter.onLoginButtonClicked("asd@123.com","1234");

        verify(mILoginView, times(1)).goHome();
    }

    @Test
    public void should_ShowWrongEmailAndPasswordError_When_CredentialsAreNotValid() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Runnable)invocation.getArgument(3)).run();
                return null;
            }
        }).when(mLoginAdapterApi).getUsers(anyString(), anyString(), any(), any(), any());

        mLoginPresenter.onLoginButtonClicked("asd@1234.com", "1234");

        verify(mILoginView, times(1)).setWrongEmailPassword();
    }

    @Test
    public void should_ShowUnsuccessfulResponseError_When_TheResponseFail() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Runnable)invocation.getArgument(4)).run();
                return null;
            }
        }).when(mLoginAdapterApi).getUsers(anyString(), anyString(), any(), any(), any());

        mLoginPresenter.onLoginButtonClicked("asd@1234.com", "11234");

        verify(mILoginView, times(1)).unsuccessfulResponse();
    }
}