import java.util.ArrayList;
import java.util.Scanner;

public class PlayerManager {

    ArrayList<Player> players =
            new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void addPlayer() {

        try {

            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Sport: ");
            String sport = sc.nextLine();

            players.add(
                    new Player(id, name, sport));

            System.out.println(
                    "Player Added Successfully!");

        } catch (Exception e) {

            System.out.println(
                    "Invalid Input!");
            sc.nextLine();
        }
    }

    public void viewPlayers() {

        if (players.isEmpty()) {
            System.out.println("No Players Found!");
            return;
        }

        for (Player p : players) {
            p.display();
            System.out.println();
        }
    }

    public void searchPlayer() {

        System.out.print(
                "Enter ID to Search: ");

        int id = sc.nextInt();

        for (Player p : players) {

            if (p.getId() == id) {

                p.display();
                return;
            }
        }

        System.out.println(
                "Player Not Found!");
    }

    public void deletePlayer() {

        System.out.print(
                "Enter ID to Delete: ");

        int id = sc.nextInt();

        for (int i = 0;
             i < players.size(); i++) {

            if (players.get(i).getId()
                    == id) {

                players.remove(i);

                System.out.println(
                        "Player Deleted!");

                return;
            }
        }

        System.out.println(
                "Player Not Found!");
    }
}