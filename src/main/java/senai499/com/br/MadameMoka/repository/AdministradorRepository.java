package senai499.com.br.MadameMoka.repository;

import senai499.com.br.MadameMoka.model.Administrador;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRepository
        extends JpaRepository<Administrador, Long> {

    Optional<Administrador> findByEmail(String email);

    boolean existsByEmail(String email);

}