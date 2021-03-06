/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import edu.eci.arsw.threads.ServerSearch;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class HostBlackListsValidator {

    private static final int BLACK_LIST_ALARM_COUNT=5;

    /**
     * Check the given host's IP address in all the available black lists,
     * and report it as NOT Trustworthy when such IP was reported in at least
     * BLACK_LIST_ALARM_COUNT lists, or as Trustworthy in any other case.
     * The search is not exhaustive: When the number of occurrences is equal to
     * BLACK_LIST_ALARM_COUNT, the search is finished, the host reported as
     * NOT Trustworthy, and the list of the five blacklists returned.
     * @param ipaddress suspicious host's IP address.
     * @param N number of Threads.
     * @return  Blacklists numbers where the given host's IP address was found.
     */
    public List<Integer> checkHost(String ipaddress, int N){





        HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();

        int checkedListsCount=0;

        int ocurrencesCount=0;
        LinkedList<Integer> blackListOcurrences=new LinkedList<>();

        int searchNumber = skds.getRegisteredServersCount()/N;

        List<ServerSearch> searches = new LinkedList<>();

        Object lock = new Object();

        for(int i=0; i<N ; i++){
            ServerSearch search = new ServerSearch(i*searchNumber,(i+1)*searchNumber,skds,ocurrencesCount,ipaddress,lock);
            searches.add(search);
            search.start();
            try{
                search.join(10);

            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
        while(true) {
            checkedListsCount = 0;
            ocurrencesCount = 0;
            blackListOcurrences.clear();
            for (int i = 0; i < N; i++) {
                ServerSearch search = searches.get(i);
                if (search.getOcurrencesCount() != 0) {
                    blackListOcurrences.addAll(search.getOcurrences());
                    ocurrencesCount += search.getOcurrencesCount();
                }
                checkedListsCount += search.getCheckedListsCount();
            }

            if (ocurrencesCount >= BLACK_LIST_ALARM_COUNT) {
                ServerSearch.setStop(true);
                skds.reportAsNotTrustworthy(ipaddress);
                break;

            }
        }
        if (!(ocurrencesCount >= BLACK_LIST_ALARM_COUNT)){
            skds.reportAsTrustworthy(ipaddress);
        }

        LOG.log(Level.INFO, "Checked Black Lists:{0} of {1}", new Object[]{checkedListsCount, skds.getRegisteredServersCount()});

        return blackListOcurrences;
    }
    
    
    private static final Logger LOG = Logger.getLogger(HostBlackListsValidator.class.getName());
    
    
    
}
