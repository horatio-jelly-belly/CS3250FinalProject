import javafx.scene.image.Image;

public class CharacterSprite {
	// TODO: Create an array of Images for a sprite
	private Image[] spriteFrames;
	private String spritePath;
	private String filePrefix;
	private int frameCount;
	private int startIndex;
	private boolean usePadding;
	
	// TODO: A method to load character sprites
	// Both sprite characters are from craftpix.net
	public CharacterSprite(String spritePath, String filePrefix, int frameCount, int startIndex, boolean usePadding) {
		this.spritePath = spritePath;
		this.filePrefix = filePrefix;
		this.frameCount = frameCount;
		this.startIndex = startIndex;
		this.usePadding = usePadding;
		loadSpriteFrames();
	}
	
	// Get a specific frame at index
	public Image getFrame(int index) {
		if (index >= 0 && index < spriteFrames.length) {
			return spriteFrames[index];
		}
		
		return null;
	}
	
	// Get total number of frames
	public int getFrameCount() {
		return frameCount;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	
	 private void loadSpriteFrames() {
	     // Size array to fit all frames including any offset for 1-indexed sprites
		 spriteFrames = new Image[startIndex + frameCount];
		 
		 for (int i = 0; i < frameCount; i++) {
			 String filename;
			 int fileNumber = startIndex + i;
			 
			 if (usePadding) {
	             filename = String.format("%s%s%03d.png", spritePath, filePrefix, fileNumber);
	         } else {
	             filename = String.format("%s%s%d.png", spritePath, filePrefix, fileNumber);
	         }
			 
			 spriteFrames[fileNumber] = new Image(filename);
		 }   
	 }
}
