package com.example.android.notepad;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteListAdapter extends SimpleCursorAdapter {

    private Cursor m_cursor;
    private Context m_context;
    private LayoutInflater miInflater;
    public NoteListAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        m_context = context;
        m_cursor = c;
    }

    @Override
    public void bindView(View arg0, Context arg1, Cursor arg2) {
        View convertView = null;
        if (arg0 == null) {
            convertView = miInflater.inflate(R.layout.noteslist_item, null);
        } else {
            convertView = arg0;
        }
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);

        tv_name.setText(arg2.getString(arg2.getColumnIndex("title")));
        tv_time.setText(timeStamp2Date(arg2.getString(arg2 .getColumnIndex("modified"))));
    }

    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @return
     */
    public static String timeStamp2Date(String seconds) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }
}