package com.autumntechcreation.click4panditcustomer.di.component;

import android.app.Application;

import com.autumntechcreation.click4panditcustomer.Click4PanditApp;
import com.autumntechcreation.click4panditcustomer.di.module.ActivityModule;
import com.autumntechcreation.click4panditcustomer.di.module.AppModule;
import com.autumntechcreation.click4panditcustomer.di.module.RoomModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityModule.class,
        RoomModule.class,
})


public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(Click4PanditApp click4PanditApp);
}
