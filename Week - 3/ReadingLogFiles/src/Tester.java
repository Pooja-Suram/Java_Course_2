
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("short-test_log");
        analyzer.printAll();
    }

    public void testUniqueIP(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("short-test_log");
        int uniqueIPs = analyzer.countUniqueIPs();
        System.out.println("Unique IPs are :" + uniqueIPs);
    }

    public void testPrintAllHigherThanNum(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog1_log");
        analyzer.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog1_log");
        ArrayList<String> uniqueIPs = analyzer.uniqueIPVisitsOnDay("Mar 17");
        System.out.println("IP addresses visited on given day are:");
        for(String ipAddress: uniqueIPs){
            System.out.println(ipAddress);
        }
        System.out.println("size: " + uniqueIPs.size());
    }

    public void testCountUniqueIPsInRange(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog1_log");
        int uniqueIPs = analyzer.countUniqueIPsInRange(200, 299);
        System.out.println("Unique IPs in given range are: " + uniqueIPs);
    }

    public void testCountVisitsPerIP(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog1_log");
        HashMap<String, Integer> counts = analyzer.countVisitsPerIP();
        System.out.println(counts);
        int maxNoOfVisits = analyzer.mostNumberVisitsByIP(counts);
        System.out.println("Max number of visits by IP : " + maxNoOfVisits);
        ArrayList<String> ipsList = analyzer.iPsMostVisits(counts);
        System.out.println("IPs that visited mostly are :");
        System.out.println(ipsList);
    }

    public void testIPsForDays(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> ipsOfDay = analyzer.iPsForDay();
        System.out.println(ipsOfDay);
        String dayWithHighestCount = analyzer.dayWithMostIPVisits(ipsOfDay);
        System.out.println("Day with highest ip visits is " + dayWithHighestCount);
        ArrayList<String> ipsWithMostVisits = analyzer.iPsWithMostVisitsOnDay(ipsOfDay, "Mar 17");
        System.out.println("IPs most frequently visited are :");
        System.out.println(ipsWithMostVisits);
    }

    public static void main(String[] args) {
        Tester test = new Tester();
        //test.testLogAnalyzer();
        //test.testUniqueIP();
        //test.testPrintAllHigherThanNum();
        //test.testUniqueIPVisitsOnDay();
        //test.testCountUniqueIPsInRange();
        //test.testCountVisitsPerIP();
        test.testIPsForDays();
    }
}
