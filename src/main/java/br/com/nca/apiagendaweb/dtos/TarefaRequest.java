package br.com.nca.apiagendaweb.dtos;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TarefaRequest {

    @Size(min = 6, max = 150, message = "Informe no mínimo 6 caracteres e no máximo 150 caracteres.")
    @NotBlank(message = "Por favor, informe o nome da tarefa.")
    private String nome;

    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "A data deve estar no formato yyyy-MM-dd."
    )
    @NotBlank(message = "Por favor, informe a data da tarefa.")
    private String data;

    @Pattern(
            regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$",
            message = "A hora deve estar no formato HH:mm (00:00 até 23:59)."
    )
    @NotBlank(message = "Por favor, informe o nome da tarefa.")
    private String hora;


    @NotBlank(message = "Por favor, informe a prioridade da tarefa.")
    @Pattern(
            regexp = "^(ALTA|MEDIA|BAIXA)$",
            message = "A prioridade deve ser ALTA, MEDIA ou BAIXA."
    )
    private String prioridade;

    @NotNull(message = "Por favor, informe a categoria da tarefa.")
    private UUID categoriaId;
}
