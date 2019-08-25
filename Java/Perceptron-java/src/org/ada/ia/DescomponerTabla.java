package org.ada.ia;

class DescomponerTabla {

	   int[] nK;
	   double[][] tVerdad;
	   double[][] tEntradas;
	   double[][] tSalidasDeseadas;

	   int nEntradas, nSalidas;

	   public DescomponerTabla(int[] nK, double[][] tablaVerdad) {

	      this.nK = nK;
	      this.tVerdad = tablaVerdad;

	      nEntradas = nK[0];
	      nSalidas = nK[nK.length - 1];
	      int nFilas = tablaVerdad.length;

	      tEntradas = new double[nFilas][nEntradas];
	      for (int i = 0; i < nFilas; i++) {         
	         System.arraycopy(tablaVerdad[i], 0, tEntradas[i], 0, nEntradas);
	      }

	      tSalidasDeseadas = new double[nFilas][nSalidas];
	      for (int i = 0; i < nFilas; i++) {
	         for (int j = 0; j < nSalidas; j++) {
	            tSalidasDeseadas[i][j] = tablaVerdad[i][nEntradas + j];
	         }
	      }

	   }

	   public double[][] getTablaEntradas() {
	      return tEntradas;
	   }

	   public double[][] getTablaSalidasDeseadas() {
	      return tSalidasDeseadas;
	   }

	   public double[] getEntradas(int fila) {
	      double[] vEntrada = new double[nEntradas];
	      System.arraycopy(tEntradas[fila], 0, vEntrada, 0, nEntradas);
	      return vEntrada;
	   }

	   public double[] getSalidas(int fila) {
	      double[] vSalida = new double[nSalidas];
	      System.arraycopy(tSalidasDeseadas[fila], 0, vSalida, 0, nSalidas);
	      return vSalida;
	   }

	}