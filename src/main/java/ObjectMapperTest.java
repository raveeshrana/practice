import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * Created by Raveesh Rana on 1/10/2017.
 */
public class ObjectMapperTest {

  @Test()
  public void whenSerializingUsingMixInAnnotation_thenCorrect() throws JsonProcessingException {
    Item item = new Item(1, "book", "Do not serialize this.");

    String result = new ObjectMapper().writeValueAsString(item);
    System.out.println(result);

    ObjectMapper mapper = new ObjectMapper();
    mapper.addMixIn(Object.class, MyMixInForIgnoreType.class);
     // mapper.getSerializationConfig().addMixInAnnotations();

    result = mapper.writeValueAsString(item);
    System.out.println(result);

  }

  class Item {
    public Item(int id, String book, Object owner) {
      this.id = id;
      this.itemName = book;
      this.owner = owner;
    }

    public int id;
    public String itemName;
    public Object owner;
  }

  @JsonIgnoreType()
  class MyMixInForIgnoreType {
  }
}
