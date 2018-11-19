package mobile.fpts.com.ezmibile.view.marketOverview.details;

import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeHNX;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeHNX30;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeHNX_UPCOM_30;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeItem_VNI;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeUpcom;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeVNI;
import mobile.fpts.com.ezmibile.model.entity.detail_home.DetailHomeVNI30;

public interface IDetailsHome {
    void loadDataVNI(DetailHomeVNI detailHomeVNI);

    void loadDataItemVNI (DetailHomeItem_VNI detailHome, String sPhien);

    void loadDataHNX_UPCOM_30 (DetailHomeHNX_UPCOM_30 detailHomeHNX_upcom_30);

    void loadDataHNX (DetailHomeHNX detailHomeHNX);

    void loadDataUPCOM (DetailHomeUpcom detailHomeUpcom);

    void loadDataVNI30 (DetailHomeVNI30 detailHomeVNI30);

    void loadDataHNX_30 (DetailHomeHNX30 detailHomeHNX30);

    void onload(boolean b);

    void setupViewPager();
}
