package Clase9;

import java.util.ArrayList;

public class OrdenarCurp {
    static String getCurp(){
        String letra = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numero = "1234567890";
        String sexo = "HM";
        String[] entidad = {"AS","BC","BS","CC","CS","CH","CL","CM","DF","DG","GT","GR","HG","JC",
                "MC","MN","MS","NT","NL","OC","PL","QT","QR","SP","SL","SR","TC","TL","TS","VZ","YN","ZS"};

        int indice;
        StringBuilder sb =  new StringBuilder(18);
        for(int i = 1; i < 5; i++){
            indice = (int) (Math.random() * letra.length());
            sb.append(letra.charAt(indice));
        }
        for(int i = 5; i < 11; i++){
            indice = (int) (Math.random() * numero.length());
            sb.append(numero.charAt(indice));
        }
        indice = (int) (Math.random() * sexo.length());
        sb.append(sexo.charAt(indice));
        sb.append(entidad[(int) (Math.random() * 32)]);

        for(int i = 14; i < 17; i++){
            indice = (int) (Math.random() * letra.length());
            sb.append(letra.charAt(indice));
        }

        for(int i = 17; i < 19; i++){
            indice = (int) (Math.random() * numero.length());
            sb.append(numero.charAt(indice));
        }

        return sb.toString();
    } // Fin del metodo getCurp

    public static void main(String[] args) {

        String curp = getCurp();
        System.out.println("CURP inicial: " + curp);
        System.out.println();
        ArrayList<String> lista = new ArrayList<String>();

        lista.add(curp);

        for (int i = 0; i < 6; i++){
            curp = getCurp();
            System.out.println("Nueva CURP: " + curp);
            System.out.println();
            int indice = 0;
            boolean encontrado = false;
           for (int j = 0 ; j < lista.size(); j++) {
                if (curp.compareTo(lista.get(j)) < 0) {
                    indice = j;
                    encontrado = true;
                    break;
                }
            }
           if (encontrado){
               lista.add(indice, curp);
           }else{
               lista.add(curp);
           }
            System.out.println("Lista ordenada: ");
            System.out.println(lista);
        }

    }

} // Fin de la clase
