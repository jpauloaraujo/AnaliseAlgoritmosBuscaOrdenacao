package analise;

import java.util.Arrays;
import java.util.Collections;

import interfaces.ISortingAlgorithm;
import model.Estudante;
import sortingAlgorithms.BubbleSort;
import sortingAlgorithms.BubbleSortOtimizado;
import sortingAlgorithms.CountingSort;
import sortingAlgorithms.MergeSort;
import utils.GerarArrayAleatorio;
import utils.Shuffle;

public class DesempenhoAlgoritmoOrdenacao {
	public static void main(String[] args) {
		
		// Parâmetros padrão
		int tamanhoArray = 20_000;
		int quantidadeExecucoes = 20;
		int warmUp = 5;
		
		// Se o usuário passou argumentos, sobrescreve os padrões
		if (args.length >= 1)
			tamanhoArray = Integer.parseInt(args[0]);
		if (args.length >= 2)
			quantidadeExecucoes = Integer.parseInt(args[1]);
		if (args.length >= 3)
			warmUp = Integer.parseInt(args[2]);
		
		// Validação
		if (warmUp >= quantidadeExecucoes) {
			throw new IllegalArgumentException(
					"WARM_UP (" + warmUp + ") deve ser menor que QUANTIDADE_EXECUCOES (" + quantidadeExecucoes + ").");
		}

		System.out.printf("Executando benchmark com: tamanhoArray=%d, execucoes=%d, warmUp=%d%n", tamanhoArray,
				quantidadeExecucoes, warmUp);
		
		// Instancia o algoritmo de ordenação desejado
		ISortingAlgorithm sortingAlgorithm = new BubbleSort();
		Estudante[] estudantes = GerarArrayAleatorio.gerarArrayEstudantes(tamanhoArray);

		// Cenários
		Estudante[] aleatorio = Arrays.copyOf(estudantes, estudantes.length);
		Shuffle.shuffle(aleatorio); // Embaralha para cenário aleatório

		Estudante[] crescente = Arrays.copyOf(estudantes, estudantes.length);
		Arrays.sort(crescente); // Ordena crescente

		Estudante[] decrescente = Arrays.copyOf(crescente, crescente.length);
		Collections.reverse(Arrays.asList(decrescente)); // Ordena decrescente

		// Testa cada cenário
		String[] cenarios = { "Crescente", "Aleatório",  "Decrescente" };
		Estudante[][] arraysCenario = { crescente, aleatorio, decrescente };

		for (int c = 0; c < cenarios.length; c++) {
			System.out.println("\n=== Cenário: " + cenarios[c] + " ===");

			double somaTempos = 0.0;

			for (int i = 0; i < quantidadeExecucoes; i++) {

				// Cria uma cópia do cenário atual
				Estudante[] copia = Arrays.copyOf(arraysCenario[c], arraysCenario[c].length);

				long tempoInicial = System.nanoTime();
				sortingAlgorithm.sort(copia);
				long tempoFinal = System.nanoTime();

				double tempoSegundos = (tempoFinal - tempoInicial) / 1_000_000_000.0;

				if (i >= warmUp) { // acumula apenas execuções válidas
					somaTempos += tempoSegundos;
				}

				System.out.printf("Execução %d: %.6f segundos %s%n", i + 1, tempoSegundos,
						(i < warmUp ? "(warm-up)" : ""));
			}

			int execucoesValidas = quantidadeExecucoes - warmUp;
			double media = somaTempos / execucoesValidas;

			System.out.println("\nAlgoritmo: " + sortingAlgorithm.getClass().getSimpleName());
			System.out.printf("Tempo médio após warm-up (%d execuções): %.6f segundos%n", execucoesValidas, media);
		}
	}
}
