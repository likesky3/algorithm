package probability;

public class ReseviorSamplingWithKSamples {
	private int[] samples;
	private int count = 0;
	private int k;

	public ReseviorSamplingWithKSamples(int k) {
		this.k = k;
		samples = new int[k];
	}

	public void read(int val) {
		count++;
		if (count <= k) {
			samples[count - 1] = val;
		} else {
			int randIdx = (int) (Math.random() * count);
			if (randIdx < k) { // probability of the new element is k / count
				samples[randIdx] = val;
			}
			// probality of the old element not replaced = k / (count - 1) * (1
			// - 1 / (count)) = k / count
		}
	}

	public int[] getKSamples() {
		return samples;
	}

}
