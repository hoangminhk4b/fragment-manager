package com.example.fragmentmanagerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new FragmentContent()).commit();
    }

    @Override
    public void onBackPressed() {
        //nó sẽ get ra fragment được commit cuối cùng trong fragment manager
        Fragment fragmentCurrent = getSupportFragmentManager().findFragmentById(R.id.mainActivity);
        if (fragmentCurrent != null && fragmentCurrent instanceof FragmentContent) {
            Boolean check = ((FragmentContent) fragmentCurrent).onBackPress();
            if (check) super.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}
