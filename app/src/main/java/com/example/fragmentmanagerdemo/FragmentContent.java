package com.example.fragmentmanagerdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FragmentContent extends Fragment {
    List<Fragment> listFragment = new ArrayList<>();
    Integer active = 0;
    String tabShop = "TAB_SHOP";
    String tabGift = "TAB_GIFT";
    String tabCart = "TAB_CART";
    BottomNavigationView navigation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        listFragment.add(new FragmentShopContainer());
        listFragment.add(new FragmentGiftContainer());
        listFragment.add(new FragmentCartContainer());
        getFragmentManager().beginTransaction().replace(R.id.frame_container, listFragment.get(2), tabCart).hide(listFragment.get(2)).commit();
        getFragmentManager().beginTransaction().add(R.id.frame_container, listFragment.get(1), tabGift).hide(listFragment.get(1)).commit();
        getFragmentManager().beginTransaction().add(R.id.frame_container, listFragment.get(0), tabShop).commit();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    loadFragment(0);
                    active = 0;
                    return true;
                case R.id.navigation_gifts:
                    loadFragment(1);
                    active = 1;
                    return true;
                case R.id.navigation_cart:
                    loadFragment(2);
                    active = 2;
                    return true;
            }

            return false;
        }
    };

    public void loadFragment(Integer index) {
        getFragmentManager().beginTransaction().hide(listFragment.get(active)).show(listFragment.get(index)).commit();
    }

    public boolean onBackPress() {
        Fragment current = listFragment.get(active);
        FragmentManager childrenFragmentManager = current.getChildFragmentManager();
        if (childrenFragmentManager.getBackStackEntryCount() > 0) {
            childrenFragmentManager.popBackStack();
            return false;
        } else {
            if (current instanceof FragmentShopContainer) {
                return true;
            } else {
                getFragmentManager().beginTransaction().hide(listFragment.get(active)).show(listFragment.get(0)).commit();
                navigation.setSelectedItemId(R.id.navigation_shop);
                return false;
            }
        }
    }
}
