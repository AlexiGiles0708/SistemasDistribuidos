public class PruebaPoligono {
    public static void main(String[] args) {

        PoligonoIrreg poli = new PoligonoIrreg();

        for (int i = 0; i < 3; i++) {
            double x = Math.round((Math.random() * 200 - 100) * 1000) / 1000.0;
            double y = Math.round((Math.random() * 200 - 100) * 1000) / 1000.0;
            Coordenada nuevo = new Coordenada(x, y);
            poli.anadeVertice(nuevo);
        }

        System.out.println("Polígono después de añadir 3 vértices:");
        System.out.println(poli);




    }
}
