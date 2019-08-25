package org.ada.ia;

import java.util.Iterator;
import java.util.List;

class Mostrar {

	   Mostrar(int[] nK, List<Double> listaPesos, List<Double> listaSalidas) {
	      Iterator pesos = listaPesos.iterator();
	      Iterator salidas = listaSalidas.iterator();

	      // mostrar entradas
	      int k = 0;
	      System.out.println("\n* Capa(k): " + k);
	      for (int i = 0; i < nK[0]; i++) {
	         System.out.println("s[" + k + "][" + i + "] = " + (double) salidas.next());
	      }

	      for (k = 1; k < nK.length; k++) {
	         System.out.println("\n* Capa(k): " + k);
	         for (int i = 0; i < nK[k]; i++) {
	            for (int j = 0; j < nK[k - 1]; j++) {
	               System.out.println("w[" + k + "][" + j + "][" + i + "] = " + (double) pesos.next());
	            }
	            System.out.println("s[" + k + "][" + i + "] = " + (double) salidas.next());
	            System.out.println("");
	         }
	      }
	   }
	}