package catalog;

import java.util.ArrayList;
import java.util.List;

public class AudioBook implements LibraryItem{
    private String title;
    private  int length;
    private List<String> authors;
    private List<String> performers;

    public AudioBook(String title, int length, List<String> authors, List<String> performers) {
        validate(title,length,authors,performers);
        this.title = title;
        this.length = length;
        this.authors = authors;
        this.performers = performers;
    }

    @Override
    public List<String> getContributors() {
        List<String> contributors=new ArrayList<>();
        contributors.addAll(authors);
        contributors.addAll(performers);
        return contributors;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean isPrinted() {
        return false;
    }

    @Override
    public boolean isAudio() {
        return true;
    }

    public int getLength() {
        return length;
    }

    private void validate(String title, int length, List<String> authors, List<String> performers){
        if(Validators.isBlank(title)){
            throw new IllegalArgumentException("empty title!");
        }
        if(Validators.isEmpty(authors)){
            throw new IllegalArgumentException("no authors!");
        }
        if(length<=0){
            throw new IllegalArgumentException("Illegal length size:"+length);
        }
        if(Validators.isEmpty(performers)){
            throw new IllegalArgumentException("no performers!");
        }
    }
}
