import java.io.Serializable;

public class UserDetails implements Serializable {
    private String name;
    private int age;

    public UserDetails(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "NAME: " + name + " AGE: " + age;
    }
}

