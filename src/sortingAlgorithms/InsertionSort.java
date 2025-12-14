package sortingAlgorithms;

import interfaces.ISortingAlgorithm;
import model.Estudante;

/* Implementação do Insertion Sort
 * Complexidade: O(n^2) no pior caso, Ω(n) no melhor caso
 * Estável: Sim
 * In-place: Sim
 */
public class InsertionSort implements ISortingAlgorithm {

	@Override
	public void sort(Estudante[] dados) {
		int n = dados.length;
		for (int i = 1; i < n; i++) {
			Estudante key = dados[i];
			int j = i - 1;
			while (j >= 0 && dados[j].compareTo(key) > 0) {
				dados[j + 1] = dados[j];
				j--;
			}
			dados[j + 1] = key;
		}
	}

	@Override
	public void sort(int[] arrayInt) {
		int n = arrayInt.length;
		for (int i = 1; i < n; i++) {
			int key = arrayInt[i];
			int j = i - 1;
			while (j >= 0 && arrayInt[j] > key) {
				arrayInt[j + 1] = arrayInt[j];
				j--;
			}
			arrayInt[j + 1] = key;
		}
	}
}
