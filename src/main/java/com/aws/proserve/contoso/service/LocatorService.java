package com.aws.proserve.contoso.service;

import java.util.List;

public interface LocatorService {

	public abstract void saveWeightedRoutes(List<String> input);

	public abstract String calculateRouteDistance(String route);

	public abstract int findNumberOfRoutes(String string, String string2);

}
