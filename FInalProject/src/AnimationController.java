/**
 * Controls the timing and progression of sprite animations.
 * Manages frame-by-frame advancement based on time intervals rather than raw frame counts.
 * This ensures animations play at consistent speed regardless of frame rate.
 */
public class AnimationController {
    /**
     * The current frame being displayed in the animation sequence.
     * This tracks which specific image from the sprite sheet should be shown.
     */
    private int currentFrame;
    
    /**
     * The first frame number in the animation sequence.
     * Sprites may be 0-indexed (minotaur) or 1-indexed (skeleton), so this allows flexibility.
     */
    private int startFrame;
    
    /**
     * Stores the time stamp (in nanoseconds) of when the last frame change occurred.
     * Used to calculate if enough time has passed to advance to the next frame.
     */
    private long lastFrameTime = 0;
    
    /**
     * Duration in nanoseconds to display each frame before advancing.
     * 50_000_000 nanoseconds = 50 milliseconds = 0.05 seconds per frame.
     * This creates smooth animation at approximately 20 frames per second.
     */
    private final long FRAME_DURATION = 50_000_000;
    
    /**
     * Flag indicating whether the animation has played through all frames once.
     * Prevents continuous looping - animation plays once then stops.
     */
    private boolean animationComplete = false;
    
    /**
     * Total number of frames in this animation sequence.
     * Used to determine when we've reached the end of the animation.
     */
    private int totalFrames;
    
    /**
     * Constructor to initialize an animation controller for a specific sprite.
     * @param numOfFrames Total number of frames in the animation
     * @param startFrame The index of the first frame (0 or 1 depending on sprite naming)
     */
    public AnimationController(int numOfFrames, int startFrame) {
        this.totalFrames = numOfFrames;
        this.startFrame = startFrame;
        this.currentFrame = startFrame;
    }
    
    /**
     * Updates the animation state based on elapsed time.
     * Called repeatedly by the game loop to advance frames at the right timing.
     * @param currentTime Current system time in nanoseconds
     * @return true if the frame was updated (requiring a redraw), false otherwise
     */
    public boolean update(long currentTime) {
        // Check if enough time has passed since the last frame update
        // This ensures animation speed is consistent
        if (currentTime - lastFrameTime < FRAME_DURATION) {
            return false;
        }
        
        // Don't update if animation is already complete
        // Prevents animation from looping or continuing past the end
        if (animationComplete) {
            return false;
        }
        
        // Move to the next frame
        currentFrame++;
        
        // Check if all frames have been shown
        // For skeleton: frames 1-12, so when currentFrame >= 13
        // For minotaur: frames 0-17, so when currentFrame >= 18
        if (currentFrame >= startFrame + totalFrames) {
            // Mark animation as complete
            animationComplete = true;
            // Set to last frame so it displays before stopping
            // This ensures the final frame is visible
            currentFrame = startFrame + totalFrames - 1;
        }
        
        // Record the time of this frame change
        // Used for timing the next frame advancement
        lastFrameTime = currentTime;
        
        return true;
    }
    
    /**
     * Checks if the animation has finished playing through all frames.
     * Used by GameController to know when to stop updating this animation.
     * @return true if animation is complete, false if still playing
     */
    public boolean isAnimationComplete() {
        return animationComplete;
    }
    
    /**
     * Resets the animation to its initial state.
     * Called when starting a new animation sequence (e.g., new attack).
     * Allows the same animation to be played multiple times.
     */
    public void resetAnimation() {
        currentFrame = startFrame;
        animationComplete = false;
        lastFrameTime = 0;
    }
    
    /**
     * Gets the current frame index for rendering.
     * GameWorld uses this to know which sprite image to draw.
     * @return The index of the current frame to display
     */
    public int getCurrentFrame() {
        return currentFrame;
    }
}