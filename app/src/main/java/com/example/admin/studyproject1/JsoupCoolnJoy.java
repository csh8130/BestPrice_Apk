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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class JsoupCoolnJoy extends AsyncTask<Void, Void, Void> {
    Activity mainActivity;
    public ArrayList<BoardElement> array;
    View v;
    String url = "http://www.coolenjoy.net/bbs/board.php?bo_table=jirum"; //특가게시판

    public JsoupCoolnJoy(View view,Activity main)
    {
        v = view;
        mainActivity = main;
    }

    public JsoupCoolnJoy(View view,Activity main,String url0)
    {
        v = view;
        mainActivity = main;
        url = url0;
    }

    @Override
    protected Void doInBackground(Void... params) {
        array = new ArrayList<BoardElement>(24);
        for(int i=0;i<24;i++)
        {
            array.add(new BoardElement());
        }

        String selector = "tr:not(.bo_notice) td.td_subject";

        //출처: http://cooljy.tistory.com/349 [CoolJY 네 다락방]
        try{
            Document doc = Jsoup.connect(url).get();
            Elements titles = doc.select(selector);

            int x = 0;
            for (Element e : titles) {
                array.get(x).title = e.text();
                x++;
                if(x==24)
                    break;
            }

            selector = "tr:not(.bo_notice) td.td_subject a:nth-child(1)";

            Elements links = doc.select(selector);

            //if(links.size() ==0)
                //Toast.makeText(mainActivity.getApplicationContext(),""+links.size(),Toast.LENGTH_LONG).show();

            x = 0;

            for (Element e : links) {
                array.get(x).link = e.attributes().get("href");
                x++;
                if(x==24)
                    break;
            }

            selector = "tr:not(.bo_notice) td.td_date";

            Elements dates = doc.select(selector);

            x = 0;
            for (Element e : dates) {
                array.get(x).date = e.text();
                x++;
                if(x==24)
                    break;
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

