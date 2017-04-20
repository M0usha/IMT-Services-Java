package infrastructure.jaxrs;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import infrastructure.jaxrs.annotations.ReponsesCreatedPOST;

@Provider
@ReponsesCreatedPOST
@Priority(Priorities.HEADER_DECORATOR)
public class AdapterReponsesCreatedPOST implements ClientResponseFilter, ReaderInterceptor {

	private static String HYPERLIEN = "Hyperlien";
	@Override
	public Object aroundReadFrom(ReaderInterceptorContext reponse)
			throws IOException, WebApplicationException {
		String pragma = reponse.getHeaders().getFirst("Pragma");
		if((pragma != null) && (pragma.equals(HYPERLIEN))){
			return new HyperLien<Object>(reponse.getHeaders().getFirst("Location"));
		}
		return reponse.proceed();
	}
	
	@Override
	public void filter(ClientRequestContext requete, ClientResponseContext reponse)
			throws IOException {
		if(reponse.getStatus() == Response.Status.CREATED.getStatusCode()){
			reponse.getHeaders().putSingle("Pragma", HYPERLIEN);
		}
	}

}
