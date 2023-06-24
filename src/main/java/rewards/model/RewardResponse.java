package rewards.model;

public class RewardResponse {
	private int totalPoints;
	private int[] pointsPerMonth;
	public int getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	public int[] getPointsPerMonth() {
		return pointsPerMonth;
	}
	public void setPointsPerMonth(int[] pointsPerMonth) {
		this.pointsPerMonth = pointsPerMonth;
	}
	
	
}
