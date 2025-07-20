package catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogMain {
    public static void main(String[] args) {
        Catalog catalog=new Catalog();
        catalog.readFromFile("books.csv");
        for (CatalogItem item :catalog.getCatalogItems()){
            System.out.println(item.getTitles()+" "+item.getPieces()+" "+item.getContributors()+" "+item.getNumberOfPagesAtOneItem());
            System.out.println(item.hasPrintedFeature());
        }
        SearchCriteria searchCriteria=SearchCriteria.createByTitle("Egri");
        List<CatalogItem> items = catalog.findByCriteria( searchCriteria );
        for(CatalogItem item : items){
            System.out.println(item.toString());
            System.out.println("for ciklus");
        }
    }


}
