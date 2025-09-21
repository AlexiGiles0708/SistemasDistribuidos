import java.util.ArrayList;


public class PoligonoIrreg {
    private ArrayList<Coordenada>vertices;

    public PoligonoIrreg() {
        vertices = new ArrayList<>();
    }
    public void anadeVertice(Coordenada coordenada){
        vertices.add(coordenada);
    }
    @Override
    public String toString() {
        String texto = "";
        for (Coordenada coordenada : vertices) {
            texto = texto + coordenada + "\n";
        }
        return texto;
    }
}
