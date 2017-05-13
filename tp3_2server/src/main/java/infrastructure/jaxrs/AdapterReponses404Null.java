package infrastructure.jaxrs;

import configuration.JAXRS;
import infrastructure.jaxrs.annotations.Reponses404Null;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import java.io.IOException;
import java.util.ArrayList;

@Provider
@Reponses404Null
@Priority(Priorities.HEADER_DECORATOR)
public class AdapterReponses404Null implements ContainerResponseFilter, ReaderInterceptor{
	public AdapterReponses404Null() {
		System.out.println("** Initialisation du filtre de type "
				+ this.getClass());
	}

	@Override
	public void filter(ContainerRequestContext requete,
			ContainerResponseContext reponse) throws IOException {

		if (Response.Status.NOT_FOUND.getStatusCode() == reponse.getStatus()) {
			MultivaluedMap<String, Object> enTetes = reponse.getHeaders();
			enTetes.putSingle("PRAGMA", "ERREUR404");
		}
	}

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext reponse) throws IOException, WebApplicationException {
		if (reponse.getHeaders().getOrDefault("PRAGMA", new ArrayList<>()).contains("ERREUR404")) {
			return null;
		}

		return reponse.proceed();
	}
}
