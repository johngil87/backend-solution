package co.com.sofka.questions.routercrud;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecase.UseCaseCreateQuestion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CrearQuestionRouter {

    @Bean
    public RouterFunction<ServerResponse> crearQuestion(UseCaseCreateQuestion useCaseCrearQuestion) {
        return route(POST("/crearquestion").and(accept(MediaType.APPLICATION_JSON)),//uso json
                request -> request.bodyToMono(QuestionDTO.class)
                        .flatMap(questionDTO -> useCaseCrearQuestion.insertar(questionDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)//tipo respuesta texto plano o json
                                        .bodyValue(result))
                        )
        );
    }
}
