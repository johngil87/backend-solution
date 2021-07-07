package co.com.sofka.questions.routercrud;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecase.UseCaseListQuestion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ListarQuestionRouter {

    @Bean
    public RouterFunction<ServerResponse> listQuestions (UseCaseListQuestion listUseCase){
        return route(GET("/listarQuestion").and(accept(MediaType.APPLICATION_JSON)),
                request-> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listUseCase.listarPreguntas(), QuestionDTO.class)));
    }
}
