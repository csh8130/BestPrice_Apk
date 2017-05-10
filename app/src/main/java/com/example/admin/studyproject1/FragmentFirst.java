package com.example.admin.studyproject1;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2017-05-09.
 */

public class FragmentFirst extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first,container,false);

        JsoupCoolnJoy coolnJoy = new JsoupCoolnJoy(v,getActivity());
        coolnJoy.execute();

        return v;
    }
}
