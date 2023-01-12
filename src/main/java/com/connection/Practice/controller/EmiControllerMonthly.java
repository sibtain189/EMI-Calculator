package com.connection.Practice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connection.Practice.model.EmiDelayDto;
import com.connection.Practice.model.EmiDelayModel;
import com.connection.Practice.model.EmiDelayResponseModel;
import com.connection.Practice.model.EmiModel;
import com.connection.Practice.model.EmiPauseModel;
import com.connection.Practice.model.EmiPauseResponseModel;
import com.connection.Practice.model.EmiResponseModel;
import com.connection.Practice.model.Emidto;
import com.connection.Practice.model.ForeclosureDTO;
import com.connection.Practice.model.ForeclosureModel;
import com.connection.Practice.model.ForeclosureResponseModel;
import com.connection.Practice.service.IEmiDelayService;
import com.connection.Practice.service.IEmiForeclosure;
import com.connection.Practice.service.impl.EmiPause;
import com.connection.Practice.service.impl.EmiServiceMonthly;

@RestController
@RequestMapping("/v2/loans/emi")
public class EmiControllerMonthly {

	@Autowired
	EmiServiceMonthly emiMonthlyService;

	@Autowired
	private IEmiDelayService emiDelayService;

	@Autowired
	IEmiForeclosure emiForeclosueService;

	@Autowired
	EmiPause emiPause;

	@PostMapping("/schedule/monthly")
	public ResponseEntity<EmiResponseModel> emiGenetarion(@RequestBody @Valid Emidto emidto) throws Exception {

//        EmiModel model=new EmiModel(Integer.parseInt(emidto.getLoanAmount()),emidto.getRateOfInterest(),Integer.parseInt(emidto.getTenure()));

		EmiModel model = new EmiModel(Integer.parseInt(emidto.getLoanAmount()), emidto.getRateOfInterest(),
				Integer.parseInt(emidto.getTenure()));
		return new ResponseEntity<>(emiMonthlyService.GenerateEMI(model), HttpStatus.CREATED);

	}

	@PostMapping("/schedule/delay")
	public ResponseEntity<EmiDelayResponseModel> calculationOfExtraInterest(@RequestBody @Valid EmiDelayDto emiDelayDto)
			throws Exception {
		EmiDelayModel emiDelayModel = new EmiDelayModel(Double.parseDouble(emiDelayDto.getEmi()),
				Integer.parseInt(emiDelayDto.getNumberOfDaysDelayed()), emiDelayDto.getPenaltyRateOfInterest());
		return new ResponseEntity<>(emiDelayService.calculateDelayedInterest(emiDelayModel), HttpStatus.CREATED);
	}

	@PostMapping("/schedule/foreclosure")
	public ForeclosureResponseModel emiGenetarion(@RequestBody @Valid ForeclosureDTO foreclosureDTO) throws Exception {

		ForeclosureModel model = new ForeclosureModel(Integer.parseInt(foreclosureDTO.getLoanAmount()),
				foreclosureDTO.getRateOfInterest(), Integer.parseInt(foreclosureDTO.getTenure()),
				Integer.parseInt(foreclosureDTO.getInstallmentPaid()));
		return emiForeclosueService.GenerateEMI(model);

	}

	@PostMapping("/schedule/emiPause")
	public ResponseEntity<EmiPauseResponseModel> emiGenetarion(@RequestBody EmiPauseModel pausemodel) throws Exception {
//		log.info("Inside controller");
		return new ResponseEntity<>(emiPause.calculatedEMIpause(pausemodel), HttpStatus.CREATED);

	}
}
