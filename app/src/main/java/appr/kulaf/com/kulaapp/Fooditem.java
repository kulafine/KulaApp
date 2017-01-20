package appr.kulaf.com.kulaapp;

/**
 * Created by Prince on 1/20/2017.
 */

public class Fooditem {

    private String name;
    private String url;
    private String word;
    private String price;

    public Fooditem(String name, String url, String word, String price) {
        this.name = name;
        this.url = url;
        this.word = word;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getWord() {
        return word;
    }

    public String getPrice() {
        return price;
    }
}
