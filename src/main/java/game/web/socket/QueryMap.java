package game.web.socket;

import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class QueryMap {

    private final Map<String, String> queryMap;

    public QueryMap() {
        this.queryMap = new HashMap<>();
    }

    public QueryMap(Map<String, String> queryMap) {
        this.queryMap = queryMap;
    }

    public QueryMap(String query) {
        this();
        String[] params = query.split("&");
        for (String param : params) {
            String[] parameter = param.split("=");
            if (parameter.length > 1) {
                queryMap.put(parameter[0], parameter[1]);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder queryMapAsString = new StringBuilder();
        for (Iterator<String> iterator = queryMap.keySet().iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            queryMapAsString.append(key).append("=").append(queryMap.get(key));
            if (iterator.hasNext()) {
                queryMapAsString.append("&");
            }
        }
        return queryMapAsString.toString();
    }

    public QueryMap(URI uri) {
        this(uri.getQuery());
    }

    public Map<String, String> getQueryMap() {
        return queryMap;
    }
}
