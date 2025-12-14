package searchAlgorithms;

import interfaces.ISearchingAlgorithm;
import model.Estudante;

public class BuscaLinearIterativa implements ISearchingAlgorithm {

	@Override
	public boolean search(Estudante[] dados, Estudante estudante) {
		int n = dados.length;
		for (int i = 0; i < n; i++) {
			if (dados[i].compareTo(estudante) == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean search(int[] array, int numero) {
		int n = array.length;
		for (int i = 0; i < n; i++) {
			if (array[i] == numero) {
				return true;
			}
		}
		return false;
	}
}
