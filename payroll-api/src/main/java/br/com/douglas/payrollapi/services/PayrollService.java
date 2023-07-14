package br.com.douglas.payrollapi.services;

import br.com.douglas.payrollapi.domain.Payroll;
import br.com.douglas.payrollapi.feignClients.UserFeign;
import br.com.douglas.userapi.services.exceptions.ObjectNotFoundException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
@Service
public class PayrollService {

    private final Environment env;
    private final UserFeign userFeign;

    public Payroll getPayment(Long workerId, Payroll payroll) {
        log.info("PAYROLL_SERVICE :: GET Request on " + env.getProperty("local.server.port") + " port");
        try{
            var user = userFeign.findById(workerId).getBody();
            if (Objects.nonNull(user)) {
                return new Payroll(user.getName(),
                        payroll.getDescription(),
                        user.getHourlyPrice(),
                        payroll.getWorkedHours(),
                        user.getHourlyPrice() * payroll.getWorkedHours());
            }
        } catch (FeignException.NotFound ex) {
           throw new ObjectNotFoundException("User not found");
        } catch (Exception ex) {
            throw new IllegalArgumentException("Unexpected error");
        }
        return null;
    }
}
