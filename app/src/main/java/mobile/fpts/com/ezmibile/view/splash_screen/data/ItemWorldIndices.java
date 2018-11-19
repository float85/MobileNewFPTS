package mobile.fpts.com.ezmibile.view.splash_screen.data;

import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndices;

public class ItemWorldIndices extends ItemHomeChild {

    private int viewType;

    @Override
    public int getTypeView() {
        return viewType;
    }

    private WorldIndices WorldIndices;

    public ItemWorldIndices(int viewType, WorldIndices worldIndices) {
        this.viewType = viewType;
        WorldIndices = worldIndices;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public WorldIndices getWorldIndices() {
        return WorldIndices;
    }

    public void setWorldIndices(WorldIndices worldIndices) {
        WorldIndices = worldIndices;
    }
}
