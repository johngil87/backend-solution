package co.com.sofka.questions.usecase;

import co.com.sofka.questions.mapper.QuestionMapper;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseGetQuestion {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public UseCaseGetQuestion(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public Mono<QuestionDTO> consultarQuestionId(String id){
        var respuesta = questionRepository.findById(id);
        return respuesta.map(questionMapper.mapQuestionToDTO());
    }
}
