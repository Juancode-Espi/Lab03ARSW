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
    private static final int BLACK_LIST_ALARM_COUNT=5;

    public void run(){
        checkedListsCount = 0;
        ocurrences = new LinkedList<>();
        for (int i=inicial;i<fin && ocurrencesCount<BLACK_LIST_ALARM_COUNT;i++){
            checkedListsCount++;

            if (skds.isInBlackListServer(i, ipaddress)){

                ocurrences.add(i);

                ocurrencesCount++;
            }
        }

    }

    public ServerSearch(int inicial, int fin, HostBlacklistsDataSourceFacade skds, int ocurrencesCount , String ipaddress){
        this.inicial = inicial;
        this.fin = fin;
        this.skds = skds;
        this.ipaddress = ipaddress;
        this.ocurrencesCount = ocurrencesCount;
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
