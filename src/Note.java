import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable{
    private String title;
    private String text;
    private Date date;

    public Note(String title, String text, Date date) {
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "title:" + title + "\n" +
                "text:" + text + "\n" +
                "date:" + date +
                "\n";
    }

}
