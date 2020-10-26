import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


/*this is the class that contains the main method, there is also 2 methods, a method to copy the songs from the FileQueue object 
 * to an array of Strings, and a method to merge two Strings and then return the merged strings as a queue object, when all the csv
 * files are merged into one large queue after multiple merge sorts, all the items are copied onto a playlist queue. There is a scanner
 * that takes in user input, if the user wishes to listen to a song, then the listenTo method of the playlist queue is called, which is
 * the equivalent of the dequeue class, the it it is pushed to the SongHistory stack, if the user seeks to listen to a previous song, then
 * the pop method of the stack class is called.*/

public class Lab4 {

	public static void main(String[] args) throws Exception {
		
		//instance of my FileQueue class, it is being instantiated with 13 csv files
		//these 13 files make up the third fiscal year of 2020
		
		//it is actually an array of queues, each file containing 200 songs is turned into its own queue
		FileQueue[] fiscalQuarter = { 
				new FileQueue (new File("regional-us-weekly-2020-07-03--2020-07-10.csv")), 
				new FileQueue (new File("regional-us-weekly-2020-07-10--2020-07-17.csv")),
				new FileQueue (new File("regional-us-weekly-2020-07-17--2020-07-24.csv")),
                new FileQueue (new File("regional-us-weekly-2020-07-24--2020-07-31.csv")),
                new FileQueue (new File("regional-us-weekly-2020-07-31--2020-08-07.csv")),                    
                new FileQueue (new File("regional-us-weekly-2020-08-07--2020-08-14.csv")),
                new FileQueue (new File("regional-us-weekly-2020-08-14--2020-08-21.csv")),
                new FileQueue (new File("regional-us-weekly-2020-08-21--2020-08-28.csv")),
                new FileQueue (new File("regional-us-weekly-2020-08-28--2020-09-04.csv")),
                new FileQueue (new File("regional-us-weekly-2020-09-04--2020-09-11.csv")),
                new FileQueue (new File("regional-us-weekly-2020-09-11--2020-09-18.csv")),
                new FileQueue (new File("regional-us-weekly-2020-09-18--2020-09-25.csv")),
                new FileQueue (new File("regional-us-weekly-2020-09-25--2020-10-02.csv"))};
		
		//all the song field will be copied on to a 2D array of strings, this is for the sake of merging them
		//I wouldve just merged the queues, but I dont have much experience with merge sort, first time implement-
		//ing in a program, I tried to merge the queues, but kept getting null pointer exceptions, because i was 
		//pressed for time, I took a least effecient method, and copied all 13 queues into 13 string array, and then
		//merged 2 string arrays at a time
		String[][] weeks = new String[13][200];
		
		//this copies all the songs from the 13 queues then copies them onto 13 string arrays 
		for (int i = 0; i < weeks.length; i++)
			weeks[i] = queueToArray(weeks[i], fiscalQuarter[i]); //calls a method called queueToArray
																 //queue to array just calls the dequeue
																 //method to get the song name then copies it
		
		//now the size of the array of File Queues will be halved, as pairs of queues will be merged
		//I know the following is terribly innefficent and a loop couldve been used instead, but I
		//kept getting out of bound exceptions, didnt have time to find the error, so I merged every
		//pair of queues manually
		
		fiscalQuarter = new FileQueue[7];
		
		String[][] temp = new String[7][];
		
		//I call the mergeQueues method to merge two arrays, it takes in 2 arrays, their lengths and a third array as parameters
		
		//weeks 1 and 2
		temp[0] = new String[weeks[0].length + weeks[1].length];
		fiscalQuarter[0]= mergeQueues(weeks[0], weeks[0].length, weeks[1], weeks[1].length, temp[0]);
		
		//weeks 3 and 4
		temp[1] = new String[weeks[2].length + weeks[3].length];
		fiscalQuarter[1]= mergeQueues(weeks[2], weeks[2].length, weeks[3], weeks[3].length, temp[1]);
		
		//weeks 5 and 6
		temp[2] = new String[weeks[4].length + weeks[5].length];
		fiscalQuarter[2]= mergeQueues(weeks[4], weeks[4].length, weeks[5], weeks[5].length, temp[2]);
		
		//weeks 7 and 8
		temp[3] = new String[weeks[6].length + weeks[7].length];
		fiscalQuarter[3]= mergeQueues(weeks[6], weeks[6].length, weeks[7], weeks[7].length, temp[3]);
		
		//weeks 9 and 10
		temp[4] = new String[weeks[8].length + weeks[9].length];
		fiscalQuarter[4]= mergeQueues(weeks[8], weeks[8].length, weeks[9], weeks[9].length, temp[4]);
		
		//weeks 11 and 12
		temp[5] = new String[weeks[10].length + weeks[11].length];
		fiscalQuarter[5]= mergeQueues(weeks[10], weeks[10].length, weeks[11], weeks[11].length, temp[5]);
		
		//weeks 12 and 13
		temp[6] = new String[weeks[11].length + weeks[12].length];
		fiscalQuarter[6]= mergeQueues(weeks[11], weeks[11].length, weeks[12], weeks[12].length, temp[6]);
		
		
		//now the 13 queues have been merged to 7 queues, they will be copied onto 7 arrays, then merged into 4 queues
		
		for (int i = 0; i < 7; i++)
			weeks[i] = queueToArray(temp[i], fiscalQuarter[i]);
		
		//weeks 1,2 and 3,4
		temp[0] = new String[weeks[0].length + weeks[1].length];
		fiscalQuarter[0]= mergeQueues(weeks[0], weeks[0].length, weeks[1], weeks[1].length, temp[0]);
	
		//weeks 5,6 and 7,8
		temp[1] = new String[weeks[2].length + weeks[3].length];
		fiscalQuarter[1]= mergeQueues(weeks[2], weeks[2].length, weeks[3], weeks[3].length, temp[1]);
				
		//weeks 8,9 and 10,11
		temp[2] = new String[weeks[4].length + weeks[5].length];
		fiscalQuarter[2]= mergeQueues(weeks[4], weeks[4].length, weeks[5], weeks[5].length, temp[2]);
				
		//weeks 10,11 and 11,12
		temp[3] = new String[weeks[5].length + weeks[6].length];
		fiscalQuarter[3]= mergeQueues(weeks[5], weeks[5].length, weeks[6], weeks[6].length, temp[3]);
		
		//now that they've been merged into 4 queues, they'll be copied onto 4 arrays using the queueToArray method, then merged into 2 queues
		
		for (int i = 0; i < 4; i++)
			weeks[i] = queueToArray(temp[i], fiscalQuarter[i]);
		
		//weeks 1,2,3,4 and 5,6,7,8
		temp[0] = new String[weeks[0].length + weeks[1].length];
		fiscalQuarter[0]= mergeQueues(weeks[0], weeks[0].length, weeks[1], weeks[1].length, temp[0]);
			
		//weeks 9,10,11,12 and 10,11,12,13
		temp[1] = new String[weeks[2].length + weeks[3].length];
		fiscalQuarter[1]= mergeQueues(weeks[2], weeks[2].length, weeks[3], weeks[3].length, temp[1]);
		
		//now that they've been merged into 2 queues, they'll be copied onto 2 arrays using the queueToArray method, then merged into 1 queue
		
		for (int i = 0; i < 2; i++)
			weeks[i] = queueToArray(temp[i], fiscalQuarter[i]);
		
		fiscalQuarter = new FileQueue[1];
		
		//weeks 1,2,3,4,5,6,7,8 and 8,9,10,11,12,13
		temp[0] = new String[weeks[0].length + weeks[1].length];
		fiscalQuarter[0] = mergeQueues(weeks[0], weeks[0].length, weeks[1], weeks[1].length, temp[0]);
		
		//now we all the files have merged into one large queue containing all the songs in the top 200 lists of the 3rd fiscal quarter of 2020
		
		//my custom playlist class has now been instantiated
		Playlist playlist = new Playlist();		
		
		//all songs from the file queue will be added to the playlist queue now and removed the file queue
		for (int i = 0; i < fiscalQuarter[0].size(); i++) {
			playlist.addTrack(fiscalQuarter[0].remove());
		}
		
		//instance of the history stack
		SongHistory stacktrack = new SongHistory();
		
		//scanner for user input
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("PLAYLIST");
		
		System.out.println("Enter Next if you want to play the next song"
				+ " or enter Previous if you would like to play the Previous song"
				+ " or enter Quit if you wish to quit.");
		
		while (keyboard.hasNext()) {
			
			//user is prompted to play next song, previous or quit
			//if user enters next, then the dequeue method of the playlist called listenTo() is called and printed out
			//then it is pushed onto the history stack
			
			//if the user enters previous, the pop method is called, so the last song listened to is returend which was
			//at the top of the stack
			
			//the user will continue to be prompted until the user decides to quit
			
			String next = "", nowPlaying = "";
			
			next = keyboard.next();
				
			if (next.equals("Next") || next.equals("next")) {
				nowPlaying = playlist.listenToSong();
				System.out.println("Now Playing: \n"  + nowPlaying);
			}
			
			else if (next.equals("Previous") || next.equals("previous")) {
				nowPlaying = stacktrack.pop();
				nowPlaying = stacktrack.pop();
				if (nowPlaying.equals("History is empty!"))
					System.out.println("Now Playing: \n" + nowPlaying +
						"\nEnter Next if you want to play the next song " + 
						 " or enter Previous if you would like to play the Previous song " + 
						 " or enter Quit if you wish to quit.");
				else System.out.println("Now Playing: \n" + nowPlaying);
			}
			
			else if (next.equals("Quit") || next.equals("quit")) 
				break;
					
			else System.out.println("That is invalid. Please enter Next or Previous or Quit.");	
			
			stacktrack.push(nowPlaying);
			
			System.out.println("\nEnter Next if you want to play the next song"
					+ " or enter Previous if you would like to play the Previous song"
					+ " or enter Quit if you wish to quit.");
			
			}		

	}
	
	private static String[] queueToArray(String[] arr, FileQueue q) { //method to copy file queue onto a string array
		//the dequeue method called remove is called which returns a string containing the song name, then added onto array
		for (int i = 0; i < arr.length; i++) 
				arr[i] = q.remove();
		return arr;
	}
	
	//merge method, takes 2 string arrays, their sizes and and a third string array
	private static FileQueue mergeQueues(String[] tracks1, int length1, String[] tracks2, int length2, String[] tracks3) {
		int cnt1 = 0, cnt2 = 0, cnt3 = 0; //counter variable to iterate through all 3 arrays
		
		while (cnt1 < length1 && cnt2 < length2) {
			if (cnt1 < tracks1.length && cnt2 < tracks2.length) {
				if(tracks1[cnt1].compareTo(tracks2[cnt2]) == -1) //if the corresponding element in the other array is larger
					tracks3[cnt3++] = tracks1[cnt1++];	//the smaller value is added to the third array, cnt3 is incremented
				else if (tracks1[cnt1].compareTo(tracks2[cnt2]) == 1) //if the corresponding element in the other array is smaller
					tracks3[cnt3++] = tracks2[cnt2++]; //the smaller value is added to the third array, cnt3 is incremented
				else { //if the strings are equal
						tracks3[cnt3++] = tracks1[cnt1++];	//one element is added, the other isnt, so as to not add duplicates
						cnt2++;	//the second array is also incremented so the duplicate is skipped
					}
				}
			}
		
		while(cnt1 < length1) //if the second is empty,
			tracks3[cnt3++] = tracks1[cnt1++];// but the first isn’t, then the remaining elements from the first are added
						
		while(cnt2 < length2)  //if the first is empty,
			tracks3[cnt3++] = tracks2[cnt2++];// but the second isn’t, then the remaining elements from the second are added
		
		FileQueue newQueue = new FileQueue(); //instatiate FileQueue object
		
		ArrayList<String> tracksHolder = new ArrayList<>();
		
		//copy elements to arraylist, for dynamic resizing, and get around null pointer exceptions
		for (int i = 0; i < tracks3.length; i++) {
			if (tracks3[i] != null)	
				tracksHolder.add(tracks3[i]);
			else i = tracks3.length;
		}
		
		//adds all elements from the arraylist, which are names of the song to the file queue objecy
		for (int i = 0; i < tracksHolder.size(); i++) {
			//the string is in the format "Song by Artist", the constructor takes the parameters (artist, song)
			//so the string is split in two with the word by, making 2 seperate strings, then the enqueue method
			//called insert is called, the two strings are passed and the elements are added
			String[] songAndArtist = tracksHolder.get(i).split(" by "); 
			if(songAndArtist.length == 2)
				newQueue.insert(songAndArtist[1], songAndArtist[0]);
		}
		return newQueue; //the queue is returned, it is a queue that is a result of two merged string arrays containing song names
	}

}
