package cmd.entity;

import java.nio.ByteBuffer;
import java.util.Collection;

import domain.gameplay.CashGameImpl;
import domain.gameplay.HandEntity;
import domain.gameplay.Player;
import domain.gameplay.holder.Hand;


public class PackUtils extends BasePackUtils{
	private static PackUtils instance;

	private PackUtils() {}

	public static PackUtils getInstance(){
		if(instance == null){
			instance = new PackUtils();
		}
		return instance;
	}

    public void packCashGameImpl(ByteBuffer bf, CashGameImpl obj){
       if(obj == null) {
            bf.put((byte)0);
            return;
        }
        bf.put((byte)1);
        packHandEntity(bf, obj.getCurrentHand());
		packPlayer(bf, obj.getPlayers());
		
    }

    public void packCashGameImpl(ByteBuffer bf, Collection<CashGameImpl> objs){
       if(objs == null || objs.size() == 0) {
            bf.put((byte)0);
            return;
        }
        bf.put((byte)1);
        bf.putShort((short) objs.size());
        for(CashGameImpl obj: objs){
            packCashGameImpl(bf, obj);
        }
    }
    public void packHandEntity(ByteBuffer bf, HandEntity obj){
       if(obj == null) {
            bf.put((byte)0);
            return;
        }
        bf.put((byte)1);
        
    }

    public void packHandEntity(ByteBuffer bf, Collection<HandEntity> objs){
       if(objs == null || objs.size() == 0) {
            bf.put((byte)0);
            return;
        }
        bf.put((byte)1);
        bf.putShort((short) objs.size());
        for(HandEntity obj: objs){
            packHandEntity(bf, obj);
        }
    }
    public void packPlayer(ByteBuffer bf, Player obj){
       if(obj == null) {
            bf.put((byte)0);
            return;
        }
        bf.put((byte)1);
        bf.putInt(obj.getId());
		bf.putInt(obj.getGamePosition());
		packHand(bf, obj.getHand());
		bf.putLong(obj.getChips());
		putStr(bf, obj.getPlayerName());
		
    }

    public void packPlayer(ByteBuffer bf, Collection<Player> objs){
       if(objs == null || objs.size() == 0) {
            bf.put((byte)0);
            return;
        }
        bf.put((byte)1);
        bf.putShort((short) objs.size());
        for(Player obj: objs){
            packPlayer(bf, obj);
        }
    }
    public void packHand(ByteBuffer bf, Hand obj){
       if(obj == null) {
            bf.put((byte)0);
            return;
        }
        bf.put((byte)1);
        bf.putLong(obj.getValue());
		
    }

    public void packHand(ByteBuffer bf, Collection<Hand> objs){
       if(objs == null || objs.size() == 0) {
            bf.put((byte)0);
            return;
        }
        bf.put((byte)1);
        bf.putShort((short) objs.size());
        for(Hand obj: objs){
            packHand(bf, obj);
        }
    }

}
