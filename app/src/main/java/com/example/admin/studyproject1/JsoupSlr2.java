package com.example.admin.studyproject1;

/**
 * Created by admin on 2017-05-09.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class JsoupSlr2 extends AsyncTask<Void, Void, Void> {
    Activity mainActivity;
    public ArrayList<BoardElement> array;
    View v;

    JsoupSlr2(View view, Activity main)
    {
        v = view;
        mainActivity = main;
    }

    @Override
    protected Void doInBackground(Void... params) {
        array = new ArrayList<BoardElement>(15);
        for(int i=0;i<30;i++)
        {
            array.add(new BoardElement());
        }
        String url = "http://m.slrclub.com/l/sourcing";

        String selector;

        //출처: http://cooljy.tistory.com/349 [CoolJY 네 다락방]
        try{
            Document doc = Jsoup.connect(url).get();
            int x;
            Elements titles;
            selector = "li:not(.notice) div div.subject";
            titles = doc.select(selector);
            titles = titles.select("a");

            x = 0;
            for (Element e : titles) {
                array.get(x).title = e.text();
                x++;
            }

            selector = "li:not(.notice) div div.subject";
            titles = doc.select(selector);
            titles = titles.select("a");

            x = 0;
            for (Element e : titles) {
                String link = e.attributes().get("href");
                array.get(x).link = "http://m.slrclub.com"+link;
                x++;
            }

            selector = "li:not(.notice) div div.article-info";
            titles = doc.select(selector);
            //titles = titles.select("");
            x = 0;
            for (Element e : titles) {
                array.get(x).date = e.text();
                x++;
            }

        }
        catch ( Exception e)
        {
            Log.e("error:",e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        LinearLayout scrollViewgroup = (LinearLayout)v.findViewById(R.id.viewgroup);

        for (int i = 0; i < array.size(); i++) {
            final BoardElement element = array.get(i);

            final View singleFrame = mainActivity.getLayoutInflater().inflate(R.layout.frame_element, null);
            singleFrame.setId(i);

            //테마관련 코드
            SharedPreferences setRefer = PreferenceManager.getDefaultSharedPreferences(mainActivity);
            String themeNum = setRefer.getString("list_preference_theme","0");
            if(themeNum.equals("0"))
            {
                ((TextView)singleFrame.findViewById(R.id.caption) ).setTextColor(mainActivity.getResources().getColor(R.color.textCaption));
                ((TextView)singleFrame.findViewById(R.id.date) ).setTextColor(mainActivity.getResources().getColor(R.color.textGray));
                singleFrame.findViewById(R.id.delimeter).setBackgroundColor(mainActivity.getResources().getColor(R.color.deli_color));
            }
            else if(themeNum.equals("1"))
            {
                ((TextView)singleFrame.findViewById(R.id.caption) ).setTextColor(mainActivity.getResources().getColor(R.color.textCaption2));
                ((TextView)singleFrame.findViewById(R.id.date) ).setTextColor(mainActivity.getResources().getColor(R.color.textGray2));
                singleFrame.findViewById(R.id.delimeter).setBackgroundColor(mainActivity.getResources().getColor(R.color.deli_color2));
            }
            else if(themeNum.equals("2"))
            {
                ((TextView)singleFrame.findViewById(R.id.caption) ).setTextColor(mainActivity.getResources().getColor(R.color.textCaption3));
                ((TextView)singleFrame.findViewById(R.id.date) ).setTextColor(mainActivity.getResources().getColor(R.color.textGray3));
                singleFrame.findViewById(R.id.delimeter).setBackgroundColor(mainActivity.getResources().getColor(R.color.deli_color3));
            }
            else if(themeNum.equals("3"))
            {
                ((TextView)singleFrame.findViewById(R.id.caption) ).setTextColor(mainActivity.getResources().getColor(R.color.textCaption4));
                ((TextView)singleFrame.findViewById(R.id.date) ).setTextColor(mainActivity.getResources().getColor(R.color.textGray4));
                singleFrame.findViewById(R.id.delimeter).setBackgroundColor(mainActivity.getResources().getColor(R.color.deli_color4));
            }

            TextView caption = (TextView) singleFrame.findViewById(R.id.caption);
            caption.setText(element.title);
            TextView date = (TextView) singleFrame.findViewById(R.id.date);
            date.setText(element.date);

            scrollViewgroup.addView(singleFrame);

            singleFrame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(element.link);
                    Intent it = new Intent(Intent.ACTION_VIEW,uri);
                    mainActivity.startActivity(it);
                }
            });
        }
    }

}

