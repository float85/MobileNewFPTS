package mobile.fpts.com.ezmibile.view.watchlist.detail.analysis;

public class AnalysisData {
    private String urlAnalysis;

    private String titleAnalysis;

    private String sourceAnalysis;

    private String timeAnalysis;

    public AnalysisData() {
    }

    public AnalysisData(String urlAnalysis, String titleAnalysis, String sourceAnalysis, String timeAnalysis) {
        this.urlAnalysis = urlAnalysis;
        this.titleAnalysis = titleAnalysis;
        this.sourceAnalysis = sourceAnalysis;
        this.timeAnalysis = timeAnalysis;
    }

    public String getUrlAnalysis() {
        return urlAnalysis;
    }

    public void setUrlAnalysis(String urlAnalysis) {
        this.urlAnalysis = urlAnalysis;
    }

    public String getTitleAnalysis() {
        return titleAnalysis;
    }

    public void setTitleAnalysis(String titleAnalysis) {
        this.titleAnalysis = titleAnalysis;
    }

    public String getSourceAnalysis() {
        return sourceAnalysis;
    }

    public void setSourceAnalysis(String sourceAnalysis) {
        this.sourceAnalysis = sourceAnalysis;
    }

    public String getTimeAnalysis() {
        return timeAnalysis;
    }

    public void setTimeAnalysis(String timeAnalysis) {
        this.timeAnalysis = timeAnalysis;
    }
}
