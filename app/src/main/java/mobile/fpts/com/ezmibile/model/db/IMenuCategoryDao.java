package mobile.fpts.com.ezmibile.model.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.menu_navigation.MenuCategory;


@Dao
public interface IMenuCategoryDao {
    //-----------------------------------------TABLE MENU CATEGORY----------------------------------
    @Query("DELETE FROM MenuCategory")
    void deleteCategory();

    @Query("SELECT * FROM MenuCategory")
    List<MenuCategory> getAll();

    @Query("SELECT *FROM MenuCategory WHERE typeGroup != 1005")
    List<MenuCategory> getAllMenuCategory();

    @Query("SELECT *FROM MenuCategory WHERE typeGroup = 1005")
    List<MenuCategory> getAllTitle_MenuCategory();

    @Query("SELECT *FROM MenuCategory WHERE typeGroup = :typeGroup AND isFavorite=0")
    List<MenuCategory> getAllMenuCategory(int typeGroup);

    //1005 TYPE -TITLE
    //1004 TYPE -SETTING
    @Query("SELECT *FROM MenuCategory WHERE typeGroup != 1005 AND typeGroup != 1004")
    List<MenuCategory> getAllCategorySetting_MenuCategory();

    @Query("SELECT *FROM MenuCategory WHERE isFavorite = 1")
    List<MenuCategory> getAllFravorite_MenuCategory();

    @Query("UPDATE MenuCategory SET isFavorite= :isFavorite WHERE id =:id")
    void updateMenuCategory(int id, boolean isFavorite);

    @Insert
    void addMenuCategory(MenuCategory category);

    @Query("SELECT * FROM MenuCategory WHERE typeGroup = :typeGroup")
    List<MenuCategory> getAllMenuCategorySub(int typeGroup);

    @Query("SELECT id FROM MenuCategory WHERE name =:name LIMIT 1")
    int getMenuCategoryId(String name);

    @Query("SELECT * FROM MenuCategory WHERE id =:id LIMIT 1")
    MenuCategory getMenuCategoryById(int id);


}
