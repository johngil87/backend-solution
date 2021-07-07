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
public class UseCaseActulizarQuestion {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public UseCaseActulizarQuestion(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public Mono<QuestionDTO> actualizarQuestion(QuestionDTO questionDTO){
        var question = questionMapper.mapperToQuestion(questionDTO.getId()).apply(questionDTO);
        var respuesta = questionRepository.save(question);
        return respuesta.map(questionMapper.mapQuestionToDTO());
    }
}
