package com.andrew.postfactoandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.andrew.postfactoandroid.fragment.RetroColumnFragment;
import com.andrew.postfactoandroid.model.Retro;
import com.andrew.postfactoandroid.model.RetroItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RetroColumnPagerAdapter extends FragmentPagerAdapter {
    private final Retro retro;

    public RetroColumnPagerAdapter(FragmentManager fm, Retro retro) {
        super(fm);
        this.retro = retro;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return RetroColumnFragment.newInstance(Retro.Category.HAPPY, new ArrayList<>(getRetroItemsByCategory(Retro.Category.HAPPY)));
            }
            case 1: {
                return RetroColumnFragment.newInstance(Retro.Category.MEH, new ArrayList<>(getRetroItemsByCategory(Retro.Category.MEH)));
            }
            case 2: {
                return RetroColumnFragment.newInstance(Retro.Category.SAD, new ArrayList<>(getRetroItemsByCategory(Retro.Category.SAD)));
            }
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: {
                return "Happy";
            }
            case 1: {
                return "Meh";
            }
            case 2: {
                return "Sad";
            }
            default:
                return null;
        }
    }

    private List<RetroItem> getRetroItemsByCategory(Retro.Category category) {
        return retro.items.stream().filter(retroItem -> retroItem.category.equals(category)).collect(Collectors.toList());
    }
}
