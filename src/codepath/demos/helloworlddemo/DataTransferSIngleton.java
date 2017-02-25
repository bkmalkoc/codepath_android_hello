package codepath.demos.helloworlddemo;

/**
 * Created by burak.malkoc on 2/24/2017.
 */
public class DataTransferSIngleton {

    private String text;

    private String textPosition;

    private static DataTransferSIngleton ourInstance = new DataTransferSIngleton();

    public static DataTransferSIngleton getInstance() {
        return ourInstance;
    }

    private DataTransferSIngleton() {
    }

    public String getTextPosition() {
        return textPosition;
    }

    public void setTextPosition(String textPosition) {
        this.textPosition = textPosition;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
