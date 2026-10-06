/**
 * Clase Instalador de Nivel 3 que simula una intalación de archivos ya descargado
 * @author Anxo Vázquez
 */
public class Instalador extends Thread{
    private Descarga[] descargas;
    public Instalador(String nombre, Descarga[] descargas){
        super(nombre);
        this.descargas = descargas;

    }

    /**
     * Método que comprueba que las Descargas hayan finalizado para imprimir proceso de instalación
     */
    @Override
    public void run(){
        for(Descarga d: this.descargas){
            try {
                d.join(); //espera a que los hilos hayan terminado
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //una vez han finalizado:
        System.out.println("[Instalador] " + this.descargas[0].nombreArchivo + " y " + this.descargas[0].nombreArchivo + " listos: instalando...");
        System.out.println("[Instalador] Instalación completada");
    }
}
