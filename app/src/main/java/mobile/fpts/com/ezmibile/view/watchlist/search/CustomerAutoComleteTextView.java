package mobile.fpts.com.ezmibile.view.watchlist.search;

public class CustomerAutoComleteTextView {
    private String stock_code;
    private String name_vn = "";
    private String name_en = "";
    private String post_to;

    public CustomerAutoComleteTextView(String stock_code, String name_vn, String name_en, String post_to) {
        this.stock_code = stock_code;
        this.name_vn = name_vn;
        this.name_en = name_en;
        this.post_to = post_to;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getName_vn() {
        return name_vn;
    }

    public void setName_vn(String name_vn) {
        this.name_vn = name_vn;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getPost_to() {
        return post_to;
    }

    public void setPost_to(String post_to) {
        this.post_to = post_to;
    }
}
