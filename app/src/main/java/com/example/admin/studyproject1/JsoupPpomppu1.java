package com.example.admin.studyproject1;

/**
 * Created by admin on 2017-05-09.
 */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class JsoupPpomppu1 extends AsyncTask<Void, Void, Void> {
    Activity mainActivity;
    public ArrayList<BoardElement> array;
    View v;

    JsoupPpomppu1(View view, Activity main)
    {
        v = view;
        mainActivity = main;
    }

    @Override
    protected Void doInBackground(Void... params) {
        array = new ArrayList<BoardElement>(15);
        for(int i=0;i<15;i++)
        {
            array.add(new BoardElement());
        }
        String url = "http://m.ppomppu.co.kr/new/bbs_list.php?id=ppomppu";

        String selector;

        //출처: http://cooljy.tistory.com/349 [CoolJY 네 다락방]
        try{
            Document doc = Jsoup.connect(url).get();
            int x;
            Elements titles;
            selector = "li.none-border";
            titles = doc.select(selector);
            titles = titles.select("span.title");

            x = 0;
            for (Element e : titles) {
                array.get(x).title = e.text();
                x++;
            }

            selector = "li.none-border";
            titles = doc.select(selector);
            titles = titles.select("a.list_b_01");

            x = 0;
            for (Element e : titles) {
                String link = e.attributes().get("href");
                array.get(x).link = "http://m.ppomppu.co.kr/new/"+link;
                x++;
            }

            selector = "li.none-border";
            titles = doc.select(selector);
            titles = titles.select("span.info");
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

