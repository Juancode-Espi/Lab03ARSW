# Lab01ARSW

## Parte I - Introducción a hilos en Java

 1. Realizado
 2. La diferencia entre el ```.run()``` y el ```.start()``` es que el primero hace un llamado común al método
    ```run()``` como cualquier otro, mientras que el ```start()``` nos proporciona la iniciación del hilo.
   
 Podemos ver esto reflejado en: 
### Para el caso de Start
![](https://github.com/Juancode-Espi/Lab01ARSW/blob/main/Imagenes/Start.png)
### Para el caso de Run
![](https://github.com/Juancode-Espi/Lab01ARSW/blob/main/Imagenes/Run.png)
## Parte II - Ejercicio Black List Search
Esta realizada en el codigo 
## Parte III - Evaluación de Desempeño
###Proceso realizado con 1 hilo
![](https://github.com/Juancode-Espi/Lab01ARSW/blob/main/Imagenes/Hilo_1.png)
### Proceso realizado con nucleos del computador
![](https://github.com/Juancode-Espi/Lab01ARSW/blob/main/Imagenes/Hilo_pro.png)
### Proceso realizado con 50 hilo
![](https://github.com/Juancode-Espi/Lab01ARSW/blob/main/Imagenes/Hilo_50.png)
### Proceso realizado con 100 hilos, el procesador que se tiene actualmente no lo puede mostrar en pantalla
## Parte IV
1. Según la ley de Amdahls pudimos identificar que no se logra el un mejor desempeño al estar corriendo 500 hilos, ya que a mayor por más
hilos que use el programa por su tiempo de ejecución llega a un límite donde este no se puede reducir más. La explicación del porque esto sucede
depende de los componentes que tenga el computador que ejecuta el programa.
2. El comportamiento del uso de tantos hilos de procesos sería más optimo con el doble de núcleos y se atribuiría mejor el paralelismo
3. Si hipotéticamente hablando tuviéramos 100 computadores y cada uno estuviera corriendo solo un hilo mejoraría, 
pero no sería lo más optimo ya que cada núcleo es capaz de ejecutar una cantidad mayor de un hilo al mismo tiempo y no se aprovecharía totalmente. 





