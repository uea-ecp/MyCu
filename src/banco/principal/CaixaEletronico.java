package banco.principal;

import java.util.Scanner;
import banco.conta.ContaCorrente;
import banco.transacao.Transacao;
import banco.usuario.Cliente;

/**
 * Classe principal do sistema. Representa o Caixa Eletronico
 * 
 * @author Daniela e Carlos Alberto
 */
public class CaixaEletronico {
	// constantes para gerenciar fluxo de transacoes
	private static final int FINALIZA = 0; // Encerra o programa
	private static final int PRIMEIR0_FLUXO = 1; // Fluxo da AP2 questao 2
	private static final int CONTINUA = 4; // Segue operando transacoes

	/**
	 * Metodo main, principal do sistema
	 * 
	 * @param args padrao
	 */
	public static void main(String[] args) {
		int operandoTransacoes = PRIMEIR0_FLUXO;

		System.out.println("Seja bem-vindo(a) a cooperativa de credito mycu");
		System.out.println();

		Scanner ler = new Scanner(System.in);

		// Entrada de dados de Cliente
		Cliente cliente = leituraDadosCliente(ler);

		// Entrada de dados de Conta Corrente
		ContaCorrente contacorrente = leituraDadosConta(ler);

		// Atribui conta corrente ao cliente
		cliente.setConta(contacorrente);

		// Realiza Transacoes
		while (operandoTransacoes >= PRIMEIR0_FLUXO) {
			if (operandoTransacoes == PRIMEIR0_FLUXO) {
				// Questao 2, segue o fluxo:
				realizaDeposito(cliente, ler);
				realizaSaque(cliente, ler);
				mostrarExtrato(cliente);
			}

			// Continua para realizar mais transacoes, apenas para demonstracao
			System.out.println();
			System.out.println();
			System.out.println("O que deseja fazer agora?");
			System.out.println("0 - Sair		1 - Sacar		2 - Depositar		3 - Obter extrato");

			switch (ler.nextInt()) {
			case 0:
				operandoTransacoes = FINALIZA;
				break;
			case 1: {
				realizaSaque(cliente, ler);
				break;
			}
			case 2: {
				realizaDeposito(cliente, ler);
				break;
			}
			case 3: {
				mostrarExtrato(cliente);
				break;
			}
			}

			if (operandoTransacoes > FINALIZA) {
				operandoTransacoes = CONTINUA;
			}
		}
		ler.close();
	}

	/**
	 * Imprime dados bancarios e extrato da conta
	 * 
	 * @param cliente objeto cliente com seus dados e conta corrente
	 */
	public static void mostrarExtrato(Cliente cliente) {
		System.out.println();
		System.out.println("                        EXTRATO DA CONTA CORRENTE");
		System.out.println("                            AUTOATENDIMENTO");

		System.out.println("AGENCIA:  " + cliente.getConta().getNumeroAgencia() + "  CONTA-CORRENTE: "
				+ cliente.getConta().getNumeroConta());
		System.out.println("CLIENTE:  " + cliente.getNome());

		System.out.println("+-----------------+--------------------+---------------------+----------------+");
		System.out.println("  📆 DATA E HORA     💲 TRANSACAO    	      💵 VALOR              💵 SALDO ");
		System.out.println("+-----------------+--------------------+---------------------+----------------+");

		for (Transacao transacao : cliente.getConta().obterExtrato()) {
			System.out.print(" " + transacao.getDataHora() + "           " + transacao.getOperacao());

			String espacos = "                ";

			if (transacao.getOperacao().equals("Deposito")) {
				System.out.print(espacos + "＋ ");
			} else {
				espacos += "   ";
				System.out.print(espacos + "﹣ ");
			}

			System.out.print(transacao.getValor());

			System.out.println(espacos + transacao.getNovoSaldo());
			System.out.println();
		}

		System.out.println("Saldo Atual: R$ " + cliente.getConta().getSaldo());
	}

	/**
	 * Imprime o mensagem do status do saque
	 * 
	 * @param saldo  valor do saldo da conta corrente do cliente
	 * @param status statud do saque
	 */
	public static void mostraMensagemSaque(float saldo, boolean status) {
		if (status) {
			System.out.println("Sacado com sucesso 🤑");
		} else
			System.out.println(
					"Perai, não tem como você sacar esse valor. Você só tem R$ " + saldo + ". Quer ficar devendo? 🤨");
	}

	/**
	 * Interage com usuario para receber seus dados de cliente
	 * 
	 * @param ler leitor (scanner) para receber entradas
	 * @return Cliente nova instancia de cliente
	 */
	public static Cliente leituraDadosCliente(Scanner ler) {
		String nome, endereco, rg;
		System.out.print("Digite o seu nome: ");
		nome = ler.nextLine();
		System.out.print("Digite o seu rg: ");
		rg = ler.nextLine();

		System.out.print("Digite o seu endereco: ");
		endereco = ler.nextLine();

		return new Cliente(nome, rg, endereco);
	}

	/**
	 * Interage com cliente para receber dados da conta
	 * 
	 * @param ler leitor (scanner) para receber entradas
	 * @return ContaCorrente nova instancia de conta corrente
	 */
	public static ContaCorrente leituraDadosConta(Scanner ler) {
		int numeroConta, numeroAgencia;

		System.out.print("Digite o numero da sua conta bancaria: ");
		numeroConta = ler.nextInt();

		System.out.print("Digite o numero da sua agencia: ");
		numeroAgencia = ler.nextInt();

		return new ContaCorrente(numeroConta, numeroAgencia);
	}

	/**
	 * Interage com o cliente para realizar saque. Se mal sucedido, informa o
	 * cliente
	 * 
	 * @param cliente objeto cliente com seus dados e conta corrente
	 * @param ler     leitor (scanner) das entradas
	 */
	public static void realizaSaque(Cliente cliente, Scanner ler) {
		System.out.print("Digite um valor para sacar em sua conta: ");
		boolean statusDoSaque = cliente.getConta().sacar(ler.nextFloat());
		mostraMensagemSaque(cliente.getConta().getSaldo(), statusDoSaque);
	}

	/**
	 * Interage com o cliente para realizar deposito
	 * 
	 * @param cliente objeto cliente com seus dados e conta corrente
	 * @param ler     leitor (scanner) das entradas
	 */
	public static void realizaDeposito(Cliente cliente, Scanner ler) {
		System.out.print("Digite um valor para depositar em sua conta: ");
		cliente.getConta().depositar(ler.nextFloat());
	}
}