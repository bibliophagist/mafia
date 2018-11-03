import game.controller.GameController;
import game.tools.GameSession;
import game.tools.NewGameHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ControllerTest {


    @Test
    public void testGameController() {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<String> responseEntityFromServer = new GameController().findGame("test");
        ResponseEntity<String> responseEntityModel = new ResponseEntity<>(
                Long.toString(GameSession.getAtomicLong().get() - 1), headers, HttpStatus.OK);
        Assert.assertEquals(responseEntityFromServer, responseEntityModel);
        NewGameHandler.clearGamePool();
    }
}
