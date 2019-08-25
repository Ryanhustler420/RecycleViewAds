package com.example.recycleads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdView;

import java.util.List;

public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> recyclerItems;
    private Context context;

    private static final int ITEM_PERSON = 0;
    private static final int ITEM_BANNER_AD = 1;


    public RecyclerAdaptor(List<Object> recyclerItems, Context context) {
        this.recyclerItems = recyclerItems;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_PERSON:
                View personView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.matched_item_layout, parent, false);
            return new PersonViewHolder(personView);
            case ITEM_BANNER_AD:
                default:
                View bannerView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.banner_ad_container, parent, false);
                return new BannerAdViewHolder(bannerView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        switch (viewType) {
            case ITEM_PERSON:
                PersonViewHolder personViewHolder = (PersonViewHolder) holder;
                Person person = (Person) recyclerItems.get(position);

                int imageId = R.drawable.my_avatar;
                personViewHolder.personImage.setImageResource(imageId);
                personViewHolder.name.setText(person.getName());
                personViewHolder.place.setText(person.getPlace());
                break;
            case ITEM_BANNER_AD:
                default:
                BannerAdViewHolder adViewHolder = (BannerAdViewHolder) holder;
                AdView adView = (AdView) recyclerItems.get(position);

                ViewGroup adCardView = (ViewGroup) adViewHolder.itemView;

                if(adCardView.getChildCount() > 0) {
                    adCardView.removeAllViews();
                }

                if (adCardView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }

                adCardView.addView(adView);
        }
    }

    @Override
    public int getItemCount() {
        return recyclerItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % MainActivity.ITEMS_PER_ADS == 0) {
            return ITEM_BANNER_AD;
        } else {
            return ITEM_PERSON;
        }
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        private ImageView personImage;
        private TextView name, place;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            personImage = itemView.findViewById(R.id.person_image);
            name = itemView.findViewById(R.id.person_name);
            place = itemView.findViewById(R.id.person_place);
        }
    }

    public static class BannerAdViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public BannerAdViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.add_card_view);
        }
    }
}
