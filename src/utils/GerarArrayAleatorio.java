package utils;

import java.util.HashSet;
import java.util.Set;

import model.Estudante;

public class GerarArrayAleatorio {

	public static int[] gerarArrayInteiros(int tamanho, int maxValor) {
		int[] array = new int[tamanho];
		for (int i = 0; i < tamanho; i++) {
			array[i] = (int) (Math.random() * maxValor);
		}
		return array;
	}

	private static Set<Integer> matriculasUsadas = new HashSet<>();	
	
	public static Estudante[] gerarArrayEstudantes(int tamanho) {
		Estudante[] array = new Estudante[tamanho];
		for (int i = 0; i < tamanho; i++) {
			array[i] = gerarEstudanteAleatorio();
		}
		return array;
	}

	private static Estudante gerarEstudanteAleatorio() {
		String[] prenomes = { "Ana", "Bruno", "Carlos", "Daniela", "Eduardo", "Fernanda", "Gabriel", "Helena", "Igor",
				"Juliana", "Kevin", "Larissa", "Marcelo", "Natália", "Otávio", "Patrícia", "Quirino", "Rafael", "Sofia",
				"Tiago", "Ursula", "Valter", "Wesley", "Ximena", "Yago", "Zara", "Amanda", "Bruna", "Caio", "Diego",
				"Eliane", "Fabio", "Gustavo", "Hugo", "Isabela", "João", "Karla", "Lucas", "Mariana", "Nicolas",
				"Olivia", "Paulo", "Pedro", "Queila", "Renato", "Sandra", "Thiago", "Ubirajara", "Vanessa", "William",
				"Xuxa", "Yasmin", "Zélia" };
		String[] sobrenomes = { "Silva", "Santos", "Oliveira", "Araujo", "Souza", "Pereira", "Lima", "Gomes", "Ribeiro",
				"Almeida", "Costa" };

		// gerar um prenome aleatório
		String prenomeAleatorio = prenomes[(int) (Math.random() * prenomes.length)];
		// gerar um sobrenome aleatório
		String sobrenomeAleatorio = sobrenomes[(int) (Math.random() * sobrenomes.length)];
		// combinar prenome + sobrenome
		String nomeCompleto = prenomeAleatorio + " " + sobrenomeAleatorio;
		
		int nota = (int) (Math.random() * 11); // 0 a 10 inclusive
		
		//matricula no intervalo de 1.000.000 a 9.999.999
		int matricula;
	    do {
	        matricula = 1_000_000 + (int) (Math.random() * 9_000_000);
	    } while (matriculasUsadas.contains(matricula)); //Garante que a matrícula é única

	    matriculasUsadas.add(matricula);

		return new Estudante(nomeCompleto, nota, matricula);
	}
}
