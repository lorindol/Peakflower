package net.brotzeller.peakflower;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;

public class AnalysisFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public AnalysisFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AnalysisFragment newInstance(int sectionNumber) {
        AnalysisFragment fragment = new AnalysisFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_analysis, container, false);
        //TextView textView = (TextView) rootView.findViewById(R.id.time_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        // in this example, a LineChart is initialized from xml
        LineChart chart = (LineChart) rootView.findViewById(R.id.chart);
        MeterStorage storage = ((EntryActivity)getActivity()).getMeterStorage();
        Charting charting = new Charting(chart, storage);
        charting.chart();
        return rootView;
    }
}
