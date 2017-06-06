

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class PreenchedorTabela {
    private JTable tabela;
    private DefaultTableModel conteudo;

    public PreenchedorTabela(JTable tabela) {
        this.tabela = tabela;
    }
    
    public void atualizarDados(String[][] dados, String[] cabecalho){
        conteudo = new DefaultTableModel(dados, cabecalho);
        tabela.setModel(conteudo);
    }
    
    public String getNomeItem(int indice){
        return (String) conteudo.getValueAt(indice, 1);
    }
    

    /*public String getUltimaLinha(){
        int colunas = conteudo.getColumnCount();
        int ultimaLinha = conteudo.getRowCount()-1;
        String dados = "";
        for(int i=0; i<colunas; i++){
            dados = dados + "'" +(String) conteudo.getValueAt(ultimaLinha, i) + "'";
            if(i!=colunas-1){
                dados = dados + ",";
            }
        }
        String[] linhaBranco = {""};
        conteudo.addRow(linhaBranco);
        return dados;
    }
    
    public String getCabecalho(){
        int colunas = conteudo.getColumnCount();
        String cabecalho = "";
        for(int i=0; i<colunas; i++){
            cabecalho = cabecalho + "`" +(String) conteudo.getColumnName(i) + "`";
            if(i!=colunas-1){
                cabecalho = cabecalho + ",";
            }
        }
        return cabecalho;
    }
    
    public TableModel getConteudo(){
        return conteudo;
    }
    
    public void adicionaLinhaBranco(){
        String[] linhaBranco = {""};
        conteudo.addRow(linhaBranco);
    }
    
    public void removerUltimaLinha(){
        int num = conteudo.getRowCount()-1;
        conteudo.removeRow(num);
    }
    
    public void valorSelecionadoARemover(String catalogo, String tabela, int indice, GerenciadorDeDadosPostgress gerenciador) throws Exception{
        String valor  = (String) conteudo.getValueAt(indice, 0);
        if(valor=="" || valor==null){
            throw new Exception();
        }else{
            //gerenciador.removeLinha(catalogo, tabela, indice);
            conteudo.removeRow(indice);
        }
    }
    
    public void inserirNovaLinha(String dados, String catalogo, String tabela, GerenciadorDeDadosPostgress gerenciador ) throws Exception{
        gerenciador.inserirNovosDados(dados, tabela, getCabecalho(), getConteudo());
    }*/
}
