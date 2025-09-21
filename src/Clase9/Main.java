package Clase9;

import java.util.*;

public class Main {
    static int num = 7;

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
    }

    public static void main(String[] args) {

        ArrayList <String> curpFiltrado = new ArrayList();
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingresa el sexo que deseas eliminar (hombre/mujer): ");
        String sexo =  sc.next();
        char letraS = 0;

        String curp;

        if (sexo.equalsIgnoreCase("hombre")){
            letraS = 'H';
        }
        else if (sexo.equalsIgnoreCase("mujer")){
            letraS = 'M';
        }

        System.out.println(sexo + " " + letraS);

        for  (int i = 0; i < num; i++) {

            String cur = getCurp();
            System.out.println("CURP: " + cur);
            curpFiltrado.add(cur);

        }

        Iterator<String> it = curpFiltrado.iterator();
        int i = 0;

        while (it.hasNext()) {
            curp = it.next();
            if (curp.charAt(10) == letraS) {
                it.remove();
            }


        }
        System.out.println("CURP filtrado: ");
        System.out.println(curpFiltrado);



    }
}
