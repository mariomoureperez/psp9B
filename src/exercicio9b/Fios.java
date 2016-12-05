/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio9b;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mmoureperez
 */
public class Fios extends Thread{
    public static int suma3=0;
    public static int suma5=0;
    String nome;
   public static boolean lleno3= false;
   public static boolean lleno5=false;
    
    
    public Fios(String nome){
        super(nome);
        this.nome=nome;
    }
    
    public synchronized void multiplos3() {
        for (int i = 0; i <= 1000; i++) {
            if(i%3==0){
                suma3+=i;
            }         
        }
        lleno3=true;
        notify();
        System.out.println("Suma dos multiplos de 3 e: "+suma3);
    }
    public synchronized void multiplos5() {
        for (int i = 0; i <= 1000; i++) {
            if(i%5==0){
                suma5+=i;
            }
        }
           
        
        
        lleno5=true;
        notify();
        
        System.out.println("Suma dos multiplos de 5 e: "+suma5);
    }
    public synchronized void factorial(){
        Scanner entrada=new Scanner(System.in);
        System.out.println("Ingresa un numero positivo");
        int numero=entrada.nextInt();
        int numeroIntroducido=numero;
        double factorial=1;
        while(numero!=0){
          factorial=factorial*numero;
          numero--;
    }System.out.println("O factorial de "+numeroIntroducido+" e: "+factorial);
    notify();
        }
        
    
    public synchronized void sumaMultiplos() throws InterruptedException{
        
       while((lleno3==false) && (lleno5==false)){
               System.out.println("Entro"+lleno3+lleno5);       
               wait();
               System.out.println("Salgo"+lleno3+lleno5);     
       }
        int sumaMul=suma3+suma5;
        System.out.println("Suma dos multiplos de 3 e 5: "+sumaMul);
    }
    
    @Override
    public void run(){
        
        if("suma3".equals(getName())){
            multiplos3();
        }else if("suma5".equals(getName())){
            multiplos5();
        }else if("factor".equals(getName())){
            factorial();
        }else{
            try {
                sumaMultiplos();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
}
    }
    
}

