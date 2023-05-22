import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
   private Acervo acervo;
   
   public App() {
      acervo = new Acervo();
   }
   
   public void executar() {
      String[] linhas = new String[20]; // Numéro máximo de linhas que realiza a leitura
      int numLinhas = 0;
      
      try {
         Scanner sc = new Scanner(new BufferedReader(new FileReader("dados.csv", Charset.defaultCharset())));
         PrintStream printStream = new PrintStream("resultado.csv", Charset.defaultCharset());
         System.setOut(printStream);
         
         sc.nextLine(); // Pula header do arquivo
         
         while (sc.hasNext()) {
            linhas[numLinhas] = sc.nextLine(); // Armazena a linha no vetor
            numLinhas++;
         }
      } catch (FileNotFoundException e) {
         System.err.format("Arquivo não encontrado: %s%n", e);
      } catch (IOException e) {
         System.err.format("Erro de E/S: %s%n", e);
      } catch (ArrayIndexOutOfBoundsException e) {
         System.err.format("Não é possível ler mais linhas: %s%n", e);
      } catch (Exception e) {
         System.err.format("Erro: %s%n", e.getMessage());
      }
      // Quebrar as linhas em palavras
      for (int i = 0; i < numLinhas; i++) {
         String[] palavras = linhas[i].split(";");
         // Instancia os objetos a partir das info nos índices
         if(palavras.length > 3) { // Válida se o arranjo tem no mínimo 4 ou mais parâmetros
            String nome = palavras[0];
            double precoBase = Double.parseDouble(palavras[1]);
            int tipo = Integer.parseInt(palavras[2]);
            
            if (tipo == 1) { // Se é um BlueRay
               int minutos = Integer.parseInt(palavras[3]);
               
               acervo.add(new BluRay(nome, precoBase, minutos));
            }else if(tipo == 2) { // Senão, é um Game
               Categoria categoria = null;
               for (Categoria c : Categoria.values()) { // Verifica a categoria em nosso Enum
                  if(c.getNome().equals(palavras[3])) {
                     categoria = Categoria.valueOf(palavras[3]); // Se ela existe, cria o Game
                     acervo.add(new Game(nome, precoBase, categoria));
                  }
               }
            }
         }
      }
      
      // Exercício 1
      System.out.println("1;" + acervo.size());
      
      // Exercício 2
      for (AudioVisual element : acervo.getAudioVisual()) {
         System.out.println("2;" + element.toString());
      }
      
      // Exercício 3
      int count = 0;
      for (AudioVisual element : acervo.getAudioVisual()) {
         if (element instanceof Game game && game.getCategoria().equals(Categoria.RPG)) {
            count++;
         }
      }
      System.out.println("3;" + count);
      
      // Exercício 4
      ArrayList<BluRay> bluRays = new ArrayList<>();
      for (AudioVisual element : acervo.getAudioVisual()) {
         if (element instanceof BluRay by) { // Descobre a quantidade de BlueRays registrados
            bluRays.add(by);
         }
      }
      
      double soma = 0.0;
      for (BluRay element : bluRays) { // Faz a média de imposto dos BlueRays existentes
         soma += element.calculaImposto();
      }
      double mediaImposto = soma/bluRays.size();
      
      BluRay aux = null;
      double maior = 0.0;
      for (BluRay element : bluRays) { // Descobre o BlueRay mais próximo da média
         double imposto = element.calculaImposto();
         if (imposto > maior && imposto <= Math.ceil(mediaImposto)) {
            maior = imposto;
            aux = element;
         }
      }
      // Informa o resultado
      String formatted;
      if (aux != null) {
         formatted = "4;"
                 + String.format("%.2f", mediaImposto)
                 .replace(',', '.')
                 + ";"
                 + aux.getTitulo();
      }else {
         formatted = "4;Nenhum BlueRay";
      }
      System.out.print(formatted);
   }
}
