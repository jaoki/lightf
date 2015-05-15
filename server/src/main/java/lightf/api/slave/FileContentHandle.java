package lightf.api.slave;

import offheap.OffHeapManager;

public class FileContentHandle {
	private int blockIndex;
	
	public String getContent(){
		return OffHeapManager.get(blockIndex);
	}
	public void setContent(String content){
		OffHeapManager.allocate();
		blockIndex = OffHeapManager.add(content);
	}
}
