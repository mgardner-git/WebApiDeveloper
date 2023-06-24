package rewards.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rewards.model.RewardRequest;
import rewards.model.RewardResponse;
import rewards.services.RewardsService;


@RestController
public class RewardController {

	@Autowired
	private RewardsService rewardsService;
	
	@RequestMapping(value = "/calculatePoints", method = RequestMethod.GET,produces = "application/json", consumes = "application/json")
	//@ResponseBody
	//  
	public RewardResponse calculatePoints(@RequestParam String request) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		RewardRequest requestObj = mapper.readValue(request, RewardRequest.class);
		
		RewardResponse response = rewardsService.calculatePoints(requestObj);

		return response;
	}
}
