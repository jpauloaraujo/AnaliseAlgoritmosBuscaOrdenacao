package analise;

import java.util.Arrays;
import java.util.Random;

import interfaces.ISortingAlgorithm;
import sortingAlgorithms.QuickSort;
import sortingAlgorithms.QuickSort_shuffle;
// import sortingAlgorithms.CountingSort; 
// Importe suas outras classes conforme necessário

public class DesempenhoOrdenacaoInteiros {
    public static void main(String[] args) {

        // Parâmetros padrão
        int tamanhoArray = 500_000;
        int quantidadeExecucoes = 20;
        int warmUp = 5;
        // Opções: ordenado, inverso, aleatorio
        String cenarioEscolhido = "inverso"; 

        // Se o usuário passou argumentos, sobrescreve os padrões
        if (args.length >= 1) tamanhoArray = Integer.parseInt(args[0]);
        if (args.length >= 2) quantidadeExecucoes = Integer.parseInt(args[1]);
        if (args.length >= 3) warmUp = Integer.parseInt(args[2]);
        if (args.length >= 4) cenarioEscolhido = args[3].toLowerCase();
        
        // Validação
        if (warmUp >= quantidadeExecucoes) {
            throw new IllegalArgumentException(
                    "WARM_UP (" + warmUp + ") deve ser menor que QUANTIDADE_EXECUCOES (" + quantidadeExecucoes + ").");
        }

        System.out.printf("Configuração (INTEIROS): Tamanho=%d, Execuções=%d, WarmUp=%d, Cenário=%s%n", 
                tamanhoArray, quantidadeExecucoes, warmUp, cenarioEscolhido);

        // Instancia o algoritmo de ordenação desejado
        ISortingAlgorithm sortingAlgorithm = new QuickSort_shuffle(); 
        // ISortingAlgorithm sortingAlgorithm = new CountingSort(); // Exemplo para trocar depois

        // Gera o vetor base de inteiros
        // DICA: Para CountingSort ser eficiente, o bound (limite) dos números não pode ser gigantesco
        int[] arrayBase = gerarArrayInteiros(tamanhoArray, tamanhoArray * 10);
        int[] arrayParaTeste = null;

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
                inverterArray(arrayParaTeste); // Inverte manualmente (Collections.reverse não aceita int[])
                break;
                
            case "aleatorio":
            default:
                arrayParaTeste = Arrays.copyOf(arrayBase, arrayBase.length);
                embaralharArray(arrayParaTeste); // Shuffle manual para int[]
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

            // Cria uma cópia para o teste atual
            int[] copiaExecucao = Arrays.copyOf(arrayParaTeste, arrayParaTeste.length);

            long tempoInicial = System.nanoTime();
            
            // Chama o método da interface específico para int[]
            sortingAlgorithm.sort(copiaExecucao);
            // Dual-Pivot QuickSort
//            Arrays.sort(copiaExecucao); 
            
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

    // --- MÉTODOS AUXILIARES PARA PRIMITIVOS (int) ---

    // Gera array aleatório
    private static int[] gerarArrayInteiros(int tamanho, int limiteValor) {
        Random random = new Random();
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = random.nextInt(limiteValor);
        }
        return array;
    }

    // Inverte um array de int (Substituto para Collections.reverse)
    private static void inverterArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    // Embaralha um array de int (Fisher-Yates)
    private static void embaralharArray(int[] array) {
        Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Troca simples
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }
}