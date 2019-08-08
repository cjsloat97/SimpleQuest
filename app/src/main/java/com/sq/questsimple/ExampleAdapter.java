package com.sq.questsimple;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class ExampleAdapter extends ArrayAdapter<String> {
    Typeface myTypeFace;

    public ExampleAdapter(Context context, int textViewResourceId, String[] objects) {
        super(context, textViewResourceId, objects);
        this.myTypeFace = Typeface.createFromAsset(getContext().getAssets(), "orange.ttf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        ((TextView)view).setTypeface(this.myTypeFace);
        return view;
    }
}
