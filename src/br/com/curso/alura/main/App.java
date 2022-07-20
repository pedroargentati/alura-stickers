package br.com.curso.alura.main;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import br.com.curso.alura.gerador.figurinha.GeradorDeFigurinhas;
import br.com.curso.alura.properties.Manipulador;

public class App {

	public static final String extensaoArquivo = ".png";
	
	public static void main(String[] args) throws Exception {
		getMostPopularFilmes();
	}
	
	public static List<Map<String, String>> getMostPopularFilmes() throws IOException, InterruptedException {
		Properties prop = Manipulador.getProp();
		String login = prop.getProperty("prop.login.imdb");
//		String urlMostPopularMovies = "https://imdb-api.com/en/API/MostPopularMovies/" + login;
		String urlMostPopularMovies = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
		
		URI enderecoMostPopularMovies = URI.create(urlMostPopularMovies);

		HttpClient clienteMostPopularMovies = HttpClient.newHttpClient();
		HttpRequest requestMostPopularMovies = HttpRequest.newBuilder(enderecoMostPopularMovies).GET().build();

		HttpResponse<String> responseMostPopularMovies = clienteMostPopularMovies.send(requestMostPopularMovies, BodyHandlers.ofString());
		String bodyMostPopularMovies = responseMostPopularMovies.body();

		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaFilmesMaisPopulares = parser.parse(bodyMostPopularMovies);

		String star = "";
		double ratingDouble = 0;
		GeradorDeFigurinhas geradorDeFigurinhas = new GeradorDeFigurinhas();

		for (Map<String, String> mostPopularFilms : listaFilmesMaisPopulares) {
			String rating = mostPopularFilms.get("imDbRating");
			if (rating != null && rating != "") {
				ratingDouble = Math.floor(Double.parseDouble(rating));
				star = "";
				for (int i = 0; i < ratingDouble; i++) {
					star += "⭐";
				}
			}
			
			String imageURL = mostPopularFilms.get("image");
			String filmTitle = mostPopularFilms.get("title");
			InputStream inputStram = new URL(imageURL).openStream();
			
			
			geradorDeFigurinhas.createFigure(inputStram, filmTitle + extensaoArquivo);
			
			System.out.println("Título: " + mostPopularFilms.get("title"));
			System.out.println("Nota:   " + mostPopularFilms.get("imDbRating") + " " + star);
			System.out.println();
		}
		return listaFilmesMaisPopulares;

	}

}
