package guru.sfg.beer.order.service.services.beer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import guru.sfg.beer.order.service.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;

@Profile("!local-discovery")
@Slf4j
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = true)
@Component
public class BeerServiceRestTemplateImpl implements BeerService {
	
    public static final String BEER_SERVICE_PATH = "/api/v1";
    private final RestTemplate restTemplate;

    private String beerServiceHost;

    public void setBeerServiceHost(String beerInventoryServiceHost) {
        this.beerServiceHost = beerInventoryServiceHost;
    }

    public BeerServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder                                              ) {
        this.restTemplate = restTemplateBuilder
                .build();
    }

	@Override
	public Optional<BeerDto> getBeerById(UUID beerId) {
		// TODO Auto-generated method stub
		ResponseEntity<BeerDto> responseEntity = restTemplate.exchange(beerServiceHost+BEER_SERVICE_PATH+"/beer/{beerId}", HttpMethod.GET,null,
				new ParameterizedTypeReference<BeerDto>(){}, (Object) beerId);
		return Optional.of(responseEntity.getBody());
	}

	@Override
	public Optional<BeerDto> getBeerByUpc(String upc) {
		// TODO Auto-generated method stub
		ResponseEntity<BeerDto> responseEntity = restTemplate.exchange(beerServiceHost+BEER_SERVICE_PATH+"/beerUpc/{upc}", HttpMethod.GET,null,
				new ParameterizedTypeReference<BeerDto>(){}, (Object) upc);
		return Optional.of(responseEntity.getBody());
	}

}
