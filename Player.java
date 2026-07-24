public class Player {

    protected int id;
    protected String name;
    protected String sport;

    public Player(int id, String name, String sport) {
        this.id = id;
        this.name = name;
        this.sport = sport;
    }

    public int getId() {
        return id;
    }

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Sport: " + sport);
    }
}