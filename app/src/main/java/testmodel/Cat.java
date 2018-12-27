package testmodel;

/**
 * Crate by E470PD on 2018/8/14
 */
public class Cat {
    public String name;
    public int age;

    public Cat() {

    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name:" + this.name + "---age:" + this.age;
    }
}
