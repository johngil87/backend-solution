package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.usecase.UseCaseActulizarQuestion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.when;

@SpringBootTest
class UpdateUseCaseTest {


    @SpyBean
    private UseCaseActulizarQuestion useCaseActulizarQuestion;
    @MockBean
    private QuestionRepository questionRepository;

    @Test
    public void editarQuestionTest(){

        var question = new QuestionDTO("12","1","que fue primero", "OPEN","xxx");
        var questionResponse= new Question();
        questionResponse.setId("12");
        questionResponse.setQuestion("que fue primero");
        questionResponse.setUserId("1");
        questionResponse.setType("OPEN");
        questionResponse.setCategory("xxx");

       when(questionRepository.save(Mockito.any(Question.class))).thenReturn(Mono.just(questionResponse));

       var spy = useCaseActulizarQuestion.actualizarQuestion(question);

        Assertions.assertEquals(spy.block().getQuestion(), "que fue primero");
    }

}