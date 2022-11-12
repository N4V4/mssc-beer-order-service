package guru.sfg.beer.order.service.web.mappers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import guru.sfg.beer.order.service.domain.BeerOrderLine;
import guru.sfg.beer.order.service.services.beer.BeerService;
import guru.sfg.beer.order.service.web.model.BeerDto;
import guru.sfg.beer.order.service.web.model.BeerOrderLineDto;

public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper{
	
	private BeerOrderLineMapper beerOrderLineMapper;
	private BeerService beerService;
	
	@Autowired
	public void setBeerOrderLineMapper(BeerOrderLineMapper beerOrderLineMapper) {
		this.beerOrderLineMapper = beerOrderLineMapper;
	}
	
	@Autowired
	public void setBeerService(BeerService beerService) {
		this.beerService = beerService;
	}
	
	@Override
	public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine beerOrderLine) {
		
		BeerOrderLineDto orderLineDto = beerOrderLineMapper.beerOrderLineToDto(beerOrderLine);
		Optional<BeerDto> beerDtoOptional = beerService.getBeerByUpc(beerOrderLine.getUpc());
		
		beerDtoOptional.ifPresent(beerDto -> { 
			orderLineDto.setBeerStyle(beerDto.getBeerStyle().toString());
			orderLineDto.setPrice(beerDto.getPrice());
			orderLineDto.setBeerName(beerDto.getBeerName());
		} )
		;
			
		return orderLineDto;
	}

	@Override
	public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
		// TODO Auto-generated method stub
		return beerOrderLineMapper.dtoToBeerOrderLine(dto);
	}
	

	

}
