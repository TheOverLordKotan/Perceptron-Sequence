package org.ada.ia;


class TablaSuma {

   final double[][] tv;

   TablaSuma(int nFilas, int col) {

      this.tv = new double[nFilas][col];
      
      // Tabla de verdad suma
      tv[0][0] = 1; tv[0][1] = 1; tv[0][2] = 2;
      tv[1][0] = 1; tv[1][1] = 2; tv[1][2] = 3;
      tv[2][0] =1; tv[2][1] = 3; tv[2][2] =1;
      tv[3][0] =  6.0; tv[3][1] = 27.0; tv[3][2] = 33.0;
      tv[4][0] =  5.0; tv[4][1] = 42.0; tv[4][2] = 47.0;
      tv[5][0] = 18.0; tv[5][1] = 12.0; tv[5][2] = 30.0;
      tv[6][0] = 35.0; tv[6][1] = 39.0; tv[6][2] = 74.0;
      tv[7][0] =  2.0; tv[7][1] = 17.0; tv[7][2] = 19.0;
      tv[8][0] = 44.0; tv[8][1] = 14.0; tv[8][2] = 58.0;
      tv[9][0] = 24.0; tv[9][1] = 37.0; tv[9][2] = 61.0;

   }

   public double[][] getTv() {
      return tv;
   }

}
