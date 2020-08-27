import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class SalesCalculator {
	// this class's objects will store a particular entry of data
	static class DataSet {
		
		Date date;
		String item;
		int uPrice;
		int qntt;
		int tPrice;
		
		//constructor
		DataSet(Date d, String i, int u, int q, int t) {
			this.date = d;
			this.item = i;
			this.uPrice = u;
			this.qntt = q;
			this.tPrice = t;
		}
	}
	
	static void mySales(List<DataSet> dataStore, Date feb, Date mar) {
		
		int janSales = 0, febSales = 0, marSales = 0, totalSales = 0;
		
		//reading data from dataStore List and comparing them month wise
		for(DataSet data : dataStore) {
			
			//storing data of each month is a separate variable
			if(data.date.compareTo(feb) < 0) janSales += data.tPrice;
			else if(data.date.compareTo(mar) < 0) febSales += data.tPrice;
			else marSales += data.tPrice;
		}
		// calculating total sales
		totalSales = janSales + febSales + marSales;
		
		//printing result
		System.out.println("Total sales: " + totalSales);
		System.out.println();
		System.out.println("Total sales for Jan: " + janSales);
		System.out.println("Total sales for Feb: " + febSales);
		System.out.println("Total sales for Mar: " + marSales);
	}
	static String myPopular(List<DataSet> dataStore, Date feb, Date mar) {
		
		//creating maps for each month
		HashMap<String, Integer> janPopular = new HashMap<>();
		HashMap<String, Integer> febPopular = new HashMap<>();
		HashMap<String, Integer> marPopular = new HashMap<>();
		
		//this map will store quantity of each item from all months
		HashMap<String, Integer> allPopular = new HashMap<>();
		
		//reading data from dataStore List
		for(DataSet data : dataStore) {
			//here we storing for all months
			//if current item exists in map then update its value
			if(allPopular.containsKey(data.item)) allPopular.put(data.item, (allPopular.get(data.item) + data.qntt));
			//else put that item in map
			else allPopular.put(data.item, data.qntt);
			
			//here we store for each month separately
			if(data.date.compareTo(feb) < 0) {
				//if current item exists in map then update its value
				if(janPopular.containsKey(data.item)) janPopular.put(data.item, (janPopular.get(data.item) + data.qntt));
				//else put that item in map
				else janPopular.put(data.item, data.qntt);
			}
			else if(data.date.compareTo(mar) < 0) {
				if(febPopular.containsKey(data.item)) febPopular.put(data.item, (febPopular.get(data.item) + data.qntt));
				else febPopular.put(data.item, data.qntt);
			}
			else {
				if(marPopular.containsKey(data.item)) marPopular.put(data.item, (marPopular.get(data.item) + data.qntt));
				else marPopular.put(data.item, data.qntt);
			}
		}
		
		//finding the max value in map
		int popInJan = (Collections.max(janPopular.values()));
		//finding key corresponding to max value
        for (Map.Entry<String, Integer> entry : janPopular.entrySet()) {
        	//when found
            if (entry.getValue() == popInJan) {
            	//print that key
                System.out.println("Popular in Jan: " + entry.getKey());
            }
        }
        
        int popInFeb = (Collections.max(febPopular.values()));
        for (Map.Entry<String, Integer> entry : febPopular.entrySet()) {
            if (entry.getValue() == popInFeb) {
                System.out.println("Popular in Feb: " + entry.getKey());
            }
        }
		
        int popInMar = (Collections.max(marPopular.values()));
        for (Map.Entry<String, Integer> entry : marPopular.entrySet()) {
            if (entry.getValue() == popInMar) {
                System.out.println("Popular in Mar: " + entry.getKey());
            }
        }
        
        //to store key associated to max value for data of all months
        String mostPopular = "";
        
        int popInAll = (Collections.max(allPopular.values()));
        for (Map.Entry<String, Integer> entry : allPopular.entrySet()) {
            if (entry.getValue() == popInAll) {
            	//storing most popular item in all months
            	mostPopular = entry.getKey();
                //System.out.println("Popular in All: " + entry.getKey());
            }
        }
        
        //returning most popular item in all data
        return mostPopular;
        
		/*System.out.println(janPopular);
		System.out.println(febPopular);
		System.out.println(marPopular);
		System.out.println(allPopular);*/
	}
	static void avgOrders(List<DataSet> dataStore, Date feb, Date mar, String key) {
		
		int jMin = Integer.MAX_VALUE, jMax = Integer.MIN_VALUE, jSum = 0, jCount = 0;
		int fMin = Integer.MAX_VALUE, fMax = Integer.MIN_VALUE, fSum = 0, fCount = 0;
		int mMin = Integer.MAX_VALUE, mMax = Integer.MIN_VALUE, mSum = 0, mCount = 0;
		
		//reading data from dataStore List
		for(DataSet data : dataStore) {
			
			//storing data of each month separately
			if(data.date.compareTo(feb) < 0) {
				//if current item is the most popular item
				if(data.item.equals(key)) {
					// then check for the quantity sold
					if (data.qntt < jMin) jMin = data.qntt;
					if(data.qntt > jMax) jMax = data.qntt;
					jSum += data.qntt;
					jCount++;
				}
			}
			else if(data.date.compareTo(mar) < 0) {
				if(data.item.equals(key)) {
					if (data.qntt < fMin) fMin = data.qntt;
					if(data.qntt > fMax) fMax = data.qntt;
					fSum += data.qntt;
					fCount++;
				}
			}
			else {
				if(data.item.equals(key)) {
					if (data.qntt < mMin) mMin = data.qntt;
					if(data.qntt > mMax) mMax = data.qntt;
					mSum += data.qntt;
					mCount++;
				}
			}
		}
		
		//printing result
		System.out.println("Popular in All: " + key);
		System.out.println();
		System.out.println("Sales of " + key + " in Jan:");
		System.out.printf("Min Order: %d, Max Order: %d, Avg Orders %d", jMin, jMax, jSum/jCount);
		System.out.println();
		System.out.println("Sales of " + key + " in Feb:");
		System.out.printf("Min Order: %d, Max Order: %d, Avg Orders %d", fMin, fMax, fSum/fCount);
		System.out.println();
		System.out.println("Sales of " + key + " in Mar:");
		System.out.printf("Min Order: %d, Max Order: %d, Avg Orders %d", mMin, mMax, mSum/mCount);
		System.out.println();
	}
	static void myRevenue(List<DataSet> dataStore, Date feb, Date mar) {
		
		// to store revenue of each item in each month
		HashMap<String, Integer> janRevenue = new HashMap<>();
		HashMap<String, Integer> febRevenue = new HashMap<>();
		HashMap<String, Integer> marRevenue = new HashMap<>();
		
		//reading data from dataStore List
		for(DataSet data : dataStore) {
			//separating data of each month
			if(data.date.compareTo(feb) < 0) {
				//if current item exists in map then update its value
				if(janRevenue.containsKey(data.item)) janRevenue.put(data.item, (janRevenue.get(data.item) + data.tPrice));
				//else put that value in map
				else janRevenue.put(data.item, data.tPrice);
			}
			else if(data.date.compareTo(mar) < 0) {
				if(febRevenue.containsKey(data.item)) febRevenue.put(data.item, (febRevenue.get(data.item) + data.tPrice));
				else febRevenue.put(data.item, data.tPrice);
			}
			else {
				if(marRevenue.containsKey(data.item)) marRevenue.put(data.item, (marRevenue.get(data.item) + data.tPrice));
				else marRevenue.put(data.item, data.tPrice);
			}
		}
		
		//storing max value of revenue
		int revInJan = (Collections.max(janRevenue.values()));
        for (Map.Entry<String, Integer> entry : janRevenue.entrySet()) {
        	//finding key corresponding to that value
            if (entry.getValue() == revInJan) {
                System.out.println("Item generated most revenue in Jan: " + entry.getKey());
            }
        }
        
        int revInFeb = (Collections.max(febRevenue.values()));
        for (Map.Entry<String, Integer> entry : febRevenue.entrySet()) {
            if (entry.getValue() == revInFeb) {
                System.out.println("Item generated most revenue in Feb: " + entry.getKey());
            }
        }
        
        int revInMar = (Collections.max(marRevenue.values()));
        for (Map.Entry<String, Integer> entry : marRevenue.entrySet()) {
            if (entry.getValue() == revInMar) {
                System.out.println("Item generated most revenue in Mar: " + entry.getKey());
            }
        }
        /*System.out.println(janRevenue);
        System.out.println(febRevenue);
        System.out.println(marRevenue);*/
	}
	
	public static void main(String[] args)throws Exception 
	{ 	
		//created SimpleDateFormat to compare dates
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		
		//created 2 objects of SimpleDateFormat to use for comparing other dates
		Date feb = new SimpleDateFormat("yyyy-MM-dd").parse("2019-02-01");
		Date mar = new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-01");
		
		//created file reader 
		File file = new File("saleData.txt");  
		//created buffered reader to read from file
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		//skipping first line because it was not required
		String st = br.readLine(); 
		
		//creating a list to store DataSet objects
		List<DataSet> dataStore = new ArrayList<DataSet>();
		
		//reading input from file
		while ((st = br.readLine()) != null) {
			//splitting each line and storing in String array
			String[] lineOfData = st.split(",");
			//creating a new DataSet object and storing it in list dataStore
			dataStore.add(new DataSet(sdformat.parse(lineOfData[0]), lineOfData[1], Integer.parseInt(lineOfData[2]), Integer.parseInt(lineOfData[3]), Integer.parseInt(lineOfData[4])));
		}
		br.close();
		
		//calling helper methods to print calculate and print result
		
		mySales(dataStore, feb, mar);
		System.out.println();
		
		String mostPop = myPopular(dataStore, feb, mar);
		System.out.println();
		
		myRevenue(dataStore, feb, mar);
		System.out.println();
		
		avgOrders(dataStore, feb, mar, mostPop);
		System.out.println();
	} 
}
