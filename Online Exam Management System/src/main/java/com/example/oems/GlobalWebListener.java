package com.example.oems;

import com.example.oems.lifecycle.LifecycleOwner;
import com.example.oems.lifecycle.MutableLiveData;
import com.example.oems.util.SensitiveUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class GlobalWebListener implements ServletContextListener, ServletRequestListener, LifecycleOwner {
    private static final String TAG = GlobalWebListener.class.getSimpleName();
    private final GlobalTaskManager taskManager = GlobalTaskManager.getInstance();
    private boolean isActive = false;

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        isActive = true;
        SensitiveUtil.initialize();
        Logger.t(TAG, "contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        isActive = false;
        Logger.t(TAG, "contextDestroyed");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequestListener.super.requestDestroyed(sre);
        Logger.t(TAG, "requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequestListener.super.requestInitialized(sre);
        MutableLiveData<Integer> liveData = taskManager.getMutableLiveData();
        liveData.setValue(liveData.getValue() + 1);
        Logger.t(TAG, "requestInitialized");
    }
}
