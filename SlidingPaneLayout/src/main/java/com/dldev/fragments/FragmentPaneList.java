package com.dldev.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dldev.activities.R;
import com.dldev.interfaces.OnItemSelectedListener;

/**
 * Created by leo on 14/07/13.
 * Fragment that contains pane list logic
 */
public class FragmentPaneList extends ListFragment {
    private ViewGroup containerLayoutList;
    private OnItemSelectedListener onItemSelectedListener;

    public FragmentPaneList() {

    }

    /**
     * Static method to create new instance of FragmentPaneList
     * */
    public static FragmentPaneList newInstance() {
        FragmentPaneList fragmentPaneList = new FragmentPaneList();
        return fragmentPaneList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initUI(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onItemSelectedListener = (OnItemSelectedListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnItemSelectedListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setAdapter();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        setSelectedItem(position);
    }

    /**
     * Method that initializes UI for fragment
     *
     * @return {@see View}
     */
    private View initUI(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        containerLayoutList = (ViewGroup) inflater.inflate(R.layout.fragment_list_layout, container, false);
        return containerLayoutList;
    }

    /**
     * Method to set adapter with names array
     */
    private void setAdapter() {
        String[] programmingLanguagesNames = getActivity().getResources().getStringArray(R.array.languages);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, programmingLanguagesNames);
        setListAdapter(arrayAdapter);

    }

    /**
     * Helper to set selected item from list
     */
    private void setSelectedItem(int index) {
        onItemSelectedListener.onItemSelect(index);
    }


}
