package sofrosyn.tech.com.orpheus.modals;

public class Assessment {


    private  String noLessons;
    private String theoryScore;
    private  String pacticalScore;
    private String melodyScore;
    private String rate;


    public Assessment() {

    }

    public Assessment(String noLessons, String theoryScore, String pacticalScore, String melodyScore, String rate) {
        this.noLessons = noLessons;
        this.theoryScore = theoryScore;
        this.pacticalScore = pacticalScore;
        this.melodyScore = melodyScore;
        this.rate = rate;
    }


    public String getNoLessons() {
        return noLessons;
    }

    public String getTheoryScore() {
        return theoryScore;
    }

    public String getPacticalScore() {
        return pacticalScore;
    }

    public String getMelodyScore() {
        return melodyScore;
    }

    public String getRate() {
        return rate;
    }
}
