package catalog;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Catalog {
    private List<CatalogItem> catalogItems=new ArrayList<>();

    public List<CatalogItem> getCatalogItems() {
        return new ArrayList<>(catalogItems);//hogy ne lehessen véletlenül sem módosítani

    }
    public void addItem(CatalogItem item){
        if(item ==null){
            throw new IllegalArgumentException("Empty catalog!");
        }
    }
    public void deleteItemByRegistrationNumber(String registrationNumber){
        if(Validators.isBlank(registrationNumber)){
            throw new IllegalArgumentException("Empty registration number!");
        }
        CatalogItem itemToDelete=null;
        for(CatalogItem item : catalogItems){
            if(item.getRegistrationNumber()==registrationNumber){
                itemToDelete=item;
            }
        }
        if(itemToDelete!=null){
            catalogItems.remove(itemToDelete);
        }
    }
    public List<CatalogItem> getAudioLibraryItems(){
        List<CatalogItem> audioItems=new ArrayList<>();
        for (CatalogItem item :catalogItems){
            if(item.hasAudioFeature()){
                audioItems.add(item);
            }
        }
        return audioItems;
    }
    public List<CatalogItem> getPrintedLibraryItems(){
        List<CatalogItem> printedItems=new ArrayList<>();
        for (CatalogItem item :catalogItems){
            if(item.hasPrintedFeature()){
                printedItems.add(item);
            }
        }
        return printedItems;
    }
    public int getAllPagesNumber(){
        int numberOfPages=0;
        for(CatalogItem item :catalogItems){
            numberOfPages+=item.getNumberOfPagesAtOneItem();
        }
        return numberOfPages;
    }
    public List<CatalogItem> findByCriteria(SearchCriteria searchCriteria){
        if(searchCriteria==null){
            throw new IllegalArgumentException("Empty criteria!");
        }
        List<CatalogItem> foundItems=new ArrayList<>();
        for(CatalogItem item :catalogItems){
            boolean found=false;
            if(searchCriteria.hasTitle() && item.getTitles().contains(searchCriteria.getTitle())){
                found=true;
            }
            if(searchCriteria.hasContributor() && item.getContributors().contains(searchCriteria.getContributor())){
                found=true;
            }
            if(found){
                foundItems.add(item);
            }
        }
        return foundItems;
    }
    public void readFromFile(Paths path) throws IOException {
        try (Scanner scanner = new Scanner(path.toString())) {
            while(scanner.hasNextLine()){
                String line=scanner.nextLine();
                processLine(line);
            }
        }
    }

    private  void processLine(String line){
        String[] part=line.split(";");
        String regiostrationNumber="R-"+catalogItems.size()+1;
        List<String> authors=new ArrayList<>();
        for (int i = 3; i <part.length ; i++) {
           authors.add(part[i]);
        }
        LibraryItem book=new Book(part[1],Integer.parseInt(part[2]),authors);
        CatalogItem item=new CatalogItem(regiostrationNumber,Integer.parseInt(part[0]),book);
        catalogItems.add(item);
    }
}
