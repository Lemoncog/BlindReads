package com.lemoncog.blindreads.next;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.lemoncog.blindreads.R;



/**
 * Created by Gaming on 23/01/14.
 */


public class NextBook extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_next);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new NextBookFragment())
                    .commit();
        }
    }
}
