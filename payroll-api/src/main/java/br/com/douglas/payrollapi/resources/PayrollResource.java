package br.com.douglas.payrollapi.resources;

import br.com.douglas.payrollapi.domain.Payroll;
import br.com.douglas.payrollapi.services.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/payments")
public class PayrollResource {

    private final PayrollService payrollService;


    @GetMapping(value = "/{workerId}")
    public ResponseEntity<Payroll> getPayment(@PathVariable Long workerId, @RequestBody Payroll payment) {

        return ResponseEntity.ok().body(
                payrollService.getPayment(workerId, payment)
        );
    }
}
