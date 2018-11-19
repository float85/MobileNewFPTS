package mobile.fpts.com.ezmibile.model.entity;

/**
 * Created by dinht on 3/20/2018.
 */
public class StockMarket {

    private String MarketName;
    private String MarketupdatedTime;
    private String MarketPrice;
    private String MarketValueChange;
    private String MarketValueChangeRatio;
    private String MarketToTalVolumn;
    private String MarketToTalValue;
    private String MarketColorCode;
    private boolean isMarketData = false;

    private String symbol;
    private String company;
    private String price;
    private String valueChange;
    private String valueChangeRatio;
    private String volume;
    private String colorCode;
    private String status;
    private String time;

    private boolean isQuoteData = false;

    /* @ Property MarketName */
    public String getMarketName() {
        return MarketName;
    }

    public void setMarketName(String MarketName) {
        this.MarketName = MarketName;
    }

    /* @ Property MarketupdatedTime */
    public String getMarketupdatedTime() {
        return MarketupdatedTime;
    }

    public void setMarketupdatedTime(String MarketupdatedTime) {
        this.MarketupdatedTime = MarketupdatedTime;
    }

    /* @ Property MarketIndices */
    public String getMarketPrice() {
        return MarketPrice;
    }

    public void setMarketPrice(String MarketIndices) {
        this.MarketPrice = MarketIndices;
    }

    /* @ Property MarketValueEarning */
    public String getMarketValueChange() {
        return MarketValueChange;
    }

    public void setMarketValueChange(String MarketValueChange) {
        this.MarketValueChange = MarketValueChange;
    }

    /* @ Property MarketValueToEarningRatio */
    public String getMarketValueChangeRatio() {
        return MarketValueChangeRatio;
    }

    public void setMarketValueChangeRatio(String MarketValueChangeRatio) {
        this.MarketValueChangeRatio = MarketValueChangeRatio;
    }

    /* @ Property MarketToTalVolumn */
    public String getMarketToTalVolumn() {
        return MarketToTalVolumn;
    }

    public void setMarketToTalVolumn(String MarketToTalVolumn) {
        this.MarketToTalVolumn = MarketToTalVolumn;
    }

    /* @ Property MarketToTalValue */
    public String getMarketToTalValue() {
        return MarketToTalValue;
    }

    public void setMarketToTalValue(String MarketToTalValue) {
        this.MarketToTalValue = MarketToTalValue;
    }

    /* @ Property getMarketColorCode */
    public String getMarketColorCode() {
        return MarketColorCode;
    }

    public void setMarketColorCode(String MarketColorCode) {
        this.MarketColorCode = MarketColorCode;
    }

    /* @ Property isMarketData */
    public boolean isMarketData() {
        return isMarketData;
    }

    public void setIsMarketData(boolean isMarketData) {
        this.isMarketData = isMarketData;
    }

    /* @ Property symbol */
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /* @ Property symbol */
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    /* @ Property price */
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    /* @ Property valueChange */
    public String getValueChange() {
        return valueChange;
    }

    public void setValueChange(String valueChange) {
        this.valueChange = valueChange;
    }

    /* @ Property volume */
    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    /* @ Property ColorCode */
    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    /* @ Property isQuoteData */
    public boolean isQuoteData() {
        return isQuoteData;
    }

    public void setIsQuoteData(boolean isQuoteData) {
        this.isQuoteData = isQuoteData;
    }

    /* @ Property valueChangeRatio */
    public String getValueChangeRatio() {
        return valueChangeRatio;
    }

    public void setValueChangeRatio(String valueChangeRatio) {
        this.valueChangeRatio = valueChangeRatio;
    }

    /* @ Property status */
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /* @ Property time */
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
