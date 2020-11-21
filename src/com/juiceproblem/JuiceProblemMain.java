
package com.juiceproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class JuiceProblemMain {

	public static void main(String[] args) {
		ReadFile file = new ReadFile();

		List<String> fileInput = file.readFileInput(); /* reading fileInput */

		String caloriesSeparatedBySpaces = fileInput.get(1); /* 5 4 6 */

		String fruitNameAlphabetSequence = fileInput.get(2); /* abcbacbabcc */

		int totalCalorieIntake = Integer.parseInt(fileInput.get(3)); /* 15 */

		Map<Character, Integer> caloriePerFruitJuiceMap = prepareFruitJuiceCaloryMap(caloriesSeparatedBySpaces,
				fruitNameAlphabetSequence); // returns 
		
		List<Integer> fruitJuicesAllCombinations = prepareNumberOfFruitJuicesAndRespectiveCalorieCombinations(
				caloriePerFruitJuiceMap);
		
		calculateFruitCombination(fruitJuicesAllCombinations ,caloriePerFruitJuiceMap, totalCalorieIntake);
	}

	private static void calculateFruitCombination(List<Integer> fruitJuicesAllCombinations , Map<Character, Integer> uniqueFruitCalorieMap,
			int totalCalorieIntake) {

		Combinations combinations = new Combinations(fruitJuicesAllCombinations, totalCalorieIntake, false);
		combinations.calculateCombinations();

		List<List<Character>> allFruitJuicesCombinations = new ArrayList<>();

		for (List<Integer> combination : combinations.getCombinations()) {
			List<Character> fruitJuiceCombinations = new ArrayList<Character>();
			for (Integer fruitCalorie : combination) {
				Collection<Character> fruitCalorieKeySet = uniqueFruitCalorieMap.keySet();
				for (Character fruitCalorieKey : fruitCalorieKeySet) {
					if (uniqueFruitCalorieMap.get(fruitCalorieKey) == fruitCalorie) {
						fruitJuiceCombinations.add(fruitCalorieKey);
						break;
					}
				}
			}
			allFruitJuicesCombinations.add(fruitJuiceCombinations);
		}

		List<String> allFruitJuicesCombinationsAsString = new ArrayList<String>();

		for (List<Character> list : allFruitJuicesCombinations) {
			StringBuilder s = new StringBuilder("");
			for (Character list2 : list) {
				s.append(list2);
			}
			allFruitJuicesCombinationsAsString.add(s.toString());
		}

		Collections.sort(allFruitJuicesCombinationsAsString);

		if (null == allFruitJuicesCombinationsAsString || allFruitJuicesCombinationsAsString.isEmpty()) {
			System.out.println("SORRY, You are Having Only Water");
			return;
		} else {
			System.out.println(allFruitJuicesCombinationsAsString.get(0));
		}
	}

	
	/**
	 * 
	 * @param uniqueFruitCalorieMap
	 * @return
	 */
	private static List<Integer> prepareNumberOfFruitJuicesAndRespectiveCalorieCombinations(
			Map<Character, Integer> uniqueFruitCalorieMap) {
		List<Integer> fruitJuicesAllCombinations = new ArrayList<Integer>();

		for (Map.Entry<Character, Integer> uniqueFruitCalorieMapEntrySet : uniqueFruitCalorieMap.entrySet()) {
			int calorieValue = uniqueFruitCalorieMap.get(uniqueFruitCalorieMapEntrySet.getKey());

			for (int uniqueFruitCalorieMapIndex = 1; uniqueFruitCalorieMapIndex <= uniqueFruitCalorieMapEntrySet
					.getValue(); uniqueFruitCalorieMapIndex++) {
				fruitJuicesAllCombinations.add(calorieValue);
			}
		}
		return fruitJuicesAllCombinations;
	}

	/**
	 * This method returns unique Fruit-Calorie eg.{a=5, b=4, c=6}
	 * @param uniqueCalories
	 * @param actualFruit
	 * @return
	 */
	private static Map<Character, Integer> prepareFruitJuiceCaloryMap(String uniqueCalories, String actualFruit) {

		Map<Character, Integer> uniqueUniqueFruitCalorieMap = new HashMap<Character, Integer>();
		List<Character> uniqueFruitList = new ArrayList<Character>();

		String[] delimatedString = uniqueCalories.split(" ");

		char[] chars = actualFruit.toCharArray();
		Arrays.sort(chars);
		Set<Character> uniqKeys = new TreeSet<Character>();
		for (char eachChar : chars) {
			uniqKeys.add(eachChar);
		}

		uniqueFruitList.addAll(uniqKeys);

		for (int i = 0; i < uniqKeys.size(); i++) {

			uniqueUniqueFruitCalorieMap.put(uniqueFruitList.get(i), Integer.parseInt(delimatedString[i]));
		}
		return uniqueUniqueFruitCalorieMap;
	}
}
