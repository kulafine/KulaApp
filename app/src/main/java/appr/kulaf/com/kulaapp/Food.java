package appr.kulaf.com.kulaapp;

/**
 * Created by Fiston on 1/18/2017.
 */

public class Food {

    private String name;
    private String desc;
    private String url;
    private String price;

    public Food(String name, String desc, String url, String price) {
        this.name = name;
        this.desc = desc;
        this.url = url;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }

    public String getPrice() {
        return price;
    }
}
