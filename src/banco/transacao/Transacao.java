package banco.transacao;

public class Transacao {
	private String operacao;
	private float valor;
	private String dataHora;
	private float novoSaldo;

	/**
	 * Atribui estado (constroi) uma Transacao. 
	 * @param novoSaldo  valor float para registrar o saldo apos a operacao
	 * @param operacao  tipo de operacao
	 * @param valor  o valor a ser processado na transacao
	 * */
	public Transacao(float novoSaldo, String operacao, float valor) {
		this.novoSaldo = novoSaldo;
		this.operacao = operacao;
		this.valor = valor;
	}

	/* Metodos de Encapsulamento - Getters e Setters */
	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public float getNovoSaldo() {
		return novoSaldo;
	}

	public void setNovoSaldo(float novoSaldo) {
		this.novoSaldo = novoSaldo;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
}