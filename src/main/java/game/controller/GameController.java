package game.controller;

import game.player.Player;
import game.tools.NewGameHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class GameController {

    private final HttpHeaders headers = new HttpHeaders();
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @RequestMapping(
            path = "login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestParam("login") String login,
                                        @RequestParam("password") String password) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(login + password, headers, HttpStatus.OK);
        LOGGER.info("Request for login was handled successfully: {}", true);
        return responseEntity;
    }

    @RequestMapping(
            path = "findGame",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> findGame(@RequestParam("login") String login) {
        Long gameID = NewGameHandler.connectToGame(new Player(login));
        ResponseEntity<String> responseEntity = new ResponseEntity<>(gameID.toString(), headers, HttpStatus.OK);
        LOGGER.info("Request for login was handled successfully: {}", true);
        return responseEntity;
    }
}
