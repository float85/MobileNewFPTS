package mobile.fpts.com.ezmibile.model.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.menu_navigation.MenuCategoryChild;

@Dao
public interface IMenuCategoryChildDao {

    //-----------------------------------------TABLE MENU CATEGORYCHILD-----------------------------
    @Query("DELETE FROM MenuCategoryChild")
    void deleteMenuCategoryChild();

    @Query("UPDATE MenuCategoryChild SET isFavorite= :isFavorite WHERE id =:id")
    void updateMenuCategoryChild(int id, boolean isFavorite);

    @Insert
    void addMenuCategoryChild(MenuCategoryChild child);

    @Query("SELECT *FROM MenuCategoryChild")
    List<MenuCategoryChild> getAllMenuCategoryChild();

    @Query("SELECT *FROM MenuCategoryChild WHERE isFavorite=1")
    List<MenuCategoryChild> getAllMenuCategoryChildIsFavorite();

    @Query("SELECT *FROM MenuCategoryChild  WHERE categoryId =:categoryId")
    List<MenuCategoryChild> getAllMenuCategoryChild(int categoryId);


    @Query("SELECT *FROM MenuCategoryChild WHERE categoryId =:categoryId AND isFavorite = 0")
    List<MenuCategoryChild> getAllMenuCategoryChildByCategoryId(int categoryId);

    @Query("SELECT * FROM MenuCategoryChild WHERE id =:id LIMIT 1")
    MenuCategoryChild getMenuCategoryChildFromId(int id);

}
