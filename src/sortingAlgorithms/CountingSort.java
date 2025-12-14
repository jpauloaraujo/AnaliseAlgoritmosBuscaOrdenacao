package sortingAlgorithms;

import java.util.Arrays;

import interfaces.ISortingAlgorithm;
import model.Estudante;

/* Implementação do Counting Sort
 * Complexidade: O(n + k), onde n é o número de elementos e k é o valor máximo
 * Estável: Sim
 * In-place: Não
 * Observação: Adequado para inteiros em um intervalo limitado, como notas de 0 a 10
 */
public class CountingSort implements ISortingAlgorithm {

	@Override
	public void sort(Estudante[] dados) {
	    int n = dados.length;
	    if (n == 0) return;

	    int k = 10; // notas variam de 0 a 10
	    Estudante[] B = new Estudante[n];
	    countingSortDesc(dados, B, k);
	}

	private void countingSortDesc(Estudante[] A, Estudante[] B, int k) {
	    int[] C = new int[k + 1];

	    // Conta ocorrências das notas
	    for (Estudante e : A) {
	        C[e.getNota()]++;
	    }

	    // Soma cumulativa
	    for (int i = 1; i <= k; i++) {
	        C[i] += C[i - 1];
	    }

	    // Preenche array de saída de trás para frente para ordem decrescente
	    for (int j = A.length - 1; j >= 0; j--) {
	        int nota = A[j].getNota();
	        B[A.length - C[nota]] = A[j];  // posição invertida
	        C[nota]--;
	    }

	    // Copia de volta para o array original
	    System.arraycopy(B, 0, A, 0, A.length);
	}

	@Override
	public void sort(int[] A) {
	    int n = A.length;
	    if (n == 0) return;

	    // Descobre o valor máximo para definir o tamanho do array de contagem
	    int k = Arrays.stream(A).max().getAsInt();
	    int[] B = new int[n];
	    countingSort(A, B, k);
	}

	private void countingSort(int[] A, int[] B, int k) {
	    int[] C = new int[k + 1];

	    // Conta ocorrências
	    for (int j = 0; j < A.length; j++) {
	        C[A[j]]++;
	    }

	    // Soma cumulativa
	    for (int i = 1; i <= k; i++) {
	        C[i] += C[i - 1];
	    }

	    // Preenche array de saída de trás para frente para manter estabilidade
	    for (int j = A.length - 1; j >= 0; j--) {
	        B[C[A[j]] - 1] = A[j];
	        C[A[j]]--;
	    }

	    // Copia de volta para o array original
	    System.arraycopy(B, 0, A, 0, A.length);
	}
}
