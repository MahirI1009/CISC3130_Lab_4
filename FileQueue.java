import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/* This custom Queue class is subclass of my LinkedListOfArtists class, this queue has a node class, the node class
 * extends the node class belonging to the custom LinkedList class. It inherits the name field from its parent class
 * and has its own field for the name of the song, as well a field which is a string that is in the format "Song by Artist: */
class Song extends ArtistNode {
	
	protected String song; //field for song name
	protected String songByArtist; //field for string in format "song by artist"
	protected Song next; //pointer to next node
	
	//constructor for Song Node
	public Song (String artist, String song) {
		super(artist);
		this.song = song;
		songByArtist = song + " by " + artist;
	}
	
	//this method was for testing purposes, hence now commented out
	/* public void displaySong() {
		System.out.println(songByArtist);
	} */
}


//this is the custom Queue class, it extends my custom LinkedList, and it is made up of Song nodes

public class FileQueue extends LinkedListOfArtists {
	
	protected Song front; //pointer for front
	protected Song rear; //pointer for rear
	protected int numOfSongs = 0; //int for song count, this is for the size of the queue
	
	//constructor
	public FileQueue() {
		super();
		front = null; //keep track of first element
		rear = null; //keep track of last element
	}
	
	
	//constructor when a file is passed as a parameter, the file is read and all the songs are added into the queue
	public FileQueue (File file) throws Exception {
		this();
	
		ArrayList<String> sortedSongs = new ArrayList<>();
		
		Scanner fileReader = new Scanner(file, "UTF-8"); //Scanner
		String line = fileReader.nextLine(); //skip header 1
		line = fileReader.nextLine(); //skip header 2
		String artist, song;
		
		while (fileReader.hasNext()) { //read till end of file
			line = fileReader.nextLine(); //get an entire line
			String[] separatedLine = line.split(","); //split line by commas and store into an array
			String extractedName, extractedSong;
			
			if (separatedLine.length == 5) {
				extractedName = separatedLine[2];
				extractedSong = separatedLine[1];
			}
			
			else {
				int nameColumn = separatedLine.length-3;
				extractedName = separatedLine[nameColumn];
				extractedSong = separatedLine[1];
				
				for (int i = 2; i < nameColumn; i++) 
					extractedSong += separatedLine[i];
			}
			
			/* I made sure extractedName is assigned the 2nd to last value of every row because some values have commas 
             * within them, this assures I get the artist's name every time and not some other value, this also makes sure
             * to get the song name which is the 2nd value of every row, and in the case that the second value itself has
             * commas, then the for loop gets all the values that would belong to the 2nd row, and the loop stops when it
             * reaches the 3rd row which contains the artist*/
			
			if (extractedName.charAt(0) == '"') 
				artist = extractedName.substring(1, extractedName.length()-1);
			else artist = extractedName;
			
			if (extractedSong.charAt(0) == '"') 
				song = extractedSong.substring(1, extractedSong.length()-1);
			else song = extractedSong;
			
			/*some of the artist names in the file were surrounded with quotation marks while some didnt, which caused a problem 
             * for sorting the list in alphabetical order, so I made sure to check if a name starts with a quotation mark, if it
             * does then the quotation marks are removed from the String, this what the String remove is being used for */
			
			sortedSongs.add(song + " by " + artist);
			//the songs and artists are put in a "song by artist" format and added to an arraylist
		}
		
		/*this loop sorts arraylist in alphabetical order, the first character of an element is compared to the first character 
        of the next element, then it swaps the two songs if the song after it starts with a letter that comes before it in the alphabet*/
        for (int i = 1; i < sortedSongs.size(); i++) {
        	String temp = "";
        	for (int j = 0; j < sortedSongs.size()-1; j++) {
        		/*I'm using the toLowerCase method, because some song names start with lowercase letters, and unfortunately the comparison
        		 * operator is case sensitive, so this how I got around that issue*/
        		if(sortedSongs.get(i).toLowerCase().charAt(0) < sortedSongs.get(j).toLowerCase().charAt(0)) { //compares first letter of strings
        			temp = sortedSongs.get(i); 
        			sortedSongs.set(i, sortedSongs.get(j));
        			sortedSongs.set(j, temp);
        		}
        	} //end of second for loop
        } //end of for loop
		
        //this loop takes the elements from the arraylist, splits the element into two part, the song and the name, then adds to the queue
		for (int i = 0; i < sortedSongs.size(); i++) {
			String[] songAndArtist = sortedSongs.get(i).split(" by ");
			insert(songAndArtist[1], songAndArtist[0]);
		}
		
		fileReader.close();
	} 
	
	//enqueue method
	public void insert(String artist, String song) {
		Song newSong = new Song(artist, song);
		if (rear != null) {
			rear.next = newSong;
			rear = newSong;
		}
		if(front == null) {
			front = newSong;
			rear = newSong;
		}
		numOfSongs++;
	}
	
	//dequeue method
	public String remove() {
		if (front != null) {
			String removed = front.songByArtist;
			front = front.next;
			if (front == null)
				rear = null;
			numOfSongs--;
			return removed;
		}
		else return "Queue is Empty, nothing to remove!";
	}
	
	
	//peek method
	public String peek() {
		if (front != null)
			return front.songByArtist;
		else return "Queue is empty";
	}
	
	//isEmpty method
	public boolean isEmpty() {
		return (front == null);
	}
	
	//isFull method
	public boolean isFull() {
		return (front != null);
	}
	
	//size method, returns number of nodes/elements in the queue
	public int size() {
		return numOfSongs;
	}
	
	//this method was for testing purposes, hence now commented out
	/*public void displaySongs() {
		Song current = front;
		int cnt = 1;
		while (current != null) {
			System.out.print(cnt + " ");
			current.displaySong();
			current = current.next;
			cnt++;
		}
		
		cnt--;
		System.out.println("\nThere are " + cnt + " songs in this file.");
	}*/
	
}