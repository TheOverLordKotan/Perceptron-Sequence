using System;
using System.Numerics;

namespace PerceptronSequence
{
    class Program
    {
        static float funcionActivacionV = 0.0f;
        static float errorV = 1.0f;
        int iteraciones = 0;
        static float[] pesos = { -1.4f, 1.9f, -1.6f };
        static float[][] entradas = new float[][] {
new float[] {1f , 1f  ,1.6f},
new float[] {1f , 2f , 1.6f},
new float[] {1f, 3f  , 1.6f},
new float[] {2f , 1f , 1.6f},
new float[] {2f , 2f  ,-3.62f},
new float[] {2f , 3f , 2f},
new float[] {3f, 1f  , 1f},
new float[] {3f , 2f , 2f},
new float[] {3f , 3f , -2f}
};

        static float factorAprendizaje = (2 * .00090f);
        static float[] salidasDeseadas = new float[9];
        static int establecioSalidas = 0;


        static void Main(string[] args)
        {
            salidasDeseadas[0] = 2f;
            salidasDeseadas[1] = 3f;
            salidasDeseadas[2] = 1f;
            salidasDeseadas[3] = 3f;
            salidasDeseadas[4] = 1f;
            salidasDeseadas[5] = 2f;
            salidasDeseadas[6] = 1f;
            salidasDeseadas[7] = 2f;
            salidasDeseadas[8] = 3f;


            establecioSalidas = 1;
            int z = 0;

            Console.WriteLine("Pesos Iniciales");
            //imprimimos los pesos que definimos anteriormente
            for (int i = 0; i < pesos.Length; i++)
            {
                Console.WriteLine(pesos[i]);
            }
            Console.WriteLine("");
            int contador = 0;

            for (int i = 0; i < entradas.Length; i++)
            {
                Console.WriteLine("ITERACION " + contador + ":");
                float funcionActivacionV = funcionActivacion(entradas[i]);
                Console.WriteLine("valor equivalente funcion: " + funcionActivacionV);
                float errorV = error(salidasDeseadas[i]);
                Console.WriteLine("errorV: " + errorV);
                if (z > 40)
                {
                    break;
                }

                if (errorV == 0f)
                {

                    Console.WriteLine("--------------------------------------");
                    contador++; z++;

                }
                else
                {
                    //Si hay errorV, recalcula los pesos
                    calculaPesos(entradas[i], errorV);
                    z++;
                    i = -1;
                    contador = 0;
   
                }
            }
            /**
for (CaminoDto z1 : values) {
Console.WriteLine(z1.getA() + " "+ z1.getB());
}
*/
            Console.WriteLine("EL PERCEPTRON APRENDIO");
            Console.WriteLine("Pesos Finales");
            for (int i = 0; i < pesos.Length; i++)
            {
                Console.WriteLine(pesos[i]);
            }

            if (true)
            {
              //  @SuppressWarnings("resource")
           /// Scanner myObj = new Scanner(System.in);  // Create a Scanner object
                                                     //Console.WriteLine("Enter username");
                                                     /*
                                                     string x1=myObj.nextLine();                    
                                                     string x2=myObj.nextLine();                  
                                                     float [] entradasPrueba=new float[3];
                                                     entradasPrueba [0]=Float.parseFloat(x1); //entrada neurona 1
                                                     entradasPrueba [1]=Float.parseFloat(x2); //entrada neurona 2
                                                     entradasPrueba [2]=-1f;                  //entrada para el umbral
                                                     //float resultado = probarRed(entradasPrueba);      
                                                     //Console.WriteLine(resultado);
                                                     * */
            }

        }
         static float funcionActivacion(float[] entradas)
        {
            funcionActivacionV = 0.0f;
            Console.WriteLine("Funcion");
            for (int i = 0; i < entradas.Length; i++)
            {


                funcionActivacionV += pesos[i] * entradas[i];

                //redondeamos a 2 decimales el valor de la funcion activacion
                BigInteger big = new BigInteger(funcionActivacionV);
                funcionActivacionV = (float)big;
                //Console.WriteLine("Multiplicacion");
                Console.WriteLine("w" + i + " * " + "x " + i);
                Console.WriteLine(pesos[i] + "*" + entradas[i] + "= " + pesos[i] * entradas[i]);

            }
            Console.WriteLine("y = " + funcionActivacionV);
            //se determina el valor de la salida
            if (funcionActivacionV < 0.8f && funcionActivacionV > -0.8f)
            {
                funcionActivacionV = 3;

            }
            else if (funcionActivacionV >= 0)
            {
                funcionActivacionV = 1;

            }
            else if (funcionActivacionV < 0)
            {
                funcionActivacionV = 2;
            }
            return funcionActivacionV;
        }
        //metodo para verificar si hay o no errorV
        public static float error(float salidaDeseada)
        {
            Console.WriteLine("Salida deseada - salida");
            errorV = salidaDeseada - funcionActivacionV;
            Console.WriteLine(salidaDeseada + " - " + funcionActivacionV);
            return errorV;
        }
        //metodo para el reajuste de pesos
        public static void calculaPesos(float[] entradas, float errorV)
        {
            if (errorV != 0)
            {
                for (int i = 0; i < entradas.Length; i++)
                {
                    //Console.WriteLine(pesos[i]+" + (2 * .5) * "+salidas+" * "+entradas[i]);
                    Console.WriteLine("pesos[i] =   ; " + "pesos[i]" + pesos[i] + "(" + "errorV " + errorV + "*" + "entradas[i]" + entradas[i]);
                    //Console.WriteLine("salida  ; "+pesos[i]);
                    if (i == entradas.Length - 1)
                    {
                        pesos[i] = (pesos[i] + (errorV));
                    }
                    else
                    {
                        pesos[i] = (pesos[i] + (errorV * entradas[i]));
                    }

                    string val = pesos[i] + "";
                    decimal big = Convert.ToDecimal(val);
                    funcionActivacionV = (float)big;

                    Console.WriteLine("PESOS NUEVOS :" + pesos[i]);
                }
            }
        }
    }
}
