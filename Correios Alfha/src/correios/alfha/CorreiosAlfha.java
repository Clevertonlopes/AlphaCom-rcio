/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correios.alfha;

import java.io.IOException;
import java.security.spec.InvalidParameterSpecException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clvrt
 */
public class CorreiosAlfha {

    Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws InvalidParameterSpecException {
        CorreiosAlfha m = new CorreiosAlfha();
        m.Menu();
    }

    private void Menu() throws InvalidParameterSpecException {
        HashMap<String, Logradouro> logradourosCadastrados = null;
        int opc = -1;
        String diretorio = "C:\\Users\\Brenno\\Downloads\\AlphaCorreio-master\\Correios Alfha\\Arquivos\\ceps.txt";
        LeitorArquivo lerArquivo = new LeitorArquivo(diretorio);
        while (opc != 0) {
            System.out.println(""
                    + "1-Buscar logradouro por CEP\n"
                    + "2-Buscar logradouros de um bairro\n"
                    + "0-Sair\n");

            opc = input.nextInt();
            
            switch (opc) {
                case 1:
                    System.out.println("Digite o cep: ");
                    String cep = input.next();
                                                                             
                    
                    try {
                        lerArquivo.carregarArquivo();
                        logradourosCadastrados = lerArquivo.getLogradouros();
                    } catch (IOException ex) {
                        Logger.getLogger(CorreiosAlfha.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    LocalTime tempo1,
                     tempo2;
                    tempo1 = LocalTime.now();
                    if(logradourosCadastrados.containsKey(cep) && cep != null){
                    Logradouro log = logradourosCadastrados.get(cep);
                    
                    if (log == null) {
                        System.out.println("Logradouro nao encontrado!");
                    } else {
                    tempo2 = LocalTime.now();
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
                    break;
                    
                case 2:
                    /*System.out.print("UF: ");
                    String uf = input.next();*/
                    
                    /*System.out.print("Cidade: ");
                    String cidade = input.next();*/
                    
                    System.out.println("Bairro: ");
                    String bairro = input.next();
                    HashMap<String,Bairro> bairrosCadastrados = null;
                    try {
                        lerArquivo.carregarArquivo();
                        bairrosCadastrados = lerArquivo.getBairros();
                    } catch (IOException ex) {
                        Logger.getLogger(CorreiosAlfha.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (bairrosCadastrados.containsKey(bairro)) {
                        Bairro b = bairrosCadastrados.get(bairro);
                        if(b.getLogradouros() == null){
                            System.out.println("Logradouros nao encontrados");
                        } else{
                            logradourosCadastrados = b.getLogradouros();
                            for(Logradouro l : logradourosCadastrados.values()){
                                if(l.getBairro().getNomeBairro().equals(bairro)){
                                System.out.println(l);    
                                }
                            }
                        }
                    } else {
                        System.out.println("Bairro nao cadastrado");
                    }
                  break;
                }


            }
        
    


              
            }
    }

