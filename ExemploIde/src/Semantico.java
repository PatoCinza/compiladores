
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Semantico implements Constants
{
    
    int contadorEscopo = 1;
    int contadorParam = 1;
    List<Symbol> listaSimbolos = new ArrayList<>();
    Stack pilhaEscopo = new Stack();
    Stack<Integer> pilhaExp = new Stack<>();
    Stack pilhaTipo = new Stack();
    SemanticTable tabela = new SemanticTable();
    Symbol s = new Symbol();
    Symbol s_aux = new Symbol();
    
    String nomeIdAtributo;
    public String assemblyCode = "";
    
    
    public void executeAction(int action, Token token)	throws SemanticError
    {
        String lexema = token.getLexeme();
        switch(action){
            case 1: //tipo
                //System.out.println("1 - Tipo: "+lexema);
                pilhaTipo.push(lexema);
                break;
            case 2: //id - função
                s.tipo = pilhaTipo.peek().toString();
                s.id = lexema;
                s.escopo = (int) pilhaEscopo.peek();
                s.funcao = true;
                for (Symbol sss : listaSimbolos) {
                        if(sss.id.equals(s.id) && sss.escopo == s.escopo){
                            throw new SemanticError("Função com nomes iguais no mesmo escopo!");
                        }
                }
                listaSimbolos.add(s);
                s = new Symbol();
                //System.out.println("2 - Func Id: "+lexema);
                break;
            case 3: //Inicio de Escopo
                pilhaEscopo.push(contadorEscopo);
                contadorEscopo++;
                //System.out.println("3 - Inicio de escopo: "+lexema);
                break;
            case 4: //Id de variaveis e vetores
                s.tipo = pilhaTipo.peek().toString();
                s.id = lexema;
                s.escopo = (int) pilhaEscopo.peek();
                for (Symbol sss : listaSimbolos) {
                        if(sss.id.equals(s.id) && sss.escopo == s.escopo){
                            throw new SemanticError("Variaveis com nomes iguais no mesmo escopo!");
                        }
                }
                //System.out.println("4 - Id: "+lexema);

                break;
            case 5: //Atribuição de variaveis e vetores
                for (Symbol simb : listaSimbolos) {
                    if(simb.id == lexema){
                        switch(simb.tipo){
                            case "int": pilhaExp.push(SemanticTable.INT); break;
                            case "string": pilhaExp.push(SemanticTable.STR); break;
                            case "real": pilhaExp.push(SemanticTable.FLO); break;
                            case "bool": pilhaExp.push(SemanticTable.BOO); break;
                        }
                        //System.out.println("Push PILHA: "+pilhaExp.peek());
                        //System.out.println("5 - Tipo da variavel que vai ser atribuida: "+simb.tipo);
                        break;
                    }
                }
                break;
            case 6: //Tipo de operando ou se for ID seta como usado // TIPO_ID
                Symbol simboloAtual = null;
                //int pos = 0 ;
                for (Symbol sim : listaSimbolos) {
                    if(sim.id.equals(lexema)){
                        Symbol y = sim;
                        listaSimbolos.remove(sim);
                        y.usado = true;
                        listaSimbolos.add(y);
                        switch(y.tipo){
                            case "int": pilhaExp.push(SemanticTable.INT); break;
                            case "string": pilhaExp.push(SemanticTable.STR); break;
                            case "real": pilhaExp.push(SemanticTable.FLO); break;
                            case "bool": pilhaExp.push(SemanticTable.BOO); break;
                        }
                        //System.out.println("Push PILHA: "+pilhaExp.peek());
                        //System.out.println("6 - o operador está na tabela de simbolos: "+lexema);
                        break;
                    }
                    //pos++;
                }
                
                break;
                
            case 7:{ //Tipo do operador
                int operador = 0;
                switch( lexema ) {
                    case "+":
                        operador = SemanticTable.SUM;
                        break;
                    case "-":
                        operador = SemanticTable.SUB;
                }

                
                pilhaExp.push(operador);
                //System.out.println("Push PILHA: "+pilhaExp.peek());
                //System.out.println("7 - Tipo operador: "+lexema);
                break;
            }
            case 8: //ID para atribuição FDI--------------------------------------------------------------
                boolean naoEncontrou = true;
                //localizar ID na tabela
                    for (Symbol sss : listaSimbolos) {
                        //System.out.println("FLAG");
                        if(sss.id.equals(lexema)){
                            s_aux = sss;
                            listaSimbolos.remove(sss);
                            naoEncontrou = false;
                            this.nomeIdAtributo = lexema;
                            switch(sss.tipo){
                            case "int": pilhaExp.push(SemanticTable.INT); break;
                            case "string": pilhaExp.push(SemanticTable.STR); break;
                            case "real": pilhaExp.push(SemanticTable.FLO); break;
                            case "bool": pilhaExp.push(SemanticTable.BOO); break;
                        }
                            //System.out.println("Push PILHA: "+pilhaExp.peek());
                            break;
                        }else{
                            naoEncontrou = true;
                       }
                    }
                    if(naoEncontrou){
                      throw new SemanticError("Variavel não declarada");
                    }
                        if(pilhaEscopo.search(s_aux.escopo)==-1){
                             
                             throw new SemanticError("Escopo da variavel incorreto");
                        }else{
                            listaSimbolos.add(s_aux);
                            s_aux.inicializado=true;
                        }
                
                //System.out.println("8 - Atribuição, ID da variavel: "+lexema);
                break;
                    // int a; = 10 * c * 8, b;
            case 10: //Fim de escopo
                pilhaEscopo.pop();
                //System.out.println("10 - Fim de escopo");
                break;
            case 12: //Constante INT, BIN e HEX
//                pilhaExp.push("DEC");
                pilhaExp.push( SemanticTable.INT );
                
                //System.out.println("12 - Tipo operador: "+"DEC");
                //System.out.println("Push PILHA: "+pilhaExp.peek());
                break;
                
            
            case 13: //Constante FLOAT
//                pilhaExp.push("REAL");
                pilhaExp.push(SemanticTable.FLO);
                //System.out.println("Push PILHA: "+pilhaExp.peek());
                //System.out.println("13 - Tipo operador: "+"FLOAT");
                break;
            case 14: //Constante String
//                pilhaExp.push("TEXTO");
                pilhaExp.push(SemanticTable.STR);
                //System.out.println("Push PILHA: "+pilhaExp.peek());
                //System.out.println("14 - Tipo operador: "+"TEXTO");
                break;
            case 15: //Constante Bool(
//                pilhaExp.push("bool");
                pilhaExp.push(SemanticTable.BOO);
                //System.out.println("Push PILHA: "+pilhaExp.peek());
                //System.out.println("15 - Tipo operador: "+"BOOLEAN");
                break;
//            case 16: //)
//                pilhaExp.push(lexema);
//                System.out.println("16 - Tipo operador: "+lexema);
//                break;
            case 18: //FDI em declaração
                listaSimbolos.add(s);
                pilhaTipo.pop();
                s = new Symbol();
                //System.out.println("18 - FDI de declaracao: "+lexema);
                break;
            case 19: //Declara vetor
                s.vetor=true;
                //System.out.println("19 - Declara vetor: "+lexema);
                break;
            case 20: //Parametros de funcao
                s.tipo = pilhaTipo.peek().toString();
                s.id = lexema;
                s.escopo = ((int) pilhaEscopo.peek());
                s.parametro=true;
                s.pos = contadorParam;
                listaSimbolos.add(s);
                s = new Symbol();
                contadorParam++;
                //System.out.println("20 - Novo parametro: "+lexema);
                break;
            case 21: //Encerra funcao
                s = new Symbol();
                contadorParam = 1;
                //pilhaEscopo.pop();
                //System.out.println("21 - Encerra função: "+lexema);
                break;
            case 22: {
                this.assemblyCode += ("STO "+this.nomeIdAtributo+"\n");
                //break;
                //System.out.println("POP PILHA: "+pilhaExp.peek());
                int opEsq = pilhaExp.pop();
                //System.out.println("POP PILHA: "+pilhaExp.peek());
                int opDir = pilhaExp.pop();
                
                int atribType = SemanticTable.atribType(opEsq, opDir); 
                if( atribType == SemanticTable.ERR ) {
                    throw new SemanticError("Tipos incompativeis na atribuicao",token.getPosition());
                }
                if( atribType == SemanticTable.WAR ) {
                    //System.out.println("WARNING na atribuicao");
                }
              
                break;
            }
            case 49:
                s.tamanho = Integer.parseInt(lexema);
                break;
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57: {
                if(pilhaExp.empty()){
                    //System.out.println("flasd");
                }
                //System.out.println("POP PILHA: "+pilhaExp.peek());
                int op2 = pilhaExp.pop();
                //System.out.println("POP PILHA: "+pilhaExp.peek());
                int oper = pilhaExp.pop();
                //System.out.println("POP PILHA: "+pilhaExp.peek());
                int op1 = pilhaExp.pop();
                if( op1 == SemanticTable.ERR || op2 == SemanticTable.ERR ) {
                    throw new SemanticError("Operando com ERRO",token.getPosition());
                }
                
                int resultType = SemanticTable.resultType(op1, op2, oper);
                if( resultType == SemanticTable.ERR ) {
                    throw new SemanticError("Tipos incompativeis na expressao",token.getPosition());
                }
                
                pilhaExp.push(resultType);
                //System.out.println("Push PILHA: "+pilhaExp.peek());
                break;
            }
        }
        
    }	
}
