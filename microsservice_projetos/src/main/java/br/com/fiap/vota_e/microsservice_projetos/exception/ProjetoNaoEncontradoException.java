package br.com.fiap.vota_e.microsservice_projetos.exception;

public class ProjetoNaoEncontradoException extends RuntimeException {
    public ProjetoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
