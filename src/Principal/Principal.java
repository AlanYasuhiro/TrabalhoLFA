package Principal;

import java.util.Scanner;

public class Principal {
	
	public static void main(String[] args) {
		String inicio;
		
		EntradaDados dados;
		Instrucoes instrucao;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Entre com 'h' ou 'H' para ajuda ou [ENTER] para continuar");
		inicio = sc.nextLine();
		
		if(inicio.trim().equals("h") || inicio.trim().equals("H")){
			instrucao = new Instrucoes();
			
			dados = new EntradaDados();
		}else{
			dados = new EntradaDados();
		}	
	}
}
