package aplut.coronastats;

public class CountryModel {

    private String country,cases,deaths,recovered,active,flag
            ,todayCases,todayRecovered,todayDeaths,critical,tests;

    public CountryModel() {
    }

    public CountryModel(String country, String cases, String deaths, String recovered, String active, String flag
    ,String todayCases,String todayRecovered,String todayDeaths,String critical,String tests) {
        this.country = country;
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
        this.flag = flag;
        this.todayCases = todayCases;
        this.todayRecovered = todayRecovered;
        this.todayDeaths = todayDeaths;
        this.critical = critical;
        this.tests = tests;
    }

    public String getCountry() {
        return country;
    }

    public String getCases() {
        return cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getActive() {
        return active;
    }

    public String getFlag() {
        return flag;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public String getCritical() {
        return critical;
    }

    public String getTests() {
        return tests;
    }
}
