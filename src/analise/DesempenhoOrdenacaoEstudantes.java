package analise;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import interfaces.ISortingAlgorithm;
import model.Estudante;
import sortingAlgorithms.BubbleSort;
import sortingAlgorithms.BubbleSortOtimizado;
import sortingAlgorithms.CountingSort;
import sortingAlgorithms.InsertionSort;
import sortingAlgorithms.MergeSort;
import sortingAlgorithms.QuickSort_shuffle;
import sortingAlgorithms.SelectionSort;
import sortingAlgorithms.SelectionSortEstavel;
// import sortingAlgorithms.BubbleSortOtimizado;
// import sortingAlgorithms.CountingSort;
// import sortingAlgorithms.MergeSort;
import utils.GerarArrayAleatorio;
import utils.Shuffle;

public class DesempenhoOrdenacaoEstudantes {
    public static void main(String[] args) {

        // Parâmetros padrão
        int tamanhoArray = 500_000;
        int quantidadeExecucoes = 20;
        int warmUp = 5;
        // Opções: ordenado, inverso, aleatorio
        String cenarioEscolhido = "inverso"; 
        

        // Se o usuário passou argumentos, sobrescreve os padrões
        // Ordem: [0]=tamanho, [1]=execuções, [2]=warmup, [3]=cenario
        if (args.length >= 1) tamanhoArray = Integer.parseInt(args[0]);
        if (args.length >= 2) quantidadeExecucoes = Integer.parseInt(args[1]);
        if (args.length >= 3) warmUp = Integer.parseInt(args[2]);
        if (args.length >= 4) cenarioEscolhido = args[3].toLowerCase();
        
        // Validação
        if (warmUp >= quantidadeExecucoes) {
            throw new IllegalArgumentException(
                    "WARM_UP (" + warmUp + ") deve ser menor que QUANTIDADE_EXECUCOES (" + quantidadeExecucoes + ").");
        }

        System.out.printf("Configuração (Estudantes): Tamanho=%d, Execuções=%d, WarmUp=%d, Cenário=%s%n", 
                tamanhoArray, quantidadeExecucoes, warmUp, cenarioEscolhido);

        // Instancia o algoritmo de ordenação desejado
        ISortingAlgorithm sortingAlgorithm = new InsertionSort(); 

        // Gera o vetor base de estudantes
        Estudante[] arrayBase = GerarArrayAleatorio.gerarArrayEstudantes(tamanhoArray);
        Estudante[] arrayParaTeste = null;

        // === PREPARAÇÃO DO CENÁRIO ESCOLHIDO ===
        System.out.println("Preparando cenário: " + cenarioEscolhido + "...");
        
        switch (cenarioEscolhido) {
            case "ordenado":
                arrayParaTeste = Arrays.copyOf(arrayBase, arrayBase.length);
                Arrays.sort(arrayParaTeste);
                break;
                
            case "inverso":
            case "invertido":
                arrayParaTeste = Arrays.copyOf(arrayBase, arrayBase.length);
                Arrays.sort(arrayParaTeste); // Ordena primeiro
                List<Estudante> lista = Arrays.asList(arrayParaTeste);
                Collections.reverse(lista); // Depois inverte
                break;
                
            case "aleatorio":
            default:
                arrayParaTeste = Arrays.copyOf(arrayBase, arrayBase.length);
                Shuffle.shuffle(arrayParaTeste);
                if (!cenarioEscolhido.equals("aleatorio")) {
                    System.out.println("Aviso: Cenário não reconhecido. Usando 'aleatorio' como padrão.");
                }
                break;
        }

        // === EXECUÇÃO DOS TESTES ===
        System.out.println("\n=== Iniciando Benchmark: " + cenarioEscolhido.toUpperCase() + " ===");
        System.out.println("Algoritmo: " + sortingAlgorithm.getClass().getSimpleName());

        double somaTempos = 0.0;

        for (int i = 0; i < quantidadeExecucoes; i++) {

            // Cria uma cópia fresca para o teste atual (para não reordenar o que já foi ordenado na iteração anterior)
            Estudante[] copiaExecucao = Arrays.copyOf(arrayParaTeste, arrayParaTeste.length);

            long tempoInicial = System.nanoTime();
            sortingAlgorithm.sort(copiaExecucao);
//            Arrays.sort(copiaExecucao); // TimSort
            long tempoFinal = System.nanoTime();

            double tempoSegundos = (tempoFinal - tempoInicial) / 1_000_000_000.0;

            if (i >= warmUp) { // acumula apenas execuções válidas
                somaTempos += tempoSegundos;
            }

            System.out.printf("Execução %d: %.6f segundos %s%n", i + 1, tempoSegundos,
                    (i < warmUp ? "(warm-up - descartado)" : ""));
        }

        int execucoesValidas = quantidadeExecucoes - warmUp;
        double media = somaTempos / execucoesValidas;

        System.out.println("--------------------------------------------------");
        System.out.printf("Tempo MÉDIO após warm-up (%d execuções): %.6f segundos%n", execucoesValidas, media);
        System.out.println("--------------------------------------------------");
    }
}