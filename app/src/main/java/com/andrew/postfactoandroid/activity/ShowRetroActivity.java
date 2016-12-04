package com.andrew.postfactoandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.andrew.postfactoandroid.R;
import com.andrew.postfactoandroid.adapter.RetroColumnPagerAdapter;
import com.andrew.postfactoandroid.model.Retro;

public class ShowRetroActivity extends AppCompatActivity {

    private static final String SHOW_RETRO_RETRO_KEY = "ShowRetroRetroKey";
    private Retro retro;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public static void start(Context context, Retro retro) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SHOW_RETRO_RETRO_KEY, retro);
        Intent intent = new Intent(context, ShowRetroActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        retro = (Retro) bundle.getSerializable(SHOW_RETRO_RETRO_KEY);

        setContentView(R.layout.activity_show_retro);

        viewPager = (ViewPager) findViewById(R.id.viewpager_retro_columns);

        viewPager.setAdapter(new RetroColumnPagerAdapter(this.getSupportFragmentManager(), retro));

        tabLayout = (TabLayout) findViewById(R.id.tablayout_retro_columns);

        tabLayout.setupWithViewPager(viewPager);
    }
}
