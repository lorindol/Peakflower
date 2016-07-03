package net.brotzeller.peakflower;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

/**
 * Created by martin on 14.06.16.
 */
public class SectionsPagerAdapter  extends FragmentPagerAdapter {
    static final int PAGE_ENTRY    = 0;
    static final int PAGE_DISPLAY  = 1;
    static final int PAGE_ANALYSIS = 2;

    EntryFragment ef;
    DisplayFragment df;
    AnalysisFragment af;
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment;
        switch (position) {
            case PAGE_ENTRY:
                ef = EntryFragment.newInstance(position + 1);
                fragment = ef;
                break;
            case PAGE_DISPLAY:
                df = DisplayFragment.newInstance(position + 1);
                fragment = df;
                break;
            case PAGE_ANALYSIS:
            default:
                af = AnalysisFragment.newInstance(position + 1);
                fragment = af;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Eingabe";
            case 1:
                return "Ansicht";
            case 2:
                return "Auswertung";
        }
        return null;
    }

    public void updateData() {
        if (null != ef) {
            ef.clearData();
        }
        if (null != df) {
            df.updateVariables(null);
        }
    }

}
