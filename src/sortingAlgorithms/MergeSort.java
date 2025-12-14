package sortingAlgorithms;

import java.util.Arrays;

import interfaces.ISortingAlgorithm;
import model.Estudante;

/* Implementação do Merge Sort
 * Complexidade: O(n log n) 
 * Estável: Sim
 * In-place: Não (usa espaço adicional para arrays temporários)
 */
public class MergeSort implements ISortingAlgorithm {

	@Override
	public void sort(Estudante[] dados) {
		if(dados.length < 2) {
			// Caso base: se o array tiver menos de 2 elementos, ele já está ordenado.
			return;
		}
		
		int mid = dados.length / 2;
		
		// Divide o array em duas metades: esquerda e direita.
		Estudante[] leftArray = Arrays.copyOfRange(dados, 0, mid);
		Estudante[] rightArray = Arrays.copyOfRange(dados, mid, dados.length);
		
		// Chamada recursiva para ordenar as duas metades.
		sort(leftArray);
		sort(rightArray);
		
		// Combina as duas metades ordenadas.
		merge(dados, leftArray, rightArray);
	}

	private static void merge(Estudante[] dados, Estudante[] leftArray, Estudante[] rightArray) {
		int i = 0; // Ponteiro para o leftArray
		int j = 0; // Ponteiro para o rightArray
		int k = 0; // Ponteiro para o array original (destino)

		// Combina os elementos em ordem enquanto houver elementos em ambas as metades.
		while (i < leftArray.length && j < rightArray.length) {
			if (leftArray[i].compareTo(rightArray[j]) <= 0) {
				dados[k++] = leftArray[i++];
			} else {
				dados[k++] = rightArray[j++];
			}
		}

		// Copia os elementos restantes da metade esquerda (se houver).
		while (i < leftArray.length) {
			dados[k++] = leftArray[i++];
		}

		// Copia os elementos restantes da metade direita (se houver).
		while (j < rightArray.length) {
			dados[k++] = rightArray[j++];
		}	
	}

	@Override
	public void sort(int[] arrayInt) {
		if (arrayInt.length < 2) {
			// Caso base: se o array tiver menos de 2 elementos, ele já está ordenado.
			return;
		}

		int mid = arrayInt.length / 2;

		// Divide o array em duas metades: esquerda e direita.
		int[] leftArray = Arrays.copyOfRange(arrayInt, 0, mid);
		int[] rightArray = Arrays.copyOfRange(arrayInt, mid, arrayInt.length);

		// Chamada recursiva para ordenar as duas metades.
		sort(leftArray);
		sort(rightArray);

		// Combina as duas metades ordenadas.
		merge(arrayInt, leftArray, rightArray);
	}
	
	private static void merge(int[] array, int[] leftArray, int[] rightArray) {
		int i = 0; // Ponteiro para o leftArray
		int j = 0; // Ponteiro para o rightArray
		int k = 0; // Ponteiro para o array original (destino)

		// Combina os elementos em ordem enquanto houver elementos em ambas as metades.
		while (i < leftArray.length && j < rightArray.length) {
			if (leftArray[i] <= rightArray[j]) {
				array[k++] = leftArray[i++];
			} else {
				array[k++] = rightArray[j++];
			}
		}

		// Copia os elementos restantes da metade esquerda (se houver).
		while (i < leftArray.length) {
			array[k++] = leftArray[i++];
		}

		// Copia os elementos restantes da metade direita (se houver).
		while (j < rightArray.length) {
			array[k++] = rightArray[j++];
		}
	}
}
