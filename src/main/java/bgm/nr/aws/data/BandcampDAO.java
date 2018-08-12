package bgm.nr.aws.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class BandcampDAO {

    private static final Logger log = LogManager.getLogger(BandcampDAO.class);

    private final String api;
    private final String key;
    private final Client client;
    private final WebTarget apiTarget;
    private final WebTarget discographyTarget;

    public BandcampDAO(String api, String key) {
        this.api = api;
        this.key = key;
        client = ClientBuilder.newClient();
        apiTarget = client.target(api);
        discographyTarget = apiTarget.path("band/3/discography");
    }

    public Album[] getDiscography(String bandId) {
        return discographyTarget
                .queryParam("key", key)
                .queryParam("band_id", bandId)
                .request(MediaType.APPLICATION_JSON)
                .get(Album[].class);
    }

}
