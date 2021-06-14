package by.auto.artur.mapper;

import by.auto.artur.dto.AdvertisementDto;
import by.auto.artur.entity.Advertisement;
import org.mapstruct.Mapper;

import java.util.List;

/**
 *
 *
 *
 *
 *@Author ArturVazniak
 */

@Mapper(componentModel = "spring")
public interface AdvertisementMapper {

    AdvertisementDto advertisementToDto(Advertisement advertisement);


    List<AdvertisementDto> advertisementListToDto(List<Advertisement> advertisement);


    Advertisement DtoFromAdvertisement(AdvertisementDto advertisementDto);
}
