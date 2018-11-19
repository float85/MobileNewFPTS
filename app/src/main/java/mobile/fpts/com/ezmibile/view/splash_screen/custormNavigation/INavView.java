package mobile.fpts.com.ezmibile.view.splash_screen.custormNavigation;

import android.util.SparseArray;

import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.menu_navigation.MenuBigGroup;
import mobile.fpts.com.ezmibile.model.entity.menu_navigation.MenuCategory;
import mobile.fpts.com.ezmibile.util.ErrorApp;

/**
 * Created by dinht on 1/27/2018.
 */

public interface INavView {
    void onDisplay(SparseArray<MenuBigGroup> groups);

    void onSetting(List<MenuCategory> categories);

    void onSetFragment(int id);

    void onDisplay();

    void onError(ErrorApp errorApp);

    interface IOnMenuClickListener {

        void onClickItemListener(int id, int typeGroup, int typeCategory);
    }
}
