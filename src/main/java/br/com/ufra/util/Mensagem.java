
package br.com.ufra.util;


public class Mensagem {
    private static final Mensagem uniqueInstance = new Mensagem();
    private static String erroAoLogar;
    private static String mensagemOperacao;
    private String mensagemServClient;
    
    private Mensagem(){
        
    }
    
    public static Mensagem getInstance(){
        return  uniqueInstance;
    }

    public static String getErroAoLogar() {
        return erroAoLogar;
    }

    public static void setErroAoLogar(String erroAoLogar) {
        Mensagem.erroAoLogar = erroAoLogar;
    }

    public static String getMensagemOperacao() {
        return mensagemOperacao;
    }

    public static void setMensagemOperacao(String mensagemOperacao) {
        Mensagem.mensagemOperacao = mensagemOperacao;
    }

    public String getMensagemServClient() {
        return mensagemServClient;
    }

    public void setMensagemServClient(String mensagemServClient) {
        this.mensagemServClient = mensagemServClient;
    }

    
    

}
