package sistema;

import exce��es.*;
import perfis.*;
import tweet.*;
import java.util.Scanner;

public class Terminal {
	
	public static int menuInicial() {
		Scanner scanner = new Scanner(System.in);
		int resposta = 0;
		System.out.println("=========MyTwitter=========\n Digite o n�mero da op��o desejada\n "
				+ "[1] Entrar na sua conta\n [2] Cadastrar-se\n [3] Sair");
		
		resposta = scanner.nextInt();
	
		return resposta;
	}
	
	public static int menuPerfil(String usuario) {
		Scanner scanner = new Scanner(System.in);
		int resposta = 0;
		System.out.println("======Bem vindo, " + usuario + ", o que deseja fazer?======");
		System.out.println("[1] Tweetar\n[2] Ver sua Timeline\n[3] Ver Tweets de algu�m\n"
				+ "[4] Seguir algu�m\n"
				+ "[5] Verificar seguidores de algu�m\n[6] Verificar os usu�rios que algu�m segue\n"
				+ "[7] Cancelar Conta\n"
				+ "[8] Deslogar\n[9] Sair do sistema");
		
		resposta = scanner.nextInt();
	
		return resposta;
		
	}
	
	public static int menuCadastro() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Qual tipo de Perfil deseja criar?\n"
				+ "[1] Pessoa F�sica\n[2] Pessoa Jur�dica");
		
		int resposta = scanner.nextInt();
		
		return resposta;
	}
	
	public static void main(String[] args) {
		
		MyRepositorioUsuario repositorio = new MyRepositorioUsuario();
		MyTwitter twitter = new MyTwitter(repositorio);
		Perfil perfilAtual = null;
		Scanner scanner = new Scanner(System.in);
		boolean terminalLigado = true;
		
		while(terminalLigado) {
			if(perfilAtual == null) {
				switch(menuInicial()){
					case 1:
						System.out.println("Insira seu nome de Usu�rio");
						String usuario = scanner.next();
						
						if(repositorio.buscar(usuario) == null) {
							System.out.println("Usu�rio n�o cadastrado!");
						} else {
							perfilAtual = repositorio.buscar(usuario);
						}
						break;
						
					case 2:
						switch(menuCadastro()) {
							case 1:
								String usuarioCadastro;
								long cpf;
								
								System.out.println("Insira seu nome de usuario");
								usuarioCadastro = scanner.next();
								System.out.println("Insira seu CPF");
								cpf = scanner.nextLong();
								
								PessoaFisica pessoaFisica = new PessoaFisica(usuarioCadastro, cpf);
								
								try {
									twitter.criarPerfil(pessoaFisica);
									System.out.println("Perfil criado com sucesso!");
								} catch(PEException pee) {
									System.out.println("J� existe um perfil com esse nome de Usu�rio");
								}
								break;
								
							case 2:
								long cnpj;
								
								System.out.println("Insira seu nome de usuario");
								usuarioCadastro = scanner.next();
								System.out.println("Insira o CNPJ");
								cnpj = scanner.nextLong();
								
								PessoaJuridica pessoaJuridica = new PessoaJuridica(usuarioCadastro, cnpj);
								
								try {
									twitter.criarPerfil(pessoaJuridica);
									System.out.println("Perfil criado com sucesso!");
								} catch(PEException pee) {
									System.out.println("J� existe um perfil com esse nome de Usu�rio");
								}
								break;
								
							default:
								break;
						}
						break;
					
					case 3:
						terminalLigado = false;
						
					default:
						break;
				}
			} else {
				switch(menuPerfil(perfilAtual.getUsuario())) {
					case 1:
						String mensagem;
							
						System.out.println("Escreva seu tweet (M�ximo de 140 caracteres):");
						mensagem = scanner.next();
						try {
							twitter.tweetar(perfilAtual.getUsuario(), mensagem);
						} catch(MFPException mfp) {
							System.out.println("O tweet deve ter de 1 � 140 caracteres");							} catch(PIException pi) {
							System.out.println("Usuario n�o existe");
						}
						break;
							
					case 2:
						System.out.println("============Timeline============\n");
						for(Tweet tweet : perfilAtual.getTimeline()) {
							System.out.println(tweet.getUsuario()+":\n"+tweet.getMensagem()+"\n");
						}
						System.out.println("================================");
						break;
					case 3:
						String usuario;
						System.out.println("Insira o usu�rio de quem voc� quer ver os tweets:");
						usuario = scanner.next();
						Perfil perfilBuscado;
						perfilBuscado = repositorio.buscar(usuario);
							
						try {
							System.out.println("============Tweets============\n");
							for(Tweet tweet : twitter.tweets(perfilBuscado.getUsuario())) {
								System.out.println(tweet.getUsuario()+":\n"+tweet.getMensagem()+"\n");
							}
							System.out.println("==============================");
						} catch(PIException pi) {
							System.out.println("Usu�rio inexistente");
						} catch(PDException pd) {
							System.out.println("O perfil do usu�rio est� desativado");
						}
						break;
					case 4:
						String seguido;
						System.out.println("Quem voc� deseja seguir?");
						seguido = scanner.next();
							
						try {
							twitter.seguir(perfilAtual.getUsuario(), seguido);
						} catch(PIException pi) {
							System.out.println("Usu�rio inexistente");
						} catch(PDException pd) {
							System.out.println("O perfil do usu�rio est� desativado");
						} catch(SIException si) {
							System.out.println("Voc� n�o pode seguir a si mesmo");
						}
						break;
					case 5:
						System.out.println("De quem voc� deseja verificar os seguidores?");
						usuario = scanner.next();
						
						try {
							twitter.seguidores(usuario);
						} catch(PIException pi) {
							System.out.println("Usu�rio inexistente");
						} catch(PDException pe) {
							System.out.println("O perfil do usu�rio est� desativado");
						}
							
						break;
					case 6:
						System.out.println("De quem voc� deseja verificar os seguidos?");
						usuario = scanner.next();
							
						try {
							twitter.seguidos(usuario);
						} catch(PIException pi) {
							System.out.println("Usu�rio inexistente");
						} catch(PDException pd) {
							System.out.println("O perfil do usu�rio est� desativado");
						}
						break;
					case 7:
						try {
							twitter.cancelarPerfil(perfilAtual.getUsuario());
						} catch(PIException pi) {
							System.out.println("Usu�rio inexistente");
						} catch(PDException pd) {
							System.out.println("O perfil do usu�rio est� desativado");
						}
						break;
					case 8:
						perfilAtual = null;
						break;
					case 9:
						terminalLigado = false;
						break;
					default:
						break;
				}
			}
		}
	}
}
