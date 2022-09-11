package banco.conta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import banco.transacao.Transacao;

/**
 * Representa uma ciinta corrente
 * 
 * @author Daniela e Carlos Alberto
 */
public class ContaCorrente {
	private float saldo;
	private int numeroConta;
	private int numeroAgencia;

	// Composicao: ContaCorrente e a classe mais forte
	// Extrato da conta
	private List<Transacao> transacoes = new ArrayList<>();

	/**
	 * Atribui estado (constroi) uma ContaCorrente.
	 * 
	 * @param numeroConta   numero da conta corrente
	 * @param numeroAgencia numero da agebcia do cliente
	 */
	public ContaCorrente(int numeroConta, int numeroAgencia) {
		this.saldo = 0.0f;
		this.numeroConta = numeroConta;
		this.numeroAgencia = numeroAgencia;
	}

	/* Metodos de operacoes bancarias */

	/**
	 * Opera o deposito com o incremento do novo valor ao saldo da conta.
	 * 
	 * @param v o valor float para efetuar o deposito
	 */
	public void depositar(float v) {
		this.saldo += v;
		Transacao t = new Transacao(saldo, "Deposito", v);
		registrarTransacao(t);
	}

	/**
	 * Opera o saque. O saque acontece desde que o saldo não fique negativo.
	 * 
	 * @param v o valor float para efetuar o saque
	 * @return status da operacao saque, se foi realizado com sucesso ou não
	 */
	public boolean sacar(float v) {
		float novoSaldo = saldo - v;

		if (novoSaldo >= 0) {
			saldo = novoSaldo;
			Transacao transacao = new Transacao(saldo, "Saque", v);
			registrarTransacao(transacao);
			return true;
		}

		return false;
	}

	/**
	 * Recupera a listagem de transações.
	 * 
	 * @return transacoes
	 */
	public List<Transacao> obterExtrato() {
		return transacoes;
	}

	/**
	 * Registra uma transação de saque. Registro acontece desde que saque seja
	 * efetuado com sucesso.
	 * 
	 * @param t o objeto transacao a ser registrado
	 */
	public void registrarTransacao(Transacao t) {
		Date dataHoraAtual = new Date();
		String dataHora = new SimpleDateFormat("dd/MM HH:mm").format(dataHoraAtual);
		t.setDataHora(dataHora);

		transacoes.add(t);
	}

	/* Metodos de Encapsulamento - Getters e Setters */
	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public int getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(int numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}
}