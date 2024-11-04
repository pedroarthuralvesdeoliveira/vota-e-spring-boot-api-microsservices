package br.com.fiap.vota_e.microsservice_sugestoes.exception;

public class SugestaoNaoEncontradaException extends RuntimeException {
    public SugestaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
