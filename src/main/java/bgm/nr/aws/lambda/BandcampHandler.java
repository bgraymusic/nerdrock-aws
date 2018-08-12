package bgm.nr.aws.lambda;

import bgm.nr.aws.data.BandcampDAO;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BandcampHandler {

    private static final Logger log = LogManager.getLogger(BandcampHandler.class);

    private final BandcampDAO dao;

    public BandcampHandler(BandcampDAO dao) {
        this.dao = dao;
    }

    public String handleGetDiscography(JsonObject request, Context context) {
        log.trace("Entered handleGetDiscography");
        return "";
    }

}
