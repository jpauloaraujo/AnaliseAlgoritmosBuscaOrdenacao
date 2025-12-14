package sortingAlgorithms;

import interfaces.ISortingAlgorithm;
import model.Estudante;

/* Implementação do Bubble Sort Otimizado
 * Complexidade: O(n^2) no pior caso, Ω(n) no melhor caso
 * Estável: Sim
 * In-place: Sim
 */
public class BubbleSortOtimizado implements ISortingAlgorithm {

	@Override
	public void sort(Estudante[] dados) {
		int n = dados.length;
		boolean trocou;
		for (int i = 0; i < n - 1; i++) {
			trocou = false;
			for (int j = 0; j < n - 1 - i; j++) {
				if (dados[j].compareTo(dados[j + 1]) > 0) {
					swap(dados, j, j + 1);
					trocou = true;
				}
			}
			if (!trocou) { // Se não houve troca, o array já está ordenado
				break;
			}
		}
	}

	@Override
	public void sort(int[] arrayInt) {
		int n = arrayInt.length;
		boolean trocou;
		for (int i = 0; i < n - 1; i++) {
			trocou = false;
			for (int j = 0; j < n - 1 - i; j++) {
				if (arrayInt[j] > arrayInt[j + 1]) {
					swap(arrayInt, j, j + 1);
					trocou = true;
				}
			}
			if (!trocou) { // Se não houve troca, o array já está ordenado
				break;
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
