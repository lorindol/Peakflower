package net.brotzeller.peakflower;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public DisplayFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static DisplayFragment newInstance(int sectionNumber) {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_display, container, false);
        updateVariables(rootView);
        /*
        Snackbar.make(container, "Hello again "+m, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
                */
        //TextView textView = (TextView) rootView.findViewById(R.id.time_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            updateVariables(getView());
        }
    }
    private void updateVariables(View view) {

        TextView best = (TextView) view.findViewById(R.id.best_view);

        EntryActivity a = (EntryActivity) getActivity();
        MeterStorage m = a.getMeterStorage();
        int max = m.getMax();
        String maxString = (max==0)?"---":Integer.toString(max);
        best.setText(maxString);

        TextView current = (TextView) view.findViewById(R.id.current_view);
        int currentValue = m.getCurrent();
        String currentString = (currentValue==0)?"---":Integer.toString(currentValue);
        current.setText(currentString);

        TextView percent = (TextView) view.findViewById(R.id.percentage);
        String percentString = "---%";
        if (max > 0) {
            percentString = String.format("%.2f%%", (float) currentValue / (float) max * 100);
        }
        percent.setText(percentString);
    }
}
