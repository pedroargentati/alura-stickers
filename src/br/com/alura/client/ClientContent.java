package br.com.alura.client;

public class ClientContent {

	private final String title;
	private final String urlImage;

	public ClientContent(String title, String urlImage) {
		super();
		this.title = title;
		this.urlImage = urlImage;
	}

	public String getTitle() {
		return title;
	}

	public String getUrlImage() {
		return urlImage;
	}

}
