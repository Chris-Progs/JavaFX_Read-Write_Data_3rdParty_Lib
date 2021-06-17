/*
Java AT2.6 - Read and Write data CSV file
 */
package fxcsvtest;

public class DataStore {
    
    private String name;
    
    public DataStore(){
        this.name = "";
    } 
    public DataStore(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }   
}
