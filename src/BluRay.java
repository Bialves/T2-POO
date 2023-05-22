public class BluRay extends AudioVisual {
   private final int DURACAO;
   
   public BluRay(String titulo, double precoBase, int duracao) {
      super(titulo, precoBase);
      this.DURACAO = duracao;
   }
   
   public int getDuracao() {
      return DURACAO;
   }
   
   @Override
   public double calculaPrecoVenda() {
      return (super.getPrecoBase() * DURACAO) / 100.0;
   }
   
   @Override
   public double calculaImposto() {
      return this.calculaPrecoVenda() * 0.4;
   }
}
