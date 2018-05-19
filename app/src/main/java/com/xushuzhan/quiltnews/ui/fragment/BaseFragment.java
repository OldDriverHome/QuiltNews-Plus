package com.xushuzhan.quiltnews.ui.fragment;


import android.support.v4.app.Fragment;

import com.xushuzhan.quiltnews.ui.fragment.video.VideoInfoPageFragment;

import java.util.ArrayList;

/**
 * Created by xushuzhan on 2018/5/19.
 */

public class BaseFragment extends Fragment {
    ArrayList<ILifeCycleMonitor> mMonitors = new ArrayList<>();


    public void registLifeCycleMonitor(ILifeCycleMonitor monitor) {
        mMonitors.add(monitor);
    }

    public void unRegistLifeCycleMonitor(ILifeCycleMonitor monitor) {
        mMonitors.remove(monitor);
    }

    public void notifyLifeCycleCreate(){
        for (ILifeCycleMonitor monitor:
             mMonitors) {
            monitor.onCreate();
        }
    }
    public void notifyLifeCycleStart(){
        for (ILifeCycleMonitor monitor:
                mMonitors) {
            monitor.onStart();
        }
    }
    public void notifyLifeCycleResume(){
        for (ILifeCycleMonitor monitor:
                mMonitors) {
            monitor.onResume();
        }
    }
    public void notifyLifeCyclePause(){
        for (ILifeCycleMonitor monitor:
                mMonitors) {
            monitor.onPause();
        }
    }
    public void notifyLifeCycleStop(){
        for (ILifeCycleMonitor monitor:
                mMonitors) {
            monitor.onStop();
        }
    }
    public void notifyLifeCycleOnDestroy(){
        for (ILifeCycleMonitor monitor:
                mMonitors) {
            monitor.onDestroy();
        }
    }

    public interface ILifeCycleMonitor {
        void onCreate();

        void onStart();

        void onPause();

        void onResume();

        void onStop();

        void onDestroy();
    }
}
