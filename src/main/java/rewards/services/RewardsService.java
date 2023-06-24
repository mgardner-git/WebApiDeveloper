package rewards.services;
import java.util.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import rewards.model.RewardRequest;
import rewards.model.RewardResponse;
import rewards.model.Transaction;

@Service
public class RewardsService {

	
	public RewardResponse calculatePoints(RewardRequest request) {
		
		List<Transaction> transactions = getTransactionsForTimePeriod(request.getStartDate(), request.getEndDate());
		RewardResponse response = new RewardResponse();
		int totalPoints = 0; 
		//map months to total points in that month
		Map<Integer, Integer> pointsByMonth = new HashMap<Integer,Integer>();
		for (Transaction transaction : transactions) {
			int transPoints = calculatePoints(transaction);
			totalPoints += transPoints;
			Integer month = transaction.getTransactionDate().getMonth();
			Integer pointsInCurrentMonth = pointsByMonth.get(month);
			if (pointsInCurrentMonth == null) {
				pointsByMonth.put(month, transPoints);
			} else {
				pointsByMonth.put(month,   pointsInCurrentMonth+transPoints);
			}
		}
		//transform the map to an array
		Set<Integer> monthIndices = pointsByMonth.keySet();
		int[] monthPoints = new int[monthIndices.size()];
		int i = 0;
		for (int month : monthIndices) {
			monthPoints[i++] = pointsByMonth.get(month);
		}
		response.setPointsPerMonth(monthPoints);
		response.setTotalPoints(totalPoints);
		return response;
	}
	
	public int calculatePoints(Transaction transaction) {
		double dollars = transaction.getDollarAmount();
		if (dollars > 100) {
			int doubleDollars = (int)(dollars-100); //TODO: Double check on truncation issues
			int singleDollars = 50;  
			int totalPointsEarned = doubleDollars*2 + singleDollars;
			return totalPointsEarned;
			
		} else if (dollars > 50) {
			int singleDollars = (int)(dollars-50);
			return singleDollars;
		} else {
			return 0;
		}
	}
	
	
	private List<Transaction> getTransactionsForTimePeriod(Date beginDate, Date endDate) {
		//hard-coded dummy data for now,we can hook this up to a database later
		List<Transaction> transactions = loadTransactionsFromDatabase();
		List<Transaction> filteredTransactions = new ArrayList<Transaction>();
		for (Transaction checkTransaction : transactions) {
			long time = checkTransaction.getTransactionDate().getTime();
			if (time >= beginDate.getTime() && time <= endDate.getTime()) {
				filteredTransactions.add(checkTransaction);
			}
		}
		return filteredTransactions;
	}

	private List<Transaction> loadTransactionsFromDatabase() {
		Transaction tr1 = new Transaction(120, new GregorianCalendar(2023, 0,1).getTime()); //jan 1
		Transaction tr2 = new Transaction(49, new GregorianCalendar(2023,1,1).getTime());  //feb 1
		Transaction tr3 = new Transaction(51, new GregorianCalendar(2023,2,1).getTime()); //march 1
		Transaction tr4 = new Transaction(101, new GregorianCalendar(2023,3,1).getTime()); //apr 1
		List<Transaction> dummyData = new ArrayList<>();
		dummyData.add(tr1);
		dummyData.add(tr2);
		dummyData.add(tr3);
		dummyData.add(tr4);
		return dummyData;
	}
	
}
