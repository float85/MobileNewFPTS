package mobile.fpts.com.ezmibile.view.watchlist.detail;

public class ModelApp {
    private String keyModel;
    private String valueModel;

    public ModelApp(String keyModel, String valueModel) {
        this.keyModel = keyModel;
        this.valueModel = valueModel;
    }

    public String getKeyModel() {
        return keyModel;
    }

    public void setKeyModel(String keyModel) {
        this.keyModel = keyModel;
    }

    public String getValueModel() {
        return valueModel;
    }

    public void setValueModel(String valueModel) {
        this.valueModel = valueModel;
    }
}
