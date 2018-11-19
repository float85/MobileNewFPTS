package mobile.fpts.com.ezmibile.model.entity.menu_navigation;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by dinht on 1/27/2018.
 */

@Entity
public class MenuCategory {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "name_en")
    private String name_en;

    @ColumnInfo(name = "typeGroup")
    private int typeGroup;

    @ColumnInfo(name = "typeFragment")
    private int typeFragment;

    @ColumnInfo(name = "isFavorite")
    private boolean isFavorite;

    @Ignore
    public MenuCategory() {
    }

    public MenuCategory(String name, String name_en, int typeGroup, int typeFragment, boolean isFavorite) {
        this.name = name;
        this.name_en = name_en;
        this.typeGroup = typeGroup;
        this.typeFragment = typeFragment;
        this.isFavorite = isFavorite;
    }

    public int getTypeFragment() {
        return typeFragment;
    }

    public void setTypeFragment(int typeFragment) {
        this.typeFragment = typeFragment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeGroup() {
        return typeGroup;
    }

    public void setTypeGroup(int type) {
        this.typeGroup = type;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }
}
