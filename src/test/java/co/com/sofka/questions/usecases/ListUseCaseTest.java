package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.usecase.UseCaseListQuestion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ListUseCaseTest {

    @MockBean
    private QuestionRepository questionRepository;
    @SpyBean
    private UseCaseListQuestion useCaseListQuestion;

    @Test
    public void useCaseListQuestionTest(){

        var question= new Question();
        question.setId("12");
        question.setQuestion("que fue primero");
        question.setUserId("1");
        question.setType("OPEN");
        question.setCategory("xxx");
        var question2= new Question();
        question.setId("12");
        question.setQuestion("que fue primero");
        question.setUserId("1");
        question.setType("OPEN");
        question.setCategory("xxx");

        Mockito.when(questionRepository.findAll()).thenReturn(Flux.just(question,question2));

        var list = useCaseListQuestion.listarPreguntas();

        Assertions.assertEquals(list.count().block(),2);
    }
}