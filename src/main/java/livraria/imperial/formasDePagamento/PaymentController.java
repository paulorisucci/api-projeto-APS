package livraria.imperial.formasDePagamento;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/payments")
@AllArgsConstructor
public class PaymentController {

    PaymentService service;

    @GetMapping
    public ResponseEntity<List<PaymentEntity>> listar() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{idPayment}")
    public ResponseEntity<PaymentEntity> find(@PathVariable("idPayment") Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
