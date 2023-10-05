package com.example.tenpochallenge.dao.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.tenpochallenge.dao.RandomNumberDao;

@Repository
public class RandomNumberDaoImpl implements RandomNumberDao {
	
	@Override
	@Cacheable("miCache")
	public String getRandomNumber() throws Exception {
//			WebClient webClient = WebClient.create("https://aaa");
			WebClient webClient = WebClient.create("https://www.random.org");
			String responseMono = webClient.get().uri("/integers/?num=1&min=1&max=100&col=1&base=10&format=plain&rnd=new").retrieve().bodyToMono(String.class).block();
			return responseMono;
	}
	
//	@Override
//	public int getRandomNumber() {
//		Instant start = Instant.now();
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		objectMapper.setSerializationInclusion(Include.NON_NULL);
//		String urlEndpoint = ApplicationPropertiesResolver.getInstance().getProperties()
//				.getProperty(API_SALESFORCE_URL);
//		String apikey = (String) ApplicationPropertiesResolver.getInstance().get(APIKEY, "");
//		try {
//			ApplicationPropertiesResolver applicationPropertiesResolver = ApplicationPropertiesResolver.getInstance();
//			NetHttpClient httpClient = new NetHttpClient(StringUtils.EMPTY,
//					applicationPropertiesResolver.getProperties(), API_SALESFORCE);
//			httpClient.setUrl(urlEndpoint);
//			httpClient.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//			Map<String, String> headers = new HashMap<>();
//			headers.put(HEADER_AUTHORIZATION, BASIC.concat(apikey));
//			httpClient.setHeaders(headers);
//			Map<String, Object> parameterMap = new HashMap<>();
//			parameterMap.put("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
//			String params = objectMapper.writeValueAsString(sf);
//			String response = httpClient.execute(params);
//			if (response.contains("status\":\"OK"))
//				ejecucionExitosaNetUtilsMonitorMonitorSAM(
//						splitAndReturnComponentIdForMonitorSAM(urlEndpoint) + MONITOR_MESSAGE_ALTA, start);
//			else {
//				byte[] respuesta = response.getBytes(StandardCharsets.ISO_8859_1);
//				response = new String(respuesta, StandardCharsets.UTF_8);
//				response = response.substring(response.indexOf('[') + 1, response.indexOf(']'));
//				response = response.replaceAll(",", "<br>");
//				throw new TechnicalException(response);
//			}
//		} catch (JsonProcessingException e) {
//			logListener.onInfo("Error parseando objeto JSON - " + e.getMessage());
//			ejecucionFallidaNetUtilsMonitorSAM(
//					splitAndReturnComponentIdForMonitorSAM(urlEndpoint) + MONITOR_MESSAGE_ALTA, start, e);
//			throw new TechnicalException(SALESFORCE_ERROR_MESSAGE);
//		} catch (UnauthorizedException e) {
//			logListener.onInfo("Error de autorizacion - " + e.getMessage());
//			ejecucionFallidaNetUtilsMonitorSAM(
//					splitAndReturnComponentIdForMonitorSAM(urlEndpoint) + MONITOR_MESSAGE_ALTA, start, e);
//			throw new TechnicalException(SALESFORCE_ERROR_MESSAGE);
//		} catch (TechnicalException e) {
//			logListener.onInfo("Error en la llamada a la API Technical - " + e.getMessage());
//			ejecucionFallidaNetUtilsMonitorSAM(
//					splitAndReturnComponentIdForMonitorSAM(urlEndpoint) + MONITOR_MESSAGE_ALTA, start, e);
//			throw new BusinessException(e.getMessage());
//		} catch (Exception e) {
//			logListener.onInfo("Error en la llamada a la API - " + e.getMessage());
//			ejecucionFallidaNetUtilsMonitorSAM(
//					splitAndReturnComponentIdForMonitorSAM(urlEndpoint) + MONITOR_MESSAGE_ALTA, start, e);
//			throw new BusinessException(SALESFORCE_ERROR_MESSAGE);
//		}
//	}
	
}
