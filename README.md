# Tarea 9 - Descargas Cuánticas: el gestor de Don Magufo

## Alumno

- Anxo Vázquez Lorenzo (2 DAM)

## Asignatura

- PSP

## Niveles hechos

## Errores encontrados

### Nivel 1

**Cálculo de tiempo real**

Después de pensar e investigar cuál era la mejor forma de calcular el tiempo real y ver varias alternativas, me acordé de que cada descarga se ejecuta en un hilo distinto, por lo tanto, como todas se ejecutan en paralelo, realmente solo necesito saber cuál fue la que más tardó (`tiempoBloque`).

Para ello utilizo está función:

```java
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
```

Y la implemento así en mi código:

```
System.out.println("Tiempo real: " + calcularMayorTiempoBloque(descargas).tiempoBloque * 10 + " ms");
```

## Bibliografía

- W3Schools - generar números aleatorios

  https://www.w3schools.com/java/java_howto_random_number.asp

