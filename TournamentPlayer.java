public class TournamentPlayer extends Player {

    private String tournamentName;

    public TournamentPlayer(int id, String name,
                            String sport,
                            String tournamentName) {

        super(id, name, sport);
        this.tournamentName = tournamentName;
    }

    @Override
    public void display() {

        super.display();
        System.out.println("Tournament: " + tournamentName);
    }
}