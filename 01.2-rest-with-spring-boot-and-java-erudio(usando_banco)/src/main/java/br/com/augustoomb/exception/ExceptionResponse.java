package br.com.augustoomb.exception;

// RECORD SIMPLIFICA O USO DE CLASSES QUE VÃO ARMAZENAR DADOS. CRIA AUTOMATICAMENTO CONSTRUTOR E GETTERS/SETTERS
// NÃO PRECISO MAIS CRIAR NA MÃO CLASSE COM GET, SET, CONTRUTOR, EQUAL, HASHCODE, ETC

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {
}
