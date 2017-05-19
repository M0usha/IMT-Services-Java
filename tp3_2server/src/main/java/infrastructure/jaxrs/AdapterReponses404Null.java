package infrastructure.jaxrs;

import infrastructure.jaxrs.annotations.Reponses404Null;

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
import java.io.IOException;

@Provider
@Reponses404Null
@Priority(Priorities.HEADER_DECORATOR)
public class AdapterReponses404Null implements ClientResponseFilter, ReaderInterceptor{

	private static final String PRAGMA = "PRAGMA";
	private static final String ERREUR_404 = "ERREUR404";

	public AdapterReponses404Null() {
		System.out.println("** Initialisation du filtre de type "
				+ this.getClass());
	}

	@Override
	public void filter(ClientRequestContext requete, ClientResponseContext reponse) throws IOException {
		if (Response.Status.NOT_FOUND.getStatusCode() == reponse.getStatus()) {
			reponse.getHeaders().putSingle(PRAGMA, ERREUR_404);
			reponse.setStatus(Response.Status.OK.getStatusCode());
		}
	}

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext reponse) throws IOException, WebApplicationException {
		return ERREUR_404.equals(reponse.getHeaders().getFirst(PRAGMA)) ? null : reponse.proceed();
	}
}
