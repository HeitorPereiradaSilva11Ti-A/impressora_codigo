import com.sun.jna.Library;
import com.sun.jna.Native;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;


public class Main {


    // Interface que representa a DLL, usando JNA
    public interface ImpressoraDLL extends Library {


        // Caminho completo para a DLL
        ImpressoraDLL INSTANCE = (ImpressoraDLL) Native.load(
                "C:\\Users\\pereira_heitor\\Documents\\Java-Aluno EM\\Java-Aluno EM\\E1_Impressora01.dll",
                ImpressoraDLL.class
        );


        int AbreConexaoImpressora(int tipo, String modelo, String conexao, int param);




        int FechaConexaoImpressora();




        int ImpressaoTexto(String dados, int posicao, int estilo, int tamanho);




        int Corte(int avanco);




        int ImpressaoQRCode(String dados, int tamanho, int nivelCorrecao);




        int ImpressaoCodigoBarras(int tipo, String dados, int altura, int largura, int HRI);




        int AvancaPapel(int linhas);




        int StatusImpressora(int param);




        int AbreGavetaElgin();




        int AbreGaveta(int pino, int ti, int tf);




        int SinalSonoro(int qtd, int tempoInicio, int tempoFim);




        int ModoPagina();




        int LimpaBufferModoPagina();




        int ImprimeModoPagina();




        int ModoPadrao();




        int PosicaoImpressaoHorizontal(int posicao);




        int PosicaoImpressaoVertical(int posicao);




        int ImprimeXMLSAT(String dados, int param);




        int ImprimeXMLCancelamentoSAT(String dados, String assQRCode, int param);
    }




    private static boolean conexaoAberta = false;
    private static int tipo;
    private static String modelo;
    private static String conexao;
    private static int parametro;
    private static final Scanner scanner = new Scanner(System.in);




    private static String capturarEntrada(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }




    public static void configurarConexao() {
        if (!conexaoAberta) {
            Scanner scanner = new Scanner(System.in);




            System.out.println("Digite o tipo de conexão: ");
            tipo = scanner.nextInt();




            System.out.println("Digite o modelo de conexão: ");
            modelo = scanner.nextLine();
            scanner.nextLine();


            System.out.println("Digite a conexão: ");
            conexao = scanner.nextLine();


            System.out.println("Digite o parametro de conexão: ");
            parametro = scanner.nextInt();




        }
    }




    public static void abrirConexao () {




        //sempre que for chamar uma funçao da biblioteca, usar como abaixo (ImpressoraDLL.INSTANCE.nomeDaFuncao)




        if (!conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(tipo, modelo, conexao, parametro);
            if (retorno == 0) {
                conexaoAberta = true;
                System.out.println("Conexão aberta com sucesso.");
            } else {
                System.out.println("Erro ao abrir conexão. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Conexão já está aberta.");
        }
    }








    public static void fecharConexao () {
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();
            if (retorno == 0) {
                conexaoAberta = false;
                System.out.println("Conexão fechada com sucesso.");
            } else {
                System.out.println("Erro ao fechar conexão. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Conexão já está fechada.");
        }
    }








    //criar o restante das funçoes aqui!
    public static void ImpressaoTexto () {
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoTexto( "Teste de impressao",  1,  4, 0);
            if (retorno == 0) {




                System.out.println("Teste de impressao.");
            } else {
                System.out.println("Erro ao imprimir. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Primeiro precisa abrir conexão da impressora");
        }
    }







    public static void ImpressaoQRCode () {
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode( "Teste de impressao",  6,  4);
            if (retorno == 0) {




                System.out.println("Teste de impressao.");
            } else {
                System.out.println("Erro ao imprimir. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Primeiro precisa abrir conexão da impressora");
        }
    }




    public static void ImpressaoCodigoBarras()  {
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(8, "{A012345678912",  100, 2, 3);
            if (retorno == 0) {




                System.out.println("Teste de impressao.");
            } else {
                System.out.println("Erro ao imprimir. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Primeiro precisa abrir conexão da impressora");
        }
    }




    public static void AbreGavetaElgin ()  {
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AbreGavetaElgin();
            if (retorno == 0) {




                System.out.println("Gaveta aberta");
            } else {
                System.out.println("Erro ao abrir. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Primeiro precisa abrir conexão da impresso");
        }
    }




    public static void AbreGaveta ()  {
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.AbreGaveta(1, 5, 10);
            if (retorno == 0) {




                System.out.println("Gaveta aberta.");
            } else {
                System.out.println("Erro ao abrir. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Primeiro precisa abrir conexão da impresso");
        }
    }




    public static void SinalSonoro ()  {
        if (conexaoAberta) {
            int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(4,5,5);
            if (retorno == 0) {




                System.out.println("Sinal tocando.");
            } else {
                System.out.println("Erro ao tocar. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Primeiro precisa abrir conexão da impresso");
        }
    }




    public static void ImprimeXMLSAT () {
        if (conexaoAberta) {
            String dados = "path=C:\\Users\\pereira_heitor\\Documents\\Java-Aluno EM\\Java-Aluno EM\\XMLSAT.xml";
            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(dados,0);
            if (retorno == 0) {
                System.out.println("Impressao OK");
            } else {
                System.out.println("Erro ao imprimir xml sat. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Primeiro precisa abrir conexão da impresso");
        }
    }


    public static void ImprimeXMLCancelamentoSAT () {
        if (conexaoAberta) {
            String assQRcode = "Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQsE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+ZfO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";
            String dados = "path=C:\\Users\\pereira_heitor\\Documents\\Java-Aluno EM\\Java-Aluno EM\\XMLSAT.xml";
            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(dados, assQRcode, 0);
            if (retorno == 0) {
                System.out.println("Impressao OK");
            } else {
                System.out.println("Erro ao imprimir xml sat. Código de erro: " + retorno);
            }
        } else {
            System.out.println("Primeiro precisa abrir conexão da impresso");
        }
    }










  /* - `()`          ("Teste de impressao", 1, 4, 0);
  - `Corte()`                   (2)  usar sempre após a impressao de algum documento
  - `ImpressaoQRCode()`            ("Teste de impressao", 6, 4)
  - `ImpressaoCodigoBarras()`    (8, "{A012345678912", 100, 2, 3)
  - `AvancaPapel()`                 (2)  usar sempre após a impressao de algum documento
  - `AbreGavetaElgin()`            (1, 50, 50)
  - `AbreGaveta()`                  (1, 5, 10)
  - `SinalSonoro()`            (4,5,5)
  - `ImprimeXMLSAT()`
  - `ImprimeXMLCancelamentoSAT()`    (assQRCode = "Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQsE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+ZfO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";)
  */








    public static void main (String[]args){
        while (true) {
            System.out.println("\n*************************************************");
            System.out.println("**************** MENU IMPRESSORA *******************");
            System.out.println("*************************************************\n");




            System.out.println("1  - Configurar Conexao");
            System.out.println("2  - Abrir Conexao");
            System.out.println("3 - Impressao Texto");
            System.out.println("4 - Impressao QRCode");
            System.out.println("5 - Impressao Cod Barras");
            System.out.println("6 - Impressao XML SAT");
            System.out.println("7 - Impressao XML Canc SAT");
            System.out.println("8 - Abrir Gaveta Elgin");
            System.out.println("9 - Abrir Gaveta");
            System.out.println("10 - Sinal Sonoro");
            System.out.println("0 - Fechar Conexao e Sair");
            //criar o restante do MENU








            String escolha = capturarEntrada("\nDigite a opção desejada: ");




            if (escolha.equals("0")) {
                fecharConexao();
                System.out.println("Programa encerrado.");
                break;
            }




            switch (escolha) {
                case "1":
                    configurarConexao();
                    break;
                case "2":
                    abrirConexao();
                    break;


                case "3":
                    ImpressaoTexto();
                    ImpressoraDLL.INSTANCE.Corte(3);
                    break;
                case "4":
                    ImpressaoQRCode();
                    ImpressoraDLL.INSTANCE.Corte(3);
                    break;
                case "5":
                    ImpressaoCodigoBarras();
                    ImpressoraDLL.INSTANCE.Corte(3);
                    break;
                case "6":
                    ImprimeXMLSAT();
                    ImpressoraDLL.INSTANCE.Corte(3);
                    break;




                case "7":
                    ImprimeXMLCancelamentoSAT();
                    break;


                case "8":
                    AbreGaveta();
                    break;




                case "9":
                    AbreGavetaElgin();
                    break;




                case "10":
                    SinalSonoro();
                    break;


                default:
                    System.out.println("OPÇÃO INVÁLIDA");
            }
        }




        scanner.close();
    }




    private static String lerArquivoComoString (String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] data = fis.readAllBytes();
        fis.close();
        return new String(data, StandardCharsets.UTF_8);
    }
}



