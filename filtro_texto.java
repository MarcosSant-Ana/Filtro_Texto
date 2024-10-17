
package AP_2semestre;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.text.Normalizer;

public class Atividade2programacaotentativa2 {
    public static void main(String[] args) throws FileNotFoundException {
        // Solicita o nome do arquivo ao usuário
        Scanner nomeArquivo = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo que deseja filtrar: ");
        String nome = nomeArquivo.nextLine();

        System.out.println(" ");
        
        System.out.println("Palavras do dicionario: ");
        // Chama o método para ler o arquivo e criar o dicionário de palavras
        lerArquivo(nome);
    }

    // Método para ler o arquivo e criar o dicionário de palavras
    public static int lerArquivo(String nome) throws FileNotFoundException {
        File arquivo = new File(nome);
        Scanner leitor = new Scanner(arquivo);

        String[] dicionario = new String[1000]; // Vetor para armazenar as palavras do dicionário
        int qntiaPalavras = 0; // Contador para o número de palavras únicas no dicionário

        // Loop para ler o arquivo palavra por palavra
        while (leitor.hasNext() && qntiaPalavras < dicionario.length) {

            // Lê a próxima palavra e a converte para minúsculas
            String palavra = leitor.next().toLowerCase();

            // Remove caracteres especiais e acentos da palavra
            palavra = removerAcentuacao(palavra);
            palavra = palavra.replaceAll("[^a-zA-Z0-9]", "");

            // Verifica se a palavra não é vazia
            if (!palavra.isEmpty()) {

                // Verifica se a palavra já está no dicionário
                if (!achaPalavraRepetida(dicionario, qntiaPalavras, palavra)) {

                    // Se a palavra não estiver no dicionário, insere a mesma de forma ordenada
                    inserePalavraOrdenada(dicionario, qntiaPalavras, palavra);

                    qntiaPalavras++; // soma 1 ao tamanho do dicionário apenas quando uma nova palavra é inserida
                }
            }
        }
        
        // Imprime o conteúdo do dicionário
        for (int i = 0; i < dicionario.length; i++) {
            if (dicionario[i] != null) {
                System.out.println(dicionario[i]);
            }
        }

        // Imprime o número de palavras únicas no dicionário
        System.out.println("Contém "+qntiaPalavras+" palavras únicas.");
        return qntiaPalavras;    
    }

    // Método para inserir uma palavra de forma ordenada no dicionário
    public static String[] inserePalavraOrdenada(String[] dicionario, int qntiaPalavras, String palavra) {
        int i = qntiaPalavras - 1;

        // Ordenação por insertionSort para inserir a palavra de forma ordenada
        while (i >= 0 && palavra.compareTo(dicionario[i]) < 0) {
            dicionario[i + 1] = dicionario[i];
            i--;
        }
        dicionario[i + 1] = palavra;
        return dicionario;
    }

    // Método para verificar se uma palavra está no dicionário
    public static boolean achaPalavraRepetida(String[] dicionario, int qntiaPalavras, String palavra) {
        int inicio = 0;
        int fim = qntiaPalavras - 1;

        // Busca binária para verificar se a palavra já está presente no dicionário
        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            int comparacao = palavra.compareTo(dicionario[meio]);

            if (comparacao == 0) {
                return true; // Palavra encontrada no dicionário
            } else if (comparacao < 0) {
                fim = meio - 1;
            } else {
                inicio = meio + 1;
            }
        }
        return false; // Palavra não encontrada no dicionário
    }

    public static String removerAcentuacao(String palavra) { //criado para remover todo tipo de acentuação ou caracter especial, para padronizar o texto 
        String palavraSemAcento = Normalizer.normalize(palavra, Normalizer.Form.NFD);
        return palavraSemAcento.replaceAll("[^\\p{ASCII}]", "");
    }
    
}
