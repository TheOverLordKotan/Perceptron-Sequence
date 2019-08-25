package org.ada.ia;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Red {

	   public static void main(String[] args) {

	      // arquitectura perceptron      
	      int nK[] = {2, 4, 1};
	      int nRegistros = 10;
	      int nTests = 1000000;
	      double tasaAprendizaje = 0.001;

	      TablaSuma tv = new TablaSuma(nRegistros, 3);

	      // TESTS:
	      double[] vEntradaTest = new double[nK[0]];

	      // tabla de entrenamiento fija (sumas)
	      double tablaVerdad[][] = tv.getTv();

	      // inicializar pesos (aleatorios)
	      InicializarPesos iniciarPesos = new InicializarPesos(nK, 0, 1);
	      List<Double> listaPesos = iniciarPesos.getPesos();

	      // - GESTIÓN TABLA DE VERDAD -
	      DescomponerTabla dt = new DescomponerTabla(nK, tablaVerdad);
	      mostrarTablaEntrenamiento(nRegistros, dt);

	      // - CALCULOS SALIDAS -
	      PropagacionAdelante pd = new PropagacionAdelante();
	      pd.sets(nK, listaPesos, dt.getEntradas(0), 0); // nRegistro=0, op=0 -> tipo funcion

	      // calculo del vError:
	      double err[] = new double[tablaVerdad.length];

	      // inicializar min a valores maximos
	      double minTotal = Double.MAX_VALUE;
	      double maxTotal = Double.MIN_VALUE;

	      pd.run();

	      // variables basicas
	      int indice;
	      double pesoNuevo;

	      // inicializar variables
	      double tSalidaDeseada[][] = dt.getTablaSalidasDeseadas();
	      double vSalidaReal[] = pd.getSalidasFinales();
	      double vError[] = new double[vSalidaReal.length];
	      double errorMedio[] = new double[nRegistros];
	      double errorMedioTotal;

	      System.out.println("\n01- Iniciando fase de aprendizaje...");
	      for (int t = 0; t < nTests; t++) { // numero de tests...

	         indice = (int) (Math.random() * (listaPesos.size())); // escoje un peso aleatorio
	         pesoNuevo = listaPesos.get(indice);
	         listaPesos.set(indice, pesoNuevo - (tasaAprendizaje * 2)); // actualiza peso

	         for (int nRegistro = 0; nRegistro < nRegistros; nRegistro++) { // numero registros...
	            pd.setPesos(listaPesos);
	            pd.setEntradas(dt.getEntradas(nRegistro)); // row
	            pd.run();
	            vSalidaReal = pd.getSalidasFinales();

	            // calcular error medio
	            double tmp = 0;
	            for (int i = 0; i < vError.length; i++) { // numero salidas finales del registro x
	               vError[i] = tSalidaDeseada[nRegistro][i] - (vSalidaReal[i] * 100); // row = 0
	               tmp = tmp + Math.abs(vError[i]);
	            }
	            errorMedio[nRegistro] = tmp / vError.length;
	         }

	         // calculo error medio total
	         errorMedioTotal = 0;
	         for (int i = 0; i < errorMedio.length; i++) {
	            errorMedioTotal += Math.abs(errorMedio[i]);
	         }
	         errorMedioTotal = errorMedioTotal / errorMedio.length;

	         // Error minimo Total
	         if (errorMedioTotal < minTotal) {
	            minTotal = errorMedioTotal;
	         } else {
	            listaPesos.set(indice, pesoNuevo); // restaura peso anterior
	         }

	         if (errorMedioTotal > maxTotal) {
	            maxTotal = errorMedioTotal;
	         }

	      }

	      // Fase de testeo.
	      System.out.println("02- Iniciando fase de Testeo...");

	      double sDeseada;
	      double[] real;
	      double confiable;
	      double aux1 = 0;
	      int nTestsConfiabilidad = 100;
	      for (int i = 0; i < nTestsConfiabilidad; i++) {
	         vEntradaTest[0] = (Math.random() * 50);
	         vEntradaTest[1] = (Math.random() * 50);
	         pd.sets(nK, listaPesos, vEntradaTest, 0); // nRegistro=0, op=0 -> tipo 
	         pd.run();
	         real = pd.getSalidasFinales();
	         sDeseada = vEntradaTest[0] + vEntradaTest[1];
	         confiable = (((Math.abs(real[0] * 100.0 - sDeseada)) * 100.0) / sDeseada) - 100.0;
	         aux1 += confiable;
	      }
	      System.out.println("03- Resultado confiabilidad %:");
	      double conf = Math.abs(aux1 / nTestsConfiabilidad);
	      System.out.println(conf + "%");

	      // Fase de testeo manual.
	      System.out.println("\nIniciando fase de testeo manual...");
	      System.out.println("Introduce entrada 1: ");
	      Scanner leerX1 = new Scanner(System.in);
	      vEntradaTest[0] = Double.parseDouble(leerX1.next());
	      System.out.println("Introduce entrada 2: ");
	      Scanner leerX2 = new Scanner(System.in);
	      vEntradaTest[1] = Double.parseDouble(leerX2.next());
	      pd.sets(nK, listaPesos, vEntradaTest, 0);
	      pd.run();
	      real = pd.getSalidasFinales();
	      double ia = real[0] * 100.0;
	      double iafinal = (conf * ia) / 100;
	      System.out.println("Salida = " + iafinal);

	   }

	   private static void mostrarTablaEntrenamiento(int nFilas, DescomponerTabla dt) {
	      System.out.println("Tabla de referencia para el entrenamiento:");
	      for (int i = 0; i < nFilas; i++) {
	         System.out.println(""
	                 + Arrays.toString(dt.getEntradas(i)) + "\t "
	                 + Arrays.toString(dt.getSalidas(i)));
	      }
	   }

	}