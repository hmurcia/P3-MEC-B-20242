/* Proyecto 1.1    Este programa convierte galones en litros.
   Llame a este programa GalALit.java. */

class GalALit {
  public static void main(String args[]) {
    double galones; // contiene la cantidad de galones
    double litros;  // contiene lo convertido a litros
    galones = 10; // empieza con 10 galones
    litros = galones * 3.7854; // convierte a litros
    System.out.println(galones + " galones son " + litros + " litros.");
  }
}