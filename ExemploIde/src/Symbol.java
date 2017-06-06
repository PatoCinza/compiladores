/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 5670403
 */
public class Symbol {
    String id;
    String tipo;
    boolean inicializado = false;
    boolean usado = false;
    int escopo;
    boolean parametro = false;
    int pos;
    boolean vetor = false;
    boolean matriz = false;
    boolean referencia = false;
    boolean funcao = false;
    int tamanho;
    
    public void reseta(){
     id = null;
     tipo = null;
     inicializado = false;
     usado = false;
     escopo = 0;
     parametro = false;
     pos = 0;
     vetor = false;
     matriz = false;
     referencia = false;
     funcao = false;
     tamanho = 0;
    }
    
}
