package co.com.sofka.questions.usecase;

import co.com.sofka.questions.mapper.QuestionMapper;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseDeleteQuestions {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final AnswerRepository answerRepository;

    @Autowired
    public UseCaseDeleteQuestions(QuestionRepository questionRepository, QuestionMapper questionMapper, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
        this.answerRepository = answerRepository;
    }

    public Mono<Void> deleteQuestion(String id){
        return  questionRepository.deleteById(id).switchIfEmpty(answerRepository.deleteByQuestionId(id));
    }
}
