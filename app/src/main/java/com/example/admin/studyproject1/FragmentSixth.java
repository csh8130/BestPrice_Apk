package com.example.admin.studyproject1;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victor.loading.rotate.RotateLoading;

/**
 * Created by admin on 2017-05-09.
 */

public class FragmentSixth extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first,container,false);

        //로딩
        RotateLoading loading = (RotateLoading)v.findViewById(R.id.rotateloading);
        loading.start();

        //테마관련 코드
        SharedPreferences setRefer = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String themeNum = setRefer.getString("list_preference_theme","0");
        if(themeNum.equals("0"))
        {
            v.setBackgroundColor(getResources().getColor(R.color.textBack));
        }
        else if(themeNum.equals("1"))
        {
            v.setBackgroundColor(getResources().getColor(R.color.textBack2));
        }
        else if(themeNum.equals("2"))
        {
            v.setBackgroundColor(getResources().getColor(R.color.textBack3));
        }
        else if(themeNum.equals("3"))
        {
            v.setBackgroundColor(getResources().getColor(R.color.textBack4));
        }

        JsoupSlr2 slr2 = new JsoupSlr2(v,getActivity());
        slr2.execute();

        return v;
    }
}
