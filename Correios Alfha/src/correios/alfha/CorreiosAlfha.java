/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correios.alfha;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author clvrt
 */
public class CorreiosAlfha {

	public static String unaccent(String src) {
		return Normalizer.normalize(src, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	public static void main(String[] args) {
		try {
			BufferedReader leitor = new BufferedReader(new FileReader("Arquivos//DNE_GU_SE_LOGRADOUROS.TXT"));
			Pattern padrao = Pattern.compile("(\\w+ )*(\\w)+");
			Matcher matcher;
			String linha = leitor.readLine(), conteudo[], nomeCidade = null, nomeBairro = null, cep = null,
					nomeLogradouro = null, nomeEstado = null;
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
					System.out.println("Linha) " + num_linha);

					linha = unaccent(linha);

					matcher = padrao.matcher(linha);
					conteudo = linha.split(" ");
					nomeEstado = conteudo[0];
					nomeLogradouro = conteudo[320];
					nomeCidade = conteudo[6];
					cep = conteudo[452];
					nomeBairro = conteudo[76];
					int str_num = 0;
					while (matcher.find()) {
						str_num++;
						System.out.print(str_num + ")" + matcher.group());
					}
				}
				estado = new Estado(nomeEstado, nomeEstado);
				cidade = new Cidade(nomeCidade, estado);
				bairro = new Bairro(nomeBairro);
				logradouro = new Logradouro(cep, cidade, estado, bairro, nomeLogradouro);
				if (!logradouros.containsKey(cep)) {
					logradouros.put(cep, logradouro);
				}
				if(!estados.containsKey(nomeEstado)) {
					estados.put(nomeEstado, estado);					
				}
				if(!cidades.containsKey(nomeCidade)) {
					cidades.put(nomeCidade, cidade);
				}
				if(!bairros.containsKey(nomeBairro)) {
					bairros.put(nomeBairro, bairro);
				}
				linha = leitor.readLine();
				num_linha++;
			}
			leitor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
