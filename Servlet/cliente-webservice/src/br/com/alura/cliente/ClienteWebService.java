package br.com.alura.cliente;


import org.apache.http.client.fluent.Request;

public class ClienteWebService {

    public static void main(String[] args) throws Exception{

        String conteudo = Request.Post("endereço desejado")
                .addHeader("Accept", "application/json")
                .execute()
                .returnContent()
                .asString();

        System.out.println(conteudo);
    }
}
