/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correios.alfha;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author clvrt
 */
public class CorreiosAlfha {

	public static void main(String[] args) {
		try {
			BufferedReader leitor = new BufferedReader(new FileReader("Arquivos//DNE_GU_SE_LOGRADOUROS.TXT"));
			String linha = leitor.readLine(), conteudo[], nomeCidade, nomeBairro, cep, nomeLogradouro, siglaEstado;
			HashMap<String, Logradouro> logradouros = new HashMap<String, Logradouro>();
			HashMap<String, Estado> estados = new HashMap<String, Estado>();
			HashMap<String, Cidade> cidades = new HashMap<String, Cidade>();
			HashMap<String, Bairro> bairros = new HashMap<String, Bairro>();
			Logradouro logradouro;
			Cidade cidade;
			Estado estado;
			Bairro bairro;
			int num_linha = 1;
			while (linha != null) {
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
					if (!logradouros.containsKey(cep)) {
						logradouros.put(cep, logradouro);
					}
					if (!estados.containsKey(siglaEstado)) {
						estados.put(siglaEstado, estado);
					}
					if (!cidades.containsKey(nomeCidade)) {
						cidades.put(nomeCidade, cidade);
					}
					if (!bairros.containsKey(nomeBairro)) {
						bairros.put(nomeBairro, bairro);
					}
				}
				linha = leitor.readLine();
				num_linha++;
			}
			for (Logradouro l : logradouros.values()) {
				System.out.println(l);
			}
			leitor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
