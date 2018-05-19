package com.masudias.flickerdashboard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.masudias.flickerdashboard.R;
import com.masudias.flickerdashboard.domain.db.Photo;

import java.util.List;

import static com.masudias.flickerdashboard.network.parser.PhotoHttpResponse.PHOTO_SOURCE_FLICKR;

public class PhotoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Photo> photoList;
    private Context context;
    private EndlessScroller loadMoreImagesListener;

    private static final int FOOTER_VIEW = 1;

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

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

    public PhotoListAdapter(Context context, List<Photo> photoList, EndlessScroller loadMoreImagesListener) {
        this.photoList = photoList;
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
        if (photoList == null || photoList.size() == 0) return 0;
        else return photoList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (photoList.size() != 0 && position == photoList.size())
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
            final String photoUrl = photoList.get(pos).photoUrl;
            final String ownerPhotoUrl = photoList.get(pos).ownerPhotoUrl;
            final String title = photoList.get(pos).title;
            final String ownerName = photoList.get(pos).owner;
            final int imageHeight = photoList.get(pos).height;
            final int imageWidth = photoList.get(pos).width;
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