package net.brotzeller.peakflower;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EntryFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public EntryFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static EntryFragment newInstance(int sectionNumber) {
        EntryFragment fragment = new EntryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_entry, container, false);
        TextView time = (TextView) rootView.findViewById(R.id.time_entry);
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        time.setText(date);
        final ViewGroup con = container;
        //TextView textView = (TextView) rootView.findViewById(R.id.time_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        Button save = (Button) rootView.findViewById(R.id.button_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You clicked save", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                ViewPager foo = (ViewPager) getView().getParent();
                FragmentPagerAdapter bar = (FragmentPagerAdapter) foo.getAdapter();
                bar.setPrimaryItem(con, 1, bar.getItem(1));

                bar.notifyDataSetChanged();
            }
        });
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        return rootView;
    }
}
