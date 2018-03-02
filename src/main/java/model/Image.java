package model;

public class Image {
    private int uid;
    private String path;

    public Image(int uid, String path) {
        this.uid = uid;
        this.path = path;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Image{" +
                "uid=" + uid +
                ", path='" + path + '\'' +
                '}';
    }
}
