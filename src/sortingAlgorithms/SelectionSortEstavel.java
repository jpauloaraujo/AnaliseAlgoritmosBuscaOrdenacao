package sortingAlgorithms;

import interfaces.ISortingAlgorithm;
import model.Estudante;

/* Implementação do Selection Sort Estável
 * Complexidade: O(n^2)
 * Estável: Sim
 * In-place: Sim
 */
public class SelectionSortEstavel implements ISortingAlgorithm {

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
			Estudante key = dados[minIndex];
			while (minIndex > i) {
				dados[minIndex] = dados[minIndex - 1];
				minIndex--;
			}
			dados[i] = key;
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
			int key = arrayInt[minIndex];
			while (minIndex > i) {
				arrayInt[minIndex] = arrayInt[minIndex - 1];
				minIndex--;
			}
			arrayInt[i] = key;
		}
	}
	
}
