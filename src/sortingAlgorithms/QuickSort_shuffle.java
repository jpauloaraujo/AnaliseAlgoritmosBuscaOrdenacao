package sortingAlgorithms;

import interfaces.ISortingAlgorithm;
import model.Estudante;

/* Implementação do Quick Sort
 * Complexidade: O(n log n) no caso médio, O(n^2) no pior caso
 * Estável: Não
 * In-place: Sim
 */
public class QuickSort_shuffle implements ISortingAlgorithm {

	@Override
	public void sort(Estudante[] dados) {
		sort(dados, 0, dados.length - 1);
	}
	
	// Método auxiliar (não usa @Override)
	private void sort(Estudante[] dados, int left, int right) {
	    if (left < right) {
	        int pivot = partition(dados, left, right);
	        sort(dados, left, pivot);       // Hoare → usa pivot diretamente
	        sort(dados, pivot + 1, right);
	    }
	}

	private static int partition(Estudante[] dados, int left, int right) {

		// Escolhe pivô aleatório e troca com left
		int randomIndex = left + (int)(Math.random() * (right - left + 1));
		swap(dados, left, randomIndex);

		Estudante pivot = dados[left];
		int i = left - 1;
		int j = right + 1;

		while (true) {

			// Move i para a direita até achar elemento >= pivot
			do {
				i++;
			} while (dados[i].compareTo(pivot) < 0);

			// Move j para a esquerda até achar elemento <= pivot
			do {
				j--;
			} while (dados[j].compareTo(pivot) > 0);

			// Quando cruzam, j é a posição final da partição
			if (i >= j)
				return j;

			// Caso contrário, troca e continua
			swap(dados, i, j);
		}
	}

	@Override
	public void sort(int[] arrayInt) {
	    sort(arrayInt, 0, arrayInt.length - 1);
	}

	// Método auxiliar (não usa @Override)
	private void sort(int[] arrayInt, int left, int right) {
	    if (left < right) {
	        int pivot = partition(arrayInt, left, right);
	        sort(arrayInt, left, pivot);     // Hoare
	        sort(arrayInt, pivot + 1, right);
	    }
	}

	private static int partition(int[] array, int left, int right) {

		// Escolhe pivô aleatório
		int randomIndex = left + (int)(Math.random() * (right - left + 1));
		swap(array, left, randomIndex);

		int pivot = array[left];
		int i = left - 1;
		int j = right + 1;

		while (true) {

			do {
				i++;
			} while (array[i] < pivot);

			do {
				j--;
			} while (array[j] > pivot);

			if (i >= j)
				return j;

			swap(array, i, j);
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
