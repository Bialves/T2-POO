public class Game extends AudioVisual {
   private final Categoria CATEGORIA;
   
   public Game(String titulo, double precoBase, Categoria categoria) {
      super(titulo, precoBase);
      this.CATEGORIA = categoria;
   }
   
   public Categoria getCategoria() {
      return CATEGORIA;
   }
   
   @Override
   public double calculaPrecoVenda() {
      String categoria = this.getCategoria().getNome(); // Puxa a categoria do Enum
      double preco = super.getPrecoBase();
      
      switch (categoria) {
         case "ACAO":
            preco *= 1.2;
            break;
         case "ESPORTE":
            preco *= 1.3;
            break;
         case "ESTRATEGIA":
            preco *= 1.4;
            break;
         case "SIMULACAO":
            preco *= 1.5;
            break;
         case "RPG":
            preco *= 1.7;
      }
      return preco;
   }
   
   @Override
   public double calculaImposto() {
      return this.calculaPrecoVenda() * 0.5;
   }
}
