/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correios.alfha;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;

/**
 *
 * @author clvrt
 */
public class CorreiosAlfha {

    public static void main(String[] args) {
        String diretorio = "C://Users//Brenno//Downloads//Fixo//DNE_GU_AL_LOGRADOUROS.TXT";
        String cep = "57309620NS";
        LeitorArquivo lerArquivo = new LeitorArquivo(diretorio);
        HashMap<String, Logradouro> logradourosCadastrados = null;
        LocalTime tempo1, tempo2;
        try {
            lerArquivo.carregarArquivo();
            logradourosCadastrados = lerArquivo.getLogradouros();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tempo1 = LocalTime.now();
        Logradouro log = logradourosCadastrados.get(cep);
        tempo2 = LocalTime.now();
        if (log == null) {
            System.out.println("Logradouro nao encontrado!");
        } else {
            System.out.println("Buscando pelo CEP: " + cep);
            System.out.println("\n" + log);
            int horas = Math.abs(tempo2.getHour() - tempo1.getHour());
            int minutos = Math.abs(tempo2.getMinute() - tempo1.getMinute());
            int segundos = Math.abs(tempo2.getSecond() - tempo1.getSecond());
            int nanos = Math.abs(tempo2.getNano() - tempo1.getNano());
            LocalTime tempoTotal = LocalTime.of(horas, minutos, segundos, nanos);
            System.out.println("Tempo gasto para encontrar o CEP: " + cep + " " + tempoTotal);
        }

    }
}
