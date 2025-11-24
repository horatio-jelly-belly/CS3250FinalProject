import javafx.scene.image.Image;

/**
 * Manages sprite sheet loading and frame access for animated characters.
 * Generic class that can handle different sprite naming conventions and frame counts.
 * Loads all animation frames into memory for quick access during gameplay.
 */
public class CharacterSprite {
    /**
     * Array storing all loaded sprite frame images.
     * Indexed to match the sprite file numbering (0-based or 1-based).
     * Pre-loaded for performance - no disk access during animation.
     */
    private Image[] spriteFrames;
    
    /**
     * Directory path where sprite images are located.
     * Example: "images/skeleton/" or "images/minotaur/"
     * Must include trailing slash for proper file path construction.
     */
    private String spritePath;
    
    /**
     * Base filename prefix before the frame number.
     * Example: "skeleton_" or "Minotaur_01_Walking_"
     * Combined with frame number to create full filename.
     */
    private String filePrefix;
    
    /**
     * Total number of frames in this animation sequence.
     * Determines how many image files to load.
     * Example: 12 for skeleton attack, 18 for minotaur walk.
     */
    private int frameCount;
    
    /**
     * Starting index for frame numbering.
     * 0 for 0-indexed sprites (minotaur: 000-017)
     * 1 for 1-indexed sprites (skeleton: 1-12)
     */
    private int startIndex;
    
    /**
     * Flag for whether frame numbers use zero-padding.
     * true: uses format like "001", "002" (3 digits with leading zeros)
     * false: uses format like "1", "2" (no padding)
     */
    private boolean usePadding;
    
    /**
     * Constructor to create a sprite loader with specific parameters.
     * Immediately loads all frames into memory upon creation.
     * Both sprite characters are from craftpix.net
     * @param spritePath Directory containing sprite files
     * @param filePrefix Base filename before frame numbers
     * @param frameCount Number of frames to load
     * @param startIndex First frame number (0 or 1)
     * @param usePadding Whether to use zero-padded numbers
     */
    public CharacterSprite(String spritePath, String filePrefix, int frameCount, int startIndex, boolean usePadding) {
        this.spritePath = spritePath;
        this.filePrefix = filePrefix;
        this.frameCount = frameCount;
        this.startIndex = startIndex;
        this.usePadding = usePadding;
        loadSpriteFrames();  // Load all frames immediately
    }
    
    /**
     * Retrieves a specific frame image by index.
     * Used by GameWorld during rendering to get the current frame.
     * @param index Frame number to retrieve
     * @return Image object for the requested frame, or null if index invalid
     */
    public Image getFrame(int index) {
        // Bounds checking to prevent array access errors
        if (index >= 0 && index < spriteFrames.length) {
            return spriteFrames[index];
        }
        
        return null;  // Return null for invalid indices
    }
    
    /**
     * Gets the total number of frames in this animation.
     * Used by animation controllers to know when animation completes.
     * @return Number of frames in the animation
     */
    public int getFrameCount() {
        return frameCount;
    }
    
    /**
     * Gets the starting index for this sprite's numbering.
     * Used by animation controllers to properly initialize frame counters.
     * @return Starting frame index (0 or 1)
     */
    public int getStartIndex() {
        return startIndex;
    }
    
    /**
     * Loads all sprite frames from disk into memory.
     * Private method called during construction.
     * Handles both padded (001) and non-padded (1) filename formats.
     */
    private void loadSpriteFrames() {
        // Size array to accommodate the indexing scheme
        // For 1-indexed: need indices 0-12 (13 slots) to store frames 1-12
        // For 0-indexed: need indices 0-17 (18 slots) to store frames 0-17
        spriteFrames = new Image[startIndex + frameCount];
        
        // Load each frame file
        for (int i = 0; i < frameCount; i++) {
            String filename;
            int fileNumber = startIndex + i;  // Actual number in filename
            
            if (usePadding) {
                // Create padded filename like "Minotaur_01_Walking_001.png"
                // %03d means 3-digit number with leading zeros
                filename = String.format("%s%s%03d.png", spritePath, filePrefix, fileNumber);
            } else {
                // Create unpadded filename like "skeleton_1.png"
                // %d means regular number without padding
                filename = String.format("%s%s%d.png", spritePath, filePrefix, fileNumber);
            }
            
            // Load image and store at the index matching its frame number
            // This allows direct access: frame 5 is at index 5
            spriteFrames[fileNumber] = new Image(filename);
        }   
    }
}