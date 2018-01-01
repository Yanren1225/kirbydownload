package com.kirby.runanjing;
import android.app.*;
import com.github.anzewei.parallaxbacklayout.*;

public class Kirby extends Application
{
	@Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(ParallaxHelper.getInstance());
    }
}
