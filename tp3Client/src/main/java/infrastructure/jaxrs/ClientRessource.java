package infrastructure.jaxrs;

import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.proxy.WebResourceFactory;

import client.AppliCliente;

public class ClientRessource<T> {

	public static <T> T proxy(HyperLien<T> lien, Class<T> type) {
		return (new ClientRessource<T>(lien, type)).getProxyRessource();
	}

	private T proxyRessource;

	public ClientRessource(HyperLien<T> k, Class<T> typeInterface) {
		WebTarget cible = AppliCliente.clientJAXRS().target(k.getUri());
		proxyRessource = WebResourceFactory.newResource(typeInterface, cible);
	}

	public T getProxyRessource() {
		return proxyRessource;
	}
}
