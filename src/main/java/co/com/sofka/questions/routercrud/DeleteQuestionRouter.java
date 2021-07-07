package co.com.sofka.questions.routercrud;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecase.UseCaseDeleteQuestions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteQuestionRouter {

    @Bean
    public RouterFunction<ServerResponse> elimiarQuestion(UseCaseDeleteQuestions useCaseDeleteQuestions){
        return route(DELETE("/eliminarpregunta/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().body(useCaseDeleteQuestions.deleteQuestion(request.pathVariable("id")), QuestionDTO.class));
    }
}
