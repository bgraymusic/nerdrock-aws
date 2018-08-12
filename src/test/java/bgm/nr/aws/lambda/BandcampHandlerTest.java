package bgm.nr.aws.lambda;

import bgm.nr.aws.data.BandcampDAO;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.FileReader;
import java.io.IOException;

public class BandcampHandlerTest {

    private enum TestFiles {
        API_GATEWAY_BANDCAMP("api-gateway-bandcamp.json");

        public final String filename;
        TestFiles(String filename) {
            this.filename = filename;
        }
    }

    private BandcampDAO dao;
    private BandcampHandler lambda;
    private Context context;

    @Before public void setupTest() {
        dao = Mockito.mock(BandcampDAO.class);
        lambda = new BandcampHandler(dao);
        context = Mockito.mock(Context.class);
    }

    @Test public void testFoo() {
        JsonObject jsonObject = getJsonObjectFromFile(TestFiles.API_GATEWAY_BANDCAMP.filename);
        lambda.handleGetDiscography(jsonObject, context);
    }

    private JsonObject getJsonObjectFromFile(String filename) {
        try {
            String path = ClassLoader.getSystemClassLoader().getResource(filename).getFile();
            return new JsonParser().parse(new FileReader(path)).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
