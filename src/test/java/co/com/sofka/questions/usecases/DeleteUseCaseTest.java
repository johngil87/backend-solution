package co.com.sofka.questions.usecases;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.usecase.UseCaseDeleteQuestions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeleteUseCaseTest {

    @MockBean
    private AnswerRepository answerRepository;
    @MockBean
    private QuestionRepository questionRepository;

    @SpyBean
    UseCaseDeleteQuestions useCaseDeleteQuestions;


    @Test
    public void eliminarQuestionTest(){

        var question = new QuestionDTO("12","1","que fue primero", "OPEN","xxx");
        var respuesta = new AnswerDTO();
        respuesta.setQuestionId("12");
        respuesta.setUserId("1");
        respuesta.setAnswer("la gallina");

        Mockito.when(questionRepository.deleteById("12")).thenReturn(Mono.empty());
        Mockito.when(answerRepository.deleteByQuestionId("12")).thenReturn(Mono.empty());

        var datos = useCaseDeleteQuestions.deleteQuestion("12").block();

        Assertions.assertEquals(datos,null);
    }

}