package correios.alfha;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LeitorArquivo {

    private HashMap<String, Logradouro> logradouros = new HashMap<>();
    private HashMap<String, Bairro> bairros = new HashMap<>();
    private HashMap<String, Cidade> cidades = new HashMap<>();
    private HashMap<String, Estado> estados = new HashMap<>();
    private String diretorio;

    public LeitorArquivo(String diretorio) {
        this.diretorio = diretorio;
    }

    public void carregarArquivo() throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(diretorio));
        String linha = leitor.readLine(), conteudo[], nomeCidade, nomeBairro, cep, nomeLogradouro, siglaEstado;
        Logradouro logradouro;
        Cidade cidade;
        Estado estado;
        Bairro bairro;
        int num_linha = 1;
        while (linha != null && !linha.endsWith("#")) {
            if (num_linha != 1) {
                conteudo = linha.split(" \\s+");
                siglaEstado = conteudo[0];
                nomeCidade = conteudo[1];
                nomeBairro = conteudo[2];
                nomeLogradouro = conteudo[6];
                if (conteudo.length == 8) {
                    cep = conteudo[7];
                } else {
                    cep = conteudo[8];
                }
                estado = new Estado(siglaEstado, siglaEstado);
                cidade = new Cidade(nomeCidade, estado);
                bairro = new Bairro(nomeBairro);
                logradouro = new Logradouro(cep, cidade, estado, bairro, nomeLogradouro);

                if (!this.cidades.containsKey(nomeCidade)) {
                    this.cidades.put(nomeCidade, cidade);
                }
                if (!this.bairros.containsKey(nomeBairro)) {
                    this.bairros.put(nomeBairro, bairro);
                }
                if (!this.logradouros.containsKey(cep)) {
                    this.logradouros.put(cep, logradouro);
                }
                if (!this.estados.containsKey(siglaEstado)) {
                    this.estados.put(siglaEstado, estado);
                }
            }
            linha = leitor.readLine();
            num_linha++;
        }
        leitor.close();
    }

    public HashMap<String, Logradouro> getLogradouros() {
        return logradouros;
    }

    public HashMap<String, Bairro> getBairros() {
        return bairros;
    }

    public HashMap<String, Cidade> getCidades() {
        return cidades;
    }

    public HashMap<String, Estado> getEstados() {
        return estados;
    }

}
