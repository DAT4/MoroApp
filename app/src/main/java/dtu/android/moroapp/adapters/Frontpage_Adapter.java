package dtu.android.moroapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dtu.android.moroapp.R;
import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.models.Frontpage_Event_Model;

public class Frontpage_Adapter extends PagerAdapter {

    private Context context;
    private ArrayList<Event> modelArrayList;

    public Frontpage_Adapter(Context context, ArrayList<Event> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate( R.layout.card_item_view_pager, container, false);

        //Making the view ready
        ImageView bannerID = view.findViewById(R.id.bannerID);
        TextView card_item_titel = view.findViewById(R.id.card_item_titel);
        TextView card_item_desc = view.findViewById(R.id.card_item_desc);

        //Getting the data
        Event myModel = modelArrayList.get(position);
        String titel = myModel.getTitle();
        String desc = myModel.getGenre();


        Picasso.get().load(myModel.getImage()).into(bannerID);

        //Seting the data up in the view
        card_item_titel.setText(titel);
        card_item_desc.setText(desc);

        view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //What ever needs to happen when you click the image
            }
        } );

        container.addView(view, position);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
