package com.doit.detective;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.doit.detective.fragment.dialog8_fragment;
import com.google.android.material.appbar.AppBarLayout;

public class AboutUsActivity extends AppCompatActivity {

    private static final int BUTTON_UP_ID = R.id.btn_up;
    private static final int NESTED_SCROLL_VIEW_ID = R.id.nested_scroll_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        initializeToolbar();
        setUpButtonClickListener();
    }

    private void initializeToolbar() {
        Toolbar myChildToolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(myChildToolbar);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setUpButtonClickListener() {
        Button btnUp = findViewById(BUTTON_UP_ID);
        NestedScrollView nestedScrollView = findViewById(NESTED_SCROLL_VIEW_ID);
        AppBarLayout appBarLayout = findViewById(R.id.appBarLayout);

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nestedScrollView.fullScroll(View.FOCUS_UP);
                appBarLayout.setExpanded(true);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.help) {
            dialog8_fragment badgeDialog = new dialog8_fragment();
            badgeDialog.show(getSupportFragmentManager(), "dialog8_fragment");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
