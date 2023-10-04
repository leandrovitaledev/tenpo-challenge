package com.example.tenpochallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tenpochallenge.DTO.CalculatorDTO;
import com.example.tenpochallenge.entity.RegistroHistorial;
import com.example.tenpochallenge.exception.CustomException;
import com.example.tenpochallenge.service.CalculatorService;

@RestController
@RequestMapping("/api")
public class CalculatorController {
	
	@Autowired
	private CalculatorService calculatorService;
	
//	/**
//	 * Servicio 025 - Servicio consulta cuentas custodias
//	 * 
//	 * @return
//	 */
//	@ApiMethod(
//	        path = "/",
//	        description = "Tx: CL43",
//	        produces = { MediaType.APPLICATION_JSON_VALUE },
//	        consumes = { MediaType.APPLICATION_JSON_VALUE },
//	        responsestatuscode = "200 - OK",
//	        visibility = ApiVisibility.PRIVATE,
//	        summary ="Servicio 025 - Servicio consulta cuentas custodias",
//	    	verb = ApiVerb.GET
//	)
//	@ApiErrors(apierrors={
//	        @ApiError(code="500", description="Error tecnico <Esta transaccion no puede ser realizada en este momento>"),
//	        @ApiError(code="403", description="Un mensaje con la regla de negocio no cumplida")
//	})
//	@RequestMapping(value = StringUtils.EMPTY, method = RequestMethod.GET)
//	public @ApiResponseObject FnetResponse<CuentaCustodiaResponse> cuentasCustodia(FnetResponse<CuentaCustodiaResponse> response) {
//		response.setResult(cuentaCustodiaService.getCuentasCustodia());
//		return response.toStatus200OK();
//	}
//	
//	/**
//	 * Servicio consulta saldos de una cuenta custodia para Rescate
//	 * 
//	 * @return
//	 */
//	@ApiMethod(
//	        path = "/saldos/rescate",
//	        description = "Tx: TM_BD_CONSULTA_SALDOS",
//	        produces = { MediaType.APPLICATION_JSON_VALUE },
//	        consumes = { MediaType.APPLICATION_JSON_VALUE },
//	        responsestatuscode = "200 - OK",
//	        visibility = ApiVisibility.PRIVATE,
//	        summary ="294. Servicio consulta saldos de una cuenta custodia",
//	    	verb = ApiVerb.POST
//	)
//	@ApiErrors(apierrors={
//	        @ApiError(code="500", description="Error tecnico <Esta transaccion no puede ser realizada en este momento>"),
//	        @ApiError(code="403", description="Un mensaje con la regla de negocio no cumplida")
//	})
//	@RequestMapping(value = "/saldos/rescate", method = RequestMethod.POST)
//	public @ApiResponseObject FnetResponse<SaldosCuentaCustodiaResponse> saldosCuentasCustodiaRescate(@ApiBodyObject @RequestBody ConsultaSaldosCuentaCustodiaDTO cuenta,
//			FnetResponse<SaldosCuentaCustodiaResponse> response, SaldosCuentaCustodiaResponse result) {
//		response.setResult(cuentaCustodiaService.saldosCuentasCustodia(cuenta, DatabaseConstants.CONSULTA_SALDOS_L));
//		return response.toStatus200OK();
//	}

	@GetMapping("/calculator")
	public double getResult(@RequestBody CalculatorDTO dto) throws Exception {
		 return calculatorService.getResult(dto);
	}
	
	@GetMapping("/registry")
	public List<RegistroHistorial> getRegistry() throws Exception {
		 return calculatorService.getRegistry();
	}
	
	@ExceptionHandler(CustomException.class)
    public CustomException manejarMiExcepcion(CustomException ex) {
        return ex;
    }
	
}