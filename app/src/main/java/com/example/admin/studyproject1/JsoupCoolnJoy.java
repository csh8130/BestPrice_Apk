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

public class JsoupCoolnJoy extends AsyncTask<Void, Void, Void> {
    Activity mainActivity;
    public ArrayList<BoardElement> array;
    View v;

    JsoupCoolnJoy(View view,Activity main)
    {
        v = view;
        mainActivity = main;
    }

    @Override
    protected Void doInBackground(Void... params) {
        array = new ArrayList<BoardElement>(25);
        for(int i=0;i<25;i++)
        {
            array.add(new BoardElement());
        }
        String url = "http://www.coolenjoy.net/bbs/board.php?bo_table=jirum";

        String selector = "td.td_subject";

        //출처: http://cooljy.tistory.com/349 [CoolJY 네 다락방]
        try{
            Document doc = Jsoup.connect(url).get();
            Elements titles = doc.select(selector);

            int x = 0;
            for (Element e : titles) {
                array.get(x).title = e.text();
                x++;
            }

            selector = "td.td_subject a";

            Elements links = doc.select(selector);

            x = 0;

            for (Element e : links) {
                if (x % 2 == 0)
                    array.get(x / 2).link = e.attributes().get("href");
                x++;
            }

            selector = "td.td_date";

            Elements dates = doc.select(selector);

            x = 0;
            for (Element e : dates) {
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

