package net.brotzeller.peakflower;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

/**
 * Created by martin on 06.07.16.
 */
public class Charting {

    private LineChart chart;
    private MeterStorage storage;

    public Charting(LineChart chart, MeterStorage storage) {
        this.chart = chart;
        this.storage = storage;
    }

    public void chart() {
        ArrayList<MeterStorage.DataPoint> data = storage.getData();
        //ArrayList<String> xvals = new ArrayList<>();
        ArrayList<Entry> yvals = new ArrayList<>();
        long ts_now = System.currentTimeMillis() / 1000L / 60L;
        long ts_then = ts_now - 60 * 24 * 14; // 14 days past
        //DateFormat df = new SimpleDateFormat("m.d. HH:mm");
        for(MeterStorage.DataPoint point : data) {
            long xVal = point.getDate().getTime() / 1000L /60L ;
            if (ts_then > xVal) {
                continue;
            }
            yvals.add(new Entry(xVal, (float)point.getFlow()));
        }
        LineDataSet line = new LineDataSet(yvals, "Flow");
        ArrayList<ILineDataSet> lines = new ArrayList<>();
        lines.add(line);
        LineData chartData = new LineData(lines);

        chart.setData(chartData);
        chart.invalidate();
     }
}
