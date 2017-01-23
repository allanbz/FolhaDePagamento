import java.util.Scanner;

class Empregado {
	
	public String nome;
	public String endereco;
	
	public int tipo;	 //1 - assalariado / 2 - horista / 3 - comissionado
	public int agenda;	 //1 - mensalmente / 2 - semanalmente / 3 - bi-semanalmente
	public int recebimento; 	//1 - cheque pelos correios / 2 - cheque em maos / 3 - deposito em conta bancaria
	
	public double salarioFixo; 	//para assalariados e comissionados
	public double salarioHora; 	//para horistas
	public double comissao; 	//para comissionados
	
	public double salarioTotal; 	//guarda o salario final do empregado, considerando horas/comissoes
	
	public double taxaSindicato; 	//se o empregado for associado ao sindicato, este atributo sera diferente de zero
	public double impostos; 	//impostos gerais aplicados a todos os empregados
	
	public int contadorDeSextas; 	//usado para contar as sextas que se passam ate o bi-semanal receber
}

public class FolhaDePagamento {

	static int dia, mes, ano, semana; 	//guarda a data atual no sistema de funcionarios
	static int dataDeRecebimento; 	//guarda a data na qual os assalariados receberao (ultimo dia util do mes)
	static int maxDeFuncionarios;	//guarda o numero maximo de funcionarios para controle na adicao
	
	static void dataAtual() {
		System.out.print("\nData atual: ");
		
		switch(semana) {
			case 1:
				System.out.print("domingo, ");
				break;
			case 2:
				System.out.print("segunda-feira, ");
				break;	
			case 3:
				System.out.print("terça-feira, ");
				break;
			case 4:
				System.out.print("quarta-feira, ");
				break;	
			case 5:
				System.out.print("quinta-feira, ");
				break;	
			case 6:
				System.out.print("sexta-feira, ");
				break;	
			case 7:
				System.out.print("sábado, ");
				break;
		}
		
		System.out.printf("%d/%d/%d\n", dia, mes, ano);
	}
		
	static void ultimoDiaUtil() {
		
		int i, limite = 31, controle = semana;
		
		if(mes == 4 || mes == 6 || mes == 9 || mes == 11) {			
			limite = 30;	//se o mes tiver 30 dias, modifica o limite para o loop abaixo
		}
		
		else if(mes == 2) {			
			limite = 28;	//se for fevereiro, caso especial (28 dias)
		}
		
		for(i = dia; i < limite; i++) {			
			controle++; 	//incrementa o dia da semana para controle dos dias uteis
		}
		
		while(controle%7 == 0 || controle%7 == 1) { //enquanto o dia da semana for sabado (resto 0) ou domingo (resto 1)			
			controle--; 	//volta um dia na semana
			i--; 	//volta um dia do mes
		}
		
		dataDeRecebimento = i;	
	}

	static void avancarDia() {
		
		semana++;
		if(semana == 8) {
			semana = 1;
		}
		
		dia++;
		if(mes == 4 || mes == 6 || mes == 9 || mes == 11) {			
			if(dia == 31) {
				dia = 1; //se o mes tiver 30 dias, e o dia chegar em 31, reseta
				mes++; //avanca um mes
			}
		}
		
		else if(mes == 2) {			
			if(dia == 29) {
				dia = 1; //se for fevereiro, e o dia chegar em 29, reseta
				mes++; //avanca um mes
			}
		}
		
		else {
			if(dia == 32) {
				dia = 1; //se o mes tiver 31 dias, e o dia chegar em 32, reseta
				mes++; //avanca um mes
			}
		}
		
		if(mes == 13) {
			mes = 1; //ano novo :)
			ano++;
		}
		
		if(dia == 1) {
			ultimoDiaUtil(); //se estiver no inicio do mes, procura o ultimo dia util do mesmo
		}
	}
	
	static void configuracaoInicial() {
		
		System.out.println("Antes de começar, informe a data atual:\n");
		
		System.out.print("Dia: ");
		Scanner scanner = new Scanner(System.in);
		dia = scanner.nextInt();
		
		System.out.print("\nMês (utilize o número correspondente: 1-Jan/2-Fev/3-Mar/4-Abr/5-Mai/6-Jun/7-Jul/8-Ago/9-Set/10-Out/11-Nov/12-Dez): ");
		mes = scanner.nextInt();
		
		System.out.print("\nAno: ");
		ano = scanner.nextInt();
		
		System.out.print("\nDia da semana (utilize o número correspondente: 1-Dom/2-Seg/3-Ter/4-Qua/5-Qui/6-Sex/7-Sáb): ");
		semana = scanner.nextInt();
		
		System.out.print("\nNúmero máximo de funcionários que pretende adicionar: ");
		maxDeFuncionarios = scanner.nextInt();
		
		System.out.println("\nConfiguração concluída com sucesso!");
		
	}
	
	static void entregaDinheiro(int pagamento, String nome, String endereco, double valor) {
		
		//prints de pagamento de acordo com forma que escolheu pra receber o dinheiro (cheque nos correios, cheque em maos ou deposito em conta)
		switch(pagamento) {
			case 1:
				System.out.printf("O funcionário %s receberá um cheque no valor de R$%.2f, no endereço %s através dos Correios.\n", nome, valor, endereco);
				break;
			case 2:
				System.out.printf("O funcionário %s receberá um cheque no valor de R$%.2f.\n", nome, valor);
				break;
			case 3:
				System.out.printf("O funcionário %s receberá R$%.2f em sua conta bancária.\n", nome, valor);
				break;
		}
	}
	
	static Empregado[] adicionarEmpregado(Empregado[] vetor) {
		
		int i = 0;
		
		while(vetor[i].nome != null && i < maxDeFuncionarios) {
			i++;
		}
		
		if(i == maxDeFuncionarios) {
			System.out.println("Não há vagas!");
			return vetor;
		}
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Nome: ");
		vetor[i].nome = scanner.nextLine();	
		System.out.print("Endereço: ");
		vetor[i].endereco = scanner.nextLine();
		System.out.print("Tipo de Pagamento (1-assalariado /2-horista /3-comissionado): ");
		vetor[i].tipo = scanner.nextInt();
		
		switch(vetor[i].tipo) {
			case 1:
				System.out.print("Salário mensal do funcionário: ");
				vetor[i].salarioFixo = scanner.nextInt();
				vetor[i].agenda = 1;
				break;	
			case 2:
				System.out.print("Salário por hora: ");
				vetor[i].salarioHora = scanner.nextInt();
				vetor[i].agenda = 2;
				break;
			case 3:
				System.out.print("Salário do funcionário: ");
				vetor[i].salarioFixo = scanner.nextInt();
				System.out.print("Taxa de comissão sobre vendas (em %): ");
				vetor[i].comissao = scanner.nextInt();
				vetor[i].agenda = 3;
				break;		
		}
		
		System.out.print("Forma de Recebimento (1-cheque pelos correios /2-cheque em maos /3-deposito em conta bancaria): ");
		vetor[i].recebimento = scanner.nextInt();
		System.out.print("Deseja afiliar-se ao Sindicato, mediante taxa de 8% ao mês (1-Sim /2-Não): ");
		int sindicato = scanner.nextInt();
		
		switch(sindicato) {
			case 1:
				vetor[i].taxaSindicato = 8;
				break;
			case 2:
				vetor[i].taxaSindicato = 0;
				break;
		}
		
		vetor[i].salarioTotal = 0;
		vetor[i].impostos = 7;
		vetor[i].contadorDeSextas = 0;
		
		System.out.printf("\nFuncionário %s cadastrado com sucesso! Sua ID é %d (guarde este número!).\n", vetor[i].nome, i);
		
		return vetor;
	}
	
	static Empregado[] removerEmpregado(Empregado[] vetor, int id) {
		
		System.out.printf("\nDados apagados!\n", vetor[id].nome);
		
		vetor[id].nome = null;
		vetor[id].endereco = null;
		vetor[id].tipo = 0;
		vetor[id].agenda = 0;
		vetor[id].recebimento = 0;
		vetor[id].salarioFixo = 0;
		vetor[id].salarioHora = 0;
		vetor[id].comissao = 0;
		vetor[id].salarioTotal = 0;
		vetor[id].taxaSindicato = 0;
		vetor[id].impostos = 0;
		vetor[id].contadorDeSextas = 0;
		
		return vetor;
	}
	
	static Empregado[] registrarPonto(Empregado[] vetor, int id) {
		
		System.out.print("\nDigite a quantidade de horas trabalhadas: ");
		
		Scanner scanner = new Scanner(System.in);
		int horas = scanner.nextInt();
		
		//se ele trabalhou 8 horas ou menos, guarda salario do dia e retorna
		if(horas <= 8) {
			vetor[id].salarioTotal += (horas * vetor[id].salarioHora);
			return vetor;
		}
		
		//se trabalhou mais, conta as 8, e considera o bonus nas demais horas
		vetor[id].salarioTotal += (8 * vetor[id].salarioHora);
		horas-=8;
		vetor[id].salarioTotal += (horas * 1.5 * vetor[id].salarioHora);
		
		System.out.println("\nHoras registradas!");
		
		return vetor;
	}
	
	static Empregado[] registrarVenda(Empregado[] vetor, int id) {
		
		System.out.print("\nDigite o valor da venda realizada: ");
		
		Scanner scanner = new Scanner(System.in);
		double venda = scanner.nextDouble();
		
		vetor[id].salarioTotal += (venda * (vetor[id].comissao/100));
		
		System.out.println("\nVenda registrada!");
		
		return vetor;
	}
	
	static Empregado[] opcoesSindicato(Empregado[] vetor, int id) {
		
		System.out.println("\n1-Associar funcionário (taxa de 8% ao mês)\n2-Modificar taxa de associado");
		System.out.print("\nOpção desejada: ");
		
		Scanner scanner = new Scanner(System.in);
		int opc = scanner.nextInt();
		
		switch(opc) {
			case 1:
				vetor[id].taxaSindicato = 8;
				System.out.println("\nFuncionário associado!");
				break;
			case 2:
				System.out.print("\nNova taxa: ");
				vetor[id].taxaSindicato = scanner.nextInt();
				System.out.println("\nTaxa alterada!");
				break;
		}
		
		return vetor;
	}
		
	static Empregado[] backupDados(Empregado[] vetor, Empregado[] copia) {
		
		for(int i = 0; i < maxDeFuncionarios; i++) {
			copia[i].nome = vetor[i].nome;
			copia[i].endereco = vetor[i].endereco;
			copia[i].tipo = vetor[i].tipo;
			copia[i].agenda = vetor[i].agenda;
			copia[i].recebimento = vetor[i].recebimento;
			copia[i].salarioFixo = vetor[i].salarioFixo;
			copia[i].salarioHora = vetor[i].salarioHora;
			copia[i].comissao = vetor[i].comissao;
			copia[i].salarioTotal = vetor[i].salarioTotal;
			copia[i].taxaSindicato = vetor[i].taxaSindicato;
			copia[i].impostos = vetor[i].impostos;
			copia[i].contadorDeSextas = vetor[i].contadorDeSextas;		
		}
		
		return copia;
	}

	static Empregado[] recuperarBackup(Empregado[] vetor, Empregado[] copia, Empregado[] reserva) {
		
		for(int i = 0; i < maxDeFuncionarios; i++) { //guarda estado atual para possivel 'redo'
			reserva[i].nome = vetor[i].nome;
			reserva[i].endereco = vetor[i].endereco;
			reserva[i].tipo = vetor[i].tipo;
			reserva[i].agenda = vetor[i].agenda;
			reserva[i].recebimento = vetor[i].recebimento;
			reserva[i].salarioFixo = vetor[i].salarioFixo;
			reserva[i].salarioHora = vetor[i].salarioHora;
			reserva[i].comissao = vetor[i].comissao;
			reserva[i].salarioTotal = vetor[i].salarioTotal;
			reserva[i].taxaSindicato = vetor[i].taxaSindicato;
			reserva[i].impostos = vetor[i].impostos;
			reserva[i].contadorDeSextas = vetor[i].contadorDeSextas;	
		}
		
		for(int i = 0; i < maxDeFuncionarios; i++) {
			vetor[i].nome = copia[i].nome;
			vetor[i].endereco = copia[i].endereco;
			vetor[i].tipo = copia[i].tipo;
			vetor[i].agenda = copia[i].agenda;
			vetor[i].recebimento = copia[i].recebimento;
			vetor[i].salarioFixo = copia[i].salarioFixo;
			vetor[i].salarioHora = copia[i].salarioHora;
			vetor[i].comissao = copia[i].comissao;
			vetor[i].salarioTotal = copia[i].salarioTotal;
			vetor[i].taxaSindicato = copia[i].taxaSindicato;
			vetor[i].impostos = copia[i].impostos;
			vetor[i].contadorDeSextas = copia[i].contadorDeSextas;	
		}
		
		System.out.println("Alteração desfeita.");
		
		return vetor;
	}
	
	static Empregado[] refazer(Empregado[] vetor, Empregado[] reserva) {
		
		for(int i = 0; i < maxDeFuncionarios; i++) {
			vetor[i].nome = reserva[i].nome;
			vetor[i].endereco = reserva[i].endereco;
			vetor[i].tipo = reserva[i].tipo;
			vetor[i].agenda = reserva[i].agenda;
			vetor[i].recebimento = reserva[i].recebimento;
			vetor[i].salarioFixo = reserva[i].salarioFixo;
			vetor[i].salarioHora = reserva[i].salarioHora;
			vetor[i].comissao = reserva[i].comissao;
			vetor[i].salarioTotal = reserva[i].salarioTotal;
			vetor[i].taxaSindicato = reserva[i].taxaSindicato;
			vetor[i].impostos = reserva[i].impostos;
			vetor[i].contadorDeSextas = reserva[i].contadorDeSextas;	
		}
		
		return vetor;
	}
	
	static Empregado[] alterarAgenda(Empregado[] vetor, int id) {
		
		System.out.print("\nAgenda de Recebimento desejada (1-Mensalmente /2-Semanalmente /3-Bi-semanalmente): ");
		
		Scanner scanner = new Scanner(System.in);
		vetor[id].agenda = scanner.nextInt();
		
		System.out.println("\nAgenda de Recebimento alterada.");
		
		return vetor;
	}
	
	static Empregado[] pagamentoMensal(Empregado[] vetor) {
		
		for(int i = 0; i < maxDeFuncionarios; i++) {
			if(vetor[i].agenda == 1) {	
				//concede o pagamento de acordo com a condicao do empregado (assalariado, horista ou comissionado)
				switch(vetor[i].tipo) {
					case 1:
						vetor[i].salarioTotal = vetor[i].salarioFixo;
						vetor[i].salarioTotal -= (vetor[i].salarioTotal * (vetor[i].taxaSindicato + vetor[i].impostos) / 100);
						break;
					case 2:
						vetor[i].salarioTotal -= (vetor[i].salarioTotal * (vetor[i].taxaSindicato + vetor[i].impostos) / 100);
						break;
					case 3:
						vetor[i].salarioTotal += vetor[i].salarioFixo;
						vetor[i].salarioTotal -= (vetor[i].salarioTotal * (vetor[i].taxaSindicato + vetor[i].impostos) / 100);
						break;
				}
				
				entregaDinheiro(vetor[i].recebimento, vetor[i].nome, vetor[i].endereco, vetor[i].salarioTotal);	
				vetor[i].salarioTotal = 0;
			}
		}
		
		return vetor;
	}
	
	static Empregado[] pagamentoSexta(Empregado[] vetor) {
		
		for(int i = 0; i < maxDeFuncionarios; i++) {
			if(vetor[i].agenda == 2) { //se o funcionario recebe semanalmente, paga
				switch(vetor[i].tipo) {
					case 1:
						vetor[i].salarioTotal = vetor[i].salarioFixo;
						vetor[i].salarioTotal -= (vetor[i].salarioTotal * (vetor[i].taxaSindicato + vetor[i].impostos) / 100);
						vetor[i].salarioTotal /= 4;
						break;
					case 2:
						vetor[i].salarioTotal -= (vetor[i].salarioTotal * (vetor[i].taxaSindicato + vetor[i].impostos) / 400);
						break;
					case 3:
						vetor[i].salarioTotal += (vetor[i].salarioFixo / 4);
						vetor[i].salarioTotal -= (vetor[i].salarioTotal * (vetor[i].taxaSindicato + vetor[i].impostos) / 100);
						break;
				}
				
				entregaDinheiro(vetor[i].recebimento, vetor[i].nome, vetor[i].endereco, vetor[i].salarioTotal);				
				vetor[i].salarioTotal = 0;
			}
			
			else if(vetor[i].agenda == 3) { //se ele recebe bi-semanalmente, verifica se ja se passaram duas sextas para ele
				if(vetor[i].contadorDeSextas == 2)	{
					switch(vetor[i].tipo) {
						case 1:
							vetor[i].salarioTotal = vetor[i].salarioFixo;
							vetor[i].salarioTotal -= (vetor[i].salarioTotal * (vetor[i].taxaSindicato + vetor[i].impostos) / 100);
							vetor[i].salarioTotal /= 2;
							break;
						case 2:
							vetor[i].salarioTotal -= (vetor[i].salarioTotal * (vetor[i].taxaSindicato + vetor[i].impostos) / 200);
							break;
						case 3:
							vetor[i].salarioTotal += (vetor[i].salarioFixo / 2);
							vetor[i].salarioTotal -= (vetor[i].salarioTotal * (vetor[i].taxaSindicato + vetor[i].impostos) / 100);
							break;
					}
					
					entregaDinheiro(vetor[i].recebimento, vetor[i].nome, vetor[i].endereco, vetor[i].salarioTotal);				
					vetor[i].salarioTotal = 0;
					vetor[i].contadorDeSextas = 0;
				}
			}
		}
		
		return vetor;
	}

	static Empregado[] contarSexta(Empregado[] vetor) {
		
		for(int i = 0; i < maxDeFuncionarios; i++) {
			if(vetor[i].agenda == 3) {
				vetor[i].contadorDeSextas++; //se o funcionario recebe bi-semanalmente, conta a sexta para ele
			}
		}
		
		return vetor;
	}
		
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Bem-vindo ao Sistema de Gerência e Pagamento de funcionários!");
		System.out.println("");
		
		configuracaoInicial();	//metodo para definir data de inicio do sistema, usada no controle de pagamentos
		ultimoDiaUtil(); 	//metodo para definir o ultimo dia util do mes atual
		
		Empregado[] E = new Empregado[maxDeFuncionarios]; 	//vetor que guarda os dados gerais dos funcionarios
		Empregado[] Undo = new Empregado[maxDeFuncionarios];  //vetor de backup para ser usado no 'undo'
		Empregado[] Redo = new Empregado[maxDeFuncionarios];  //vetor de backup para ser usado no 'redo'
		
		int i;
		
		for(i = 0; i < maxDeFuncionarios; i++) {				
			E[i] = new Empregado();
			Undo[i] = new Empregado();
			Redo[i] = new Empregado();
		}
		
		//abaixo, o loop para leitura dos comandos que acionam os metodos
		
		int comando = 1;
		int id;
		
		while(comando != 0) {
			
			dataAtual();
			System.out.println("\nSelecione a opção desejada:\n");
			System.out.println("1-Cadastrar funcionário\n2-Remover funcionário\n3-Registrar Cartão de Ponto");
			System.out.println("4-Registrar Venda Efetuada\n5-Opções do Sindicato\n6-Editar cadastro de funcionário");
			System.out.println("7-Alterar agenda de recebimento\n8-Rodar Folha de Pagamento e finalizar o dia\n9-Desfazer última alteração");
			System.out.println("10-Refazer alteração\n\n0-Encerrar\n");
			System.out.print("Opção desejada: ");	
			comando = scanner.nextInt();
			System.out.println("");
			
			if(comando != 9 && comando!= 0) {
				backupDados(E, Undo); //se o usuario nao escolher 'undo', fazemos backup dos dados
			}
			
			switch(comando) {
				case 1:
					E = adicionarEmpregado(E);
					break;
				case 2:
					System.out.print("Digite a ID do funcionário que será removido: ");
					id = scanner.nextInt();
					E = removerEmpregado(E, id);
					break;
				case 3:
					System.out.print("Digite a ID do funcionário: ");
					id = scanner.nextInt();
					E = registrarPonto(E, id);
					break;
				case 4:
					System.out.print("Digite a ID do funcionário: ");
					id = scanner.nextInt();
					E = registrarVenda(E, id);
					break;
				case 5:
					System.out.print("Digite a ID do funcionário: ");
					id = scanner.nextInt();
					E = opcoesSindicato(E, id);
					break;
				case 6:
					System.out.println("O cadastro do funcionário será apagado e as informações poderão ser inseridas novamente.");
					System.out.print("Digite a ID do funcionário: ");
					id = scanner.nextInt();
					E = removerEmpregado(E, id);
					System.out.println("\nInsira as novas informações do funcionário.");
					E = adicionarEmpregado(E);
					System.out.println("Cadastro editado!");
					break;
				case 7:
					System.out.print("Digite a ID do funcionário: ");
					id = scanner.nextInt();
					E = alterarAgenda(E, id);
					break;
				case 8:
					if(semana == 6) {
						E = contarSexta(E); //se for sexta-feira, conta para quem recebe bi-semanalmente
						E = pagamentoSexta(E); //paga quem recebe semanalmente e bi-semanalmente
					}
					
					if(dia == dataDeRecebimento) {
						E = pagamentoMensal(E); //se for o ultimo dia util do mes, paga quem recebe mensalmente
					}	
					
					avancarDia();
					break;
				case 9:
					E = recuperarBackup(E, Undo, Redo);
					break;
				case 10:
					E = refazer(E, Redo);
					break;	
			}
		}
		
		if(scanner != null) {
			scanner.close();
		}

		System.out.println("Obrigado por utilizar esta ferramenta!\n");
	}
}