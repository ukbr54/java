package oops.modifier.street21;

class Bluehouse extends House{

     public String getColor(){
         return "BLUE";
     }

     //scope within package and inherited class
     protected String getHeight(){
         return "100";
     }
}
