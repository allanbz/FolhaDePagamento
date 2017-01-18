import java.util.Scanner;

class Empregado {
	
	String nome;
	String endereco;
	int tipo;	 //1 - assalariado / 2 - horista / 3 - comissionado
	int recebimento; 	//1 - cheque pelos correios / 2 - cheque em maos / 3 - deposito em conta bancaria
	double salarioFixo; 	//para assalariados e comissionados
	double salarioHora; 	//para horistas
	double comissao; 	//para comissionados
	double salarioTotal; 	//guarda o salario final do empregado, considerando horas/comissoes
	double taxaSindicato; 	//se o empregado for associado ao sindicato, este atributo sera diferente de zero
	double impostos; 	//impostos gerais aplicados a todos os empregados
	
	int contadorDeSextas; 	//usado para contar as sextas que se passam ate o comissionado receber
	
}

public class FolhaDePagamento {

	static int dia, mes, ano, semana; 	//guarda a data atual no sistema de funcionarios
	static int dataDeRecebimento; 	//guarda a data na qual os assalariados receberao (ultimo dia util do mes)
	
	//um metodo para cada pagamento (horista, assalariado e comissionado)
	
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
		
		System.out.println("Antes de começar, informe a data atual:");
		System.out.println("");
		
		System.out.print("Dia: ");
		Scanner scanner = new Scanner(System.in);
		dia = scanner.nextInt();
		
		System.out.println("");
		System.out.println("Mês: (utilize o número correspondente: 1-Jan/2-Fev/3-Mar/4-Abr/5-Mai/6-Jun/7-Jul/8-Ago/9-Set/10-Out/11-Nov/12-Dez)");
		mes = scanner.nextInt();
		
		System.out.println("");
		System.out.print("Ano: ");
		ano = scanner.nextInt();
		
		System.out.println("");
		System.out.println("Dia da semana: (utilize o número correspondente: 1-Dom/2-Seg/3-Ter/4-Qua/5-Qui/6-Sex/7-Sáb)");
		semana = scanner.nextInt();
		
		if(scanner != null) {
			scanner.close();
		}
		
		System.out.println("");
		System.out.println("Configuração concluída com sucesso!");
		System.out.println("");
		
	}
	
	//metodos obrigatorios (FAZER)
	
	public static void main(String[] args) {
		
		System.out.println("Bem-vindo ao Sistema de Gerência e Pagamento de funcionários!");
		System.out.println("");
		
		configuracaoInicial();	//metodo para definir data de inicio do sistema, usada no controle de pagamentos
		ultimoDiaUtil(); 	//metodo para definir o ultimo dia util do mes atual
		
		Empregado[] E = new Empregado[50]; 	//vetor que guarda os dados gerais dos funcionarios
		
		int i;
		
		for(i = 0; i < 50; i++) {
			
			E[i] = new Empregado(); 	//cria instancias para cada vaga disponivel
		}
		
		//abaixo, o loop para leitura dos comandos que acionam os metodos (FAZER)
		
	}

}
