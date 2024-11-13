package com.gophers.structures.grades;

import java.util.HashMap;
import org.junit.runner.Result;
import com.gophers.services.handlers.GradeConfigLoader;
import com.gophers.utilities.Constants;

public class ChatBotGrade extends GradeTemplate {
	private static final int totalMarks = Constants.CHATBOT_GRADE_TOTAL_MARKS;

	public ChatBotGrade(Result result) {
		super(result, totalMarks);
	}

	@Override
	protected void allocateWeightings() {
		super.testMarks = GradeConfigLoader.getWeightings(Constants.CHATBOT_GRADE);
	}

	@Override
	protected void allocateFeedback() {
		super.feedbackMap = new HashMap<String, Integer>(super.testMarks);
	}
}