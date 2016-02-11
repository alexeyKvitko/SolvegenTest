package solvegentest.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import solvegentest.repo.CatalogRepository;
import solvegentest.generated.GetCatalogResponse;
import solvegentest.generated.UpdateCatalogRequest;

/**
 * Created by a.kvitko on 11.02.2016.
 * Spring WS class registers as a potential candidate to handle incoming SOAP messages
 */
@Endpoint
public class CatalogEndPoint {

    private static final String NAMESPACE_URI = "http://solvegentest/generated";

    private CatalogRepository catalogRepository;

    @Autowired
    public CatalogEndPoint(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateCatalogRequest")
    @ResponsePayload
    public GetCatalogResponse getCatalog(@RequestPayload UpdateCatalogRequest request){
        GetCatalogResponse response = new GetCatalogResponse();
        response.setCatalog( catalogRepository.updateCatalog( request.getBook() ) );

        return response;
    }

}
