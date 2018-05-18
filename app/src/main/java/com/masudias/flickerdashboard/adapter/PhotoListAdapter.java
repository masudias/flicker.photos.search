package com.masudias.flickerdashboard.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.masudias.flickerdashboard.R;
import com.masudias.flickerdashboard.database.DBConstants;

public class PhotoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Cursor cursor;
    private Context context;

    public PhotoListAdapter(Context context, Cursor cursor) {
        this.cursor = cursor;
        this.context = context;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_photo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            ViewHolder vh = (ViewHolder) holder;
            vh.bindView(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (cursor == null || cursor.isClosed()) return 0;
        else return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View itemView;

        private final ImageView photoImageView;
        private final ImageView ownerImageView;
        private final TextView imageTitleTextView;
        private final TextView ownerNameTextView;
        private final TextView imageDimensionsTextView;
        private final TextView imageSizeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            photoImageView = (ImageView) itemView.findViewById(R.id.photo);
            ownerImageView = (ImageView) itemView.findViewById(R.id.owner_photo);
            imageTitleTextView = (TextView) itemView.findViewById(R.id.title);
            ownerNameTextView = (TextView) itemView.findViewById(R.id.name);
            imageDimensionsTextView = (TextView) itemView.findViewById(R.id.photo_dimension);
            imageSizeTextView = (TextView) itemView.findViewById(R.id.photo_size);
        }

        public void bindView(int pos) {

            cursor.moveToPosition(pos);

            final int id = cursor.getInt(cursor.getColumnIndex(DBConstants.KEY_ID));
            final String photoUrl = cursor.getString(cursor.getColumnIndex(DBConstants.KEY_PHOTO_URL));
            final String ownerPhotoUrl = cursor.getString(cursor.getColumnIndex(DBConstants.KEY_OWNER_PHOTO_URL));
            final String title = cursor.getString(cursor.getColumnIndex(DBConstants.KEY_TITLE));
            final String ownerName = cursor.getString(cursor.getColumnIndex(DBConstants.KEY_OWNER));
            final int imageHeight = cursor.getInt(cursor.getColumnIndex(DBConstants.KEY_PHOTO_HEIGHT));
            final int imageWidth = cursor.getInt(cursor.getColumnIndex(DBConstants.KEY_PHOTO_WIDTH));
            final String imageDimension = imageHeight + "x" + imageWidth;

            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_image_loader_placeholder)
                    .error(R.mipmap.ic_image_loading_error);

            try {
                Glide.with(context).load((photoUrl)).apply(requestOptions).into(photoImageView);
                Glide.with(context).load(ownerPhotoUrl).apply(requestOptions).into(ownerImageView);
            } catch (Exception e) {
                e.printStackTrace();
            }

            imageTitleTextView.setText(title);
            ownerNameTextView.setText(ownerName);
            imageDimensionsTextView.setText(imageDimension);
            imageSizeTextView.setText("getSize Here");
        }
    }
}