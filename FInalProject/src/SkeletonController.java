
public class SkeletonController {
		
	// Tracks which frame of the animation is currently being displayed
	private int currentFrame = 0;
		
	// Stores the time stamp of when the last frame change occurred (in nanoseconds)
	private long lastFrameTime = 0;
		
	// How long to wait between frame changes (50 milliseconds = 0.05 seconds)
	private final long FRAME_DURATION = 50_000_000;
	
	private boolean cycle = false;
	
	private int frames;
	
	public SkeletonController(int numOfFrames) {
		frames = numOfFrames;
	};
		
	public boolean update(long currentTime) {
		if (currentTime - lastFrameTime < FRAME_DURATION) 
			return false;
		else {
			// Move to the next frame, wrapping back to 0 after the last frame
	        // This is what creates the looping animation
	        currentFrame = (currentFrame + 1) % frames;

	        // Check if we've completed one full animation cycle
	        // If we're back at frame 0 and we've already seen frame 11, stop the animation
	        if (currentFrame == 0 && cycle) {
	            cycle = false; // Reset for next animation
	        } else if (currentFrame == 11) {
	            // Mark that we've reached the last frame
	            cycle = true;
	        }
	        
			// Record the time of this frame change
	        lastFrameTime = currentTime;
	        
	        return true;
		}
	};
	
	public boolean isAnimationComplete() {
		return cycle;
	};
	
	public void resetAnimation() {};
	
	public int getCurrentFrame() {
		return currentFrame;
	};

}
