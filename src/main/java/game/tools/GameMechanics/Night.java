package game.tools.GameMechanics;

public class Night {
    private String wasKilledByMafia;
    private String wasKilledByKiller;
    private String wasHealedByDoctor;

    public NightResults OneOrdinaryNight() {
        mafiaIsAwake();
        doctorIsAwake();
        sheriffIsAwake();
        killerIsAwake();
        return new NightResults(wasKilledByMafia, wasKilledByKiller, wasHealedByDoctor);
    }

    private void doctorIsAwake() {
    }

    private void sheriffIsAwake() {
    }

    private void killerIsAwake() {
    }

    private void mafiaIsAwake() {

    }

}
