package utils;

import model.Estudante;

public class Shuffle {
	// Embaralha um array de inteiros usando o algoritmo de Fisher-Yates
	public static void shuffle(int[] array) {
		for (int i = array.length - 1; i > 0; i--) {
			int j = (int) (Math.random() * (i + 1));
			swap(array, i, j);
		}
	}
	
	//Embaralha um array de Estudantes usando o algoritmo
	public static void shuffle(Estudante[] dados) {
		for (int i = dados.length - 1; i > 0; i--) {
			int j = (int) (Math.random() * (i + 1));
			swap(dados, i, j);
		}
	}
	
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	private static void swap(Estudante[] dados, int i, int j) {
		Estudante temp = dados[i];
		dados[i] = dados[j];
		dados[j] = temp;
	}
}
