package mobile.fpts.com.ezmibile.view.world_indexes;

import java.util.List;

import mobile.fpts.com.ezmibile.model.entity.worldIndices.WorldIndices;

/**
 * Created by TrangDTH on 3/16/2018.
 */

public interface IWorldIndeces {
    interface View {
        void dataDone(List<WorldIndices> worldIndecesList);

        void dataFail();

        void onLoad();

        void connectFail();

        void connectServerFail();
    }

    interface Presenter {
        void callData();
    }
}
