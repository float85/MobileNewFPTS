package mobile.fpts.com.ezmibile.view.world_indexes;

import android.arch.persistence.room.Room;

import java.util.ArrayList;
import java.util.List;

import mobile.fpts.com.ezmibile.App;
import mobile.fpts.com.ezmibile.model.db.AppDatabase;
import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndices;
import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndicesDB;
import mobile.fpts.com.ezmibile.util.Define;

/**
 * Created by TrangDTH on 3/16/2018.
 */

public class WorldIndecesPresenter implements IWorldIndeces.Presenter {
    private IWorldIndeces.View view;
    private List<WorldIndicesDB> worldIndecesList;
    private AppDatabase database;

    public WorldIndecesPresenter(IWorldIndeces.View view, List<WorldIndicesDB> worldIndecesList) {
        this.view = view;
        this.worldIndecesList = worldIndecesList;
        database = Room.databaseBuilder(App.getInstance(), AppDatabase.class, Define.DATABASE_APP_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        callData();
    }

    @Override
    public void callData() {
        List<WorldIndices> worldIndices = new ArrayList<>();

        List<WorldIndicesDB> worldIndicesDBList = database.worldIndicesDao().getWorldIndicesList();
        for (WorldIndicesDB world : worldIndicesDBList) {
            worldIndices.add(new WorldIndices(world.getRowId(), world.getGroupId(), world.getTitle(),
                    world.getPrice(), world.getRegionId(), world.getChange(), world.getCreated(),
                    world.getCharturl(), world.getChangePct(), world.getLocaltime(), world.getVieword()));
        }
//        worldIndecesList = database.dao().getWorldIndicesList();
        if (worldIndices.size() > 0) {
            view.dataDone(worldIndices);
        } else {
            view.dataFail();
        }
    }
}
