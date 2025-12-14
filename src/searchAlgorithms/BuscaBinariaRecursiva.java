package searchAlgorithms;

import interfaces.ISearchingAlgorithm;
import model.Estudante;

public class BuscaBinariaRecursiva implements ISearchingAlgorithm {

	@Override
	public boolean search(Estudante[] dados, Estudante estudante) {
		return search(dados, estudante, 0, dados.length - 1);
	}

	private boolean search(Estudante[] dados, Estudante estudante, int left, int right) {
		if (left > right) {
			return false; // Não encontrado
		}

		int mid = left + (right - left) / 2;

		if (dados[mid].compareTo(estudante) == 0) {
			return true; // Elemento encontrado
		} else if (dados[mid].compareTo(estudante) < 0) {
			return search(dados, estudante, mid + 1, right); // Procura na metade direita
		} else {
			return search(dados, estudante, left, mid - 1); // Procura na metade esquerda
		}
	}

	@Override
	public boolean search(int[] array, int numero) {
		return search(array, numero, 0, array.length - 1);
	}

	// Método recursivo
	private boolean search(int[] array, int numero, int left, int right) {
		if (left > right) {
			return false; // Não encontrado
		}

		int mid = left + (right - left) / 2;

		if (array[mid] == numero) {
			return true; // Elemento encontrado
		} else if (array[mid] < numero) {
			return search(array, numero, mid + 1, right); // Procura na metade direita
		} else {
			return search(array, numero, left, mid - 1); // Procura na metade esquerda
		}
	}
}
