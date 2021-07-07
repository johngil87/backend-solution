package co.com.sofka.questions.routercrud;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecase.UseCaseActulizarQuestion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ActualizarQuestionRouter {

     @Bean
     public RouterFunction<ServerResponse> editarQuestion(UseCaseActulizarQuestion useCaseActulizarQuestion){
         return route(PUT("/editarRecurso").and(accept(MediaType.APPLICATION_JSON)),
                 request -> request.bodyToMono(QuestionDTO.class)
                         .flatMap(questionDTO -> useCaseActulizarQuestion.actualizarQuestion(questionDTO)
                                 .flatMap(result ->
                                         ServerResponse.ok()
                                         .contentType(MediaType.APPLICATION_JSON)
                                         .bodyValue(result))
                         ));
     }
}
