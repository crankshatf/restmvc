package ng.crankshatf.restmvc.api.v1.mapper;

import ng.crankshatf.restmvc.api.v1.model.VendorDTO;
import ng.crankshatf.restmvc.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendorMapperTest {

    public static final String NAME = "Namen";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDTO() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
        assertEquals(vendor.getName(), vendorDTO.getName());
    }

    @Test
    public void vendorDTOtoVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
        assertEquals(vendorDTO.getName(), vendor.getName());
    }
}
