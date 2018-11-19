package mobile.fpts.com.ezmibile.view.watchlist.search;

import android.content.SharedPreferences;
import android.text.InputFilter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.model.db.AppDatabase;
import mobile.fpts.com.ezmibile.model.entity.stock.StockInfoDB;
import mobile.fpts.com.ezmibile.util.Define;
import mobile.fpts.com.ezmibile.util.Language;

import static android.arch.persistence.room.Room.databaseBuilder;

public class WatchListSearchPresenter {
    private ISearchView view;
    List<String> list = new ArrayList<>();
    SharedPreferences preferences;

    //database
    private AppDatabase database;

    public WatchListSearchPresenter(ISearchView searchCk) {
        preferences = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, App.getInstance().MODE_PRIVATE);
        this.view = searchCk;
        database = databaseBuilder(App.getInstance(), AppDatabase.class, Define.DATABASE_APP_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        validateEdittext();
        getDataRecent();
    }

    public void validateEdittext() {
        InputFilter filter = (source, start, end, dest, dstart, dend) -> {

            for (int i = start; i < end; i++) {
                if (!Character.isLetterOrDigit(source.charAt(i)) &&
                        !Character.toString(source.charAt(i)).equals(" ")) {
                    return "";
                }
            }
            return null;
        };

        view.validateEdittext(new InputFilter[]{filter, new InputFilter.LengthFilter(9)});
    }

    public void getDataRecent() {
        String maCK;
        if (Language.getLanguage() == Define.LANGUAGE_APP.LANGUAGE_EN) {
            maCK = readDataRecent(Define.CATEGORY_SHARED_PREFERENCES_ITEM_CK_EN);
        } else {
            maCK = readDataRecent(Define.CATEGORY_SHARED_PREFERENCES_ITEM_CK_VN);
        }

        list.clear();
        String[] strings = maCK.split("[,]");

        if (strings.length > 5) {
            for (int i = 0; i < 5; i++) {
                list.add(strings[i]);
            }
        } else {
            for (int i = 0; i < strings.length; i++) {
                list.add(strings[i]);
            }
        }
        view.onDisplay(list);
    }

    public void loadData() {
        final ArrayList<CustomerAutoComleteTextView> arrayList = new ArrayList<>();
        List<StockInfoDB> list = database.stockInfoDao().getSearchStockData();
        for (int i = 0; i < list.size() - 1; i++) {
            arrayList.add(new CustomerAutoComleteTextView(list.get(i).getStock_code(), list.get(i).getName_vn(),
                    list.get(i).getName_en(), list.get(i).getPost_to()));
        }

        view.loadDataOfAutoComleteTextView(arrayList);
    }

    public void saveNoteSearchRecent(String s, String item_SHARED_PREFERENCES) {
        int iMa = 5;
        String maCK = preferences.getString(item_SHARED_PREFERENCES, "");
        SharedPreferences.Editor edit = preferences.edit();

        String[] word = maCK.split("[,]");
        for (int i = 0; i < word.length; i++) {
            if (s.equals(word[i])) {
                iMa = i;
            }
        }

        String s1 = "";
        if (word.length > 5) {
            for (int i = 0; i < 5; i++) {
                if (!(iMa == i)) {
                    if (i == 0) {
                        s1 = word[0];
                    } else {
                        s1 = s1 + "," + word[i];
                    }
                }
            }
        } else {
            for (int i = 0; i < word.length; i++) {
                if (!(iMa == i)) {
                    if (i == 0) {
                        s1 = word[0];
                    } else {
                        s1 = s1 + "," + word[i];
                    }
                }
            }
        }

        edit.putString(item_SHARED_PREFERENCES, s + "," + s1);
        edit.commit();
    }

    public String readDataRecent(String item_SHARED_PREFERENCES) {
        String maCK = preferences.getString(item_SHARED_PREFERENCES, "");

        return maCK;
    }

    public boolean getWhiteBlackFormSPR() {
        return preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);
    }

    // TODO:HoaDT 7/5/2018 thêm 1 mã vào bảng giá
    public void insertCode(String code) {
        Log.w("SearchPresenter", "insertCode: " + code);
        String codeList = preferences.getString(Define.SHARED_PREFRENCES_WATCHLIST_QUOTES, "FPT,FTS,GAS,VNM");
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Define.SHARED_PREFRENCES_WATCHLIST_QUOTES, codeList + "," + code.split("-")[0].trim());
        editor.apply();
        editor.commit();
    }

    public void destroy() {
//        database.close();
    }
}
