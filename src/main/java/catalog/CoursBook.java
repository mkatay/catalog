package catalog;

import java.util.List;

//a tankönyvek a Book leszármazottja
public class CoursBook extends Book{
    String lector;

    public CoursBook(String title, int numberOfPages, List<String> authors, String lector) {
        super(title, numberOfPages, authors);
        if(Validators.isBlank(lector)){
            throw new IllegalArgumentException("No lector!");
        }
        this.lector = lector;
    }

    public String getLector() {
        return lector;
    }

    @Override
    public List<String> getContributors() {
        List<String> contributors=super.getContributors();
        contributors.add(lector);
        return contributors;
    }
}
