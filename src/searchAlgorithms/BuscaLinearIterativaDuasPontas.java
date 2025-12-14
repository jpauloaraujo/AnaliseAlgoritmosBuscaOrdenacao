package searchAlgorithms;

import interfaces.ISearchingAlgorithm;
import model.Estudante;

public class BuscaLinearIterativaDuasPontas implements ISearchingAlgorithm {

	@Override
	public boolean search(Estudante[] dados, Estudante estudante) {
		int left = 0;
		int right = dados.length - 1;

		while (left <= right) {
			if (dados[left].compareTo(estudante) == 0 || dados[right].compareTo(estudante) == 0) {
				return true; // Elemento encontrado
			}
			left++;
			right--;
		}
		return false; // Não encontrado
	}

	@Override
	public boolean search(int[] array, int numero) {
		int left = 0;
		int right = array.length - 1;

		while (left <= right) {
			if (array[left] == numero || array[right] == numero) {
				return true; // Elemento encontrado
			}
			left++;
			right--;
		}
		return false; // Não encontrado
	}
}
