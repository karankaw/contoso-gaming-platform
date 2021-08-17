package com.aws.proserve.contoso.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aws.proserve.contoso.service.LocatorService;
import com.aws.proserve.contoso.utils.UtilsHelper;

@Service
public class LocatorServiceImpl implements LocatorService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LocatorServiceImpl.class);

	// Can be implemented as In-Memory Database - Memcache/Redis, 
	// Single Source of Truth, All Data Fetched Dynamically from this DataSink
	public static Map<String, Integer> WEIGHTED_ROUTES;

	@Override
	public void saveWeightedRoutes(List<String> input) {
		WEIGHTED_ROUTES = input.stream()
				.collect(Collectors.toMap(key -> key.substring(0, 2), value -> Integer.parseInt(value.substring(2))));
	}

	@Override
	public String calculateRouteDistance(String route) {

		int distance = UtilsHelper.calculateDistanceBetweenRoutes(WEIGHTED_ROUTES, route);
		String distanceStr = distance > 0 ? String.valueOf(distance) : UtilsHelper.PATH_NOT_FOUND_MSG;
		return distanceStr;
	}

	@Override
	public int findNumberOfRoutes(String source, String destination) {
		Set<String> validRoutes = UtilsHelper.exploreRoutes(WEIGHTED_ROUTES,source,destination);
		LOGGER.info("Valid Routes between {} and  {} : {}", source, destination, validRoutes);
		return validRoutes.size();
	}	

}
