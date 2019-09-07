package Principal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * A Classe EntradaDados.
 */
public class EntradaDados {

	/**
	 * Declara as variáveis do tipo String: alfabeto, estados, estadoInicial,
	 * estadoFinal, palavra.
	 */
	private String alfabeto, estados, estadoInicial, estadoFinal, palavra;

	/**
	 * Declara as variáveis do tipo inteiro: tamAlfabeto, tamEstado, x, cont =
	 * 0, i, j, qnt = 0, d = 0
	 */
	private int tamAlfabeto, tamEstado, x, cont = 0, i, j, qnt = 0, d = 0;

	/**
	 * Declara os vetores do tipo String: estadoIndividual, letra, producao,
	 * regra, estadoFinalIndividual, palavraIndividual, conc, quebraEstado
	 */
	private String[] estadoIndividual, letra, producao, regra, estadoFinalIndividual, palavraIndividual, conc,
			quebraEstado;

	/** Declara a variavel de leitura do tipo Scanner: sc. */
	private Scanner sc;

	/**
	 * Instância o construtor e chama a função Dados().
	 */
	public EntradaDados() {
		Dados();
	}

	/**
	 * Recebe as entradas: simbolos do alfabeto, os conjuntos e as regras de
	 * produção.
	 */
	//
	public void Dados() {
		sc = new Scanner(System.in);

		System.out.println("Inicia o programa");
		System.out.print("Entre com os símbolos do alfabeto Σ = ");
		alfabeto = sc.nextLine();
		letra = alfabeto.split(",");
		tamAlfabeto = letra.length;

		System.out.print("Entre com os conjuntos Q = ");
		estados = sc.nextLine();
		estadoIndividual = estados.split(",");
		tamEstado = estadoIndividual.length;
		x = tamAlfabeto * tamEstado;

		producao = new String[x];

		Producao();
		System.out.print("Entre com o estado inicial S = ");
		estadoInicial = sc.nextLine();
		System.out.print("Entre com o conjunto de estados finais = ");
		estadoFinal = sc.nextLine();
		if (estadoFinal.contains(",")) {
			estadoFinalIndividual = estadoFinal.split(",");
		}
		pegaPalavra();
	}

	/**
	 * Recebe as regras de produção, se houver dois ou mais estados por linha
	 * separa em um vetor os estados e chama a função SepararProducao().
	 */
	public void Producao() {
		cont = 0;
		d = 0;
		System.out.println("Entre com as regras de produção para cada estado: ");

		for (i = 0; i < tamEstado; i++) {
			for (j = 0; j < tamAlfabeto; j++) {
				System.out.print("d(" + estadoIndividual[i].trim() + ", " + letra[j].trim() + ") = ");
				producao[cont] = sc.nextLine().trim();
				// if (producao[cont].contains(",")) {
				// quebraEstado = producao[cont].split(",");
				// }
				cont++;
			}
		}
		conc = new String[cont];
		for (i = 0; i < tamEstado; i++) {
			for (j = 0; j < tamAlfabeto; j++) {
				conc[d] = estadoIndividual[i] + ", " + letra[j];
				d++;
			}
		}

		SepararProducao();
	}

	/**
	 * Separa as regra de produção caso haja mais de uma
	 */

	public void SepararProducao() {
		qnt = 0;
		for (i = 0; i < x; i++) {
			// System.out.println(producao[i]);
			if (producao[i].contains(",")) {
				quebraEstado = producao[i].split(",");
				for (j = 0; j < quebraEstado.length; j++) {
					qnt++;
				}
			} else {
				qnt++;
			}
		}
		Verificar();
	}

	/**
	 * Verificar.
	 */
	// Verifica se existe o estado que a pessoa digitou
	public void Verificar() {
		String[] verificar = new String[qnt];
		int cont = 0;
		for (i = 0; i < x; i++) {
			if (producao[i].contains(",")) {
				quebraEstado = producao[i].split(",");
				for (j = 0; j < quebraEstado.length; j++) {
					verificar[cont] = quebraEstado[j];
					cont++;
				}
			} else {
				verificar[cont] = producao[i];
				cont++;
			}
		}

		int confere = 0, ver = 0, teste = 0;
		boolean a = false;
		ArrayList naoAceitaa = new ArrayList<String>();

		for (int i = 0; i < verificar.length; i++) {
			for (int j = 0; j < tamEstado; j++) {
				if (verificar[i].trim().equals(estadoIndividual[j].trim()) || verificar[i].trim().equals("-")) {
					confere++;
				} else {
					ver++;
					if (ver == estadoIndividual.length) {
						naoAceitaa.add(verificar[i]);
						teste++;
					}
				}
			}
			ver = 0;
			if (confere != 1 && confere != estadoIndividual.length) {
				a = true;
			}
			confere = 0;
		}

		if (a == true) {
			System.out.print("Estado não aceito: {");
			for (int k = 0; k < teste; k++) {
				if (k == teste - 1) {
					System.out.print(naoAceitaa.get(k) + "}");
				} else {
					System.out.print(naoAceitaa.get(k) + ", ");
				}
			}
			System.out.println("\n");
			Producao();
		}
	}

	/**
	 * Pega palavra.
	 */
	// Pega a palavra a ser computada
	public void pegaPalavra() {
		System.out.println("Informe");
		System.out.println("A palavra a ser computada pelo autômato ou");
		System.out.println("Entre com '<<' para informar novamente os parâmetros do autômato ou");
		System.out.print("Entre com 'exit' para sair do programa: ");
		palavra = sc.nextLine();
		System.out.println("");
		if (palavra.equals("<<")) {
			Dados();
		} else if (palavra.equals("exit")) {
			System.exit(0);
		} else {
			palavraIndividual = palavra.split("");
			Computacao();
		}
	}

	/**
	 * Faz a computação da palavra e verifica se a palavra é aceita ou nao é aceita ou indefinida.
	 */
	
	public void Computacao() {
		String aux, aux2, aux3 = null, aux4;
		boolean w = false, n = false, v = false;
		ArrayList palav = new ArrayList<String>();
		HashSet<String> prod = new HashSet<String>();
		int d = 0, contador = 0, c = 1;
		//insere cada letra da palavra em um arraylist
		for (i = 0; i < palavra.length(); i++) {
			palav.add(palavraIndividual[i]);
		}

		System.out.println("δ*({" + estadoInicial + "}, " + palavra + ") = ");

		//passa o estado inicial
		aux = estadoInicial;
		//executa ate a palavra termimar 
		for (i = 0; i < palav.size(); i++) {
			
			tempo();
			
			//faz a junção do estado com a letra a ser computada
			aux2 = aux + ", " + palav.get(0);
			System.out.print("δ*(δ(");

			// Verifica se tiver mais de um estado para computar
			if (w == true) {
				quebraEstado = aux.split(",");
				for (int l = 0; l < quebraEstado.length; l++) {
					//caso seja o ultimo estado, mostra o estado que vai ser computado e fecha computação 
					if (l == quebraEstado.length - 1) {
						System.out.print(quebraEstado[l].trim() + ", " + palav.get(0) + "), ");
						//faz a junção do estado com a letra a ser computada
						aux3 = quebraEstado[l].trim() + ", " + palav.get(0);
						contador++;
					} else {
						
						System.out.print(quebraEstado[l].trim() + ", " + palav.get(0) + ") ∪  δ(");
						//faz a junção do estado com a letra a ser computada
						aux3 = quebraEstado[l].trim() + ", " + palav.get(0);

					}
					
					aux3 = aux3.trim();
					
					for (int p = 0; p < conc.length; p++) {
						//compara a regra de produção com a computação e a produção se for diferente de '-' adiciona em HashSet
						if (aux3.trim().equals(conc[p]) && !producao[p].equals("-")) {
							//caso tiver mais de uma produção realiza a quebra de estado
							if (producao[p].contains(",")) {
								quebraEstado = producao[p].split(",");
								for (int i = 0; i < quebraEstado.length; i++) {
									prod.add(quebraEstado[i].trim());
								}
							} else {
								prod.add(producao[p].trim());
							}
							//recebe o resultado da computação e retira os '[]'
							aux = prod.toString();
							aux = aux.replace("[", "");
							aux = aux.replace("]", "");
							prod.clear();
							v = true;
						}
					}
				}
				
				w = false;
			} else {
				System.out.print(aux + ", " + palav.get(0) + "), ");
			}

			palav.remove(0);

			aux4 = palavra.substring(c);
			c++;
			if (palav.size() != 0) {
				System.out.println(aux4 + ") = ");
				i--;
			} else {
				System.out.println("ε) = ");
				i++;
			}

			for (j = 0; j < conc.length; j++) {
				//compara a regra de produção com a computação e a produção se for diferente de '-' ;
				if (aux2.trim().equals(conc[j]) && (!producao[j].equals("-"))) {
					
					aux = producao[j];
					if (aux.contains(",")) {
						quebraEstado = aux.split(",");
						w = true;
					}
					v = true;
					contador++;
				}
				if(v == false){
					aux = "";
				}
				if (aux.length() > 2) {
					w = true;
				}
			}
			v = false;
			tempo();
			if(aux.length() == 0){
				System.out.println("INDEFINIDO");
				break;
			}

			if (contador == 1) {
				if (palav.size() != 0) {
					System.out.println("δ*({" + aux + "}, " + aux4 + ") = ");

				} else {
					System.out.println("δ*({" + aux + "},ε) = ");

					if (estadoFinal.contains(",")) {
						for (j = 0; j < estadoFinalIndividual.length; j++) {

							if (aux.contains(estadoFinalIndividual[j])) {
								n = true;
							}
						}
					} else {

						if (aux.contains(estadoFinal)) {
							n = true;
						}
					}

					tempo();

					if (n == true) {
						System.out.println("{" + aux + "} ∩ {" + estadoFinal + "} ≠ Ø >>>>> PALAVRA ACEITA");
					} else {
						System.out.println("{" + aux + "} ∩ {" + estadoFinal + "} = Ø >>>>> PALAVRA NÂO ACEITA");
					}
					n = false;
				}
			} else {
				System.out.println("INDEFINIDO");
				break;
			}
			contador = 0;
		}

		System.out.println("");
		pegaPalavra();
	}

	/**
	 * Aplica delay na hora de exibir a computação
	 */
	public static void tempo() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {

		}
	}
}