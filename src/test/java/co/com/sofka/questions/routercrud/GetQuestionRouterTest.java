package co.com.sofka.questions.routercrud;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecase.UseCaseGetQuestion;
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
@ContextConfiguration(classes = {ConsultarQuestionRouter.class})
class GetQuestionRouterTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private UseCaseGetQuestion useCaseGetQuestion;

    @Test
    public void getQuestionTest(){

        var questionDTO = new QuestionDTO("12","1","que fue primero", "OPEN","xxx");

        Mockito.when(useCaseGetQuestion.consultarQuestionId(Mockito.any(String.class))).thenReturn(Mono.just(questionDTO));

        webTestClient.get().uri("/consultarquestion/{id}", "12")
                .exchange()
                .expectStatus().isOk()
                .expectBody(QuestionDTO.class)
                .value(respuesta -> {
                    Assertions.assertThat(respuesta.getQuestion()).isEqualTo("que fue primero");
                });
    }

}