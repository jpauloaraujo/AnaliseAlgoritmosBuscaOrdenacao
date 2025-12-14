package searchAlgorithms;

import interfaces.ISearchingAlgorithm;
import model.Estudante;

public class BuscaBinariaIterativa implements ISearchingAlgorithm {

	@Override
	public boolean search(Estudante[] dados, Estudante estudante) {
		int left = 0;
		int right = dados.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (dados[mid].compareTo(estudante) == 0) {
				return true; // Elemento encontrado
			} else if (dados[mid].compareTo(estudante) < 0) {
				left = mid + 1; // Procura na metade direita
			} else {
				right = mid - 1; // Procura na metade esquerda
			}
		}
		return false; // Não encontrado
	}

	@Override
	public boolean search(int[] array, int numero) {
		int left = 0;
		int right = array.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (array[mid] == numero) {
				return true; // Elemento encontrado
			} else if (array[mid] < numero) {
				left = mid + 1; // Procura na metade direita
			} else {
				right = mid - 1; // Procura na metade esquerda
			}
		}
		return false; // Não encontrado
	}
}
