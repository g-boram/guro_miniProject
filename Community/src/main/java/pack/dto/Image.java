package pack.dto;

public class Image {
    private String title;
    private String filename;

    public Image(String title, String filename) {
        this.title = title;
        this.filename = filename;
    }

    public String getTitle() {
        return title;
    }

    public String getFilename() {
        return filename;
    }
}
