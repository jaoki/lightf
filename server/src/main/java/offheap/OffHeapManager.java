package offheap;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class OffHeapManager {
//	private static final int CAPACITY = 1024 * 1024 * 10; // 10MB
//	private static final int CAPACITY = 1024 * 1024 * 500; // 500MB
	private static final int CAPACITY = 1024 * 1024 * 1024; // 1GB
	private static ByteBuffer buffer = null;
	private static final int BLOCK_SIZE = 256;
	private static int currentBlankBlockIndex;
	
	// FIXME not thread safe etc.
	public static void allocate(){
		if(buffer == null){
			buffer = ByteBuffer.allocateDirect(CAPACITY);
			currentBlankBlockIndex = 0;
		}
	}
	
	/**
	 * 
	 * @param content
	 * @return block index
	 */
	public static int add(String content){
		byte[] contentBytes = content.getBytes();
		if(contentBytes.length > BLOCK_SIZE){
			throw new RuntimeException();
		}
		
		for(int i = 0; i < contentBytes.length; i++){
			buffer.put((currentBlankBlockIndex * BLOCK_SIZE) + i, contentBytes[i]);
		}
		currentBlankBlockIndex++;
		return currentBlankBlockIndex - 1;
	}

	public static String get(int blockIndex){
		byte[] contentBytes = new byte[BLOCK_SIZE];
//		for(int i = (blockIndex * BLOCK_SIZE); i < (blockIndex + 1) * BLOCK_SIZE; i++){
		for(int i = 0; i < BLOCK_SIZE; i++){
			contentBytes[i] = buffer.get((blockIndex * BLOCK_SIZE) + i);
		}
		return new String(contentBytes, StandardCharsets.UTF_8);
	}
}
