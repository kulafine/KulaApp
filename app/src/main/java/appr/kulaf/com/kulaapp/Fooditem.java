package appr.kulaf.com.kulaapp;

/**
 * Created by Prince on 1/20/2017.
 */

public class Fooditem {

    private String name;
    private String desc;
    private String price;

    public Fooditem(String name, String desc, String price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }
}
