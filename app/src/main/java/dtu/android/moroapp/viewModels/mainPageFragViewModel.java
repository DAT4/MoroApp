package dtu.android.moroapp.viewModels;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.repositories.eventRepository;

public class mainPageFragViewModel extends ViewModel {

    Executor bgThread = Executors.newSingleThreadExecutor();
    Handler uithread = new Handler(Looper.getMainLooper());
    private MutableLiveData<List<Event>> mEvents = new MutableLiveData<>();
    private eventRepository mRepo;

    public void init() throws IOException {
        if (mEvents != null) {
            return;
        }
        System.out.println("This code gets run");
        mRepo = eventRepository.getInstance();
        bgThread.execute(() -> {
            try {
                List<Event> cEvents = (List<Event>) mRepo.setData();
                uithread.post(() -> {
                mEvents.postValue(cEvents);
                });
            } catch(Exception e) {
                e.printStackTrace();
            }

        });
    }

    public LiveData<List<Event>> getEvents() {
        return mEvents;
    }

}
