package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import rewards.model.RewardRequest;
import rewards.model.RewardResponse;
import rewards.model.Transaction;
import rewards.services.RewardsService;

class RewardServiceTest {

	private RewardsService rewardService = new RewardsService();
	
	
	@Test
	void testCalculatePoints() {
		Transaction t1 = new Transaction(0,null);
		Integer p1 = rewardService.calculatePoints(t1);
		assertEquals(0, p1);
		
		Transaction t2 = new Transaction(50,null);
		Integer p2 = rewardService.calculatePoints(t2);
		assertEquals(0,p2);
		
		Transaction t3 = new Transaction(51,null);
		Integer p3 = rewardService.calculatePoints(t3);
		assertEquals(1, p3);
		
		Transaction t4 = new Transaction(100,null);
		Integer p4 = rewardService.calculatePoints(t4);
		assertEquals(50,p4);
		
		Transaction t5 = new Transaction(101,null);
		Integer p5 = rewardService.calculatePoints(t5);
		assertEquals(52, p5); //50 for 50-100, double points for 101
		
	}
	
	@Test
	public void calculateResponse() {
		RewardRequest request = new RewardRequest();
		request.setCustomerId(1);
		GregorianCalendar startDate = new GregorianCalendar(2023,0,1);
		GregorianCalendar endDate = new GregorianCalendar(2023,2,1);
		request.setStartDate(startDate.getTime());
		request.setEndDate(endDate.getTime());
		RewardResponse response = rewardService.calculatePoints(request);
		
		assertEquals(3,response.getPointsPerMonth().length);
		assertEquals(90, response.getPointsPerMonth()[0]);
		assertEquals(0, response.getPointsPerMonth()[1]);
		assertEquals(1, response.getPointsPerMonth()[2]);
		assertEquals(91, response.getTotalPoints());
	}

}
