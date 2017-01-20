import java.util.Scanner;

class Empregado {
	
	public String nome;
	public String endereco;
	
	public int tipo;	 //1 - assalariado / 2 - horista / 3 - comissionado
	public int recebimento; 	//1 - cheque pelos correios / 2 - cheque em maos / 3 - deposito em conta bancaria
	
	public double salarioFixo; 	//para assalariados e comissionados
	public double salarioHora; 	//para horistas
	public double comissao; 	//para comissionados
	
	public double salarioTotal; 	//guarda o salario final do empregado, considerando horas/comissoes
	
	public double taxaSindicato; 	//se o empregado for associado ao sindicato, este atributo sera diferente de zero
	public double impostos; 	//impostos gerais aplicados a todos os empregados
	
	public int contadorDeSextas; 	//usado para contar as sextas que se passam ate o comissionado receber
}

public class FolhaDePagamento {

	static int dia, mes, ano, semana; 	//guarda a data atual no sistema de funcionarios
	static int dataDeRecebimento; 	//guarda a data na qual os assalariados receberao (ultimo dia util do mes)
	
	//um metodo para cada pagamento (horista, assalariado e comissionado)
	
	static void dataAtual() {
		System.out.print("Data atual: ");
		
		switch(semana) {
			case 1:
				System.out.print("domingo, ");
				break;
			case 2:
				System.out.print("segunda-feira, ");
				break;	
			case 3:
				System.out.print("ter�a-feira, ");
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
				System.out.print("s�bado, ");
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
			limite = 28;	//se for fevereiro, caso especial
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
	
	static void configuracaoInicial() {
		
		System.out.println("Antes de come�ar, informe a data atual:\n");
		
		System.out.print("Dia: ");
		Scanner scanner = new Scanner(System.in);
		dia = scanner.nextInt();
		
		System.out.print("\nM�s (utilize o n�mero correspondente: 1-Jan/2-Fev/3-Mar/4-Abr/5-Mai/6-Jun/7-Jul/8-Ago/9-Set/10-Out/11-Nov/12-Dez): ");
		mes = scanner.nextInt();
		
		System.out.print("\nAno: ");
		ano = scanner.nextInt();
		
		System.out.print("\nDia da semana (utilize o n�mero correspondente: 1-Dom/2-Seg/3-Ter/4-Qua/5-Qui/6-Sex/7-S�b): ");
		semana = scanner.nextInt();
		
		System.out.println("\nConfigura��o conclu�da com sucesso!\n");
		
		dataAtual();
	}
	
	static Empregado[] adicionarEmpregado(Empregado[] vetor) {
		
		int i = 0;
		
		while(vetor[i].nome != null && i < 50) {
			i++;
		}
		
		if(i == 50) {
			System.out.println("N�o h� vagas!");
			return vetor;
		}
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Nome: ");
		vetor[i].nome = scanner.nextLine();	
		System.out.print("Endere�o: ");
		vetor[i].endereco = scanner.nextLine();
		System.out.print("Tipo de Pagamento (1-assalariado /2-horista /3-comissionado): ");
		vetor[i].tipo = scanner.nextInt();
		
		switch(vetor[i].tipo) {
			case 1:
				System.out.print("Sal�rio mensal do funcion�rio: ");
				vetor[i].salarioFixo = scanner.nextInt();
				break;	
			case 2:
				System.out.print("Sal�rio por hora: ");
				vetor[i].salarioHora = scanner.nextInt();
				break;
			case 3:
				System.out.print("Sal�rio do funcion�rio: ");
				vetor[i].salarioFixo = scanner.nextInt();
				System.out.print("Taxa de comiss�o sobre vendas (em %): ");
				vetor[i].comissao = scanner.nextInt();
				break;		
		}
		
		System.out.print("Forma de Recebimento (1-cheque pelos correios /2-cheque em maos /3-deposito em conta bancaria): ");
		vetor[i].recebimento = scanner.nextInt();
		System.out.print("Deseja afiliar-se ao Sindicato, mediante taxa de 8% ao m�s (1-Sim /2-N�o): ");
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
		
		System.out.printf("\nFuncion�rio %s cadastrado com sucesso! Sua ID � %d (guarde este n�mero!).\n", vetor[i].nome, i);
		
		return vetor;
	}
	
	static Empregado[] removerEmpregado(Empregado[] vetor, int id) {
		
		System.out.printf("\nFuncion�rio %s removido!\n", vetor[id].nome);
		
		vetor[id].nome = null;
		vetor[id].endereco = null;
		vetor[id].tipo = 0;
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
		
		System.out.println("\n1-Associar funcion�rio (taxa de 8% ao m�s)\n2-Modificar taxa de associado");
		System.out.print("\nOp��o desejada: ");
		
		Scanner scanner = new Scanner(System.in);
		int opc = scanner.nextInt();
		
		switch(opc) {
			case 1:
				vetor[id].taxaSindicato = 8;
				System.out.println("\nFuncion�rio associado!");
				break;
			case 2:
				System.out.print("\nNova taxa: ");
				vetor[id].taxaSindicato = scanner.nextInt();
				System.out.println("\nTaxa alterada!");
				break;
		}
		
		return vetor;
	}
	
	//metodos obrigatorios (FAZER)
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Bem-vindo ao Sistema de Ger�ncia e Pagamento de funcion�rios!");
		System.out.println("");
		
		configuracaoInicial();	//metodo para definir data de inicio do sistema, usada no controle de pagamentos
		ultimoDiaUtil(); 	//metodo para definir o ultimo dia util do mes atual
		
		Empregado[] E = new Empregado[50]; 	//vetor que guarda os dados gerais dos funcionarios
		
		int i;
		
		for(i = 0; i < 50; i++) {		
			E[i] = new Empregado(); 	//cria instancias para cada vaga disponivel
		}
		
		//abaixo, o loop para leitura dos comandos que acionam os metodos (FAZER)
		
		int comando = 1;
		int id;
		
		while(comando != 0) {
			
			System.out.println("\nSelecione a op��o desejada:\n");
			System.out.println("1-Cadastrar funcion�rio\n2-Remover funcion�rio\n3-Registrar Cart�o de Ponto");
			System.out.println("4-Registrar Venda Efetuada\n5-Op��es do Sindicato\n6-Editar cadastro de funcion�rio");
			System.out.println("7-Desfazer/refazer �ltima op��o\n8-Rodar Folha de Pagamento e finalizar o dia\n\n0-Encerrar\n");	
			System.out.print("Op��o desejada: ");	
			comando = scanner.nextInt();
			System.out.println("");
			
			switch(comando) {
				case 1:
					E = adicionarEmpregado(E);
					break;
				case 2:
					System.out.print("Digite a ID do funcion�rio que ser� removido: ");
					id = scanner.nextInt();
					E = removerEmpregado(E, id);
					break;
				case 3:
					System.out.print("Digite a ID do funcion�rio: ");
					id = scanner.nextInt();
					E = registrarPonto(E, id);
					break;
				case 4:
					System.out.print("Digite a ID do funcion�rio: ");
					id = scanner.nextInt();
					E = registrarVenda(E, id);
					break;
				case 5:
					System.out.print("Digite a ID do funcion�rio: ");
					id = scanner.nextInt();
					E = opcoesSindicato(E, id);
					break;
				case 6:
					//metodo para editar cadastro do empregado
					break;
				case 7:
					//metodo para fazer undo/redo
					break;
				case 8:
					//metodo para avancar dia e rodar a folha de pagamento
					break;
			}		
		}
		
		if(scanner != null) {
			scanner.close();
		}
		
		System.out.println("Obrigado por utilizar esta ferramenta!\n");
	}
}

