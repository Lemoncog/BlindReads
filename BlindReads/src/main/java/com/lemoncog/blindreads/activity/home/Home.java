package com.lemoncog.blindreads.activity.home;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.lemoncog.blindreads.BlindReadsApp;
import com.lemoncog.blindreads.R;
import com.lemoncog.blindreads.engine.IUserSupplier;
import com.lemoncog.blindreads.models.IUser;

import rx.Observable;
import rx.util.functions.Action1;

public class Home extends FragmentActivity implements LoginFragmentListener {

    private View.OnClickListener logoutClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            IUserSupplier userSupplier = ((BlindReadsApp)getApplication()).getInjector().getInstance(IUserSupplier.class);

            //Invalidate its state.
            userSupplier.getUser().setLoggedIn(false);
            userSupplier.getUser().setToken(null);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        setupDrawer();

        if (savedInstanceState == null)
        {
            //Always start on login fragment...
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, new LoginFragment())
                    .commit();
        }
    }

    private void setupDrawer()
    {
        Button logoutButton = (Button) findViewById(R.id.drawer_log_out);
        logoutButton.setOnClickListener(logoutClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loginComplete() {
        //Swap out the login fragment for the home fragment.
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new HomeFragment())
                .commit();
    }
}
