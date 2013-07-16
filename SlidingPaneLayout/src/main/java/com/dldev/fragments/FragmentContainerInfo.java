package com.dldev.fragments;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dldev.activities.R;

/**
 * Created by leo on 14/07/13.
 * Fragment that contains information logic
 */
public class FragmentContainerInfo extends Fragment {

    private ViewGroup containerLayoutInfo;
    private ImageView containerLanguageImage;
    private TextView languageDescription;
    private int index = 0;

    /**
     * Empty Constructor
     */
    public FragmentContainerInfo() {
    }

    /**
     * Static method to create new instance of FragmentContainerInfo
     */
    public static FragmentContainerInfo newInstance(int index) {
        FragmentContainerInfo fragmentContainerInfo = new FragmentContainerInfo();
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        fragmentContainerInfo.setArguments(bundle);
        return fragmentContainerInfo;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveInstance(outState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initUI(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateContent(index);
        setDescription(index);

    }

    /**
     * Method that initializes UI for fragment
     *
     * @return {@see View}
     */
    private View initUI(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        containerLayoutInfo = (ViewGroup) inflater.inflate(R.layout.fragment_content_layout, container, false);
        containerLanguageImage = (ImageView) containerLayoutInfo.findViewById(R.id.image_container);
        languageDescription = (TextView)containerLayoutInfo.findViewById(R.id.text_container);
        return containerLayoutInfo;
    }

    /**
     * Method to update content 
     */
    public void updateContent(int _index) {
        index = _index;
        setLanguageImage(index);
        setDescription(index);
    }

    /**
     * Method to save current index
     */
    private void saveInstance(Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            index = arguments.getInt("index");
            updateContent(index);
        }
        bundle.putInt("index", index);
    }
    
    /**
    * Method to change image 
    */
    private void setLanguageImage(int index) {
        int resourceImage = getImageResource(index);
        if (resourceImage != 0) {
            containerLanguageImage.setImageResource(resourceImage);
        }
    }
	
    
    /**
     * Method to get image resource from array 
     */
    private int getImageResource(int index) {
        TypedArray languagesImages = getActivity().getResources().obtainTypedArray(R.array.languagesImages);
        if (languagesImages != null) {
            return languagesImages.getResourceId(index, 0);
        } else {
            return 0;
        }
    }
    
    
    /**
     * Method to set language description
     */
    private void setDescription(int index){
        String [] languagesDescriptions = getActivity().getResources().getStringArray(R.array.languages_description);
        languageDescription.setText(languagesDescriptions[index]);
    }
}
