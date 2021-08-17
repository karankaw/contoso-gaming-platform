package com.aws.proserve.contoso.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Fully Functional, No State here
public class UtilsHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilsHelper.class);

	public final static String PATH_NOT_FOUND_MSG = "Path not found";
	public final static int MAX_STOPS = 2;
	
	public static List<String> ingestInputString(String input) {
		List<String> weightedRouteList = Stream.of(input.replaceAll("\\s+$","")
				.split(" "))
				.collect(Collectors.toList());
		return weightedRouteList;
	}
	
	public static Set<String> getAllNodes(Map<String, Integer> weightedRoutes){
		Set<String> allNodes = new HashSet<>();
		getRoutes(weightedRoutes).stream()
		.forEach(route -> {
			allNodes.add(getRouteSource(route));
			allNodes.add(getRouteDestination(route));
		});
		return allNodes;
	}
	
	public static String getRouteSource(String route) {
		return route.substring(0, 1);
	}

	public static String getRouteDestination(String route) {
		return route.substring(route.length() - 1);
	}	
		
	public static Set<String> getRoutes(Map<String, Integer> weightedRoutes) {
		return weightedRoutes.keySet();
	}	
	
	
	public static Set<String> getOutboundRoutes(Map<String, Integer> weightedRoutes, String source){
		return getRoutes(weightedRoutes)
				.stream()
				.filter(route -> getRouteSource(route).equals(source))
				.collect(Collectors.toSet());
	}
	
	public static Set<String> getOutboundNodes(Map<String, Integer> weightedRoutes, String source){
		return getOutboundRoutes(weightedRoutes, source)
				.stream()
				.map(route -> getRouteDestination(route))
				.collect(Collectors.toSet());
	}
	
	public static int calculateDistanceBetweenRoutes(Map<String, Integer> weightedRoutes, String inputRoute) {
		int distance = 0;
		String[] arrLandmarks = inputRoute.split("-");
		List<String> landmarkPairs = new ArrayList<>();
		
		for (int i = 0; i < arrLandmarks.length - 1; i++) {
			landmarkPairs.add(arrLandmarks[i] + arrLandmarks[i + 1]);
		}
		
		for (String route : landmarkPairs) {
			if (weightedRoutes.get(route) != null) {
				distance += weightedRoutes.get(route);
			} else {
				distance = -1;
				break;
			}
		}
		
		return distance;
	}

	public static Set<String> exploreRoutes(Map<String, Integer> weightedRoutes, String source, String destination) {
				
		Set<String> validRoutes2Hops = new HashSet<>();
		
		Set<String> visitedRoutes = new HashSet<>();
		visitedRoutes.add(source);
		
		for (int i = 0; i <= MAX_STOPS; i++) {
			
			Set<String> tempVisitedRoutes = new HashSet<>();
			
			for(String visitedRoute : visitedRoutes) {
								
				if(visitedRoute.length() == i+1) {
					
					Set<String> intermediateNodes = getOutboundNodes(weightedRoutes, getRouteDestination(visitedRoute));
			
					for(String intermediateNode : intermediateNodes) {
						
						if(!visitedRoute.contains(intermediateNode)) {
							tempVisitedRoutes.add(visitedRoute+intermediateNode);
						}
					}
				}
			}
			visitedRoutes.addAll(tempVisitedRoutes);
		}
		
		validRoutes2Hops = visitedRoutes.stream()
							.filter(route -> route.endsWith(destination))
							.collect(Collectors.toSet());
				
		return validRoutes2Hops;
	}
	


	

}
