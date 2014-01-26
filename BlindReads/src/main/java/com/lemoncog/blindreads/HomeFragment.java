package com.lemoncog.blindreads;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.lemoncog.blindreads.controllers.BookController;
import com.lemoncog.blindreads.controllers.LoginController;
import com.lemoncog.blindreads.next.NextBook;

import com.lemoncog.blindreads.models.User;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {

    private LoginController mLoginController;//s = new LoginController();
    private User mCurrentUser = new User();

    private ProgressBar mLoadingSpinner;
    private Button mNextBook;

    private View.OnClickListener nextClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            startActivity(new Intent(getActivity(), NextBook.class));
        }
    };

    public HomeFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();

        mLoadingSpinner = (ProgressBar) getView().findViewById(R.id.home_progress_bar);
        mNextBook = (Button) getView().findViewById(R.id.home_next_book);

        mNextBook.setOnClickListener(nextClicked);

        showLoadingBar();
        loginOrUpdate();
    }

    private void loginOrUpdate()
    {
    }

    private void showLoadingBar()
    {
        mLoadingSpinner.setVisibility(View.VISIBLE);
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
