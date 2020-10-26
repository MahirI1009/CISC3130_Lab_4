
//this is a custom playlist queue, it is made of nodes and has the same methods a normal queue would have

public class Playlist {
	
	public class Track { //nodes for songs, called Track

		protected String track; //data field for song
		protected Track next; //pointer to next node
		
		//constructor
		public Track (String track) {
			this.track = track;
		}
		
		//this method was for testing purposes, hence now commented out
		/*public void displayTrack() {
			System.out.println(track);
		}*/
	} //end of node inner class
	
	protected Track firstTrack; //pointer for front
	protected Track lastTrack; //pointer for rear
	protected int trackCount; //int for size count, for size of the queue
	
	//constructor
	public Playlist() {
		firstTrack = null; 
		lastTrack = null;
	}
	
	//enqueue method
	public void addTrack (String track) {
		Track newTrack = new Track(track);
		if (lastTrack != null) {
			lastTrack.next = newTrack;
			lastTrack = lastTrack.next;
		}
		if (firstTrack == null) {
			firstTrack = newTrack;
			lastTrack = newTrack;
		}
		trackCount++;
	}
	
	//dequeue method, when a song wants to be listened to, it will be removed from the front of the queue
	public String listenToSong() {
		String play = firstTrack.track;
		
		if (firstTrack != null) {
			firstTrack = firstTrack.next;
			if (firstTrack == null)
				lastTrack = null;
			trackCount--;
			return play;
		}
		else return "The playlist is empty";
	}
	
	//peek method
	public String peek() {
		return firstTrack.track;
	}
	
	//isEmpty method
	public boolean isEmpty() {
	     return (firstTrack == null);
	}
	
	//isFull method
	public boolean isFull() {
	     return (firstTrack != null);
	}
	
	//size method, returns number of nodes/elements in the queue
	public int size() {
		return trackCount;
	}
	
	//this method was for testing purposes, hence now commented out
	/*public void displayPlaylist() {
		Track current = firstTrack;
		int cnt = 1;
		while (current != null) {
			System.out.print(cnt + " ");
			current.displayTrack();
			current = current.next;
			cnt++;
		}
		
		cnt--;
		System.out.println("\nThere are " + cnt + " songs on this playlist.");
	}*/

}
