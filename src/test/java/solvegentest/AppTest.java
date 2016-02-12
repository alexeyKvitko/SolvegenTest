package solvegentest;

/**
 * Created by a.kvitko on 12.02.2016.
 */
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
import solvegentest.generated.Book;
import solvegentest.generated.UpdateCatalogRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
public class AppTest {

    private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    @Value("${local.server.port}")
    private int port = 8080;

    @Before
    public void init() throws Exception {
        marshaller.setPackagesToScan(ClassUtils.getPackageName(UpdateCatalogRequest.class));
        marshaller.afterPropertiesSet();
    }

    @Test
    public void testSendAndReceive() {
        UpdateCatalogRequest request = new UpdateCatalogRequest();
        Book book = new Book();
        book.setId("book");
        book.setAuthor("Gambardella, Matthew");
        book.setTitle("XML Developer's Guide");
        book.setGenre("Computer");
        book.setPrice(44.95);
        book.setPublishDate("2000-10-01");
        book.setDescription("An in-depth look at creating applications with XML.");
        request.setBook( book );
        assertNotNull(new WebServiceTemplate(marshaller).marshalSendAndReceive("http://localhost:"
                + port + "/ws", request));
    }

}
