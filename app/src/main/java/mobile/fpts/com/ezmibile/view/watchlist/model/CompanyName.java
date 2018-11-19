package mobile.fpts.com.ezmibile.view.watchlist.model;

public class CompanyName {
    private String stockcode;
    private String companyname;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getstockcode() {
        return this.stockcode;
    }

    public String getcompanyname() {
        return this.companyname;
    }

    public void setstockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    public void setcompanyname(String companyname) {
        this.companyname = companyname;
    }

}