package com.connection.Practice.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.connection.Practice.model.EmiModel;
import com.connection.Practice.model.EmiResponseModel;
import com.connection.Practice.model.Emidto;
import com.connection.Practice.model.ForeclosureDTO;
import com.connection.Practice.model.ForeclosureModel;
import com.connection.Practice.model.ForeclosureResponseModel;
import com.connection.Practice.service.IEmiForeclosure;
import com.connection.Practice.service.impl.EmiService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequestMapping("/v1/loans")
public class EmiController {

	@Autowired(required = false)
	IEmiForeclosure emiserviceForeclosure;
	@Autowired
	EmiService emiservice;

	/**
	 * 
	 * @param emidto object of Emidto class that is used to hold the data for the
	 *               call
	 * @throws Exception throws exception if there is error to call the service call
	 *                   method
	 */

//	@PostMapping("/emi/schedule")
//	public ResponseEntity<EmiResponseModel> emiGenetarion(@RequestBody @Valid  Emidto emidto) throws Exception {
//		log.info("Inside controller");
//		EmiModel model=EmiModel.builder().loanAmount(Integer.parseInt(emidto.getLoanAmount())).
//				rateOfInterest(emidto.getRateOfInterest()).
//				tenure(Integer.parseInt(emidto.getTenure())).
//				build();
//	   return new ResponseEntity<>(emiservice.GenerateEMI(model),HttpStatus.CREATED);
//	  
//
//	}

	@PostMapping("/emi/schedule")
	public ResponseEntity<EmiResponseModel> emiGenetarion(@RequestBody @Valid Emidto emidto) throws Exception {

//        EmiModel model=new EmiModel(Integer.parseInt(emidto.getLoanAmount()),emidto.getRateOfInterest(),Integer.parseInt(emidto.getTenure()));

		EmiModel model = new EmiModel(Integer.parseInt(emidto.getLoanAmount()), emidto.getRateOfInterest(),
				Integer.parseInt(emidto.getTenure()));
		return new ResponseEntity<>(emiservice.GenerateEMI(model), HttpStatus.CREATED);

	}

//	@PostMapping("/emi/foreclosure")
//	public ForeclosureResponseModel emiGenetarion(@RequestBody @Valid ForeclosureDTO foreclosureDTO) throws Exception {
//
//		ForeclosureModel model = new ForeclosureModel(Integer.parseInt(foreclosureDTO.getLoanAmount()),
//				foreclosureDTO.getRateOfInterest(), Integer.parseInt(foreclosureDTO.getTenure()),
//				Integer.parseInt(foreclosureDTO.getInstallmentPaid()));
//		return emiserviceForeclosure.GenerateEMI(model);
//
//	}

}
