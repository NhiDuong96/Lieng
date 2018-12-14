package cmd.lobby.template;

public class PlayCashGame{
	private String mRoomName;
	private String mStructureID;
	private long mBuyinChips;
	private boolean mAutoBuyin;


    public PlayCashGame(){}

	public String getRoomName(){ return this.mRoomName; }
	public String getStructureID(){ return this.mStructureID; }
	public long getBuyinChips(){ return this.mBuyinChips; }
	public boolean getAutoBuyin(){ return this.mAutoBuyin; }


	public void setRoomName(String mRoomName){ this.mRoomName = mRoomName; }
	public void setStructureID(String mStructureID){ this.mStructureID = mStructureID; }
	public void setBuyinChips(long mBuyinChips){ this.mBuyinChips = mBuyinChips; }
	public void setAutoBuyin(boolean mAutoBuyin){ this.mAutoBuyin = mAutoBuyin; }

}
