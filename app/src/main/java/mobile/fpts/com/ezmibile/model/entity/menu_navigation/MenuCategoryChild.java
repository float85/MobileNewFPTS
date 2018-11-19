package mobile.fpts.com.ezmibile.model.entity.menu_navigation;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by dinht on 2/1/2018.
 */
@Entity
public class MenuCategoryChild {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "name_en")
    private String name_en;

    @ColumnInfo(name = "categoryId")
    private int categoryId;

    @ColumnInfo(name = "isFavorite")
    private boolean isFavorite;

    @ColumnInfo(name = "typeFragment")
    private int typeFragment;


    @Ignore
    public MenuCategoryChild() {
    }

    public MenuCategoryChild(String name, String name_en, int categoryId, int typeFragment, boolean isFavorite) {
        this.name = name;
        this.name_en = name_en;
        this.categoryId = categoryId;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
