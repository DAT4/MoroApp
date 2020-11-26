package dtu.android.moroapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import dtu.android.moroapp.models.Event;


public class ViewPager extends Fragment {

    private TextView mTitle;
    private TextView mDesc;
    private ImageView mImage;
    private Event mEvent;

    public static ViewPager getInstance(Event event) {
        ViewPager viewPager = new ViewPager();

        if(event != null){
            Bundle bundle = new Bundle();
            bundle.putSerializable("event", event);
            viewPager.setArguments(bundle);
        }

        return viewPager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        if(getArguments() != null){
            mEvent = (Event) getArguments().getSerializable("event");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.card_item_view_pager,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mImage = view.findViewById(R.id.bannerID);
        mTitle = view.findViewById(R.id.card_item_titel);
        mDesc = view.findViewById(R.id.card_item_desc);

        init();
    }

    private void init(){
        if(mEvent != null){
            //RequestOptions options = new RequestOptions().placeholder(R.drawable.android_1);

            //Glide.with(getActivity()).setDefaultRequestOptions(options).load(mEvent.getImage()).into(mImage);

            Picasso.get().load(mEvent.getImage()).into(mImage);

            mTitle.setText(mEvent.getTitle());
            mDesc.setText(mEvent.getGenre());
        }
    }
}