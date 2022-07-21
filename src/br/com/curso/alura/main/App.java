package br.com.curso.alura.main;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import br.com.alura.client.ClientContent;
import br.com.alura.client.ClientContentExtractor;
import br.com.alura.client.ClientContentExtractorIMDB;
import br.com.alura.client.ClientContentExtractorNASA;
import br.com.alura.client.ClientService;
import br.com.curso.alura.gerador.figurinha.GeradorDeFigurinhas;
import br.com.curso.alura.properties.Manipulador;

public class App {

	public static final String extensaoArquivo = ".png";

	public static void main(String[] args) throws Exception {
		Properties prop = Manipulador.getProp();
		String login = prop.getProperty("prop.login.imdb");
//		String url = "https://imdb-api.com/en/API/MostPopularMovies/" + login;
		String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
		ClientContentExtractor extractor  = new ClientContentExtractorIMDB();
		
//		String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
//		ClientContentExtractor extractor  = new ClientContentExtractorNASA();
		
		ClientService clientService = new ClientService();
		String json = clientService.searchData(url);
		
		List<ClientContent> listClientContent = extractor.extractContent(json);
		
		GeradorDeFigurinhas geradorDeFigurinhas = new GeradorDeFigurinhas();

		for (ClientContent clientContent : listClientContent) {
			InputStream inputStram = new URL(clientContent.getUrlImage()).openStream();
			geradorDeFigurinhas.createFigure(inputStram, clientContent.getTitle() + extensaoArquivo);
			
			System.out.println("Título: " + clientContent.getTitle());
			System.out.println("Imagem gerada!");
			System.out.println();
		}
	}

}
