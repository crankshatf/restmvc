package ng.crankshatf.restmvc.api.v1.mapper;

import ng.crankshatf.restmvc.api.v1.model.VendorDTO;
import ng.crankshatf.restmvc.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOtoVendor(VendorDTO vendorDTO);
}
