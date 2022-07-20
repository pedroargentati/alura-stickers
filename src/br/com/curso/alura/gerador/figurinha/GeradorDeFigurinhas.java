package br.com.curso.alura.gerador.figurinha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {

	public static final String CAMINHO = "saida/";
	
	public void createFigure(InputStream inputStram, String nomeArquivo) throws IOException {


		BufferedImage imagemOriginal = ImageIO.read(inputStram);
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 200;

		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
 
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal, 0, 0, null);

		graphics.setColor(Color.ORANGE);
		graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 72));
		graphics.drawString("Teste API Java", 90, (novaAltura - 100));
		
		ImageIO.write(novaImagem, "png", new File(CAMINHO + nomeArquivo));
		
	}

//	public static void main(String[] args) {
//		GeradorDeFigurinhas geradorDeFigurinhas = new GeradorDeFigurinhas();
//		try {
//			
//			geradorDeFigurinhas.createFigure();
//			if (!new File(CAMINHO).exists()) {
//				new File(CAMINHO).mkdir();
//			} else {
//				System.out.println("Pasta '" + CAMINHO + "' já existe.");
//			}
//			
//			System.out.println("Figurinha gravada no caminho ' " + CAMINHO + " ' com sucesso.");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
