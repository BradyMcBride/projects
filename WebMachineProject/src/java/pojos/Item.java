package pojos;

public class Item {
    private int id;
    private String type;
    private String name;
    private String des;
    private String fakeImage;

    public Item(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public Item(int id, String type, String name, String des, String fakeI) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.des = des;
        this.fakeImage = fakeI;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getFakeImage() {
        return fakeImage;
    }

    public void setFakeImage(String fakeImage) {
        this.fakeImage = fakeImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
