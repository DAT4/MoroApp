package dtu.android.moroapp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.repositories.eventRepository;

public class mainPageFragViewModel extends ViewModel {
    private MutableLiveData<List<Event>> mEvents;
    private eventRepository mRepo;

    public void init() {
        if (mEvents != null) {
            return;
        }

        mRepo = eventRepository.getInstance();
        mEvents = mRepo.getEvents();
    }

    public LiveData<List<Event>> getEvents() {
        return mEvents;
    }

}
