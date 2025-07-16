package catalog;

import java.util.*;

public class CatalogItem {
    private  String registrationNumber;
    private int pieces;
    private List<LibraryItem> libraryItems;//típusparaméterként az interfészre hívatkozik , s így majd hozzá fogok tudni adni bármelyik olyan osztály példányát
    // amelyik az interfészt implementálja!!!

    public CatalogItem(String registrationNumber, int pieces, LibraryItem... libraryItems) {
        validate(registrationNumber,pieces);
        this.registrationNumber = registrationNumber;
        this.pieces = pieces;
        this.libraryItems = Arrays.asList(libraryItems);
    }
    private void validate(String registrationNumber, int pieces){
        if(Validators.isBlank(registrationNumber)){
            throw new IllegalArgumentException("empty title!");
        }
        if(pieces<=0){
            throw new IllegalArgumentException("Illegal pieces:"+pieces);
        }
    }
    public boolean hasAudioFeature(){
        for(LibraryItem item :libraryItems){
            if(item.isAudio()){
                return true;
            }
        }
        return false;
    }
    public boolean hasPrintedFeature(){
        for(LibraryItem item :libraryItems){
            if(item.isPrinted()){
                return true;
            }
        }
        return false;
    }
    public int getNumberOfPagesAtOneItem(){//ha nincs ebben a katalógusban egyetlen nyomtatott könyv sem, 0-t ad vissza
        int numberOfPages=0;
        for(LibraryItem item :libraryItems){
            numberOfPages+=((Book)item).getNumberOfPages();//cast-olni kell a Book osztllyal, mert az interfacenek nincs implementált getNumberOfPage metódusa
        }
        return numberOfPages;
    }
    public List<String> getContributors(){
        //nem lehetnek ismétlődések!
        Set<String> contributorsSet = new HashSet<>();
        for(LibraryItem item :libraryItems){
            contributorsSet.addAll(item.getContributors());
        }
        return new ArrayList<>(contributorsSet);
    }
    public List<String> getTitles(){
        List<String> titels=new ArrayList<>();
        for(LibraryItem item :libraryItems){
            titels.add(item.getTitle());
        }
        return titels;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public List<LibraryItem> getLibraryItems() {
        return libraryItems;
    }

    public void setLibraryItems(List<LibraryItem> libraryItems) {
        this.libraryItems = libraryItems;
    }
}
