import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.EmployeeModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

public class JsonParseTest {

    @Test
    @DisplayName("Парсим Json")
    void jacksonTest() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        File jsonFile = new File("src/test/resources/employee.json");
        EmployeeModel jacksonGet = objectMapper.readValue(jsonFile, new TypeReference<>() {});
        Assertions.assertEquals("Pankaj", jacksonGet.getName());
        Assertions.assertTrue(jacksonGet.getPermanent());
        Assertions.assertEquals("Manager", jacksonGet.getRole());
        Assertions.assertEquals(123456, jacksonGet.getPhoneNumbers().get(0));
        Assertions.assertEquals(987654, jacksonGet.getPhoneNumbers().get(1));
        Assertions.assertEquals("Los Angeles", jacksonGet.getCities().get(0));
        Assertions.assertEquals("New York", jacksonGet.getCities().get(1));
        Assertions.assertEquals("Albany Dr", jacksonGet.getAddress().getStreet());
        Assertions.assertEquals("San Jose", jacksonGet.getAddress().getCity());
        Assertions.assertEquals(95129, jacksonGet.getAddress().getZipcode());
        Assertions.assertEquals("29 years", jacksonGet.getProperties().getAge());
        Assertions.assertEquals("1000 USD", jacksonGet.getProperties().getSalary());
    }
}
