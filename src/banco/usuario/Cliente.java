package banco.usuario;

import banco.conta.ContaCorrente;

public class Cliente {
	private String nome;
	private String rg;
	private String endereco;

	// Composicao: Cliente e classe mais forte
	private ContaCorrente contaCorrente;

	/**
	 * Atribui estado (constroi) um Cliente. 
	 * @param nome  nome do cliente
	 * @param rg  numero do registro geral
	 * @param endereco  endereco de moradia
	 * */
	public Cliente(String nome, String rg, String endereco) {
		this.nome = nome;
		this.rg = rg;
		this.endereco = endereco;
	}

	/* Metodos de Encapsulamento - Getters e Setters */
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public ContaCorrente getConta() {
		return contaCorrente;
	}

	public void setConta(ContaCorrente contaCorrente) {
		this.contaCorrente = contaCorrente;
	}
}
