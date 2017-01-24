package appr.kulaf.com.kulaapp;

/**
 * Created by Prince on 1/22/2017.
 */

public class Fooditem {

    private String image_url;
    private String name;
    private String price;
    private String word;
    private String desc;

    public Fooditem(String image_url, String name, String price, String word, String desc) {
        this.image_url = image_url;
        this.name = name;
        this.price = price;
        this.word = word;
        this.desc = desc;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getWord() {
        return word;
    }

    public String getDesc() {
        return desc;
    }
}
