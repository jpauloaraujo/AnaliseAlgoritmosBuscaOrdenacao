package sortingAlgorithms;

import interfaces.ISortingAlgorithm;
import model.Estudante;

/* Implementação do Bubble Sort Clássico
 * Complexidade: O(n^2) no pior caso e também no melhor caso.  
 * Estável: Sim
 * In-place: Sim
 */
public class BubbleSort implements ISortingAlgorithm {
	
	@Override
	public void sort(Estudante[] dados) {
		int n = dados.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - 1 - i; j++) {
				if (dados[j].compareTo(dados[j + 1]) > 0) {
					swap(dados, j, j + 1);
				}
			}
		}
	}

	@Override
	public void sort(int[] arrayInt) {
		int n = arrayInt.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - 1 - i; j++) {
				if (arrayInt[j] > arrayInt[j + 1]) {
					swap(arrayInt, j, j + 1);
				}
			}
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