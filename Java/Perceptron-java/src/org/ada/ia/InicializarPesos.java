package org.ada.ia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class InicializarPesos {

	   List<Double> listaPesos = new ArrayList<>();

	   InicializarPesos(int[] nK, int n, int m) {

	      double[][][] w = new double[nK.length][nPesos(nK)][nPesos(nK)];
	      

	      for (int k = 1; k < nK.length; k++) {
	         for (int i = 0; i < nK[k]; i++) {
	            for (int j = 0; j < nK[k - 1]; j++) {
	               w[k][j][i] = new Random().nextDouble() * (n - m) + m;
//	               w[k][j][i] = new Random().nextDouble();
	               listaPesos.add(w[k][j][i]);
	            }
	         }
	      }
	   }

	   public List<Double> getPesos() {
	      return listaPesos;
	   }

	   // calcular cantidad de pesos necesarios
	   private int nPesos(int[] nK) {
	      int nPesos = 1;
	      for (int i = 0; i < nK.length; i++) {
	         nPesos *= nK[i];
	      }
	      return nPesos;
	   }


	}