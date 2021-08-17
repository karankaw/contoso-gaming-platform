package com.aws.proserve.contoso.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aws.proserve.contoso.service.LocatorService;
import com.aws.proserve.contoso.utils.UtilsHelper;

@RestController
@RequestMapping("/actorLocator")
public class ActorLocationController {
		
	private static final String INPUT_UPLOAD_SUCCESS_MSG = "Input Data Posted Successfully";
	private static final String HEALTH_CHECK_MSG = "Contoso Game Actor Locator API - HealthCheck OK";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActorLocationController.class);

	@Autowired
	private LocatorService actorLocatorService;

	// Input should be list of pair of nodes followed by a number
	// Sample Input AB3, BC9, CD3, DE6, AD4, DA5, CE2, AE4,EB1
	@PostMapping("/addInputData")
	public String addInputData(@RequestBody String inputData) {

		actorLocatorService.saveWeightedRoutes(UtilsHelper.ingestInputString(inputData));
		LOGGER.info("New Input Data Posted");
		return INPUT_UPLOAD_SUCCESS_MSG;
	}

	
	// Route should be node names separate by dash -
	@GetMapping("/calculateRouteDistance")
	public String calcualteRouteDistance(@RequestParam("route") String route) {
		
		String routeDistance = actorLocatorService.calculateRouteDistance(route);
		LOGGER.info("{} RouteDistance : {}", route, routeDistance);
		return routeDistance;
	}
	
	
	// Fetch number of routes between given landmark pair, 
	// Input should be two landmarks separated by a space
	@GetMapping("/findNumberOfRoutes")
	public int findNumberOfRoutes(@RequestParam("landmarkPair") String landmarkPair) {

		List<String> landmarks = UtilsHelper.ingestInputString(landmarkPair);
		int numRoutes = actorLocatorService.findNumberOfRoutes(landmarks.get(0), landmarks.get(1));
		LOGGER.info("Number of Routes between {} and {} : {}", landmarks.get(0), landmarks.get(1), numRoutes);
		return numRoutes;
	}	
	
	
	// Welcome Page, Can also act as Health Check URL for ELB
	@GetMapping("")
	public String defaultWelcomePage() {

		return HEALTH_CHECK_MSG;
	}
}
