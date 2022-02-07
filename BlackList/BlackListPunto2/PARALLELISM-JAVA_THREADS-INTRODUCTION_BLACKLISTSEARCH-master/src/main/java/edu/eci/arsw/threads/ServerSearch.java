package edu.eci.arsw.threads;
import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

import java.lang.Thread;
import java.util.LinkedList;

public class ServerSearch extends Thread {
    private int inicial;
    private int fin;
    private int ocurrencesCount;
    private static HostBlacklistsDataSourceFacade skds;
    private LinkedList<Integer> ocurrences;
    private int checkedListsCount;
    private String ipaddress;
    private Object lock;
    private static final int BLACK_LIST_ALARM_COUNT=5;
    private static boolean stop = false;

    public void run(){
        checkedListsCount = 0;
        ocurrences = new LinkedList<>();
        for (int i=inicial;i<fin && ocurrencesCount<BLACK_LIST_ALARM_COUNT;i++){
            checkedListsCount++;
            synchronized (lock){
                while(stop){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (skds.isInBlackListServer(i, ipaddress)){

                ocurrences.add(i);

                ocurrencesCount++;
            }
        }

    }

    public ServerSearch(int inicial, int fin, HostBlacklistsDataSourceFacade skds, int ocurrencesCount , String ipaddress,Object lock){
        this.inicial = inicial;
        this.fin = fin;
        this.skds = skds;
        this.ipaddress = ipaddress;
        this.ocurrencesCount = ocurrencesCount;
        this.lock = lock;
    }

    public static void setStop(boolean st) {
        stop = st;
    }

    public LinkedList<Integer> getOcurrences(){
        return ocurrences;
    }
    public int getOcurrencesCount(){
        return ocurrencesCount;
    }
    public int getCheckedListsCount(){
        return checkedListsCount;
    }
}
