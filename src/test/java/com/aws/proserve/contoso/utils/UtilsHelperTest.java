package com.aws.proserve.contoso.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UtilsHelperTest {

	@Test
	void testIngestInputSucessfullyGeneratesListGivenInputString() {
		String input = "AB1 BC2 CD3";
		List<String> actualOutput = UtilsHelper.ingestInputString(input);
		List<String> expectedOutput = new ArrayList<>();
		expectedOutput.add("AB1");
		expectedOutput.add("BC2");
		expectedOutput.add("CD3");
		assertIterableEquals(actualOutput, expectedOutput);
	}

	@Test
	void calculateDistanceBetweenRoutesSucessfully() {
		//AB3, BC9, CD3, DE6, AD4, DA5, CE2, AE4,EB1
		
		Map<String, Integer> weightedRoutes = new HashMap<String, Integer>();
		weightedRoutes.put("AB", 3);
		weightedRoutes.put("BC", 9);
		weightedRoutes.put("CD", 3);
		weightedRoutes.put("DE", 6);
		weightedRoutes.put("AD", 4);
		weightedRoutes.put("DA", 5);
		weightedRoutes.put("CE", 2);
		weightedRoutes.put("AE", 4);
		weightedRoutes.put("EB", 1);
		
		String inputRoute = "A-E-B-C-D";
		
		int actualOutput = UtilsHelper.calculateDistanceBetweenRoutes(weightedRoutes, inputRoute);
		int expectedOutput = 17;
		assertEquals(actualOutput, expectedOutput);
	}

}
