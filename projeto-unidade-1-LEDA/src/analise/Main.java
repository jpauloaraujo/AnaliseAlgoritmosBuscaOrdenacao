package analise;

import java.util.Arrays;

import interfaces.ISearchingAlgorithm;
import interfaces.ISortingAlgorithm;
import model.Estudante;
import searchAlgorithms.BuscaLinearIterativa;
import searchAlgorithms.BuscaLinearRecursiva;
import sortingAlgorithms.BubbleSort;
import sortingAlgorithms.BubbleSortOtimizado;
import sortingAlgorithms.CountingSort;
import sortingAlgorithms.InsertionSort;
import sortingAlgorithms.MergeSort;
import sortingAlgorithms.QuickSort;
import sortingAlgorithms.SelectionSort;
import sortingAlgorithms.SelectionSortEstavel;
import utils.GerarArrayAleatorio;
import utils.Shuffle;

public class Main {

	public static void main(String[] args) {

		ISortingAlgorithm sorter = new CountingSort(); 
		ISearchingAlgorithm searcher = new BuscaLinearRecursiva();

		Estudante[] estudantes = GerarArrayAleatorio.gerarArrayEstudantes(5);
		for (Estudante estudante : estudantes) {
			System.out.println(estudante);
		}
		
		System.out.println("----------------------------------");
		Shuffle.shuffle(estudantes);
		sorter.sort(estudantes);
		System.out.println("Array ordenado pelo algoritmo " + sorter.getClass().getSimpleName() + ":");
		for (Estudante estudante : estudantes) {
			System.out.println(estudante);
		}
		
		System.out.println("-------------------");
		int[] numeros = {1,6,-25,0};
		System.out.println(searcher.search(numeros, 0));
		System.out.println("-------------------");
//		Estudante e1 = new Estudante("Paulo", 10, 2024108);
		Estudante e1 = estudantes[0];

		System.out.println(searcher.search(estudantes, e1));
	}
}
