package livraria.imperial.compra;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/purchases", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PurchaseController {

    PurchaseService service;

    PurchaseMapper mapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PurchaseResponse create(@RequestBody PurchaseEntity purchase) {

        final var createdPurchase = service.create(purchase);

        final var response = mapper.convertEntityToResponse(createdPurchase);

        return response;
    }

    @GetMapping(value = "/{idPurchase}")
    public ResponseEntity<PurchaseResponse> find(@PathVariable Integer idPurchase) {

        final var foundPurchase = service.find(idPurchase);

        final var response = mapper.convertEntityToResponse(foundPurchase);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseResponse>> list(PurchaseEntity purchaseFilter) {
        List<PurchaseEntity> listOfPurchases = service.list(purchaseFilter);

        List<PurchaseResponse> responseList = mapper.convertEntityListToResponse(listOfPurchases);

        return ResponseEntity.ok(responseList);

    }

    @PutMapping(value = "/{idPurchase}")
    public ResponseEntity<PurchaseResponse> update(@PathVariable Integer idPurchase, @RequestBody PurchaseEntity purchase) {

        purchase.setId(idPurchase);

        final var updatedPurchase = service.update(purchase);

        final var response = mapper.convertEntityToResponse(updatedPurchase);

        return ResponseEntity.ok(response);

    }

    @GetMapping(value = "/total-profit")
    public ResponseEntity<ProfitResponse> amountOfBooksSelled() {
        return ResponseEntity.ok(service.getTotalProfit());
    }


}
