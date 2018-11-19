package mobile.fpts.com.ezmibile.view.news.detail;

/**
 * Created by TrangDTH on 2/22/2018.
 */

public class NewsDetail {
    private String id;
    private String img;
    private String title;
    private String lead;
    private String body;

    public NewsDetail(String id, String img, String title, String lead, String body) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.lead = lead;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}