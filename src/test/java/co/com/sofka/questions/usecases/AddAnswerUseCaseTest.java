package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.usecase.UseCaseAddAnswer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

@SpringBootTest
class AddAnswerUseCaseTest {

    @SpyBean
    private UseCaseAddAnswer useCaseAddAnswer;
    @MockBean
    private AnswerRepository answerRepository;

    @Test
    public void setUseCaseAddAnswerTest(){
        var answerDTO = new AnswerDTO("1","12","la gallina fue");
        var answer = new Answer();
        answer.setQuestionId("1");
        answer.setAnswer("la gallina fue");
        answer.setUserId("12");

        Mockito.when(answerRepository.save(Mockito.any(Answer.class))).thenReturn(Mono.just(answer));

        var respuesta = useCaseAddAnswer.addAnswer(answerDTO).block();

        Assertions.assertEquals(respuesta.getAnswer(), "la gallina fue");


    }
}