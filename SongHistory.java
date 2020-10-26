/*custom Stack class for song history, when a song listened to it is pushed on to the stack, if the user would like
 * to hear the previous song, then it can be popped, it is implemented with a custom node class for songs, which is
 * pretty much the same as the node in the two custom queue files as they all hold a song item, the stack just contains
 * a constructor, and the expected push, pop and peek methods, and isEmpty method */

public class SongHistory {
	
	//inner class, node class for songs
	protected static class Track {

		protected String track; //data field for song
		protected Track next; //pointer to next node
		
		//constructor
		public Track (String track) {
			this.track = track;
		}
		
		//this method was for testing purposes, hence now commented out
		/*public void displayTrack() {
			System.out.println(track);
		} */

	}
	
	//pointer to node/element at the top of the stack
	protected Track top;
	
	//constructor
	public SongHistory() {
		top = null;
	}
	
	//push method
	public void push(String track) {
		Track played = new Track(track);
		if (top != null) {
			played.next = top;
			top = played;
		}
		else top = played;
	}
	
	//pop method
	public String pop() {
		if (top == null)
			return "History is empty!";
		
		String popped = top.track;
		top = top.next;
		return popped;
	}
	
	//peek method
	public String peek() {
		if (top == null)
			return "History is empty!";
		else return top.track;
	}
	
	//isEmpty method
	public boolean isEmpty() {
		return (top == null);
	}
	
	/*public void displayStack() {
		Track current = top;
		int cnt = 1;
		while (current != null) {
			System.out.print(cnt + " ");
			current.displayTrack();
			current = current.next;
			cnt++;
		}
		
		cnt--;
		System.out.println("\n" + cnt + " songs have been listened to.");
	} */
}
