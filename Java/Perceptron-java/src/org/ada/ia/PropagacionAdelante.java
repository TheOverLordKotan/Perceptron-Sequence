package org.ada.ia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class PropagacionAdelante {

	   private int[] nK;
	   private Iterator<Double> ipesos;
	   private double[] entradas;
	   private int op;
	   private List<Double> listaSalidas;

	   PropagacionAdelante() {
	   }

	   public void sets(int[] nK, List<Double> listaPesos, double[] entradas, int op) {
	      listaSalidas = new ArrayList<>();
	      this.nK = nK;
	      this.ipesos = listaPesos.iterator();
	      this.entradas = entradas;
	      this.op = op; // tipo de función

	   }

	   public void setPesos(List<Double> listaPesos) {
	      this.ipesos = listaPesos.iterator();
	   }

	   public void setEntradas(double[] entradas) {
	      this.entradas = entradas;
	   }

	   public void run() {
	      double[][] s = new double[nK.length][nNeuronas(nK)];
	      listaSalidas.clear();
	      // ENTRADAS:
	      int k = 0;
	      for (int i = 0; i < entradas.length; i++) {
	         s[k][i] = entradas[i] / 100.0;
	         listaSalidas.add(s[k][i]);
	      }
	      // SALIDAS:
	      double tmp;
	      for (k = 1; k < nK.length; k++) {
	         for (int i = 0; i < nK[k]; i++) {
	            tmp = 0.0;
	            for (int j = 0; j < nK[k - 1]; j++) {
	            	double val = (double) ipesos.next();
	               tmp += s[k - 1][j] * val;
	              // System.out.println("ii  "+ i +" jj  "+ j +" aa  "+  s[k - 1][j] +" bb "+val);
	            }
	            s[k][i] = fx(tmp, op);
	            listaSalidas.add(s[k][i]);
	         }
	      }
	   }

	   public List<Double> getSalidas() {
	      return listaSalidas;
	   }

	   public double[] getSalidasFinales() { // no me gusta nada, pero funciona...
	      double vSalida[] = new double[nK[nK.length - 1]];
	      List<Double> aux = listaSalidas.subList(listaSalidas.size() - nK[nK.length - 1], listaSalidas.size());
	      for (int i = 0; i < aux.size(); i++) {
	         vSalida[i] = aux.get(i);
	      }
	      return vSalida;
	   }

	   // METODOS ------------------------------------------------------------------
	   // funcion de activación(F)
	   public double fx(double n, int op) {
	      double fx = 0;
	      switch (op) {
	         case 0: // (0,1)
	            fx = 1 / (1 + Math.pow(Math.E, -n));
	            break; 
	         case 1: // (-1,1)
	            fx = Math.tanh(n);
	            break;
	      }
	      return fx;
	   }

	   // calcular cantidad de neuronas necesarias
	   private int nNeuronas(int[] nK) {
	      int nNeuronas = 1;
	      for (int i = 0; i < nK.length; i++) {
	         nNeuronas += nK[i];
	      }
	      return nNeuronas;
	   }

	}