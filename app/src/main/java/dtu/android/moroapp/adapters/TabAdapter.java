package dtu.android.moroapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import dtu.android.moroapp.FindEvent_when_fragment;
import dtu.android.moroapp.FindEvent_where_fragment;
import dtu.android.moroapp.findEvent_what_fragment;

public class TabAdapter extends FragmentStateAdapter {

    public TabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new FindEvent_when_fragment();
            case 1:
                return new FindEvent_where_fragment();
            case 2:
                return new findEvent_what_fragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
