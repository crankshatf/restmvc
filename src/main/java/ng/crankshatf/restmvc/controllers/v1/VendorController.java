package ng.crankshatf.restmvc.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ng.crankshatf.restmvc.api.v1.model.VendorDTO;
import ng.crankshatf.restmvc.api.v1.model.VendorListDTO;
import ng.crankshatf.restmvc.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "Endpoints for Vendor resources")
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public final static String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "Get a list of vendors", notes = "Pagination and limit not yet supported")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getVendorList() {
        return vendorService.getAllVendors();
    }

    @ApiOperation(value = "Get a vendor by ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @ApiOperation(value = "Create a new vendor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.createNewVendor(vendorDTO);
    }

    @ApiOperation(value = "Update an existing vendor")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.saveVendorByDTO(id, vendorDTO);
    }

    @ApiOperation(value = "Update the properties of an existing vendor")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendor(id, vendorDTO);
    }

    @ApiOperation(value = "Delete a vendor")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
    }

}
