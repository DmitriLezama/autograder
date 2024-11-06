package com.gophers.structures.grades;

import org.junit.runner.Result;

import com.gophers.services.helpers.GradeConfigLoader;

public class ChatBotPlatformGrade extends GradeTemplate {
	private static final int totalMarks = 20;

	public ChatBotPlatformGrade(Result result) {
		super(result, totalMarks);
	}

	@Override
	protected void allocateWeightings() {
		super.testMarks = GradeConfigLoader.getWeightings("ChatBotPlatformGrade");
	}

	@Override
	protected void allocateFeedback() {
		super.testFeedback = GradeConfigLoader.getFeedback("ChatBotPlatformGrade");
	}

}
