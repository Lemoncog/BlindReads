package com.lemoncog.blindreads.activity.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.inject.Injector;
import com.lemoncog.blindreads.ApiFactory;
import com.lemoncog.blindreads.BlindReadsApp;
import com.lemoncog.blindreads.R;
import com.lemoncog.blindreads.controllers.ILoginCallBack;
import com.lemoncog.blindreads.controllers.LoginController;
import com.lemoncog.blindreads.engine.IUserSupplier;
import com.lemoncog.blindreads.goodreads.OAuthService;

import com.lemoncog.blindreads.models.User;
import com.lemoncog.blindreads.oAuth.OAuthConfig;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment implements ILoginCallBack {

    private LoginController mLoginController;
    private User mCurrentUser = new User();

    private ProgressBar mLoadingSpinner;

    private WebView mHiddenWebView;
    private WebViewClient mWebViewClient;

    private LoginFragmentListener mFragmentListener;

    public LoginFragment(LoginFragmentListener fragmentListener)
    {
        mFragmentListener = fragmentListener;
    }

    @Override
    public void onStart() {
        super.onStart();

        createLoginController();

        mLoadingSpinner = (ProgressBar) getView().findViewById(R.id.login_progress_bar);

        createWebView();

        showLoadingBar();
        loginOrUpdate();
    }

    public LoginFragmentListener getFragmentListener() {
        return mFragmentListener;
    }

    private void createWebView()
    {
        mWebViewClient = new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, final String url)
            {
                //Tell the LoginController the user has accepted or denied authorization
                if(url.contains("authorize=1"))
                {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mLoginController.userAcceptedAuthorization(url);
                        }}).start();

                        removeWebView();
                    }else if(url.contains("authorize=0"))
                {
                    mLoginController.userDeniedAuthorization();
                    removeWebView();
                }

                    return false;
                }
            };

            mHiddenWebView = (WebView) getView().findViewById(R.id.login_full_webview);
            mHiddenWebView.setWebViewClient(mWebViewClient);
        }

    private void removeWebView() {
        mHiddenWebView.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    private void createLoginController()
    {
        IUserSupplier userSupplier = getInjector().getInstance(IUserSupplier.class);
        OAuthConfig oAuthConfig = getInjector().getInstance(OAuthConfig.class);
        OAuthService service = ApiFactory.provideRestAdapter(ApiFactory.provideServer(), ApiFactory.provideClient(), ApiFactory.provideConverter()).create(OAuthService.class);

        mLoginController = new LoginController(this, service, userSupplier, oAuthConfig);
    }

    private Injector getInjector()
    {
        return getApplication().getInjector();
    }

    private BlindReadsApp getApplication()
    {
        return ((BlindReadsApp)getActivity().getApplication());
    }

    private void loginOrUpdate()
    {
        //TODO - create proper class for this, aSynchtask perhaps?
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLoginController.login();
            }
        }).start();
    }

    private void showLoadingBar()
    {
        mLoadingSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void requestWebView(final String url)
    {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mHiddenWebView.setVisibility(View.VISIBLE);
                mHiddenWebView.loadUrl(url);
            }
        });
    }

    @Override
    public void userLoggedIn() {
        //Swap out the login fragment, use home fragment for real!
        getFragmentListener().loginComplete();
    }
}
