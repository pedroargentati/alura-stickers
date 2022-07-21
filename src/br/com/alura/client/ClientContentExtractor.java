package br.com.alura.client;

import java.util.List;

public interface ClientContentExtractor {

	public List<ClientContent> extractContent(String json);
	
}
