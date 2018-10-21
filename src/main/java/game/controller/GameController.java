package game.controller;

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
        ResponseEntity<String> responseEntity = new ResponseEntity<>("", headers, HttpStatus.OK);
        LOGGER.info("Request for login was handled successfully: {}", true);
        return responseEntity;
    }
}
