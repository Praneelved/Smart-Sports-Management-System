public class Tournament {

    private String tournamentName;
    private int teams;

    public Tournament(String tournamentName, int teams) {
        this.tournamentName = tournamentName;
        this.teams = teams;
    }

    public void displayTournament() {

        System.out.println("Tournament Name: "
                + tournamentName);

        System.out.println("Number of Teams: "
                + teams);
    }
}