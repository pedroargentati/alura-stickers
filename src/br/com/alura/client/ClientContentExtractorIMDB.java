package br.com.alura.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.curso.alura.main.JsonParser;

public class ClientContentExtractorIMDB implements ClientContentExtractor {

	public List<ClientContent> extractContent(String json) {
		JsonParser parser = new JsonParser();
		List<Map<String, String>> listAttributesJSON = parser.parse(json);

		List<ClientContent> contents = new ArrayList<ClientContent>();
		for (Map<String, String> attributes : listAttributesJSON) {
			String title = attributes.get("title");
			String urlImage = attributes.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
			ClientContent clientContent = new ClientContent(title, urlImage);

			contents.add(clientContent);
		}
		return contents;
	}

}
