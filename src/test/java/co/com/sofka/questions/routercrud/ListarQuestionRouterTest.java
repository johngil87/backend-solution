package co.com.sofka.questions.routercrud;


import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecase.UseCaseListQuestion;
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
import reactor.core.publisher.Flux;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ListarQuestionRouter.class})
class ListarQuestionRouterTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    UseCaseListQuestion useCaseListQuestion;

    @Test
    public void listarQuestionTest(){

        var questionDTO = new QuestionDTO("12","1","que fue primero", "OPEN","xxx");

        Mockito.when(useCaseListQuestion.listarPreguntas()).thenReturn(Flux.just(questionDTO));

        webTestClient.get().uri("/listarQuestion")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(QuestionDTO.class)
                .value(respuesta-> {
                    Assertions.assertThat(respuesta.get(0).getQuestion()).isEqualTo("que fue primero");
                        });

    }

}