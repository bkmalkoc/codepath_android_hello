package codepath.demos.helloworlddemo;

/**
 * Created by burak.malkoc on 12/20/2016.
 */

public class List {

    public List(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String name;

    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
