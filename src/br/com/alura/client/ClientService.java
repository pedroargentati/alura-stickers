package br.com.alura.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClientService {

	public String searchData(String url) throws IOException {

		try {
			URI urlAddres = URI.create(url);

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(urlAddres).GET().build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			String bodyResponse = response.body();
			return bodyResponse;

		} catch (IOException | InterruptedException ioe) {
			throw new RuntimeException(ioe);
		}
	}

}
