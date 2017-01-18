package appr.kulaf.com.kulaapp;

/**
 * Created by Fiston on 1/18/2017.
 */

public class Food_model {

    private String name;
    private String desc;
    private String url;
    private String price;
    private String word;

    public Food_model(String name, String desc, String url, String price, String word) {
        this.name = name;
        this.desc = desc;
        this.url = url;
        this.price = price;
        this.word = word;
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

    public String getWord() {
        return word;
    }
}
