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

import static com.masudias.flickerdashboard.network.parser.PhotoHttpResponse.PHOTO_SOURCE_FLICKR;

public class PhotoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Cursor cursor;
    private Context context;
    private EndlessScroller loadMoreImagesListener;

    private static final int FOOTER_VIEW = 1;

    public class FooterViewHolder extends ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class NormalViewHolder extends ViewHolder {
        public NormalViewHolder(View itemView) {
            super(itemView);
        }
    }

    public PhotoListAdapter(Context context, Cursor cursor, EndlessScroller loadMoreImagesListener) {
        this.cursor = cursor;
        this.context = context;
        this.loadMoreImagesListener = loadMoreImagesListener;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        if (viewType == FOOTER_VIEW) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_footer, parent, false);
            FooterViewHolder vh = new FooterViewHolder(v);
            return vh;
        }

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_photo, parent, false);
        NormalViewHolder vh = new NormalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if ((position >= getItemCount() - 1))
            loadMoreImagesListener.onRequestForLoadingMoreImages(PHOTO_SOURCE_FLICKR);

        try {
            if (holder instanceof NormalViewHolder) {
                NormalViewHolder vh = (NormalViewHolder) holder;
                vh.bindView(position);
            } else if (holder instanceof FooterViewHolder) {
                FooterViewHolder vh = (FooterViewHolder) holder;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (cursor == null || cursor.isClosed()) return 0;
        else return cursor.getCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == cursor.getCount())
            return FOOTER_VIEW;

        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View itemView;

        // Photo items
        private final ImageView photoImageView;
        private final ImageView ownerImageView;
        private final TextView imageTitleTextView;
        private final TextView ownerNameTextView;
        private final TextView imageDimensionsTextView;
        private final TextView imageSizeTextView;

        // Footer view items
        private final TextView footerTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            photoImageView = (ImageView) itemView.findViewById(R.id.photo);
            ownerImageView = (ImageView) itemView.findViewById(R.id.owner_photo);
            imageTitleTextView = (TextView) itemView.findViewById(R.id.title);
            ownerNameTextView = (TextView) itemView.findViewById(R.id.name);
            imageDimensionsTextView = (TextView) itemView.findViewById(R.id.photo_dimension);
            imageSizeTextView = (TextView) itemView.findViewById(R.id.photo_size);

            footerTextView = (TextView) itemView.findViewById(R.id.footer_text_view);
        }

        public void bindView(int pos) {

            cursor.moveToPosition(pos);

            final String photoUrl = cursor.getString(cursor.getColumnIndex(DBConstants.KEY_PHOTO_URL));
            final String ownerPhotoUrl = cursor.getString(cursor.getColumnIndex(DBConstants.KEY_OWNER_PHOTO_URL));
            final String title = cursor.getString(cursor.getColumnIndex(DBConstants.KEY_TITLE));
            final String ownerName = cursor.getString(cursor.getColumnIndex(DBConstants.KEY_OWNER));
            final int imageHeight = cursor.getInt(cursor.getColumnIndex(DBConstants.KEY_PHOTO_HEIGHT));
            final int imageWidth = cursor.getInt(cursor.getColumnIndex(DBConstants.KEY_PHOTO_WIDTH));
            final long timestamp = cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_CREATED_AT));
            final String imageDimension = imageHeight + "x" + imageWidth;

            Log.d("Cursor values", "id: " + timestamp + " and title: " + title);

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