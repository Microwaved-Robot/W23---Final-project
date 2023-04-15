package Code.Cinema;

public class Staff {

    protected String name;
    protected int age;

    public Staff() {
        name = null;
        age = 0;
    }

    public Staff(String n, int a) {
        name = n;
        age = a;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "staff [name=" + name + ", age=" + age + "]";
    }

}
