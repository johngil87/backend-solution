package co.com.sofka.questions.usecase;

import co.com.sofka.questions.mapper.QuestionMapper;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseListQuestion {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public UseCaseListQuestion(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public Flux<QuestionDTO> listarPreguntas(){
        return questionRepository.findAll().map(questionMapper.mapQuestionToDTO());
    }
}
