package dtu.android.moroapp.repositories;

import android.net.UrlQuerySanitizer;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dtu.android.moroapp.models.Address;
import dtu.android.moroapp.models.Coordinates;
import dtu.android.moroapp.models.Event;
import dtu.android.moroapp.models.Location;
import dtu.android.moroapp.models.Query;
import dtu.android.moroapp.models.data;

import static java.util.Collections.addAll;

public class eventRepository {

    private static eventRepository instance;
    Executor bgThread = Executors.newSingleThreadExecutor();
    Handler uithread = new Handler(Looper.getMainLooper());
    private ArrayList<Event> dataSet = new ArrayList<>();

    public static eventRepository getInstance() {
        if (instance == null) {
            instance = new eventRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Event>> getEvents() throws IOException {

        //TODO: Implement get online data

        MutableLiveData<List<Event>> data = new MutableLiveData<>();
        data.setValue(dataSet);

        System.out.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEJ");
        System.out.println("data" + data.getValue());
        return data;

    }


    public List<Event> setData() throws IOException {
        System.out.println("YEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEET");
        List<Event> dataList = new ArrayList<>();


            try {
                URL url = new URL("https://mama.sh/moro/api");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setConnectTimeout(300000);
                connection.setDoOutput(true);
                connection.setRequestProperty("User-Agent", "Your-Mom");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.connect();



                Gson gson = new Gson();
                String json = gson.toJson(new Query("{\n" +
                        "  events{\n" +
                        "    title\n" +
                        "    genre\n" +
                        "    image\n" +
                        "    link\n" +
                        "    other\n" +
                        "    price\n" +
                        "    text\n" +
                        "    tickets\n" +
                        "    time\n" +
                        "    location{\n" +
                        "      area\n" +
                        "      place\n" +
                        "      address{\n" +
                        "        city\n" +
                        "        street\n" +
                        "        no\n" +
                        "        state\n" +
                        "        street\n" +
                        "        zip\n" +
                        "      }\n" +
                        "      coordinates{\n" +
                        "        longitude\n" +
                        "        latitude\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }\n" +
                        "} "));

                byte[] data = json.getBytes(StandardCharsets.UTF_8);



                DataOutputStream request = new DataOutputStream(connection.getOutputStream());
                request.write(data);
                request.flush();

                StringBuffer buffer = new StringBuffer();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line = in.readLine()) != null) {
                    buffer.append(line);
                }

                System.out.println("BUFFER" + buffer.toString());

                //Type EventListType = new TypeToken<List<Event>>(){}.getType();

                dtu.android.moroapp.models.data out = gson.fromJson(buffer.toString(), data.class);

                    System.out.println("OUT" + out);
                    dataList.addAll(out.getData().getAllEvents());
                    connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

        return dataList;
    }
}
