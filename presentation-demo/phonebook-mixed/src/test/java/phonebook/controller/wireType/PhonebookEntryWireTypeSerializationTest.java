package phonebook.controller.wireType;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PhonebookEntryWireTypeSerializationTest {

    @Test
    public void testSerializePhonebookEntryWireType() throws Exception {
        PhonebookEntryWireType wireType = new PhonebookEntryWireType("an-id", "last-name", "first-name", "email@example.com");
        String serializedResult = new ObjectMapper().writeValueAsString(wireType);
        JSONAssert.assertEquals(serializedResult, new JSONObject(){{
            put("id", "an-id");
            put("lastName", "last-name");
            put("firstName", "first-name");
            put("emailAddress", "email@example.com");
        }}, true);
    }


    @Test
    public void testDeserializePhonebookEntryWireType() throws Exception {
        final JSONObject jsonObject = new JSONObject() {{
            put("id", "an-id");
            put("lastName", "last-name");
            put("firstName", "first-name");
            put("emailAddress", "email@example.com");
        }};
        String serializedResult = jsonObject.toString();
        assertThat(new ObjectMapper().readValue(serializedResult, PhonebookEntryWireType.class),
                is(new PhonebookEntryWireType("an-id", "last-name", "first-name", "email@example.com")));
    }
}
