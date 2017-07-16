package com.anly.samples.pref;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mingjun on 16/10/31.
 */

public class ListenerManager {

    private static ListenerManager sInstance;
    private ListenerManager() {}

    private List<SampleListener> listeners = new ArrayList<>();

    public static ListenerManager getInstance() {
        if (sInstance == null) {
            sInstance = new ListenerManager();
        }

        return sInstance;
    }

    public void addListener(SampleListener listener) {
        listeners.add(listener);
    }

    public void removeListener(SampleListener listener) {
        listeners.remove(listener);
    }
    public void removeListener(){
        if (listeners != null){
            listeners.clear();
        }
    }
    public int getListenerSize(){
        if (listeners != null){
            return listeners.size();
        }
        return -1;
    }

}
