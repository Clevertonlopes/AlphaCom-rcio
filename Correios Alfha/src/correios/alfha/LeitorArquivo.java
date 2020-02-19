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
        String linha = leitor.readLine(), conteudo[], nomeCidade= null, nomeBairro = null, cep=null, nomeLogradouro = null, siglaEstado[] = null;
        Logradouro logradouro = null;
        Cidade cidade = null;
        Estado estado = null;
        Bairro bairro = null;
        while (linha != null) {
                conteudo = linha.split("\t");               
                if(conteudo.length >= 4){
                cep = conteudo[0];
                nomeCidade = conteudo[1];
                siglaEstado = conteudo[1].split("/");
                nomeBairro = conteudo[2];
                nomeLogradouro = conteudo[3];
                estado = new Estado(siglaEstado[1], siglaEstado[1]);
                cidade = new Cidade(nomeCidade, estado);
                bairro = new Bairro(nomeBairro);
                logradouro = new Logradouro(cep, cidade, estado, bairro, nomeLogradouro);
                if (!this.logradouros.containsKey(cep)) {
                    this.logradouros.put(cep, logradouro);
                }                                               
                bairro.setLogradouros(logradouros);
                if (!this.bairros.containsKey(nomeBairro)) {
                    this.bairros.put(nomeBairro, bairro);
                }
                cidade.setBairros(bairros);
                if (!this.cidades.containsKey(nomeCidade)) {
                    this.cidades.put(nomeBairro, cidade);
                }
                estado.setCidades(cidades);
                if (!this.estados.containsKey(siglaEstado[1])) {
                    this.estados.put(siglaEstado[1], estado);
                }
            }
            linha = leitor.readLine();
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
