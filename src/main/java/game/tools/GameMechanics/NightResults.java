package game.tools.GameMechanics;

public class NightResults {
    private final String wasKilledByMafia;
    private final String wasKilledByKiller;
    private final String wasHealedByDoctor;

    public NightResults(String wasKilledByMafia, String wasKilledByKiller, String wasHealedByDoctor) {
        this.wasKilledByMafia = wasKilledByMafia;
        this.wasKilledByKiller = wasKilledByKiller;
        this.wasHealedByDoctor = wasHealedByDoctor;
    }

    public String getWasKilledByMafia() {
        return wasKilledByMafia;
    }

    public String getWasKilledByKiller() {
        return wasKilledByKiller;
    }

    public String getWasHealedByDoctor() {
        return wasHealedByDoctor;
    }
}
