package com.example.homeworktask3cat.model; // <============= CHANGE ME


/***
 * Model class for news articles. This should be 100% familiar to you.
 *
 * Week 6
 *  This class now matches what an Article is represented as in the New York Times Most Viewed API.
 */
public class Cat{

    // We use a long because the number they use for ID is too big for an int
    private String id;
    private String name;
    private String description;
    private Weight weight;
    private String temperament;
    private String origin;
    private String life_span;
    private String wikipedia_url;
    int dog_friendly;

    public Cat(String id, String name, String description, Weight weight, String temperament,
               String origin, String life_span, String wikipedia_url, int dog_friendly) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.temperament = temperament;
        this.origin = origin;
        this.life_span = life_span;
        this.wikipedia_url = wikipedia_url;
        this.dog_friendly = dog_friendly;
    }

    public class Weight {

        //api has different data available for systems of measurement. only want metric
        private String metric;

        public String getMetric() {
            return metric;
        }

        public void setMetric(String metric) {
            this.metric = metric;
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public void setWikipedia_url(String wikipedia_url) {
        this.wikipedia_url = wikipedia_url;
    }

    public int getDog_friendly() {
        return dog_friendly;
    }

    public void setDog_friendly(int dog_friendly) {
        this.dog_friendly = dog_friendly;
    }
}
