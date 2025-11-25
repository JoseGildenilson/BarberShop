package barbershop.model.interfaces;

import java.time.LocalDateTime;

public interface Agendavel {
    boolean verificarDisponibilidade(LocalDateTime dataHora);
    void agendar(LocalDateTime dataHora);
}