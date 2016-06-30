package net.brotzeller.peakflower;


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

    public MeterStorage(){
        data = new ArrayList<DataPoint>();
        maxFlow = 0;

    }
    public void insertMeasurement(int flow, Date date) {
        DataPoint point = new DataPoint(flow, date);
        data.add(point);
        if (flow > maxFlow) {
            maxFlow = flow;
        }
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

    public void clearStorage() {
        data.clear();
        maxFlow = 0;
    }
}
