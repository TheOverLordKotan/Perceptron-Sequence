package org.ada.ia;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class PerceptronSequence {    
	static List<CaminoDto> values = new ArrayList<CaminoDto>();
	int iteraciones = 0;
	static CaminoDto node =null;
	static float umbral=-1.6f;
	public static void main(String[] args) throws Exception {
		float[] pesos = {-1.4f,-0.9f,-umbral};
		float[] salidasDeseadas=new float[9];
		salidasDeseadas[0]=2f;
		salidasDeseadas[1]=3f;
		salidasDeseadas[2]=1f;
		salidasDeseadas[3]=3f;  
		salidasDeseadas[4]=1f;  
		salidasDeseadas[5]=2f;  
		salidasDeseadas[6]=1f;  
		salidasDeseadas[7]=2f;  
		salidasDeseadas[8]=3f;  
		System.out.println("Pesos Iniciales");
		for(int i = 0; i < pesos.length; i++){
			System.out.println(pesos[i]);
		}

		System.out.println("EL PERCEPTRON APRENDIO");      
		System.out.println("Pesos Finales");
		for(int i = 0; i < pesos.length; i++){
			System.out.println(pesos[i]);
		}
		float[] entradasQ= new float[3];
	
		float[][] entradas= {
				//Entrada 1, Entrada 2, Umbral
				//  {x1 , x2  , -1 }
				{1f , 1f  ,umbral},    
				{1f , 2f , umbral},
				{1f, 3f  , umbral},
				{2f , 1f , umbral},
				{2f , 2f  ,umbral},    
				{2f , 3f , umbral},
				{3f, 1f  , umbral},
				{3f , 2f , umbral},
				{3f , 3f , umbral}
			}; 

		if(true){    
			@SuppressWarnings("resource")
			Scanner myObj = new Scanner(System.in);  // Create a Scanner object
			//System.out.println("Enter username");
			String x1=myObj.nextLine();                    
			String x2=myObj.nextLine();                  
			entradasQ [0]=Float.parseFloat(x1); //entrada neurona 1
			entradasQ [1]=Float.parseFloat(x2); //entrada neurona 2
			entradasQ [2]=umbral;               //entrada para el umbral
			PercepSolver attempThePerceptron = new PercepSolver();
			float randomW1 = 0;
			float randomW2 = 0;
			int min=0;
			int max=0;
			boolean isNextLevel=false;
			int level=0;
			boolean isdoy=false;
			for (int i = 0; i < entradas.length; i++) {
				if (entradas[i][0]==entradasQ [0]) {
					if (entradas[i][1]==entradasQ [1]) {
						level=i;
						isdoy=true;
						break;
					}
				}
			}
			if (!isdoy) {
				throw new Exception("El nunero es incorrecto");
			}
			do {
				Random r = new Random();
				if (isNextLevel) {
					if (attempThePerceptron.isUpper()) {
						min=-2;
						max=0;

						randomW1= min + r.nextFloat() * (max - min);
						randomW2 = (min)+ r.nextFloat() * (max - (min));
						pesos[0]=randomW1;
						pesos[1]=randomW2;
						pesos [2]=umbral; 
					}else {
						min=0;
						max=2;
						randomW2= min + r.nextFloat() * (max - min);
						randomW1 = (min-1)+ r.nextFloat() * (max - (min-1));
						pesos[0]=randomW1;
						pesos[1]=randomW2;
						pesos [2]=umbral;
					}
				}
				System.out.println("ÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑÑ");
				attempThePerceptron = perceptronK0(level,entradas,pesos, salidasDeseadas);
				isNextLevel =true;
			} while (!attempThePerceptron.isSolver());
			pesos = attempThePerceptron.getPesos();
			attempThePerceptron = perceptronK0(level,entradas,pesos, salidasDeseadas);
			System.out.println("Resultado;;; "+attempThePerceptron.getFuncionActivacionBack());
		}

	}
	public static PercepSolver perceptronK0(int fal,float[][] entradas,float[] pesos,float[] salidasDeseadas){
		boolean isPercep = false;
		float[] pesosMethod =pesos;
		PercepSolver salidaPercep = null;
		float funcionActivacionBack = 0.0f;
		int  z=0;
		float error = .0f; 
		System.out.println("");        
		int contador = 0;  
		for(int i=fal ; i < entradas.length; i++){
			node = new CaminoDto();
			System.out.println("ITERACION "+contador+":");
			float funcionActivacion[] = PerceptronSequence.funcionActivacionF(entradas[i],pesosMethod);
			System.out.println("valor equivalente funcion: "+funcionActivacion[0]);
			funcionActivacionBack =funcionActivacion[1];
			error = error(salidasDeseadas[fal],funcionActivacion[0]);
			System.out.println("Error: "+error);
			if(z>40){
				break;
			}
			if(error==0f){      
				System.out.println("--------------------------------------");
				contador++; z++; 
				values.add(node);
					isPercep = true;
					break;
			}else{
				//Si hay error, recalcula los pesos
				calculaPesos(entradas[i],error,pesosMethod,funcionActivacion[0]);
				z++;
				i=fal;                      
				contador = 0;  
				values.add(node);
			}
		}
		salidaPercep = new PercepSolver();
		if (isPercep) {
			salidaPercep.setSolver(isPercep);
			salidaPercep.setPesos(pesosMethod);
			salidaPercep.setFuncionActivacionBack(funcionActivacionBack);
		}
		if (funcionActivacionBack>0 && error>=0) {
			salidaPercep.setUpper(true);
		}
		return salidaPercep;

	}
	
	public static float[] funcionActivacionF(float[] entradas,float[] pesos){
		float funcionActivacion[]= new float[2];
		System.out.println("Funcion");
		for(int i = 0; i < entradas.length; i++){
			funcionActivacion[0] += pesos[i] *entradas[i];
			BigDecimal big = new BigDecimal(funcionActivacion[0]);
			big = big.setScale(4, RoundingMode.HALF_UP);    
			funcionActivacion[0]=big.floatValue();
			System.out.println("w"+i+" * "+"x "+i);
			System.out.println(pesos[i] +"*" +entradas[i]+"= "+pesos[i]*entradas[i]);

		}
		System.out.println("y = "+funcionActivacion[0]);
		funcionActivacion[1]=funcionActivacion[0];
		if(funcionActivacion[0] < 0.8f && funcionActivacion[0] > -0.8f ) {
			funcionActivacion[0] = 3;
			node.setA(String.valueOf(funcionActivacion));
		}
		else if(funcionActivacion[0] >= 0) {
			funcionActivacion[0] = 1;
			node.setA(String.valueOf(funcionActivacion));
		}
		else if(funcionActivacion[0]<0) {
			funcionActivacion[0] = 2;
			node.setA(String.valueOf(funcionActivacion));
		}
		return funcionActivacion;
	}

	public static float error(float salidaDeseada,float funcionActivacion){
		float error=1.0f;
		System.out.println("Salida deseada - salida");
		error = salidaDeseada - funcionActivacion;
		System.out.println(salidaDeseada+" - "+funcionActivacion);
		return error;
	}
	
	public static void calculaPesos(float[] entradas,float error, float[] pesos,float funcionActivacion){
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