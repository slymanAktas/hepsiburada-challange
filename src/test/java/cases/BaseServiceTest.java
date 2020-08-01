package cases;
import io.restassured.RestAssured;
import api.services.PetStoreService;
import static config.Config.API_BASE_PATH;
import static config.Config.API_URL;

public class BaseServiceTest extends BaseTest {
    protected PetStoreService petStoreService = new PetStoreService();

    public BaseServiceTest() {
        RestAssured.baseURI = API_URL;
        RestAssured.basePath = API_BASE_PATH;
    }
}

