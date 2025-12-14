package analise;

import java.util.Arrays;

import interfaces.ISearchingAlgorithm;
import model.Estudante;
import searchAlgorithms.BuscaLinearIterativa;
import searchAlgorithms.BuscaLinearRecursiva;
import searchAlgorithms.BuscaBinariaIterativa;
import searchAlgorithms.BuscaBinariaRecursiva;
import searchAlgorithms.BuscaLinearIterativaDuasPontas;
import utils.GerarArrayAleatorio;

public class DesempenhoBuscaEstudantes {

    public static void main(String[] args) {

        // --- 1. Parâmetros e Configurações ---
        int tamanhoArray = 500_000;
        int quantidadeExecucoes = 20;
        int warmUp = 5;
        // Opções: melhor, medio, pior
        String cenarioEscolhido = "pior";

        if (args.length >= 1) tamanhoArray = Integer.parseInt(args[0]);
        if (args.length >= 2) quantidadeExecucoes = Integer.parseInt(args[1]);
        if (args.length >= 3) warmUp = Integer.parseInt(args[2]);
        if (args.length >= 4) cenarioEscolhido = args[3].toLowerCase();

        if (warmUp >= quantidadeExecucoes) {
            throw new IllegalArgumentException(
                "WARM_UP (" + warmUp + ") deve ser menor que QUANTIDADE_EXECUCOES (" + quantidadeExecucoes + ")."
            );
        }

        // === ESCOLHA O ALGORITMO AQUI ===
        // Ex: BuscaLinearIterativa, BuscaBinariaRecursiva, BuscaLinearIterativaDuasPontas, etc.
        ISearchingAlgorithm searchingAlgorithm = new BuscaLinearIterativaDuasPontas();

        System.out.printf(
            "Configuração (Busca): Tamanho=%d, Execuções=%d, WarmUp=%d, Cenário=%s%n",
            tamanhoArray, quantidadeExecucoes, warmUp, cenarioEscolhido
        );

        // --- 2. Preparação dos Dados ---
        // Gera e ordena o vetor (Ordenação é pré-requisito obrigatório para comparações justas com Binária)
        Estudante[] arrayBase = GerarArrayAleatorio.gerarArrayEstudantes(tamanhoArray);
        Arrays.sort(arrayBase); 

        // --- 3. Definição do Alvo (Chave de Busca) ---
        Estudante chaveBusca;
        String nomeAlgoritmo = searchingAlgorithm.getClass().getSimpleName();

        switch (cenarioEscolhido) {
            case "melhor":
                // LÓGICA ADAPTATIVA:
                // Se for Binária, o melhor caso é o MEIO.
                // Se for Linear (qualquer versão), o melhor caso é o INÍCIO.
                if (nomeAlgoritmo.toLowerCase().contains("binaria")) {
                    chaveBusca = arrayBase[arrayBase.length / 2];
                } else {
                    chaveBusca = arrayBase[0]; 
                }
                break;

            case "pior":
                // Pior caso: Elemento inexistente.
                // Garante que percorra todo o array (Linear) ou toda a altura da árvore (Binária).
                // Ajuste os parâmetros (-1, -1) conforme o construtor da sua classe Estudante.
                chaveBusca = new Estudante("Inexistente", -1, -1);
                break;

            case "medio":
            default:
                // Caso médio: Elemento existente em posição intermediária (evitando o meio exato e as pontas).
                // 1/3 do array é uma boa aproximação estatística para testes simples.
                chaveBusca = arrayBase[arrayBase.length / 3];
                
                if (!cenarioEscolhido.equals("medio")) {
                    System.out.println("Aviso: Cenário não reconhecido. Usando 'medio' como padrão.");
                }
                break;
        }

        // --- 4. Execução do Benchmark ---
        System.out.println("\n=== Iniciando Benchmark de Busca ===");
        System.out.println("Algoritmo: " + nomeAlgoritmo);
        // Exibe se estamos buscando algo que existe ou não, para clareza no log
        System.out.println("Cenário de Teste: " + cenarioEscolhido.toUpperCase());

        double somaTempos = 0.0;

        for (int i = 0; i < quantidadeExecucoes; i++) {

            long inicio = System.nanoTime();
            searchingAlgorithm.search(arrayBase, chaveBusca);
            long fim = System.nanoTime();

            double tempo = (fim - inicio) / 1_000_000_000.0;

            if (i >= warmUp) {
                somaTempos += tempo;
            }

            System.out.printf(
                "Execução %d: %.9f segundos %s%n",
                i + 1,
                tempo,
                (i < warmUp ? "(warm-up - descartado)" : "")
            );
        }

        // --- 5. Resultados ---
        int execucoesValidas = quantidadeExecucoes - warmUp;
        double media = somaTempos / execucoesValidas;

        System.out.println("--------------------------------------------------");
        System.out.printf(
            "Tempo MÉDIO após warm-up (%d execuções): %.9f segundos%n",
            execucoesValidas,
            media
        );
        System.out.println("--------------------------------------------------");
    }
}