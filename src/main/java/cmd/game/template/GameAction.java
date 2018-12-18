package cmd.game.template;

public class GameAction{
	private byte mActionID;
	private long mChips;


    public GameAction(){}

	public byte getActionID(){ return this.mActionID; }
	public long getChips(){ return this.mChips; }


	public void setActionID(byte mActionID){ this.mActionID = mActionID; }
	public void setChips(long mChips){ this.mChips = mChips; }

}
