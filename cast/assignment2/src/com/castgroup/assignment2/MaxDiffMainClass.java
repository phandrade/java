package com.castgroup.assignment2;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MaxDiffMainClass {

	private int maxDifference(int array[]) {
		
		int resultado = array[1] - array[0]; 
        int i, j; 
        for (i = 0; i < array.length; i++)  
        { 
            for (j = i + 1; j < array.length; j++)  
            { 
                if (array[j] - array[i] > resultado) 
                    resultado = array[j] - array[i]; 
            } 
        } 
        return resultado; 
	}
	
	private int maxDifference(int array[], int arraySize) {
		
		int resultado = array[1] - array[0]; 
        int i, j; 
        for (i = 0; i < arraySize; i++)  
        { 
            for (j = i + 1; j < arraySize; j++)  
            { 
                if (array[j] - array[i] > resultado) 
                    resultado = array[j] - array[i]; 
            } 
        } 
        return resultado; 
	}
	
	public static void main(String[] args) {
		Scanner inputScan = new Scanner(System.in);
		
		try {
			MaxDiffMainClass mainClass = new MaxDiffMainClass();
			int exampleArray[] = {1, 2, 6, 4};
			System.out.println("Array do exemplo");
			System.out.println("A Maior diferença é " + mainClass.maxDifference(exampleArray));
			
			System.out.println("Informe o número de elementos do array");
			int n = inputScan.nextInt();
			int a[] = null;
			if(n == 7) {
				a = new int[]{2, 3, 10, 2, 4, 8, 1};
				System.out.println("sample 0");
			} else if(n == 6) {
				a = new int[]{7, 9, 5, 6, 3, 2};
				System.out.println("Sample 1");
			} else {
				System.out.println("Este número não possui nenhum array vinculado, gerando aleatoriamente...");
				System.out.print("Array gerado={");
				a = new int[n];
				Random r = new Random();
				for(int i=0; i<n; i++) {
					int element = r.nextInt(99);
					a[i] = element;
					System.out.print(" ");
					System.out.print(element);
				}
				System.out.println(" }");
			}
			System.out.println("A Maior diferença é " + mainClass.maxDifference(a, n));			
		} catch(InputMismatchException e) {
			System.err.println("Deve ser informado um número inteiro!");
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			inputScan.close();
		}
	}
}
