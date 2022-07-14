package br.com.devdojo.endpoint;
import static org.junit.Assert.*;
public class Login {

    public void testLogin(){
        String RetornoEsperado = "Logado!";
        String RetornoFeito = this.Login("bea","123");
        assertEquals(RetornoEsperado, RetornoFeito );
        RetornoEsperado = "Usuário ou senha incorretos!";
        RetornoFeito = this.Login("bea","1234");
        assertEquals(RetornoEsperado, RetornoFeito);
    }

    public static String Login(String name, String senha){
        String mockName="bea";
        String mockSenha="123";
        if(name==mockName && senha==mockSenha){
            return "Logado!";
        }else{
            return "Usuário ou senha incorretos!";
        }
    }
}

