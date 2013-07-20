package com.dldev.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.MenuItem;
import android.widget.Toast;

import com.dldev.fragments.FragmentContainerInfo;
import com.dldev.fragments.FragmentPaneList;
import com.dldev.interfaces.OnItemSelectedListener;

public class MainActivity extends FragmentActivity implements OnItemSelectedListener {

    private static int PARA_SIZE = 40;
    private SlidingPaneLayout slidingPaneLayout;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slidingPaneConfiguration();
        addFragments(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelect(int position) {
        addFragmentSelectedItem(position);
    }

    /**
     * Method to add the fragments to activity
     *
     * @param {@see FragmentTransaction}
     */
    private void addFragments(Bundle saveInstanceState) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (saveInstanceState == null) {
            fragmentTransaction.add(R.id.container_fragment_list, FragmentPaneList.newInstance(), "fragment_pane_list");
            fragmentTransaction.add(R.id.container_fragment_content, FragmentContainerInfo.newInstance(0), "fragment_container_info");
        }
        fragmentTransaction.commit();
    }

    /**
     * Method to add fragment with current index
     */
    private void addFragmentSelectedItem(int index) {
        slidingPaneLayout.closePane();
        FragmentContainerInfo fragmentContainerInfo = (FragmentContainerInfo) getSupportFragmentManager().findFragmentByTag("fragment_container_info");
        if (fragmentContainerInfo != null) {
            fragmentContainerInfo.updateContent(index);
        } else {
            fragmentTransaction.replace(R.id.container_fragment_content, FragmentContainerInfo.newInstance(index), "fragment_container_info");
            fragmentTransaction.commit();
        }
    }

    /**
     * Method to configure the {@see SlidingPaneLayout}
     */
    private void slidingPaneConfiguration() {
        slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.sliding_pane_layout_container);
        slidingPaneLayout.setParallaxDistance(PARA_SIZE);
        slidingPaneLayout.setShadowDrawable(getResources().getDrawable(R.drawable.sliding_pane_shadow));


    }
}
