package mobile.fpts.com.ezmibile.view.event.event;

import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.ErrorApp;

public interface IEventsView {

    interface TabLayout {
        void onError(ErrorApp errorApp);
        void onDisplayList();
    }

    interface View {
        void onLoad(boolean b);

        void setupViewPager();
    }
}
