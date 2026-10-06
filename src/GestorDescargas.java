import java.util.Scanner;

/**
 * Clase Gestor descargas encargada de interactuar con el usuario
 * @author Anxo Vázquez
 */
public class GestorDescargas {
    /**
     * Main que pide al usuario el nivel y lanza el programa en función de lo escogido
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Anxo Vázquez Lorenzo"); //para que salga en las capturas

        int nivel = selectorNivel();
        Descarga[] descargas = new Descarga[4];
        if (nivel == 1 || nivel == 3 || (nivel == 2 && args.length != 4)) { //nivel 1 y 3 (o el 2 si el usuario no indica los 4 archivos por línea de comandos
            descargas[0] = new Descarga("cuarzos.png");
            descargas[1] = new Descarga("meditacion.mp4");
            descargas[2] = new Descarga("mantras.mp3");
            descargas[3] = new Descarga("horoscopo.pdf");
        }else{ //nivel 2
            descargas[0] = new Descarga(args[0]);
            descargas[1] = new Descarga(args[1]);
            descargas[2] = new Descarga(args[2]);
            descargas[3] = new Descarga(args[3]);
        }

        //Start de las descargas
        for (Descarga d: descargas){
            d.start();
        }

        //descargas[1].tiempoBloque=1000; //Para probar el nivel 3 descomentar esta línea para hacer que el hilo tarde +3s siempre (así se ahorra el ejecutarlo varias veces y que random genere un número inferior a 300)

        //Monitor del nivel 2
        if(nivel == 2) {
            Monitor monitor = new Monitor("Monitor1", descargas);
            Thread thread = new Thread(monitor);
            thread.start();
        }

        if (nivel == 3) {
            Instalador instalador = new Instalador("Instalador", new Descarga[]{descargas[1], descargas[2]}); //meditacion y mantras
            instalador.start();
        }



        //Nivel 3
        if(nivel == 3) {
            try {
                //Espera 3 segundos y si el hilo de meditacion.mp4 (posicion 1 del array) sigue vivo imprime [Main] meditacionmp4 sigue en segundo plano
                Thread.sleep(3000);
                if (descargas[1].isAlive()) { //si sigue vivo
                    System.out.println("[Main] " + descargas[1].nombreArchivo + " sigue en segundo plano");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //Cálculo de tiempo y espera de fin de las descargas
        int tiempoTotalDescargas =0;
        try {
            for (Descarga d: descargas){
                d.join();
                tiempoTotalDescargas += d.tiempoBloque * 10; //utilizo el tiempoBloque de cada descarga y lo multiplico por 10
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Mostrar información
        System.out.println("Todas las descargas han terminado");
        //Como las descarrgas se lanzan en hilos separados realmente sólo necesito saber cual es la que tarda más usando su propiedad tiempoBloque multiplicándolo por 10 (teniendo en cuenta las iteraciones)
        System.out.println("Tiempo real: " + calcularMayorTiempoBloque(descargas).tiempoBloque * 10 + " ms");
        System.out.println("Tiempo si se hubieran descargado una detrás de otra: " + tiempoTotalDescargas + " ms");

    }

    /**
     * Método que devuelve la descarga con mayor tiempo x bloque
     * @param descargas array de descargas
     * @return descarga con mayor tiempo x bloque
     */
    public static Descarga calcularMayorTiempoBloque(Descarga[] descargas) {
        int mayorTiempo = 0;
        Descarga descargaConMayorTiempo = new Descarga("");

        for(Descarga d: descargas){
            if (d.tiempoBloque > mayorTiempo){
                mayorTiempo = d.tiempoBloque;
                descargaConMayorTiempo = d;
            }
        }

        return descargaConMayorTiempo;
    }




    /**
     * Selector de Nivel que pide al usuario que seleccione un nivel
     * @return Integer indicando el nivel
     */
    private static int selectorNivel() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        String teclado;
        while(true) {
            System.out.println("Introuce nivel:\n1. Nivel 1\n2. Nivel 2\n3. Nivel 3 ");
            teclado = sc.next();

            if((teclado.compareTo("1") == 0) || (teclado.compareTo("2") == 0) || (teclado.compareTo("3") == 0)){
                sc.close();
                return Integer.parseInt(teclado);
            }
            System.out.println("Valor incorrecto, vuelve a intentarlo\n---");
        }
    }

}
