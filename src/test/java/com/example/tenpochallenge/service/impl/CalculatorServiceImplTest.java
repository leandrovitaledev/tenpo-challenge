package com.example.tenpochallenge.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.tenpochallenge.DTO.CalculatorDTO;
import com.example.tenpochallenge.dao.RandomNumberDao;
import com.example.tenpochallenge.dao.RegistroHistorialDao;
import com.example.tenpochallenge.entity.RegistroHistorial;


@ExtendWith(MockitoExtension.class)
public class CalculatorServiceImplTest {
	
	@InjectMocks
	private CalculatorServiceImpl calculatorService;

	@Mock
	private RandomNumberDao randomNumberDao;

	@Mock
	private RegistroHistorialDao registroHistorialDao;

	@Test
	    public void testGetResult() throws Exception {
	        // Configura el comportamiento de los objetos simulados (Mocks)
	        when(randomNumberDao.getRandomNumber()).thenReturn("5");

	        // Crea un objeto CalculatorDTO para simular la entrada
	        CalculatorDTO dto = new CalculatorDTO(90, 10);

	        // Llama al método que deseas probar
	        double result = calculatorService.getResult(dto);

	        // Verifica que se haya llamado a los métodos esperados
	        verify(randomNumberDao, times(1)).getRandomNumber();
	        verify(registroHistorialDao, times(1)).save(any(RegistroHistorial.class));

	        assertEquals(105.0, result);
	        // Realiza afirmaciones para verificar el resultado
	        // Asegúrate de que el resultado sea el esperado
	        // (dependiendo de los valores de entrada y el comportamiento simulado)
	    }
	
    @Test
    public void testGetRegistry() {
        // Configurar el comportamiento del mock registroHistorialDao
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);

        // Crear una lista de registros simulados
        List<RegistroHistorial> registrosSimulados = new ArrayList<>();
        // Agregar registros simulados a la lista (puedes crear registros de ejemplo)
        registrosSimulados.add(new RegistroHistorial(80, 20, 10, 110));
        registrosSimulados.add(new RegistroHistorial(90, 10, 5, 105));
        registrosSimulados.add(new RegistroHistorial(30, 70, 55, 155));

        // Crear un objeto Page simulado utilizando PageImpl
        Page<RegistroHistorial> pageSimulado = new PageImpl<>(registrosSimulados, pageable, registrosSimulados.size());

        // Configurar el comportamiento del mock para devolver la página simulada
        when(registroHistorialDao.findAll(pageable)).thenReturn(pageSimulado);

        // Llamar al método getRegistry y obtener el resultado
        Page<RegistroHistorial> result = calculatorService.getRegistry(page, size);

        // Realizar las aserciones necesarias
        assertNotNull(result);
        assertEquals(registrosSimulados.size(), result.getContent().size());
        // Agregar más aserciones según tus necesidades

        // Verificar que el método findAll del mock se haya llamado con los argumentos correctos
        verify(registroHistorialDao).findAll(pageable);
    }

//	    /**
//	     * @author agustinadrian.conde
//	     * @implNote Test de service cotizacionHistorica
//	     */
//	    @Test
//	    @DisplayName("Test de service cotizacionHistorica")
//	    void cotizacionHistoricaTest() {
	//
//	        //Mock
//	        ConsultaHistoricoDTO param = new ConsultaHistoricoDTO();
	//
//	        List<CotizacionHistoricaBO> cotizacionHistoricaBO = new ArrayList<>();
//	        cotizacionHistoricaBO.add(new CotizacionHistoricaBO());
//	        cotizacionHistoricaBO.add(new CotizacionHistoricaBO());
	//
//	        when(inversionesDao.cotizacionHistorica(any())).thenReturn(cotizacionHistoricaBO);
	//
	//
//	        //Ejecucion
//	        List<CotizacionHistoricaDTO> response = inversionesService.cotizacionHistorica(param);
	//
//	        //when(clienteBo.getNumeroClienteAltamira()).thenReturn("");
	//
//	        //Asserts
//	        assertNotNull(response);
//	        assertEquals(2, response.size());
//	    }
	//
//	    /**
//	     * @author agustinadrian.conde
//	     * @implNote Test de service ejecucionSimulacionFCI
//	     */
//	    @Test
//	    @DisplayName("Test de service ejecucionSimulacionFCI")
//	    void ejecucionSimulacionFCITest() {
	//
//	        //Mock
//	        AltaFCIDTO param = new AltaFCIDTO();
//	        param.setIdProductoCuentaCustodia("1234");
//	        param.setIdProductoCuentaVinculada("12345");
//	        param.setFondo("fondo");
//	        param.setDescripcion("descripcion");
	//
//	        when(cuentaService.getCuentaMonetariaById(param.getIdProductoCuentaVinculada())).thenReturn(null);
//	        when(cuentaCustodiaService.getCuentaCustodiaById(param.getIdProductoCuentaCustodia())).thenReturn(null);
//	        when(cuentaCustodiaDao.cotizacionesFondosComunesInversion()).thenReturn(null);
//	        when(cuentaCustodiaDao.obtenerNombreComercialFci(any(), any())).thenReturn("FBA RENAPEA");
//	        doNothing().when(inversionesDao).ejecucionSimulacionSuscripcionFCI(any());
	//
//	        //Ejecucion
//	        AltaFCIDTO response = inversionesService.ejecucionSimulacionFCI(param);
	//
//	        //Asserts
//	        assertNotNull(response);
//	        assertEquals(param.getFondo(), response.getFondo());
//	        assertEquals("FBA RENAPEA", response.getDescripcion());
//	        assertEquals(param.getIdProductoCuentaCustodia(), response.getIdProductoCuentaCustodia());
//	        assertEquals(param.getIdProductoCuentaVinculada(), response.getIdProductoCuentaVinculada());
//	    }

}