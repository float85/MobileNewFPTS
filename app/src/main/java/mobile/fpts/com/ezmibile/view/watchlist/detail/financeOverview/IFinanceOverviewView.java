package mobile.fpts.com.ezmibile.view.watchlist.detail.financeOverview;

import java.util.List;

import mobile.fpts.com.ezmibile.view.watchlist.detail.financialFigures.EzsFinanceData;

/**
 * Created by FIT-thuctap22 on 2/7/2018.
 */

public interface IFinanceOverviewView {
    void displayEzsFinance(List<EzsFinanceData> data);

    void displayEzsReport(List<EzsReportData> data);
}
