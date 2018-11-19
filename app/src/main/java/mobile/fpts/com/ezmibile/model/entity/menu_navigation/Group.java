package mobile.fpts.com.ezmibile.model.entity.menu_navigation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dinht on 1/31/2018.
 */

public class Group {
    private MenuCategory category;

    private List<MenuCategoryChild> categories = new ArrayList<>();

    public Group(MenuCategory category) {
        this.category = category;
    }

    public Group(MenuCategory category, List<MenuCategoryChild> categories) {
        this.category = category;
        this.categories = categories;
    }

    public MenuCategory getCategory() {
        return category;
    }

    public void setCategory(MenuCategory category) {
        this.category = category;
    }

    public List<MenuCategoryChild> getCategories() {
        return categories;
    }

    public void setCategories(List<MenuCategoryChild> categories) {
        this.categories = categories;
    }
}