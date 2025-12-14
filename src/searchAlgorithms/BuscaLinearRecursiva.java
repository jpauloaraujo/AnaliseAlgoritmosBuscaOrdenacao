package searchAlgorithms;

import interfaces.ISearchingAlgorithm;
import model.Estudante;

public class BuscaLinearRecursiva implements ISearchingAlgorithm {

	@Override
	public boolean search(Estudante[] dados, Estudante estudante) {
		return search(dados, estudante, 0);
	}

	private static boolean search(Estudante[] dados, Estudante estudante, int i) {
		if (i >= dados.length)
			return false;
		if (dados[i].compareTo(estudante) == 0)
			return true;
		return search(dados, estudante, i + 1);
	}

	@Override
	public boolean search(int[] array, int numero) {
		return search(array, numero, 0);
	}

	private static boolean search(int[] array, int numero, int i) {
		if (i >= array.length)
			return false; // Caso base: não encontrou
		if (array[i] == numero)
			return true; // Encontrou
		return search(array, numero, i + 1); // Chamada recursiva
	}
}
