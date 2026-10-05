public class Descarga extends Thread {
    public int tiempoBloque;
    public String nombreArchivo;
    public Descarga(String nombre) {
        super("Descarga-" + nombre); // Asigna el nombre del hilo usando la superclase (Thread)
        this.nombreArchivo = nombre;

        //genera un número del 0 al 400 (401 porque no incluye al último), por eso le sumo 100 para que cumpla con el mínimo de 100 y máximo de 500
        this.tiempoBloque = (int) (Math.random() * 401) + 100;

    }


    @Override
    public void run() {
        //hace 10 iteraciones. En cada una duerme el tiempo de un bloque e imprime el progreso
        int porcentaje = 0;
        int tiempoTotal = 0;
        for(int i = 0; i<10;i++) {
            System.out.println("[" + this.nombreArchivo + "] " + porcentaje + "%" );
            porcentaje+=10;
            try {
                Thread.sleep(this.tiempoBloque);
                //podría simplemente multiplicarlo por 10 al terminar, pero prefiero hacerlo así para que si en un futuro se cambian las iteraciones sólo se tenga que modificar el for
                tiempoTotal += this.tiempoBloque;
            }catch(InterruptedException e){
                System.out.println("Ocurrió un error durante la descarga: " + e);
            }
        }
        System.out.println("[" + this.nombreArchivo + "]" + ", descarga completada");
        System.out.println("[" + this.nombreArchivo + "]" + " completada en " + tiempoTotal + " ms");
    }
}
