package client;

import java.net.URI;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;

public class CircuitBreaker {

    private static HashMap<String, ServerState> serviceState = new HashMap<String, ServerState>();


    // Au debut le circuit est ferme
    // Si timeout le circuit se ferme
    // Ã  la 5eme requete il passe a  semi-ouvert ensuite il laisse passer 3 requetes si 3 timeoutes il ouvre
    public HttpResponse execute(HttpUriRequest request, HttpClient client) throws Exception {
        URI uri = request.getURI();
        String path = getFullPath(uri);
        // A modifier
        return null;
    }

    // retourne l'etat d'uri envoye
    private StateLabel checkState(URI uri) {
        String path = getFullPath(uri);
        if (serviceState.containsKey(path)) {
            if ((serviceState.get(path)).getStatLabel().equals(StateLabel.open)) {
                return StateLabel.open;
            } else if ((serviceState.get(path)).getStatLabel().equals(StateLabel.half_open)) {
                return StateLabel.half_open;
            }
        }
        return StateLabel.closed;
    }

    //set the circuit breaker to open for the service
    private void open(URI uri) {
        String path = getFullPath(uri);
        (serviceState.get(path)).setStatLabel(StateLabel.open);
    }

    //set the circuit breaker to close for the service
    private void close(URI uri) {
        String path = getFullPath(uri);
        (serviceState.get(path)).setStatLabel(StateLabel.closed);
    }

    //set the circuit breaker to close for the service
    private void half_open(URI uri) {
        String path = getFullPath(uri);
        (serviceState.get(path)).setStatLabel(StateLabel.half_open);
    }

    //returns the full path of the uri (removing query parameters)
    private String getFullPath(URI uri) {
        String path = uri.toString();
        int index = path.indexOf("?");
        if (index > 1)
            path = path.substring(0, index);
        return path;
    }
}
