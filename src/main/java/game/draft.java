package game;

import game.web.socket.QueryMap;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;

public class draft {
    public static void main(String[] args) {
        Map<String, String> map = null;
        try {
            URI uri = new URI("/game/connect?gameId=0&name=test");
            map = new QueryMap(uri).getQueryMap();
            for (String s : map.keySet()) {
                System.out.println(s + " " + map.get(s));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        QueryMap queryMap = new QueryMap(map);
        System.out.println(queryMap.toString());


    }
}