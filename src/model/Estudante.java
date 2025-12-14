package model;

public class Estudante implements Comparable<Estudante>{

	private String nome;
	private int nota;
	private int matricula;
	
	public Estudante(String nome, int nota, int matricula) {
		this.nome = nome;
		this.nota = nota;
		this.matricula = matricula;
	}
	
	public int getNota() {
		return nota;
	}
	
	@Override
	public int compareTo(Estudante outro) {
		//Primeiro critério de ordenação de Estudantes: nota (ordem decrescente)
		if (this.nota > outro.nota) return -1; // A nota maior fica na esquerda
		if (this.nota < outro.nota) return 1; // A nota menor fica na direita
		
		//Segundo critério de ordenação: nome
		int cmpNome = this.nome.compareTo(outro.nome);
        if (cmpNome != 0) return cmpNome;
		
		//Terceiro critério de ordenação: matrícula
        return Integer.compare(this.matricula, outro.matricula);
	}
	
	@Override
	public String toString() {
	    return nome + " | Nota: " + nota + " | Matrícula: " + matricula;
	}

}
