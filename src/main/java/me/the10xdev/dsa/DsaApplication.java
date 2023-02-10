package me.the10xdev.dsa;

import io.hypersistence.utils.hibernate.type.json.internal.JacksonUtil;
import jakarta.annotation.PostConstruct;
import me.the10xdev.dsa.entities.Problem;
import me.the10xdev.dsa.models.TestCase;
import me.the10xdev.dsa.repositories.ProblemRepo;
import me.the10xdev.dsa.types.Category;
import me.the10xdev.dsa.types.Difficulty;
import me.the10xdev.dsa.types.IOType;
import me.the10xdev.dsa.types.ValidationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DsaApplication {

	@Autowired
	private ProblemRepo problemRepo;

	public static void main(String[] args) {
		SpringApplication.run(DsaApplication.class, args);
	}

	@PostConstruct
	public void test() {
//		createProblems();
	}

	private void createProblems() {
		List<Problem> problems = getProblems();

		problemRepo.saveAll(problems);

	}

	private List<Problem> getProblems() {

		List<Problem> problems = new ArrayList<>();

		problems.add(
				Problem.builder()
						.id("two-sum")
						.title("Two Sum")
						.description("""
                                 Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.
                                                                
                                 You may assume that each input would have exactly one solution, and you may not use the same element twice.
                                                                
                                 You can return the answer in any order.
                                                                
                                 ### Example 1:
                                                                
                                                                
                                 ```
                                 Input: nums = [2,7,11,15], target = 9
                                 Output: [0,1]
                                 Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
                                 Example 2:
                                 ```
                                                                
                                 ```
                                 Input: nums = [3,2,4], target = 6
                                 Output: [1,2]
                                 Example 3:
                                 ```
                                                                
                                 ```
                                 Input: nums = [3,3], target = 6
                                 Output: [0,1]
                                 ```
                                 
                                 ### Constraints:
                                                                
                                 * 2 <= nums.length <= 104
                                 * -109 <= nums[i] <= 109
                                 * -109 <= target <= 109
                                 * Only one valid answer exists.
                                                                
                                 Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
                                """)
						.difficulty(Difficulty.EASY)
						.category(Category.ARRAY)
						.outputType(IOType.ARRAY_INT)
						.validationType(ValidationType.CONTAINS_MATCH)
						.hints(List.of("Hint1", "Hint2"))
						.optimalSpace("O(n)")
						.optimalTime("O(n)")
						.optimalMetaDescription("where n is the length of the array")
						.runTestCases(
								List.of(new TestCase(
										JacksonUtil.toJsonNode("{\"target\": 7, \"array\": [2,5]}"),
										"[0,1]",
										100,
										100
								))
						)
						.submitTestCases(
								List.of(new TestCase(
										JacksonUtil.toJsonNode("{\"target\": 7, \"array\": [2,5]}"),
										"[0,1]",
										100,
										100
								))
						)
						.codeStub("""
                                def twoSum(array,target):
                                    pass
                                """)
						.codeRunStub("""
                <user_code>
                input = <input>
                result = twoSum(input['array'], input['target'])
                print(result)
                """)
						.build()
		);

		return problems;

	}


}
