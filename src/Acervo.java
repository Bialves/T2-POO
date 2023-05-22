import java.util.ArrayList;

public class Acervo {
   private ArrayList<AudioVisual> audioVisual;
   
   public Acervo() {
      audioVisual = new ArrayList<>();
   }
   
   public ArrayList<AudioVisual> getAudioVisual() {
      return audioVisual;
   }
   
   public void add(AudioVisual element) {
      audioVisual.add(element);
   }
   
   public int size() {
      return audioVisual.size();
   }
}
