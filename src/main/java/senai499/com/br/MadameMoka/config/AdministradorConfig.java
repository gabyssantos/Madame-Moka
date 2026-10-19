package senai499.com.br.MadameMoka.config;

import senai499.com.br.MadameMoka.model.Administrador;
import senai499.com.br.MadameMoka.repository.AdministradorRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdministradorConfig {

    @Bean
    CommandLineRunner criarAdministrador(
            AdministradorRepository administradorRepository) {

        return args -> {

            if (!administradorRepository.existsByEmail(
                    "admin@madamemoka.com")) {

                Administrador administrador = new Administrador();

                administrador.setNome("Administrador");

                administrador.setEmail(
                        "admin@madamemoka.com"
                );

                administrador.setSenha("admin123");

                administradorRepository.save(administrador);
            }
        };
    }
}