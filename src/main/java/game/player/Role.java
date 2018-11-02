package game.player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Role {
    MAFIA,
    TOWNSMAN,
    DOCTOR,
    COURTESAN,
    SHERIFF;

    private static final List<Role> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static Role randomRole() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
