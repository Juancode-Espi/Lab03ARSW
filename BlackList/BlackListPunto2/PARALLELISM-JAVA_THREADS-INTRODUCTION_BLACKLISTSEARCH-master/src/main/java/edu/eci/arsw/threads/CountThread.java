/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThread extends Thread {
    private int start;
    private int end;

    public CountThread(int inicio, int fin) {
        this.start = inicio;
        this.end = fin;

    }

    
    public void run() {
        for(int i=start; i<=end; i++){
            System.out.println(i);
        }
    }
}
