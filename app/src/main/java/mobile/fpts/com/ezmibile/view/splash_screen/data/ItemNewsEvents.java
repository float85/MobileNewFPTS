package mobile.fpts.com.ezmibile.view.splash_screen.data;

public class ItemNewsEvents extends ItemHomeChild {
    private String Code;
    private String Title;
    private String DateTime;
    private int ViewType;
    private String Id;
    private String Iconlink;


    public ItemNewsEvents(String title, String dateTime, String code,
                          int ViewType, String id, String iconlink) {
        this.Title = title;
        this.DateTime = dateTime;
        this.Code = code;
        this.ViewType = ViewType;
        this.Id = id;
        this.Iconlink = iconlink;
    }

    @Override
    public int getTypeView() {
        return ViewType;
    }


    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIconlink() {
        return Iconlink;
    }

    public void setIconlink(String iconlink) {
        Iconlink = iconlink;
    }
}
