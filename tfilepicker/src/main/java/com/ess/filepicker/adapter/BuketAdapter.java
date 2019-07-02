package com.ess.filepicker.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ess.filepicker.R;
import com.ess.filepicker.model.Album;

/**
 * BuketAdapter
 * Created by TU on 2019/7/2.
 */

public class BuketAdapter extends CursorAdapter{

    private final Drawable mPlaceholder;

    public BuketAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        TypedArray ta = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.album_thumbnail_placeholder});
        mPlaceholder = ta.getDrawable(0);
        ta.recycle();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.buket_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Album album = Album.valueOf(cursor);
        ((TextView) view.findViewById(R.id.album_name)).setText(album.getDisplayName(context));
        ((TextView) view.findViewById(R.id.album_media_count)).setText(String.valueOf(album.getCount()));
        Glide
                .with(context)
                .load(album.getCoverPath())
                .centerCrop()
                .placeholder(mPlaceholder)
                .into((ImageView) view.findViewById(R.id.album_cover));
    }
}
