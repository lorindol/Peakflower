package net.brotzeller.peakflower;


import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class MeterStorage {
    public static class DataPoint implements Comparable{
        private int flow;
        private Date date;

        public Date getDate() {
            return date;
        }
        public int getFlow() {
            return flow;
        }
        public DataPoint(int flow, Date date){
            this.flow = flow;
            this.date = date;
        }

        @Override
        public int compareTo(Object another) {
            return 0;
        }
    }
    public static class DataComparator implements Comparator<DataPoint> {
        public int compare(MeterStorage.DataPoint a, MeterStorage.DataPoint b) {
            return a.getDate().compareTo(b.getDate());
        }
    }
    private ArrayList<DataPoint> data;
    private int maxFlow;
    private SharedPreferences prefs;
    private static class SerializationContainer {
        private ArrayList<DataPoint> data;
        private int maxFlow;
    }
    private final String PREF_KEY = "MeterStorage";

    public MeterStorage(SharedPreferences sharedPrefs){
        if (null != sharedPrefs) {
            if(!unserialize(sharedPrefs.getString(PREF_KEY, null))) {
                initialize();
            }
        } else {
            initialize();
        }
        this.prefs = sharedPrefs;
    }
    public void persist() {
        String storage = serialize();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREF_KEY, storage);
        editor.commit();
    }
    private void initialize() {
        data = new ArrayList<DataPoint>();
        maxFlow = 0;
    }
    public void insertMeasurement(int flow, Date date) {
        DataPoint point = new DataPoint(flow, date);
        data.add(point);
        if (flow > maxFlow) {
            maxFlow = flow;
        }
        persist();
    }
    public int getMax() {
        return maxFlow;
    }

    public int getCurrent() {
        if (data.size() == 0) {
            return 0;
        }
        return data.get(data.size()-1).getFlow();
    }
    public ArrayList<DataPoint> getData() {
        return data;
    }

    public void clearStorage() {
        data.clear();
        maxFlow = 0;
        persist();
    }

    public String serialize() {
        Gson gson = new Gson();
        SerializationContainer ser = new SerializationContainer();
        ser.data = data;
        ser.maxFlow = maxFlow;
        return gson.toJson(ser);
    }
    public boolean unserialize(String blob) {
        Gson gson = new Gson();
        SerializationContainer ser = null;
        try {
            ser = gson.fromJson(blob, SerializationContainer.class);
            if (null != ser) {
                this.maxFlow = ser.maxFlow;
                this.data = ser.data;
            }
        } catch (JsonSyntaxException e) {
            Log.e("Peakflower", "Could not read data from storage");
        }
        boolean ret = (ser != null);
        return ret;
    }
}
