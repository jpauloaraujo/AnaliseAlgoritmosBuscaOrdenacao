package sortingAlgorithms;

import interfaces.ISortingAlgorithm;
import model.Estudante;

/* Implementação do Quick Sort
 * Complexidade: O(n log n) no caso médio, O(n^2) no pior caso
 * Estável: Não
 * In-place: Sim
 */
public class QuickSort implements ISortingAlgorithm {

	@Override
	public void sort(Estudante[] dados) {
		sort(dados, 0, dados.length - 1);
	}
	
	// Método auxiliar (não usa @Override)
	private void sort(Estudante[] dados, int left, int right) {
	    if (left < right) {
	        int pivot = partition(dados, left, right);
	        sort(dados, left, pivot - 1);
	        sort(dados, pivot + 1, right);
	    }
	}
	
	private static int partition(Estudante[] dados, int left, int right) {
		Estudante pivot = dados[left];
		int i = left + 1;
		int j = right;
		while (i<=j ) {
			if(dados[i].compareTo(pivot) <= 0) {
				i++;
			} else if (dados[j].compareTo(pivot) > 0) {
				j--;
			} else {
				swap(dados, i, j);
			}
		}
		swap(dados, left, j);
		return j; // Posição do pivô
	}

	@Override
	public void sort(int[] arrayInt) {
	    sort(arrayInt, 0, arrayInt.length - 1);
	}

	// Método auxiliar (não usa @Override)
	private void sort(int[] arrayInt, int left, int right) {
	    if (left < right) {
	        int pivot = partition(arrayInt, left, right);
	        sort(arrayInt, left, pivot - 1);
	        sort(arrayInt, pivot + 1, right);
	    }
	}
	
	private static int partition(int[] array, int left, int right) {
		int pivot = array[left];
		int i = left + 1;
		int j = right;
		while (i<=j ) {
			if(array[i] <= pivot) {
				i++;
			} else if (array[j] > pivot) {
				j--;
			} else {
				swap(array, i, j);
			}
		}
		swap(array, left, j);
		return j; // Posição do pivô
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
