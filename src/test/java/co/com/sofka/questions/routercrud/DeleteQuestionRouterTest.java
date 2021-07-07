package co.com.sofka.questions.routercrud;

import co.com.sofka.questions.usecase.UseCaseDeleteQuestions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DeleteQuestionRouter.class})
class DeleteQuestionRouterTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private UseCaseDeleteQuestions useCaseDeleteQuestions;

    @Test
    public void eliminarQUestionRoutertest(){

        Mockito.when(useCaseDeleteQuestions.deleteQuestion("1")).thenReturn(Mono.empty());


        webTestClient.delete()
                .uri("/eliminarpregunta/{id}","1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class)
                .value(response->{
                    Assertions.assertThat(response).isEqualTo("1");
                });


    }

}