package baseballTracker;

public class player {
	String name;
	int pos;
	int battingOrder;
	static int number;
	String homeOrAway;
	double atBats;
	int singles;
	int doubles;
	int triples;
	int homeRuns;
	int rbi;
	double avg = 1.00;
	double obp = 1.00;
	int k;
	int bb;
	int hbp;
	String atBatResults;
	int sac;
	int strikes;
	int balls;
	int wildPitch;
	int era;
	int hitBatters;
	int strikeouts;
	int walks;
	int pitchCount;
	
	public player(String na, int p, int b, String hw, int nu) {
		name = na;
		pos = p;
		battingOrder = b;
		number = nu;
		homeOrAway = hw;
	}
	
	public player(String na, int p, int b, String hw) {
		name = na;
		pos = p;
		battingOrder = b;
		homeOrAway = hw;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
	public void setPosition(int p){
		pos = p;
	}
	
	public int getPosition(){
		return pos;
	}
	
	public void setBattingOrder(int b){
		battingOrder = b;
	}
	
	public int getBattingOrder(){
		return battingOrder;
	}
	
	public static void setNumber(int n){
		number = n;
	}
	
	public int getNumber(){
		return number;
	}
	
	public void setHomeOrAway(String hw){
		homeOrAway = hw;
	}
	
	public String getHomeOrAway(){
		return homeOrAway;
	}
	
	public String getAll(){
		return name + "," + number + "," + battingOrder + "," + homeOrAway + "," + number;
	}
	
	public String getStatsPitcher(){
		return "Name: " + name + ", Pitches: " + this.getPitches() + ", B:S ratio: " + this.getBSRatio();
		
	}
	
	public String getStatsBatter(){
		return "Name: " + name + ", AVG: " + this.getAvg() + ", OBP: " + this.getOBP();
	}
	
	public void addAtBat(){
		atBats++;
	}
	
	public double getAtBats(){
		return atBats;
	}
	
	public void setAtBatResults(String r){
		atBatResults = atBatResults + r;
	}
	
	public String getAtBatResults(){
		return atBatResults;
	}
	
	public void addSingle(){
		singles++;
	}
	
	public int getSingles(){
		return singles;
	}
	
	public void addDouble(){
		doubles++;
	}
	
	public int getDoubles(){
		return doubles;
	}
	
	public void addTriple(){
		triples++;
	}
	
	public int getTriples(){
		return triples;
	}
	
	public void addHomeRun(){
		homeRuns++;
	}
	
	public int getHomeRuns(){
		return homeRuns;
	}
	
	public void addRBI(){
		rbi++;
	}
	
	public int getRBIs(){
		return rbi;
	}
	
	public void addK(){
		k++;
	}
	
	public int getK(){
		return k;
	}
	
	public void addSac(){
		sac++;
	}
	
	public int getSac(){
		return sac;
	}
	
	public void addBB(){
		bb++;
	}
	
	public int getBB(){
		return bb;
	}
	
	public void addHBP(){
		hbp++;
	}
	
	public int getHBP(){
		return hbp;
	}
	
	public double getAvg(){
		if (this.getHits() == 0){
			return 0.00;
		}
		avg = this.getHits() / this.getAtBats();
		return avg;
	}
	
	public double getHits(){
		double hits = this.getSingles() + this.getDoubles() + this.getTriples() + this.getHomeRuns();
		return hits;
	}
	
	public double getOBP(){
		if (this.getHits() == 0){
			return 0.00;
		}
		obp = (this.getHits() + this.getBB() + this.getHBP()) / this.getHits();
		return obp;
	}
	
	//Pitching classes
	public void addStrike(){
		strikes++;
	}
	
	public int getStrikes(){
		return strikes;
	}
	
	public void addBall(){
		balls++;
	}
	
	public int getBalls(){
		return balls;
	}
	
	public void addWildPitch(){
		wildPitch++;
	}
	
	public int getWildPitch(){
		return wildPitch;
	}
	
	public void addHitBatter(){
		hitBatters++;
	}
	
	public int getHitBatters(){
		return hitBatters;
	}
	
	public void addStrikeout(){
		strikeouts++;
	}
	
	public int getStrikeouts(){
		return strikeouts;
	}
	
	public void addWalk(){
		walks++;
	}
	
	public int getWalks(){
		return walks;
	}
	
	public void addPitch(){
		pitchCount++;
	}
	
	public int getPitches(){
		return pitchCount;
	}
	
	public String getBSRatio(){
		return balls + ":" + strikes;
	}
	//FIX
	public int getEra(){
		return era;
	}
}

