package cmd.lobby.template;

public class PlayCashGame{
	private String mRoomName;
	private String mStructureID;
	private long mBuyinChips;
	private byte mPlayMode;
	private boolean mAutoBuyIn;


    public PlayCashGame(){}

	public String getRoomName(){ return this.mRoomName; }
	public String getStructureID(){ return this.mStructureID; }
	public long getBuyinChips(){ return this.mBuyinChips; }
	public byte getPlayMode(){ return this.mPlayMode; }
	public boolean getAutoBuyIn(){ return this.mAutoBuyIn; }


	public void setRoomName(String mRoomName){ this.mRoomName = mRoomName; }
	public void setStructureID(String mStructureID){ this.mStructureID = mStructureID; }
	public void setBuyinChips(long mBuyinChips){ this.mBuyinChips = mBuyinChips; }
	public void setPlayMode(byte mPlayMode){ this.mPlayMode = mPlayMode; }
	public void setAutoBuyIn(boolean mAutoBuyIn){ this.mAutoBuyIn = mAutoBuyIn; }

}
