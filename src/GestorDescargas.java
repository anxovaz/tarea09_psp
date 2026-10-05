import java.util.Scanner;

public class GestorDescargas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        nivel1();


        sc.close();
    }

    /**
     * Nivel 1
     * Lanza las 4 descargas enn el array descargas y espera a que finalicen, luego se suman los tiempos que tardaron y se muestra el tiempo total (si fuese 1 detrás de otra) y el real (la descarga más grande)
     */
    public static void nivel1() {
        //Declaración escanner
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");


        //Descargas
        Descarga[] descargas = {
                new Descarga("cuarzos.png"),
                new Descarga("meditacion.mp4"),
                new Descarga("mantras.mp3"),
                new Descarga("horoscopo.pdf")};

        //Start de las descargas
        for (Descarga d: descargas){
            d.start();
        }

        //Cálculo de tiempo y espera de fin de las descargas
        int tiempoTotalDescargas =0;

        try {
            for (Descarga d: descargas){
                d.join();
                tiempoTotalDescargas += d.tiempoBloque * 10;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Mostrar información
        System.out.println("Todas las descargas han terminado");
        //Como las descarrgas se lanzan en hilos separados realmente sólo necesito saber cual es la que tarda más usando su propiedad tiempoBloque multiplicándolo por 10 (teniendo en cuenta las iteraciones)
        System.out.println("Tiempo real: " + calcularMayorTiempoBloque(descargas).tiempoBloque * 10 + " ms");
        System.out.println("Tiempo si se hubieran descargado una detrás de otra: " + tiempoTotalDescargas + " ms");

        sc.close(); //cierre scanner
    }

    /**
     * Método que devuelve la descarga con mayor tiempo x bloque
     * @param descargas array de descargas
     * @return descarga con mayor tiempo x bloque
     */
    private static Descarga calcularMayorTiempoBloque(Descarga[] descargas) {
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


}
