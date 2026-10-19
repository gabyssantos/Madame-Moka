package senai499.com.br.MadameMoka.repository;

import senai499.com.br.MadameMoka.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    long countByStatus(String status);

    List<Pedido> findByClienteIdOrderByDataDesc(Long clienteId);

}