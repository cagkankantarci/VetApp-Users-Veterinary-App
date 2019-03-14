package com.example.forev.vetapp.Utils;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.forev.vetapp.R;

public class ChangeFragments {

    private Context context;

    public ChangeFragments(Context context)
    {
        this.context = context;
    }

    public void change(Fragment fragment)
    {
        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFrameLayout,fragment,"fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public void changeWithParameters(Fragment fragment,String petId)
    {
        Bundle bundle = new Bundle();
        bundle.putString("petid",petId);
        fragment.setArguments(bundle);
        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFrameLayout,fragment,"fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
