package tr.edu.ege.seagent.evaluation;

public class Measurement {
	private double accuracy;
	private double accuracyPercentage;
	private String content;
	private String[] trueNamedEntityList;

	public Measurement(double accuracy, double accuracyPercentage) {
		this.accuracy = accuracy;
		this.accuracyPercentage = accuracyPercentage;
	}

	public Measurement(String content, String[] trueList) {
		this.content = content;
		this.trueNamedEntityList = trueList;
	}

	public String[] getTrueNamedEntityList() {
		return trueNamedEntityList;
	}

	public void setTrueNamedEntityList(String[] trueNamedEntityList) {
		this.trueNamedEntityList = trueNamedEntityList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public double getAccuracyPercentage() {
		return accuracyPercentage;
	}

	public void setAccuracyPercentage(double accuracyPercentage) {
		this.accuracyPercentage = accuracyPercentage;
	}

}
