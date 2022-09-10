package banco.principal;

import java.util.Scanner;
import banco.conta.ContaCorrente;
import banco.transacao.Transacao;
import banco.usuario.Cliente;

public class CaixaEletronico {
	// constantes para gerenciar fluxo de transacoes
	private static final int FINALIZA = 0;
	private static final int PRIMEIR0_FLUXO = 1;
	private static final int CONTINUA = 4;
	
	public static void main(String[] args) {
		int operandoTransacoes = PRIMEIR0_FLUXO;
		boolean statusDoSaque;
		
		System.out.println("Seja bem-vindo(a) a cooperativa de credito mycu");
		System.out.println();

		Scanner ler = new Scanner(System.in);

		/* Entrada de dados de Cliente */
		String nome, endereco, rg;
		System.out.print("Digite o seu nome: ");
		nome = ler.nextLine();
		System.out.print("Digite o seu rg: ");
		rg = ler.nextLine();

		System.out.print("Digite o seu endereco: ");
		endereco = ler.nextLine();

		Cliente cliente = new Cliente(nome, rg, endereco);

		/* Entrada de dados de Conta Corrente */
		int numeroConta, numeroAgencia;

		System.out.print("Digite o numero da sua conta bancaria: ");
		numeroConta = ler.nextInt();

		System.out.print("Digite o numero da sua agencia: ");
		numeroAgencia = ler.nextInt();

		ContaCorrente contacorrente = new ContaCorrente(numeroConta, numeroAgencia);

		// Atribui conta corrente ao cliente
		cliente.setConta(contacorrente);

		while (operandoTransacoes >= PRIMEIR0_FLUXO) {
			
			// segue fluxo solicitado na AP2
			if (operandoTransacoes == PRIMEIR0_FLUXO) {
				/* Depositar na conta corrente */
				System.out.print("Digite um valor para depositar em sua conta: ");
				cliente.getConta().depositar(ler.nextFloat());

				/* Sacar da conta corrente */
				System.out.print("Digite um valor para sacar em sua conta: ");
				statusDoSaque = cliente.getConta().sacar(ler.nextFloat());

				// exibe mensagem se saldo insuficiente
				if (!statusDoSaque) {
					mostraMensagemSaque(cliente.getConta().getSaldo(), statusDoSaque);
				}

				// imprime extrato
				mostrarExtrato(cliente);
			}

//			Segue o fluxo para realizar mais transacoes, apenas para demonstracao
			System.out.println();
			System.out.println();
			System.out.println("O que deseja fazer agora?");
			System.out.println("0 - Sair		1 - Sacar		2 - Depositar		3 - Obter extrato");
			
			switch (ler.nextInt()) {
			case 0:
				operandoTransacoes = FINALIZA;
				break;
			case 1: {
				System.out.print("Digite um valor para sacar em sua conta: ");
				statusDoSaque = cliente.getConta().sacar(ler.nextFloat());
				mostraMensagemSaque(cliente.getConta().getSaldo(), statusDoSaque);
				break;
			}
			case 2: {
				System.out.print("Digite um valor para depositar em sua conta: ");
				cliente.getConta().depositar(ler.nextFloat());
				break;
			}
			case 3: {
				mostrarExtrato(cliente);
				break;
			}
			}
			
			if(operandoTransacoes > FINALIZA) {
				operandoTransacoes = CONTINUA;
			}
		}
		ler.close();
	}

	/**
	 * Imprime extrato da conta
	 * 
	 * @param cliente
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
	 * @param saldo valor do saldo da conta corrente do cliente
	 */
	public static void mostraMensagemSaque(float saldo, boolean status) {
		if (status) {
			System.out.println("Sacado com sucesso 🤑");
		} else
			System.out.println(
					"Perai, não tem como você sacar esse valor. Você só tem R$ " + saldo + ". Quer ficar devendo? 🤨");
	}
}