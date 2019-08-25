package org.ada.ia;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class PerceptronSequence {
	static float funcionActivacion=0.0f;    
	static float error=1.0f;
	static List<CaminoDto> values = new ArrayList<CaminoDto>();
	int iteraciones = 0;
	static float[] pesos = {-1.4f,1.9f,-1.6f};
	static float[][] entradas =    
		{
				//Entrada 1, Entrada 2, Umbral
				//  {x1 , x2  , -1 }

				{1f , 1f  ,1.6f},    
				{1f , 2f , 1.6f},
				{1f, 3f  , 1.6f},
				{2f , 1f , 1.6f},
				{2f , 2f  ,-3.62f},    
				{2f , 3f , 2f},
				{3f, 1f  , 1f},
				{3f , 2f , 2f},
				{3f , 3f , -2f}
		};            

	static float factorAprendizaje=(2 * .00090f);
	static CaminoDto node =null;
	static float[] salidasDeseadas=new float[9];
	static int establecioSalidas=0;


	public static void main(String[] args) {
		salidasDeseadas[0]=2f;
		salidasDeseadas[1]=3f;
		salidasDeseadas[2]=1f;
		salidasDeseadas[3]=3f;  
		salidasDeseadas[4]=1f;  
		salidasDeseadas[5]=2f;  
		salidasDeseadas[6]=1f;  
		salidasDeseadas[7]=2f;  
		salidasDeseadas[8]=3f;  


		establecioSalidas=1;
		int  z=0;

		System.out.println("Pesos Iniciales");
		//imprimimos los pesos que definimos anteriormente
		for(int i = 0; i < pesos.length; i++){
			System.out.println(pesos[i]);
		}
		System.out.println("");        
		int contador = 0;        

		for(int i = 0; i < entradas.length; i++){
			node = new CaminoDto();
			System.out.println("ITERACION "+contador+":");
			float funcionActivacion = funcionActivacion(entradas[i]);
			System.out.println("valor equivalente funcion: "+funcionActivacion);
			float error = error(salidasDeseadas[i]);
			System.out.println("Error: "+error);
			if(z>40){
				break;
			}

			if(error==0f){      

				System.out.println("--------------------------------------");
				contador++; z++; 
				values.add(node);
			}else{
				//Si hay error, recalcula los pesos
				calculaPesos(entradas[i],error);
				z++;
				i=-1;                      
				contador = 0;  
				values.add(node);
			}
		}
		/**
		for (CaminoDto z1 : values) {
			System.out.println(z1.getA() + " "+ z1.getB());
		}
		*/
		System.out.println("EL PERCEPTRON APRENDIO");      
		System.out.println("Pesos Finales");
		for(int i = 0; i < pesos.length; i++){
			System.out.println(pesos[i]);
		}

		if(true){    
			@SuppressWarnings("resource")
			Scanner myObj = new Scanner(System.in);  // Create a Scanner object
			//System.out.println("Enter username");
			/*
			String x1=myObj.nextLine();                    
			String x2=myObj.nextLine();                  
			float [] entradasPrueba=new float[3];
			entradasPrueba [0]=Float.parseFloat(x1); //entrada neurona 1
			entradasPrueba [1]=Float.parseFloat(x2); //entrada neurona 2
			entradasPrueba [2]=-1f;                  //entrada para el umbral
			//float resultado = probarRed(entradasPrueba);      
			//System.out.println(resultado);
			 * */
		}

	}
	public static float funcionActivacion(float[] entradas){
		funcionActivacion = 0.0f;
		System.out.println("Funcion");
		for(int i = 0; i < entradas.length; i++){


			funcionActivacion += pesos[i] *entradas[i];

			//redondeamos a 2 decimales el valor de la funcion activacion
			BigDecimal big = new BigDecimal(funcionActivacion);
			big = big.setScale(4, RoundingMode.HALF_UP);    
			funcionActivacion=big.floatValue();
			//System.out.println("Multiplicacion");
			System.out.println("w"+i+" * "+"x "+i);
			System.out.println(pesos[i] +"*" +entradas[i]+"= "+pesos[i]*entradas[i]);

		}
		System.out.println("y = "+funcionActivacion);
		//se determina el valor de la salida
		if(funcionActivacion < 0.8f && funcionActivacion > -0.8f ) {
			funcionActivacion = 3;
		node.setA(String.valueOf(funcionActivacion));
		}
		else if(funcionActivacion >= 0) {
			funcionActivacion = 1;
			node.setA(String.valueOf(funcionActivacion));
		}
		else if(funcionActivacion<0) {
			funcionActivacion = 2;
			node.setA(String.valueOf(funcionActivacion));
		}
		return funcionActivacion;
	}
	//metodo para verificar si hay o no error
	public static float error(float salidaDeseada){
		System.out.println("Salida deseada - salida");
		error = salidaDeseada - funcionActivacion;
		System.out.println(salidaDeseada+" - "+funcionActivacion);
		return error;
	}
	//metodo para el reajuste de pesos
	public static void calculaPesos(float[] entradas,float error){
		if(error != 0){            
			for(int i = 0; i < entradas.length; i++){                
				//System.out.println(pesos[i]+" + (2 * .5) * "+salidas+" * "+entradas[i]);
				node.setB(pesos[i]+" + (2 * .5) * "+error+" * "+entradas[i]);
				System.out.println("pesos[i] =   ; "+"pesos[i]"+pesos[i]+"("+"error "+error+"*"+"entradas[i]"+entradas[i]);
				//System.out.println("salida  ; "+pesos[i]);
				if (i==entradas.length-1) {
					pesos[i]=(pesos[i]+(error)); 
				}else {
					pesos[i]=(pesos[i]+(error*entradas[i])); 
				}
				 
				String val = pesos[i]+"";
				BigDecimal big = new BigDecimal(val);
				big = big.setScale(2, RoundingMode.HALF_UP);    
				funcionActivacion=big.floatValue();                

				System.out.println("PESOS NUEVOS :"+ pesos[i]);
			}
		}
	}        
}