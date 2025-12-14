package sortingAlgorithms;

import interfaces.ISortingAlgorithm;
import model.Estudante;

/* Implementação do Selection Sort Clássico
 * Complexidade: O(n^2)
 * Estável: Não
 * In-place: Sim
 */
public class SelectionSort implements ISortingAlgorithm {

	@Override
	public void sort(Estudante[] dados) {
		int n = dados.length;
		for (int i = 0; i < n - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (dados[j].compareTo(dados[minIndex]) < 0) {
					minIndex = j;
				}
			}
			swap(dados, i, minIndex);
		}
	}

	@Override
	public void sort(int[] arrayInt) {
		int n = arrayInt.length;
		for (int i = 0; i < n - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (arrayInt[j] < arrayInt[minIndex]) {
					minIndex = j;
				}
			}
			swap(arrayInt, i, minIndex);
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
