public abstract class AudioVisual implements Cobravel {
   private final String TITULO;
   private final double PRECO_BASE;
   
   public AudioVisual(String titulo, double precoBase) {
      this.TITULO = titulo;
      this.PRECO_BASE = precoBase;
   }
   
   public double getPrecoBase() {
      return PRECO_BASE;
   }
   
   public String getTitulo() {
      return TITULO;
   }
   
   /**
    * @see Cobravel#calculaPrecoVenda()
    */
   public double calculaPrecoVenda() {
      return 0;
   }
   
   /**
    * @see Cobravel#calculaImposto()
    */
   public double calculaImposto() {
      return 0;
   }
   
   @Override
   public String toString() {
      return TITULO + ";"
              + String.format("%.2f", calculaPrecoVenda())
                  .replace(',', '.')
              + ";"
              + String.format("%.2f", calculaImposto())
                  .replace(',', '.');
   }
}
