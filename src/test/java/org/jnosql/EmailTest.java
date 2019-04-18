package org.jnosql;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmailTest {


    @Test
    public void deveRetornarErroQuandoForNulo() {
        Assertions.assertThrows(NullPointerException.class,  () -> Email.of(null));
    }

    @Test
    public void deveRetornarErroQuandoEmailForInvalido() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Email.of("invalid.email"));
    }

    @Test
    public void deveCriarInstanciaEmail() {
        assertNotNull(Email.of("email@email.com"));
        assertNotNull(Email.of("email.test@email.com"));
        assertNotNull(Email.of("email.test@gmail.com.br"));
        Email email = Email.of("gmail@gmail.com");
        assertNotNull(email);
        Assertions.assertEquals("gmail@gmail.com", email.get());
    }
}