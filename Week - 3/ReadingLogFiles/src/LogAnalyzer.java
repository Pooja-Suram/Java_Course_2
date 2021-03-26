
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{

    //records stores all log entries
    //from given file as array list
     private ArrayList<LogEntry> records;

    public LogAnalyzer(){
        records = new ArrayList<LogEntry>();
    }

    //adds each line from a file
    // into records
    public void readFile(String filename){
        FileResource fr = new FileResource(filename);
        for(String line: fr.lines()){
            LogEntry entry = WebLogParser.parseEntry(line);
            records.add(entry);
        }
    }


    //counts unique IP addresses in given log
    public int countUniqueIPs(){
        ArrayList<String>  uniqueIPs = new ArrayList<String>();
        for(LogEntry log: records){
            String ipAddress = log.getIpAddress();
            if(!uniqueIPs.contains(ipAddress)){
                uniqueIPs.add(ipAddress);
            }
        }
        return uniqueIPs.size();
    }

    //this method prints all logs which
    //has status code greater than given num
    public void printAllHigherThanNum(int num){
        System.out.println("Log entries greater than status code(num) are :");
        for(LogEntry log: records){
            int statusCode = log.getStatusCode();
            if(statusCode > num){
                System.out.println(log);
            }
        }
    }

    /*this method returns array list of unique ip addresses
    that had access on the given day */
    /*someday format should be "MMM DD" where MMM represents
     * month in format Jan or Feb and DD represents date
     * example: Jan 22*/
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueIPInDay = new ArrayList<String>();
        for(LogEntry log: records){
            //getAccessTime() returns date object with complete
            //date and time information
            Date date = log.getAccessTime();
            //to check the given day ,
            //we are converting date object to string
            String visitData = date.toString();
            //checking whether ip is unique and day is same
            if(visitData.contains(someday) && !uniqueIPInDay.contains(log.getIpAddress())){
                uniqueIPInDay.add(log.getIpAddress());
            }
        }
        return uniqueIPInDay;
    }

    //this method returns unique ip addresses in records
    //that have status code in range between low & high
    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry log: records){
            int statusCode = log.getStatusCode();
            if((statusCode>= low && statusCode<=high) &&
                    (!uniqueIPs.contains(log.getIpAddress()))){
                uniqueIPs.add(log.getIpAddress());
            }
        }
        return uniqueIPs.size();
    }

    //this methods returns a hashmap of mapping a ip address
    // with number of times that IP address visited a site
    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for(LogEntry log: records){
            String ip = log.getIpAddress();
            if(counts.containsKey(ip)){
                counts.put(ip, counts.get(ip) + 1);
            }
            else{
                counts.put(ip, 1);
            }
        }
        return counts;
    }

    //this method return the maximum number of visits
    //by any IP address where counts maps ip address with its number
    // of visits
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
        int maxNoOfVisits = 0;
        for(String ip: counts.keySet()){
            int visits = counts.get(ip);
            if(visits > maxNoOfVisits){
                maxNoOfVisits = visits;
            }
        }
        return maxNoOfVisits;
    }

    //this method returns ip addresses which
    //had visited maximum number of times where counts maps
    // ip address with its number of visits
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
        int maxVisits = mostNumberVisitsByIP(counts);
        ArrayList<String> ipsList = new ArrayList<String>();
        for(String ip: counts.keySet()){
            if(counts.get(ip) == maxVisits){
                ipsList.add(ip);
            }
        }
        return ipsList;
    }

    //this methods iterates through records and
    //makes a hashmap which maps a day with ip
    // addresses that visited on that day
    public HashMap<String, ArrayList<String>> iPsForDay(){
        HashMap<String, ArrayList<String>> ipsOfDays = new HashMap<String, ArrayList<String>>();
        for(LogEntry log: records){
            Date date = log.getAccessTime();
            String day = date.toString().substring(4,10);
            ArrayList<String> ips = new ArrayList<String>();
            if(ipsOfDays.containsKey(day)){
                ips = ipsOfDays.get(day);
            }
            ips.add(log.getIpAddress());
            ipsOfDays.put(day, ips);
        }
        return ipsOfDays;
    }

    //this method returns the day on which there are highest visits
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipsOfDay){
        String dayWithHighCount = null;
        int highestCount = 0;
        for(String day: ipsOfDay.keySet()){
            int currCount = ipsOfDay.get(day).size();
            if(currCount > highestCount){
                highestCount = currCount;
                dayWithHighCount = day;
            }
        }
        return dayWithHighCount;
    }


    //this method returns a list of ips that occured
    //highest number of times in the given list
    public ArrayList<String> ipsWithHighestCount(ArrayList<String> ipsList){
        HashMap<String, Integer> ipsCount = new HashMap<String, Integer>();
        for(String ip: ipsList){
            if(ipsCount.containsKey(ip)){
                ipsCount.put(ip, ipsCount.get(ip) + 1);
            }
            else{
                ipsCount.put(ip, 1);
            }
        }
        ArrayList<String> ipsWithHighestVisit = iPsMostVisits(ipsCount);
        return ipsWithHighestVisit;
    }

    //here ipsOfDay is hashmap which maps
    //day with IPs visited on that day
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsOfDay,
                                                    String day){
        ArrayList<String> ipsList = new ArrayList<String>();
        ArrayList<String> ipsWithMostVisits = new ArrayList<String>();
        for(String currDay: ipsOfDay.keySet()){
            if(currDay.equals(day)){
                ipsList = ipsOfDay.get(currDay);
                ipsWithMostVisits = ipsWithHighestCount(ipsList);
            }
        }
        return ipsWithMostVisits;
    }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
