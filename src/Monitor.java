/**
 * Clase Monitor de nivel 2 que muestra descargas restantes cada 500ms
 * @author Anxo Vázquez
 */
public class Monitor implements Runnable{
    public String nombreMonitor;
    public Descarga[] descargas;
    public Monitor(String nombre, Descarga[] descargas) {
        this.nombreMonitor = nombre;
        this.descargas = descargas;

    }

    /**
     * Método run que muestra las descargas en curso cada 500ms
     */
    @Override
    public void run(){
        while(this.getNumeroDescargasVivas() != 0){ //mientras queden descargas en curso
            System.out.println("[Monitor] Descargas en curso: " + this.getNumeroDescargasVivas());
            try{
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        //Cuando no quedan más descargas
        System.out.println("[Monitor] No queda ninguna descarga en curso");
    }


    /**
     * Método que devuelve el número de hilos vivos restantes
     * @return
     */
    private int getNumeroDescargasVivas(){
        int contador = 0;
        for(Descarga d: this.descargas){
            if(d.isAlive()){ //si el hilo sigue vivo suma 1 al contador
                contador++;
            }
        }
        return contador;
    }
}
