package bgm.nr.aws.data;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.security.auth.login.Configuration;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BandcampDAOTest {

    private static final Logger log = LogManager.getLogger(BandcampDAOTest.class);
    private static final String uri = "http://api.bandcamp.com/api";

    private static Properties properties;
    private static String apiHost;
    private static String key;
    private static String bandId;

    @BeforeClass public static void loadProperties() {
        try {
            properties = new Properties();
            ClassLoader loader = BandcampDAOTest.class.getClassLoader();
            InputStream stream = loader.getResourceAsStream("test.properties");
            properties.load(stream);
//            properties.load(properties.getClass().getClassLoader().getResourceAsStream("test.properties"));
            apiHost = properties.getProperty("bandcamp_api_host");
            key = properties.getProperty("bandcamp_key");
            bandId = properties.getProperty("bandcamp_band_id_main");
        } catch (IOException e) {
            log.error("Unable to load the property file.");
            e.printStackTrace();
        }
    }

//    @Test public void testGetDiscography() {
//        BandcampDAO dao = new BandcampDAO(apiHost, key);
//        log.info(new Gson().toJson(dao.getDiscography(bandId)));
//    }

    @Test public void testJersey() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(uri).path("band").path("3").path("discography");
        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        log.debug(response.getStatus());
    }

}
