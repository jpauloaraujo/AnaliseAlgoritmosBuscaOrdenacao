package interfaces;

import model.Estudante;

public interface ISearchingAlgorithm {
	
	boolean search(Estudante[] dados, Estudante estudante);
	
	boolean search(int[] array, int numero);

}
