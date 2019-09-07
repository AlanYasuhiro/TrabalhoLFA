package Principal;

public class Instrucoes {
	


	/**
	 * Instância as instruções de utilização do programa .
	 */
	public Instrucoes() {
		
		System.out.println(
				"1 -  O incio do programa consistente em um pré-processamento onde as informações do autômato devem ser inseridas.\n");
		System.out.println(
				"2 - Toda vez que for utilizar um conjunto, separe os itens com ',' virgula (não são necesários espaços).");
		System.out.println("Ex: q0,q1,q2,q4 ou q1, q2, q3, q4\n");
		System.out.println(
				"3 - Quando for preencher as regras de produção, entre com todas os estados antigiveis dada a regra de produção descrita (não são necessários espaços).");
		System.out.println("Ex: δ(q0,a) = q0,q1 ou q0, q1\n");
		System.out.println("4 - Se o estado estiver uma transição indefinida para o simbolo preencha com '-'");
		System.out.println("Ex: δ(q0, a) = -\n");

	}

	
}
