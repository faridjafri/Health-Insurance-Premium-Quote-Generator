package com.emids.hipqg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.emids.hipqg.model.Condition;
import com.emids.hipqg.model.CustomerModel;
import com.emids.hipqg.model.Gender;
import com.emids.hipqg.model.Habit;
import com.emids.hipqg.service.QuoteService;

@Component
@PropertySource("classpath:global.properties")
public class QuoteServiceImpl implements QuoteService {

	@Value("${premium.base}")
	private double basePremium;

	@Value("${premium.base.aip}")
	private int ageIncreasePercentage;

	@Value("${premium.base.mip}")
	private int maleIncreasePercentage;

	@Value("${premium.base.fip}")
	private int femaleIncreasePercentage;

	@Value("${premium.base.oip}")
	private int othersIncreasePercentage;

	@Override
	public Double calculateQuote(CustomerModel customerModel) {
		double finalAmount = basePremium;
		int finalIncreasePercentage = ageIncreasePercentage;
		System.out.println(customerModel);
		System.out.println("basePremium is:" + basePremium);
		// Increase premium based on gender
		if (customerModel.getGender() == Gender.MALE) {
			finalIncreasePercentage += maleIncreasePercentage;
		} else if (customerModel.getGender() == Gender.FEMALE) {
			finalIncreasePercentage += femaleIncreasePercentage;
		} else if (customerModel.getGender() == Gender.OTHER) {
			finalIncreasePercentage += othersIncreasePercentage;
		}

		finalAmount += increaseQuoteByAge(finalAmount, finalIncreasePercentage, customerModel.getAge());
		finalAmount += increaseQuoteByCondition(finalAmount, customerModel.getConditions());
		finalAmount += modifyQuoteByHabits(finalAmount, customerModel.getHabits());
		return finalAmount;
	}

	private double modifyQuoteByHabits(final double finalAmount, List<Habit> habits) {
		int modifyPercentage = 0;
		for (Habit habit : habits) {
			if (habit.getHabitType() == Habit.HabitType.GOOD) {
				modifyPercentage -= 3;
			} else if (habit.getHabitType() == Habit.HabitType.BAD) {
				modifyPercentage += 3;
			}
		}
		return compute(finalAmount, modifyPercentage);
	}

	private double increaseQuoteByCondition(final double finalAmount, List<Condition> conditions) {
		int increasePercentage = 0;
		if (conditions != null && !conditions.isEmpty()) {
			increasePercentage = conditions.size();
		}
		return compute(finalAmount, increasePercentage);
	}

	private double increaseQuoteByAge(final double finalAmount, int finalAgeIncreasePercentage, int age) {
		double increase = 0;
		// Increase premium based on age. Using hard coded values to avoid usage
		// of database
		if (age < 18) {
			finalAgeIncreasePercentage = 0;
		} else if (age >= 18 && age < 25) {
			increase = compute(finalAmount, finalAgeIncreasePercentage);
		} else if (age >= 25 && age < 30) {
			finalAgeIncreasePercentage *= 2;
			increase = compute(finalAmount, finalAgeIncreasePercentage);
		} else if (age >= 30 && age < 35) {
			finalAgeIncreasePercentage *= 3;
			increase = compute(finalAmount, finalAgeIncreasePercentage);
		} else if (age >= 35 && age <= 40) {
			finalAgeIncreasePercentage *= 4;
			increase = compute(finalAmount, finalAgeIncreasePercentage);
		} else if (age > 40) {
			int baseAge = 40;
			int adder = 5;
			int multiplier = 5;
			while (!(age > baseAge && age <= (baseAge + adder))) {
				baseAge += adder;
				multiplier *= 2;
			}
			increase = compute(finalAmount, multiplier);
		}
		return increase;
	}

	private double compute(double amount, int percentage) {
		double finalAmount = (amount * percentage) / 100;
		return finalAmount;
	}

}
